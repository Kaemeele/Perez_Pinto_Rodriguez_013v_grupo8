package BullyCars.Vehiculos.Exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class VehiculoNoEncontradoException extends RuntimeException {
    public VehiculoNoEncontradoException(String mensaje) {
        super(mensaje);
    }
}