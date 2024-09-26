package store.account;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AccountResource implements AccountController {

    @Autowired
    private AccountService accountService;

    @Override
    public List<AccountOut> findAll() {
        return accountService.findAll().stream().map(AccountParser::to).toList();
    }

    @Override
    public void create(@RequestBody AccountIn in) {
        accountService.create(AccountParser.to(in));
    }
    
}
