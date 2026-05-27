package BullyCars.Resenas.Services;

import java.util.Optional;

import BullyCars.Resenas.Models.Resena;
import BullyCars.Resenas.Repositories.ResenaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class ResenaService {
    
    @Autowired
    private ResenaRepository repository;

    public List<Resena> listarTodas() { return repository.findAll(); }

    public Resena guardar(Resena resena) { return repository.save(resena); }

    public Optional<Resena> obtenerPorId(Long id) { return repository.findById(id); }
}