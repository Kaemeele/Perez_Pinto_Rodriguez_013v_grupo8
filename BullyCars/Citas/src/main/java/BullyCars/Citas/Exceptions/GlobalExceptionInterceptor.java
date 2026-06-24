package BullyCars.Citas.Exceptions;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice 
public class GlobalExceptionInterceptor {

    @ExceptionHandler(FechaPasadaException.class)
    public ResponseEntity<Map<String, Object>> manejarFechaPasada(FechaPasadaException ex) {
        Map<String, Object> respuesta = new HashMap<>();
        respuesta.put("timestamp", LocalDateTime.now());
        respuesta.put("status", HttpStatus.BAD_REQUEST.value());
        respuesta.put("error", "Fecha Invalida");
        respuesta.put("message", ex.getMessage());

        return new ResponseEntity<>(respuesta, HttpStatus.BAD_REQUEST); 
    }

    @ExceptionHandler(CitaYaProgramadaException.class)
    public ResponseEntity<Map<String, Object>> manejarCitaDuplicada(CitaYaProgramadaException ex) {
        Map<String, Object> respuesta = new HashMap<>();
        respuesta.put("timestamp", LocalDateTime.now());
        respuesta.put("status", HttpStatus.CONFLICT.value());
        respuesta.put("error", "Conflicto de Disponibilidad");
        respuesta.put("message", ex.getMessage());

        return new ResponseEntity<>(respuesta, HttpStatus.CONFLICT); 
    }

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<Map<String, Object>> manejarRuntimeException(RuntimeException ex) {
        Map<String, Object> respuesta = new HashMap<>();
        respuesta.put("timestamp", LocalDateTime.now());
        respuesta.put("status", HttpStatus.BAD_REQUEST.value());
        respuesta.put("error", "Error de Validacion");
        respuesta.put("message", ex.getMessage());

        return new ResponseEntity<>(respuesta, HttpStatus.BAD_REQUEST); 
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Map<String, Object>> manejarErroresGlobales(Exception ex) {
        Map<String, Object> respuesta = new HashMap<>();
        respuesta.put("timestamp", LocalDateTime.now());
        respuesta.put("status", HttpStatus.INTERNAL_SERVER_ERROR.value());
        respuesta.put("error", "Internal Server Error");
        respuesta.put("message", "Ocurrio un error inesperado en el servidor de control de citas.");

        return new ResponseEntity<>(respuesta, HttpStatus.INTERNAL_SERVER_ERROR); 
    }
}