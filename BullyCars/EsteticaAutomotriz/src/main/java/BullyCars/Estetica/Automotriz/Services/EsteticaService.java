package BullyCars.Estetica.Automotriz.Services;

import java.util.Optional;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import BullyCars.Estetica.Automotriz.Models.ServicioEstetica;
import BullyCars.Estetica.Automotriz.Repositories.EsteticaRepository;

@Service
public class EsteticaService {
    
    @Autowired private EsteticaRepository repo;

    public List<ServicioEstetica> listar() { return repo.findAll(); }

    public ServicioEstetica guardar(ServicioEstetica s) { return repo.save(s); }

    public Optional<ServicioEstetica> obtenerPorId(Long id) { return repo.findById(id); }
}