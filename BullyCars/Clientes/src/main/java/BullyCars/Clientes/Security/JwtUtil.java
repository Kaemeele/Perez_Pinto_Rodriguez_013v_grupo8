package BullyCars.Clientes.Security;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;
import java.security.Key;
import java.util.Date;

@Component
/**
 * Utilidad encargada de la creacion, firma y verificacion de tokens de seguridad JWT.
 */
public class JwtUtil {

    private final Key secretKey = Keys.secretKeyFor(SignatureAlgorithm.HS256);
    
    private final long expirationTime = 86400000; 

    public String generarToken(String email, String rol) {
        return Jwts.builder()
                .setSubject(email)
                .claim("rol", rol) 
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + expirationTime)) // Fecha de expiracion
                .signWith(secretKey, SignatureAlgorithm.HS256) 
                .compact();
    }
}