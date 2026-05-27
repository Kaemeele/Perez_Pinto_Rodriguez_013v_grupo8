package BullyCars.Proveedores.Services;

import java.util.Optional;

import BullyCars.Proveedores.Models.Proveedor;
import BullyCars.Proveedores.Repositories.ProveedorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class ProveedorService {
    
    @Autowired
    private ProveedorRepository repository;

    public List<Proveedor> listarTodo() { return repository.findAll(); }

    public Proveedor guardar(Proveedor p) { return repository.save(p); }

    public Optional<Proveedor> obtenerPorId(Long id) { return repository.findById(id); }
}