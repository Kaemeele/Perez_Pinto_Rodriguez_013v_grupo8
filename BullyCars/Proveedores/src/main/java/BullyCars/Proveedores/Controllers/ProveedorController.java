package BullyCars.Proveedores.Controllers;

public class ProveedorController {

}
@RestController @RequestMapping("/api/v1/proveedores")
public class ProveedorController {
    @Autowired private ProveedorService service;
    @GetMapping public ResponseEntity<List<Proveedor>> listar() { return ResponseEntity.ok(service.listar()); }
    @PostMapping public ResponseEntity<Proveedor> crear(@RequestBody Proveedor p) { 
        return new ResponseEntity<>(service.guardar(p), HttpStatus.CREATED); 
    }
}