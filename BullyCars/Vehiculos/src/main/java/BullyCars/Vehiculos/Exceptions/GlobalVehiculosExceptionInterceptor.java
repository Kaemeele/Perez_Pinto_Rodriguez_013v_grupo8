package BullyCars.Vehiculos.Exceptions;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * Interceptor global de excepciones para centralizar y unificar las respuestas de error.
 */
@RestControllerAdvice
public class GlobalVehiculosExceptionInterceptor {

    @ExceptionHandler(VehiculoNoEncontradoException.class)
    public ResponseEntity<Map<String, Object>> manejarVehiculoNoEncontrado(VehiculoNoEncontradoException ex) {
        Map<String, Object> error = new HashMap<>();
        error.put("timestamp", LocalDateTime.now());
        error.put("status", HttpStatus.NOT_FOUND.value());
        error.put("error", "Vehiculo No Encontrado");
        error.put("message", ex.getMessage());
        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }
}