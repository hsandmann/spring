package store.account;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

public interface AccountController {
    
    @GetMapping("/account")
    public List<AccountOut> findAll();

    @PostMapping("/account")
    public void create(@RequestBody AccountIn in);

}
