package BullyCars.Citas.Exceptions;

/**
 * Excepcion de negocio personalizada para representar errores especificos del dominio.
 */
public class CitaYaProgramadaException extends RuntimeException {
    public CitaYaProgramadaException(String message) {
        super(message);
    }

    public CitaYaProgramadaException(String message, Throwable cause) {
        super(message, cause);
    }
}