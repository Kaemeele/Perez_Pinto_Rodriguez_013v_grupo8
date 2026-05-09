package BullyCars.Citas.Services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import BullyCars.Citas.Models.Cita;
import BullyCars.Citas.Repositories.CitaRepository;

@Service
public class CitaService {
    @Autowired
    private CitaRepository repository;

    public List<Cita> obtenerTodas() {
        return repository.findAll();
    }

    public Cita agendar(Cita cita) {
        // Aquí podrías agregar validaciones más adelante
        return repository.save(cita);
    }
}