package store.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import store.account.AccountController;
import store.account.AccountIn;

@Service
public class AuthService {

    @Autowired
    private AccountController accountController;

    public void register(AccountIn in) {
        accountController.create(in);
    }



}
