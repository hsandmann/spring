package store.account;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AccountResource implements AccountController {

    private static List<AccountOut> accounts = new ArrayList<>();

    @Override
    public List<AccountOut> findAll() {
        return accounts;
    }

    @Override
    public void create(@RequestBody AccountIn in) {
        accounts.add(
            AccountOut.builder()
                .id(UUID.randomUUID().toString())
                .name(in.name())
                .email(in.email())
                .build()
        );
    }
    
}
