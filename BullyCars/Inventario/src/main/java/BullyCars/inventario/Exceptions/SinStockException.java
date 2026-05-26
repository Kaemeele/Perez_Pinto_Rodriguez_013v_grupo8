package BullyCars.inventario.Exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
/**
 * Excepcion de negocio personalizada para representar errores especificos del dominio.
 */
public class SinStockException extends RuntimeException {
    public SinStockException(String mensaje) {
        super(mensaje);
    }
}