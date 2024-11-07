package store.auth;

import java.util.Date;
import java.util.UUID;

import javax.crypto.SecretKey;

import org.springframework.stereotype.Service;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import store.account.AccountOut;

@Service
public class JwtService {

    private static final String message_key = "a2l6UD5EWmApL2dreyZyXX10eEhNSXY3WnYwOlRUZmxWV2E4eUsnc0RnV31RfWh1dWZ9ZCF3NlVrbWtHNF9kLUVTUE0=";

    public String create(AccountOut account) {

        final SecretKey key = Keys.hmacShaKeyFor(
            Decoders.BASE64.decode(message_key)
        );

        Date now = new Date();
        Long duration = 1000L * 60 * 60 * 24;

        String jwt = Jwts.builder()
            .header()
            .and()
            .id(UUID.randomUUID().toString())
            .issuer("ESPM")
            .issuedAt(now)
            .signWith(key)
            .notBefore(now)
            .expiration(new Date(now.getTime() + duration))
            .claim("account", account.id())
            .claim("name", account.name())
            .claim("email", account.email())
            .compact();
        return jwt;
    }
    
}
