package store.account;

import java.util.List;
import java.util.stream.StreamSupport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AccountService {

    @Autowired
    private AccountRepository accountRepository;

    public Account create(Account account) {
        return accountRepository.save(new AccountModel(account)).to();
    }
    
    public List<Account> findAll() {
        return StreamSupport
            .stream(accountRepository.findAll().spliterator(), false)
            .map(AccountModel::to)
            .toList();
    }

}
