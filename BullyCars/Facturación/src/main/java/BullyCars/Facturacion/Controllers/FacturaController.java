package BullyCars.Facturacion.Controllers;

public class FacturaController {

}
@RestController @RequestMapping("/api/v1/facturacion")
public class FacturacionController {
    @Autowired private FacturacionService service;
    @PostMapping public ResponseEntity<Factura> crear(@RequestBody Factura f) { 
        return new ResponseEntity<>(service.generar(f), HttpStatus.CREATED); 
    }
    @GetMapping public ResponseEntity<List<Factura>> listar() { return ResponseEntity.ok(service.historial()); }
}