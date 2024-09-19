package store.account;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AccountResource {

    private static List<String> accounts = new ArrayList<>();

    @GetMapping("/account")
    public List<String> getAccounts() {
        return accounts;
    }

    @PostMapping("/account")
    public void postAccount(@RequestBody AccountIn in) {
        accounts.add(in.name() + ": " + in.email());
    }
    
}
