package BullyCars.Clientes.Security;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;
import java.security.Key;
import java.util.Date;

@Component
public class JwtUtil {

    // Generamos una clave segura de 256 bits para firmar los tokens de BullyCars
    private final Key secretKey = Keys.secretKeyFor(SignatureAlgorithm.HS256);
    
    // El token expirará en 24 horas (en milisegundos)
    private final long expirationTime = 86400000; 

    public String generarToken(String email, String rol) {
        return Jwts.builder()
                .setSubject(email)
                .claim("rol", rol) // Metemos el rol dentro del token para que el Gateway o Front lo validen
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + expirationTime))
                .signWith(secretKey) // Firmamos digitalmente
                .compact();
    }
}