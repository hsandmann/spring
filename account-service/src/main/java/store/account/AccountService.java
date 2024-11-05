package store.account;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.Date;
import java.util.List;
import java.util.stream.StreamSupport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AccountService {

    @Autowired
    private AccountRepository accountRepository;

    public Account create(Account account) {
        final String pass = account.password().trim();
        if (pass.length() < 4) throw new RuntimeException("Senha curta");        
        account.sha256(calcHash(pass));
        account.creation(new Date());
        return accountRepository.save(new AccountModel(account)).to();
    }
    
    public List<Account> findAll() {
        return StreamSupport
            .stream(accountRepository.findAll().spliterator(), false)
            .map(AccountModel::to)
            .toList();
    }

    public Account findById(String id) {
        final AccountModel found = accountRepository.findById(id).orElse(null);
        return found == null ? null : found.to();
    }

    private String calcHash(String password) {
        try {
            MessageDigest digester = MessageDigest.getInstance("SHA-256");
            byte[] hash = digester.digest(password.getBytes(StandardCharsets.UTF_8));
            // converte para base64
            String encoded = Base64.getEncoder().encodeToString(hash);
            return encoded;
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }

}
