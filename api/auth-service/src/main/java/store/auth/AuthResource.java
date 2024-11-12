package store.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import store.account.AccountIn;
import store.account.AccountOut;

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
        LoginOut out = authService.register(account);
        return ResponseEntity
            .ok()
            .body(out);
    }

    @Override
    public ResponseEntity<LoginOut> login(Login in) {
        final LoginOut out = authService.login(in);
        return ResponseEntity
            .ok()
            .body(out);
    }

    @Override
    public ResponseEntity<AccountOut> solve(LoginOut in) {
        final String jwt = in.jwt();
        if (jwt == null || jwt.length() == 0) {
            return ResponseEntity.badRequest().build();
        }
        AccountOut account = authService.solve(jwt);
        return ResponseEntity.ok().body(account);         
    }
    
}
