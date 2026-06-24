package BullyCars.Estetica.Automotriz.Services;

import java.util.Optional;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import BullyCars.Estetica.Automotriz.Models.ServicioEstetica;
import BullyCars.Estetica.Automotriz.Models.ReservaEstetica;
import BullyCars.Estetica.Automotriz.Repositories.EsteticaRepository;

@Service
public class EsteticaService {
    
    @Autowired private EsteticaRepository repo;

    @Autowired private BullyCars.Estetica.Automotriz.Repositories.ReservaEsteticaRepository reservaRepo;

    public List<ServicioEstetica> listar() { return repo.findAll(); }

    public ServicioEstetica guardar(ServicioEstetica s) { return repo.save(s); }

    public Optional<ServicioEstetica> obtenerPorId(Long id) { return repo.findById(id); }

    public void eliminar(Long id) {
        if (!repo.existsById(id)) {
            throw new RuntimeException("Servicio de estética no encontrado con ID: " + id);
        }
        repo.deleteById(id);
    }

    public ReservaEstetica crearReserva(ReservaEstetica r) {
        if (r.getServicioEstetica() != null && r.getServicioEstetica().getId() != null) {
            ServicioEstetica se = repo.findById(r.getServicioEstetica().getId())
                .orElseThrow(() -> new RuntimeException("Servicio de estética catálogo no existe"));
            r.setServicioEstetica(se);
        }
        return reservaRepo.save(r);
    }

    public Optional<ReservaEstetica> obtenerReservaPorCitaId(Long citaId) {
        return reservaRepo.findByCitaId(citaId);
    }

    public Optional<ReservaEstetica> obtenerReservaPorId(Long id) {
        return reservaRepo.findById(id);
    }

    public ReservaEstetica actualizarEstadoReserva(Long id, String estado) {
        ReservaEstetica r = reservaRepo.findById(id)
            .orElseThrow(() -> new RuntimeException("Reserva no encontrada con ID: " + id));
        r.setEstado(estado);
        return reservaRepo.save(r);
    }
}