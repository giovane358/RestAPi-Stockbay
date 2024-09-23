package br.com.stockbay.userstockbay.infra.segurity;

import br.com.stockbay.userstockbay.domain.user.Auth;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

@Service
public class TokenConfigurations {
    @Value("${JWT_SECRET:my-secret-key}")
    private String secret;

    public String generateToken(Auth user) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(secret);
            String token = JWT.create()
                    .withIssuer("RestAPi-Stockbay")
                    .withSubject(user.getUsername())
                    .withExpiresAt(genExpiresData())
                    .sign(algorithm);
            return token;
        } catch (JWTCreationException exception) {
            throw new RuntimeException("Error wile generation token", exception);
        }
    }

    public  String validToken(String token){

        try {
            Algorithm algorithm =   Algorithm.HMAC256(secret);
            return JWT.require(algorithm) .withIssuer("RestAPi-Stockbay").build().verify(token).getSubject();

        }catch (JWTVerificationException exception ){
            return  "";
        }
    }

    private Instant genExpiresData() {
        return LocalDateTime.now().plusHours(2).toInstant(ZoneOffset.of("-03:00"));
    }
}