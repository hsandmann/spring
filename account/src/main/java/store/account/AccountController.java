package store.account;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

public interface AccountController {
    
    @GetMapping("/account")
    public ResponseEntity<List<AccountOut>> findAll();

    @GetMapping("/account/{idAccount}")
    public ResponseEntity<AccountOut> findById(
        @PathVariable String idAccount
    );

    @PostMapping("/account")
    public ResponseEntity<Void> create(@RequestBody AccountIn in);

}
