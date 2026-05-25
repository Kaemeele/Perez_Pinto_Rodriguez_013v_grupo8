package BullyCars.Proveedores.Services;

import BullyCars.Proveedores.Models.Proveedor;
import BullyCars.Proveedores.Repositories.ProveedorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

/**
 * Servicio que encapsula la logica de negocio principal y las reglas operacionales.
 */
@Service
public class ProveedorService {
    // Inyeccion automatica de dependencias de Spring
    @Autowired
    private ProveedorRepository repository;

    // Obtiene la lista de todos los proveedores de repuestos y servicios registrados
    public List<Proveedor> listarTodo() { return repository.findAll(); }

    // Registra un nuevo proveedor o actualiza la informacion de contacto de uno existente
    public Proveedor guardar(Proveedor p) { return repository.save(p); }
}