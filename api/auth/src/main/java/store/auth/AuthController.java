package store.auth;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import store.account.AccountOut;

@FeignClient(name = "auth", url = "http://auth:8080")
public interface AuthController {
    
    @PostMapping("/auth/register")
    public ResponseEntity<LoginOut> register(
        @RequestBody RegisterIn in
    );

    @PostMapping("/auth/login")
    public ResponseEntity<LoginOut> login(
        @RequestBody Login in
    );

    @PostMapping("/auth/solve")
    public ResponseEntity<AccountOut> solve(
        @RequestBody LoginOut in
    );

}
