package store.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import store.account.AccountIn;

@RestController
public class AuthResource implements AuthController {

    @Autowired
    private AuthService authService;

    @Override
    public ResponseEntity<LoginOut> register(RegisterIn in) {
        AccountIn account = AccountIn.builder()
            .name(in.name())
            .email(in.email())
            .password(in.password())
            .birthdate(in.birthdate())
            .build();
        authService.register(account);
        return ResponseEntity
            .ok()
            .body(LoginOut.builder().jwt("marcio nao veio").build());
    }

    @Override
    public ResponseEntity<LoginOut> login(Login in) {
        final LoginOut out = authService.login(in);
        return ResponseEntity
            .ok()
            .body(out);
    }
    
}
