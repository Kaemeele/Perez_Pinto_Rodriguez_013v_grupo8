package BullyCars.Citas.Exceptions;

public class CitaInvalidaException extends RuntimeException {
    public CitaInvalidaException(String message) {
        super(message);
    }

    public CitaInvalidaException(String message, Throwable cause) {
        super(message, cause);
    }
}