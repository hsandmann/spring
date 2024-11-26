package store.account;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
public class AccountResource implements AccountController {

    @Autowired
    private AccountService accountService;

    @Override
    public ResponseEntity<List<AccountOut>> findAll() {
        List<AccountOut> out = accountService.findAll().stream().map(AccountParser::to).toList();
        return ResponseEntity
            .ok()
            .body(out);        
    }

    @Override
    public ResponseEntity<AccountOut> findById(String idAccount) {
        AccountOut found = AccountParser.to(accountService.findById(idAccount));
        return found == null
            ? ResponseEntity.notFound().build()
            : ResponseEntity.ok().body(found);
    }

    @Override
    public ResponseEntity<AccountOut> create(@RequestBody AccountIn in) {
        AccountOut a = AccountParser.to(accountService.create(AccountParser.to(in)));
        return ResponseEntity.created(
            ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(a.id())
                .toUri()
        ).body(a);
    }

    @Override
    public ResponseEntity<AccountOut> login(AccountIn in) {
        final Account a = accountService.findByEmailAndPassword(
            in.email(),
            in.password()
        );
        return a == null
            ? ResponseEntity.notFound().build()
            : ResponseEntity.ok().body(AccountParser.to(a));
    }

    @Override
    public ResponseEntity<AccountOut> whoAmI(String accountId) {
        return findById(accountId);
    }
    
}
