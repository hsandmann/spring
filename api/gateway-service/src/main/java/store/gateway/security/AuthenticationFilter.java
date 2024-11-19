package store.gateway.security;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.server.ServerWebExchange;

import reactor.core.publisher.Mono;

@Component
public class AuthenticationFilter implements GlobalFilter {

    private static final Set<String> open = new HashSet<>(Arrays.asList(
        "POST /auth/register",
        "POST /auth/login"
    ));

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
            System.out.println("JWT: " + value);
            return chain.filter(exchange);
        }
        throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, uri + " is not open");
    }
    
}
