package BullyCars.Citas.Exceptions;

/**
 * Excepcion de negocio personalizada para representar errores especificos del dominio.
 */
public class CitaInvalidaException extends RuntimeException {
    public CitaInvalidaException(String message) {
        super(message);
    }

    public CitaInvalidaException(String message, Throwable cause) {
        super(message, cause);
    }
}