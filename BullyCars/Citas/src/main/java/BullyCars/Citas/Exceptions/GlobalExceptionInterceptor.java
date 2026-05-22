package BullyCars.Citas.Exceptions;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice // <- OBLIGATORIO: Captura los errores de todos los controladores de Citas
public class GlobalExceptionInterceptor {

    // 1. Intercepta cuando una cita se agenda en una fecha inválida (Pasada)
    @ExceptionHandler(FechaPasadaException.class)
    public ResponseEntity<Map<String, Object>> manejarFechaPasada(FechaPasadaException ex) {
        Map<String, Object> respuesta = new HashMap<>();
        respuesta.put("timestamp", LocalDateTime.now());
        respuesta.put("status", HttpStatus.BAD_REQUEST.value());
        respuesta.put("error", "Fecha Inválida");
        respuesta.put("message", ex.getMessage());

        return new ResponseEntity<>(respuesta, HttpStatus.BAD_REQUEST); // HTTP 400
    }

    // 2. Intercepta cuando el vehículo ya tiene una hora tomada
    @ExceptionHandler(CitaYaProgramadaException.class)
    public ResponseEntity<Map<String, Object>> manejarCitaDuplicada(CitaYaProgramadaException ex) {
        Map<String, Object> respuesta = new HashMap<>();
        respuesta.put("timestamp", LocalDateTime.now());
        respuesta.put("status", HttpStatus.CONFLICT.value());
        respuesta.put("error", "Conflicto de Disponibilidad");
        respuesta.put("message", ex.getMessage());

        return new ResponseEntity<>(respuesta, HttpStatus.CONFLICT); // HTTP 409
    }

    // 3. Captura cualquier otro error genérico de validación o del sistema
    @ExceptionHandler(Exception.class)
    public ResponseEntity<Map<String, Object>> manejarErroresGlobales(Exception ex) {
        Map<String, Object> respuesta = new HashMap<>();
        respuesta.put("timestamp", LocalDateTime.now());
        respuesta.put("status", HttpStatus.INTERNAL_SERVER_ERROR.value());
        respuesta.put("error", "Internal Server Error");
        respuesta.put("message", "Ocurrió un error inesperado en el servidor de control de citas.");

        return new ResponseEntity<>(respuesta, HttpStatus.INTERNAL_SERVER_ERROR); // HTTP 500
    }
}