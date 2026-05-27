package BullyCars.Citas.Exceptions;

public class FechaPasadaException extends RuntimeException {
    public FechaPasadaException(String message) {
        super(message);
    }

    public FechaPasadaException(String message, Throwable cause) {
        super(message, cause);
    }
}