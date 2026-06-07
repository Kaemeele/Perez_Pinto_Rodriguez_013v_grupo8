package BullyCars.Proveedores.Config;

import BullyCars.Proveedores.Models.Proveedor;
import BullyCars.Proveedores.Repositories.ProveedorRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

// @Component
public class DataSeeder implements CommandLineRunner {
    private final ProveedorRepository proveedorRepository;

    public DataSeeder(ProveedorRepository proveedorRepository) {
        this.proveedorRepository = proveedorRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        if (proveedorRepository.count() == 0) {
            Proveedor proveedor = new Proveedor();
            proveedor.setNombreEmpresa("AutoParts Chile");
            proveedor.setContacto("ventas@autoparts.cl");
            proveedor.setCategoriaInsumos("Repuestos Generales");
            proveedorRepository.save(proveedor);

            Proveedor proveedor2 = new Proveedor();
            proveedor2.setNombreEmpresa("WrapMasters International");
            proveedor2.setContacto("soporte@wrapmasters.com");
            proveedor2.setCategoriaInsumos("Vinilos & Wraps Premium");
            proveedorRepository.save(proveedor2);
        }
    }
}

// GET (Listar Proveedores)
// http://localhost:9081/api/v1/proveedores

// GET (Obtener por ID)
// http://localhost:9081/api/v1/proveedores/1

// POST (Crear Proveedor)
// http://localhost:9081/api/v1/proveedores
// {
//   "nombreEmpresa": "TuningParts SpA",
//   "contacto": "contacto@tuningparts.cl",
//   "categoriaInsumos": "Accesorios Deportivos y Tuning"
// }

// DELETE (Eliminar por ID)
// http://localhost:9081/api/v1/proveedores/1


