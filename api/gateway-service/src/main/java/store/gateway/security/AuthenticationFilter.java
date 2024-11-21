package store.gateway.security;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.server.ServerWebExchange;

import reactor.core.publisher.Mono;

@Component
public class AuthenticationFilter implements GlobalFilter {

    private static final Set<String> open = new HashSet<>(Arrays.asList(
        "POST /auth/register",
        "POST /auth/login"
    ));

    @Autowired
    private WebClient.Builder webClient;

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        ServerHttpRequest request = exchange.getRequest();
        String method = request.getMethod().name();
        String path = request.getPath().value();
        String uri = method + " " + path;
        // verifica se o endpoint eh aberto
        if (open.contains(uri)) {
            return chain.filter(exchange);
        }
        // verifica se tem o JWT
        if (request.getHeaders().containsKey("Authorization")) {
            String value = request.getHeaders().get("Authorization").get(0);
            String[] parts = value.strip().split(" ");
            if (parts.length != 2 || !parts[0].equals("Bearer")) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Authorization header format is invalid");
            }
            String token = parts[1];
            System.out.println("JWT: " + token);
            return validateToken(exchange, chain, token);
        }
        throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, uri + " is not open");
    }

    private Mono<Void> validateToken(ServerWebExchange exchange, GatewayFilterChain chain, String token) {
        Map<String, String> body = Map.of(
            "jwt", token
        );
        return webClient
            .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
            .build()
            .post()
            .uri("http://auth:8080/auth/solve")
            .bodyValue(body)
            .retrieve()
            .toEntity(AccountOut.class)
            .flatMap(response -> {
                if (response == null || !response.hasBody() || response.getBody() == null) {
                    throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "invalid token");
                }
                AccountOut account = response.getBody();
                System.out.println(account);
                return chain.filter(exchange);
            });
    }
    
}
