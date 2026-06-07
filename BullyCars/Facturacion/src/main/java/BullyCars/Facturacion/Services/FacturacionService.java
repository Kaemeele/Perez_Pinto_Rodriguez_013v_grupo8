package BullyCars.Facturacion.Services;

import java.util.Optional;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import BullyCars.Facturacion.Models.Factura;
import BullyCars.Facturacion.Models.DetalleFactura;
import BullyCars.Facturacion.Repositories.FacturaRepository;
import BullyCars.Facturacion.Proxy.CitaProxy;
import BullyCars.Facturacion.Proxy.ReparacionProxy;
import BullyCars.Facturacion.Proxy.EsteticaProxy;
import BullyCars.Facturacion.Proxy.NotificacionProxy;

@Service
public class FacturacionService {
    
    @Autowired private FacturaRepository repo;

    @Autowired private CitaProxy citaProxy;
    @Autowired private ReparacionProxy reparacionProxy;
    @Autowired private EsteticaProxy esteticaProxy;
    @Autowired private NotificacionProxy notificacionProxy;

    public Factura generar(Factura f) { return repo.save(f); }

    public List<Factura> historial() { return repo.findAll(); }

    public Optional<Factura> obtenerPorId(Long id) { return repo.findById(id); }

    public void eliminar(Long id) {
        if (!repo.existsById(id)) {
            throw new RuntimeException("Factura no encontrada con ID: " + id);
        }
        repo.deleteById(id);
    }

