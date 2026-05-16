package BullyCars.Notificaciones.Controllers;

public class NotificacionController {

}
@RestController @RequestMapping("/api/v1/notificaciones")
public class NotificacionController {
    @Autowired private NotificacionService service;
    @PostMapping public ResponseEntity<Notificacion> enviar(@RequestBody Notificacion n) { 
        return new ResponseEntity<>(service.registrar(n), HttpStatus.CREATED); 
    }
    @GetMapping public ResponseEntity<List<Notificacion>> listar() { return ResponseEntity.ok(service.verTodas()); }
}