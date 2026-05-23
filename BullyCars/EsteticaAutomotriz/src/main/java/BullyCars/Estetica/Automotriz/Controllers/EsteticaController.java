package BullyCars.Estetica.Automotriz.Controllers;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import BullyCars.Estetica.Automotriz.Models.ServicioEstetica;
import BullyCars.Estetica.Automotriz.Services.EsteticaService;

@RestController
@RequestMapping("/api/v1/estetica")
public class EsteticaController {
    @Autowired private EsteticaService service;

    @GetMapping
    public ResponseEntity<List<ServicioEstetica>> listar() { 
        return ResponseEntity.ok(service.listar()); 
    }

    @PostMapping
    public ResponseEntity<ServicioEstetica> crear(@RequestBody ServicioEstetica s) { 
        return new ResponseEntity<>(service.guardar(s), HttpStatus.CREATED); 
    }
}
