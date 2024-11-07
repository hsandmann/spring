package store.account;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

/* 
 * See documentation at: https://www.baeldung.com/spring-data-jpa-query
 */
public interface AccountRepository extends CrudRepository<AccountModel, String> {

    public Optional<AccountModel> findByEmail(String email);
    public Optional<AccountModel> findByEmailAndSha256(String email, String sha256);

    // @Query("Select AccountModel am where am.email = :email")
    // public AccountModel findFirstByEmailAndSha256AndNameIsNotNull(String email)
    
}
