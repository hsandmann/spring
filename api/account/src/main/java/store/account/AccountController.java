package store.account;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "account", url = "http://account:8080")
public interface AccountController {
    
    @GetMapping("/account")
    public ResponseEntity<List<AccountOut>> findAll();

    @GetMapping("/account/{idAccount}")
    public ResponseEntity<AccountOut> findById(
        @PathVariable String idAccount
    );

    @PostMapping("/account/login")
    public ResponseEntity<AccountOut> login(
        @RequestBody AccountIn in
    );

    @PostMapping("/account")
    public ResponseEntity<AccountOut> create(@RequestBody AccountIn in);

}
