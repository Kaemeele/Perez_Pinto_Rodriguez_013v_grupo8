package BullyCars.Citas.Exceptions;

/**
 * Excepcion de negocio personalizada para representar errores especificos del dominio.
 */
public class FechaPasadaException extends RuntimeException {
    public FechaPasadaException(String message) {
        super(message);
    }

    public FechaPasadaException(String message, Throwable cause) {
        super(message, cause);
    }
}