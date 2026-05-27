package BullyCars.Citas.Exceptions;

public class CitaYaProgramadaException extends RuntimeException {
    public CitaYaProgramadaException(String message) {
        super(message);
    }

    public CitaYaProgramadaException(String message, Throwable cause) {
        super(message, cause);
    }
}