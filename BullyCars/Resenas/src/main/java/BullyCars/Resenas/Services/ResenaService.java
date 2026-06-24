package BullyCars.Resenas.Services;

import java.util.Optional;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import BullyCars.Resenas.Models.Resena;
import BullyCars.Resenas.Repositories.ResenaRepository;
import BullyCars.Resenas.Proxy.ClienteProxy;
import BullyCars.Resenas.Proxy.CitaProxy;

@Service
public class ResenaService {
    
    @Autowired
    private ResenaRepository repository;

    @Autowired
    private ClienteProxy clienteProxy;

    @Autowired
    private CitaProxy citaProxy;

    public List<Resena> listarTodas() { return repository.findAll(); }

    @Transactional
    public Resena guardar(Resena resena) {
        if (resena.getClienteId() == null) {
            throw new RuntimeException("El clienteId es obligatorio.");
        }

        // 1. Validate client existence
        validarCliente(resena.getClienteId());

        // 2. Validate client has at least one appointment (Cita)
        validarClienteTieneCitas(resena.getClienteId());

        return repository.save(resena);
    }

    private void validarCliente(Long id) {
        try {
            var res = clienteProxy.obtenerClientePorId(id);
            if (res == null || res.getStatusCode().isError()) {
                throw new RuntimeException("El cliente con ID " + id + " no existe.");
            }
        } catch (Exception e) {
            throw new RuntimeException("No se pudo validar el cliente: " + e.getMessage());
        }
    }

    @SuppressWarnings("unchecked")
    private void validarClienteTieneCitas(Long clienteId) {
        try {
            var res = citaProxy.listarTodas();
            if (res == null || res.getBody() == null) {
                throw new RuntimeException("El cliente no tiene citas registradas. Solo clientes con citas atendidas pueden dejar reseñas.");
            }
            List<Object> citas = res.getBody();
            boolean tieneCita = false;
            for (Object citaObj : citas) {
                if (citaObj instanceof Map) {
                    Map<?, ?> map = (Map<?, ?>) citaObj;
                    if (map.containsKey("clienteId") && map.get("clienteId") != null) {
                        Long cId = Long.valueOf(map.get("clienteId").toString());
                        if (cId.equals(clienteId)) {
                            tieneCita = true;
                            break;
                        }
                    }
                }
            }
            if (!tieneCita) {
                throw new RuntimeException("El cliente con ID " + clienteId + " no tiene citas registradas. Solo clientes con citas pueden dejar reseñas.");
            }
        } catch (Exception e) {
            throw new RuntimeException("Error al validar el historial de citas del cliente: " + e.getMessage());
        }
    }

    public Optional<Resena> obtenerPorId(Long id) { return repository.findById(id); }

    public void eliminar(Long id) {
        if (!repository.existsById(id)) {
            throw new RuntimeException("Reseña no encontrada con ID: " + id);
        }
        repository.deleteById(id);
    }
}