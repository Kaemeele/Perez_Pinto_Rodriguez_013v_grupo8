package BullyCars.Proveedores.Config;

import BullyCars.Proveedores.Models.Proveedor;
import BullyCars.Proveedores.Repositories.ProveedorRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
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
        }
    }
}
