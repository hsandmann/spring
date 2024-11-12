package store.auth;

import java.util.Date;
import java.util.UUID;

import javax.crypto.SecretKey;

import org.springframework.stereotype.Service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtParser;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import store.account.AccountOut;

@Service
public class JwtService {

    private static final String message_key = "a2l6UD5EWmApL2dreyZyXX10eEhNSXY3WnYwOlRUZmxWV2E4eUsnc0RnV31RfWh1dWZ9ZCF3NlVrbWtHNF9kLUVTUE0=";

    public String create(AccountOut account) {

        Date now = new Date();
        Long duration = 1000L * 60 * 60 * 24;

        String jwt = Jwts.builder()
            .header()
            .and()
            .id(UUID.randomUUID().toString())
            .issuer("ESPM")
            .issuedAt(now)
            .signWith(key())
            .notBefore(now)
            .expiration(new Date(now.getTime() + duration))
            .claim("account", account.id())
            .claim("name", account.name())
            .claim("email", account.email())
            .compact();
        return jwt;
    }

    public String read(String jwt) {

        JwtParser parser = Jwts.parser().verifyWith(key()).build();
        // verifica a assinatura do jwt
        Claims claims = parser.parseSignedClaims(jwt).getPayload();
        final Date now = new Date();
        // verifica se ele jah estah ativo
        if (now.compareTo(claims.getNotBefore()) < 0)
            throw new RuntimeException("not before " + claims.getNotBefore());
        // verifica se ele nao estah expirado
        if (now.compareTo(claims.getExpiration()) > 0)
            throw new RuntimeException("expired at " + claims.getExpiration());
        // recupera o id account do token
        String idAccount = claims.get("account").toString();
        return idAccount;
    }

    private SecretKey key() {
        return Keys.hmacShaKeyFor(
            Decoders.BASE64.decode(message_key)
        );
    }
    
}
