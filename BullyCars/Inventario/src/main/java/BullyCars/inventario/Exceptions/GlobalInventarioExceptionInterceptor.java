package BullyCars.inventario.Exceptions;

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
public class GlobalInventarioExceptionInterceptor {

    @ExceptionHandler(SinStockException.class)
    public ResponseEntity<Map<String, Object>> manejarSinStock(SinStockException ex) {
        Map<String, Object> error = new HashMap<>();
        error.put("timestamp", LocalDateTime.now());
        error.put("status", HttpStatus.BAD_REQUEST.value());
        error.put("error", "Stock Agotado");
        error.put("message", ex.getMessage());
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }
}