    @SuppressWarnings("unchecked")
    @Transactional
    public Factura generarFacturaDeCita(Long citaId) {
        // 1. Fetch appointment from Citas service
        Map<String, Object> citaMap;
        try {
            var res = citaProxy.obtenerCitaPorId(citaId);
            if (res == null || res.getBody() == null) {
                throw new RuntimeException("Cita no encontrada.");
            }
            citaMap = (Map<String, Object>) res.getBody();
        } catch (Exception e) {
            throw new RuntimeException("No se pudo obtener información de la cita: " + e.getMessage());
        }

        // 2. Verify appointment is confirmed
        Boolean confirmado = (Boolean) citaMap.get("confirmado");
        if (confirmado == null || !confirmado) {
            throw new RuntimeException("La cita con ID " + citaId + " no está confirmada. No se puede facturar.");
        }

        Long clienteId = Long.valueOf(citaMap.get("clienteId").toString());
        String tipoServicio = (String) citaMap.get("tipoServicio");
        if (tipoServicio == null) {
            tipoServicio = "REPARACION";
        }

        Factura factura = new Factura();
        factura.setCitaId(citaId);
        factura.setDetalles(new ArrayList<>());

        double subtotal = 0.0;

        // 3. Process service details
        if ("REPARACION".equalsIgnoreCase(tipoServicio)) {
            // Fetch work order details
            Map<String, Object> ordenMap;
            try {
                var res = reparacionProxy.obtenerPorCitaId(citaId);
                if (res == null || res.getBody() == null) {
                    throw new RuntimeException("Orden de trabajo no encontrada en Reparaciones para la cita ID: " + citaId);
                }
                ordenMap = (Map<String, Object>) res.getBody();
            } catch (Exception e) {
                throw new RuntimeException("Error al consultar el microservicio de Reparaciones: " + e.getMessage());
            }

            Long ordenId = Long.valueOf(ordenMap.get("id").toString());
            Double manoObra = ordenMap.get("costoManoObra") != null ? Double.valueOf(ordenMap.get("costoManoObra").toString()) : 0.0;
            String ordenDesc = (String) ordenMap.get("descripcion");

            // Add labor item
            DetalleFactura itemLabor = new DetalleFactura();
            itemLabor.setDescripcion("Mano de Obra: " + ordenDesc);
            itemLabor.setCantidad(1);
            itemLabor.setPrecioUnitario(manoObra);
            itemLabor.setFactura(factura);
            factura.getDetalles().add(itemLabor);
            subtotal += manoObra;

            // Add spare parts items
            List<Map<String, Object>> repuestosList = (List<Map<String, Object>>) ordenMap.get("repuestosUsados");
            if (repuestosList != null) {
                for (Map<String, Object> repuesto : repuestosList) {
                    String nombre = repuesto.get("nombreRepuesto") != null ? repuesto.get("nombreRepuesto").toString() : "Repuesto";
                    Integer cantidad = Integer.valueOf(repuesto.get("cantidad").toString());
                    Double precio = Double.valueOf(repuesto.get("precioUnitario").toString());

                    DetalleFactura itemRepuesto = new DetalleFactura();
                    itemRepuesto.setDescripcion("Repuesto: " + nombre);
                    itemRepuesto.setCantidad(cantidad);
                    itemRepuesto.setPrecioUnitario(precio);
                    itemRepuesto.setFactura(factura);
                    factura.getDetalles().add(itemRepuesto);

                    subtotal += (precio * cantidad);
                }
            }

            // Update remote repair state to "Facturada"
            try {
                reparacionProxy.actualizarEstadoYClave(ordenId, "Facturada", null);
            } catch (Exception e) {
                // Non-blocking but logged
                System.err.println("No se pudo actualizar el estado de la Orden de Trabajo #" + ordenId + ": " + e.getMessage());
            }

        } else if ("ESTETICA".equalsIgnoreCase(tipoServicio)) {
            // Fetch aesthetics booking
            Map<String, Object> reservaMap;
            try {
                var res = esteticaProxy.obtenerReservaPorCitaId(citaId);
                if (res == null || res.getBody() == null) {
                    throw new RuntimeException("Reserva de estética no encontrada para la cita ID: " + citaId);
                }
                reservaMap = (Map<String, Object>) res.getBody();
            } catch (Exception e) {
                throw new RuntimeException("Error al consultar el microservicio de Estética: " + e.getMessage());
            }

            Long reservaId = Long.valueOf(reservaMap.get("id").toString());
            Map<String, Object> servicioMap = (Map<String, Object>) reservaMap.get("servicioEstetica");
            if (servicioMap == null) {
                throw new RuntimeException("Catálogo de servicio de estética ausente en la reserva.");
            }

            String servNombre = (String) servicioMap.get("nombre");
            Double servPrecio = Double.valueOf(servicioMap.get("precio").toString());

            DetalleFactura itemEstetica = new DetalleFactura();
            itemEstetica.setDescripcion("Servicio Estética: " + servNombre);
            itemEstetica.setCantidad(1);
            itemEstetica.setPrecioUnitario(servPrecio);
            itemEstetica.setFactura(factura);
            factura.getDetalles().add(itemEstetica);

            subtotal += servPrecio;

            // Update remote reservation state to "Facturada"
            try {
                esteticaProxy.actualizarEstadoReserva(reservaId, "Facturada");
            } catch (Exception e) {
                System.err.println("No se pudo actualizar el estado de la Reserva Estética #" + reservaId + ": " + e.getMessage());
            }
        } else {
            throw new RuntimeException("Tipo de servicio no soportado para facturación: " + tipoServicio);
        }

        // Calculate total with 19% IVA tax
        double totalConIva = subtotal * 1.19;
        factura.setTotal(Math.round(totalConIva * 100.0) / 100.0);

        // 4. Save invoice
        Factura savedFactura = repo.save(factura);

        // 5. Update Citas service that billing is recorded
        try {
            citaProxy.registrarFacturacion(citaId);
        } catch (Exception e) {
            System.err.println("No se pudo registrar la facturación en la cita ID " + citaId + ": " + e.getMessage());
        }

        // 6. Trigger notification to customer
        try {
            notificacionProxy.enviarNotificacion(Map.of(
                "clienteId", clienteId,
                "mensaje", "Estimado cliente, se ha emitido su factura #" + savedFactura.getId() + " por un total de $" + savedFactura.getTotal() + " (IVA incluido)."
            ));
        } catch (Exception e) {
            System.err.println("No se pudo disparar la notificación para el cliente ID " + clienteId + ": " + e.getMessage());
        }

        return savedFactura;
    }
}
