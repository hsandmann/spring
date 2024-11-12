package store.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import store.account.AccountController;
import store.account.AccountIn;
import store.account.AccountOut;

@Service
public class AuthService {

    @Autowired
    private AccountController accountController;

    @Autowired
    private JwtService jwtService;

    public LoginOut register(AccountIn in) {
        AccountOut account = accountController.create(in).getBody();
        final String jwt = jwtService.create(account);
        return LoginOut.builder()
            .jwt(jwt)
            .build();
    }

    public LoginOut login(Login in) {
        AccountOut account = accountController.login(
            AccountIn.builder()
                .email(in.email())
                .password(in.password())
                .build()
        ).getBody();
        final String jwt = jwtService.create(account);
        return LoginOut.builder()
            .jwt(jwt)
            .build();
    }

    public AccountOut solve(String jwt) {
        String idAccount = jwtService.read(jwt);
        ResponseEntity<AccountOut> response = accountController.findById(idAccount);
        return response.getBody();
    }

}
