package BullyCars.Reparaciones.Services;

import java.util.Optional;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import BullyCars.Reparaciones.Models.OrdenTrabajo;
import BullyCars.Reparaciones.Models.RepuestoUsado;
import BullyCars.Reparaciones.Repositories.OrdenTrabajoRepository;
import BullyCars.Reparaciones.Repositories.RepuestoUsadoRepository;
import BullyCars.Reparaciones.Proxy.VehiculoProxy;
import BullyCars.Reparaciones.Proxy.InventarioProxy;

@Service
public class ReparacionService {
    
    @Autowired
    private OrdenTrabajoRepository repository;

    @Autowired
    private RepuestoUsadoRepository repuestoUsadoRepository;

    @Autowired
    private VehiculoProxy vehiculoProxy;

    @Autowired
    private InventarioProxy inventarioProxy;

    public List<OrdenTrabajo> listarOrdenes() { return repository.findAll(); }
    
    @Transactional
    public OrdenTrabajo crearOrden(OrdenTrabajo orden) {
        if (orden.getVehiculoId() != null) {
            validarVehiculo(orden.getVehiculoId());
        }
        if (orden.getEstado() == null) {
            orden.setEstado("Pendiente");
        }
        return repository.save(orden);
    }

    private void validarVehiculo(Long vehiculoId) {
        try {
            var res = vehiculoProxy.obtenerVehiculoPorId(vehiculoId);
            if (res == null || res.getStatusCode().isError()) {
                throw new RuntimeException("El vehiculo con ID " + vehiculoId + " no existe.");
            }
        } catch (Exception e) {
            throw new RuntimeException("Error al validar el vehiculo: " + e.getMessage());
        }
    }

    public Optional<OrdenTrabajo> obtenerPorId(Long id) { return repository.findById(id); }

    public Optional<OrdenTrabajo> obtenerPorCitaId(Long citaId) {
        return repository.findByCitaId(citaId);
    }

    public void eliminar(Long id) {
        if (!repository.existsById(id)) {
            throw new RuntimeException("Orden de trabajo no encontrada con ID: " + id);
        }
        repository.deleteById(id);
    }

    @Transactional
    public RepuestoUsado agregarRepuesto(Long ordenId, Long repuestoId, Integer cantidad) {
        OrdenTrabajo orden = repository.findById(ordenId)
                .orElseThrow(() -> new RuntimeException("Orden de trabajo no encontrada con ID: " + ordenId));

        // 1. Get info from remote inventory
        Object infoRepuestoRaw;
        try {
            var res = inventarioProxy.obtenerRepuestoPorId(repuestoId);
            if (res.getBody() == null) {
                throw new RuntimeException("Repuesto con ID " + repuestoId + " no existe en el inventario.");
            }
            infoRepuestoRaw = res.getBody();
        } catch (Exception e) {
            throw new RuntimeException("No se pudo obtener información del repuesto. Detalles: " + e.getMessage());
        }

        // Parse price and name from raw map response
        Double precio = 0.0;
        String nombre = "Repuesto Desconocido";
        if (infoRepuestoRaw instanceof java.util.Map) {
            java.util.Map<?, ?> map = (java.util.Map<?, ?>) infoRepuestoRaw;
            if (map.containsKey("precio") && map.get("precio") != null) {
                precio = Double.valueOf(map.get("precio").toString());
            }
            if (map.containsKey("nombre") && map.get("nombre") != null) {
                nombre = map.get("nombre").toString();
            }
        }

        // 2. Call Inventario to discount stock
        try {
            inventarioProxy.descontarStock(repuestoId, cantidad, "Descontado por Orden Trabajo #" + ordenId);
        } catch (Exception e) {
            throw new RuntimeException("Error al descontar stock del repuesto: " + e.getMessage());
        }

        // 3. Save used spare part record
        RepuestoUsado usado = new RepuestoUsado();
        usado.setRepuestoId(repuestoId);
        usado.setCantidad(cantidad);
        usado.setPrecioUnitario(precio);
        usado.setNombreRepuesto(nombre);
        usado.setOrdenTrabajo(orden);

        return repuestoUsadoRepository.save(usado);
    }

    public OrdenTrabajo actualizarEstadoYClave(Long id, String estado, Double costoManoObra) {
        OrdenTrabajo orden = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Orden de trabajo no encontrada con ID: " + id));
        if (estado != null) orden.setEstado(estado);
        if (costoManoObra != null) orden.setCostoManoObra(costoManoObra);
        return repository.save(orden);
    }
}