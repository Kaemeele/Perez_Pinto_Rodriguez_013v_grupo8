package BullyCars.Estetica.Automotriz.Controllers;

public class EsteticaAutomotriz {

}

@RestController @RequestMapping("/api/v1/estetica")
public class EsteticaController {
    @Autowired private EsteticaService service;
    @GetMapping public ResponseEntity<List<ServicioEstetica>> listar() { return ResponseEntity.ok(service.listar()); }
    @PostMapping public ResponseEntity<ServicioEstetica> crear(@RequestBody ServicioEstetica s) { 
        return new ResponseEntity<>(service.guardar(s), HttpStatus.CREATED); 
    }
}