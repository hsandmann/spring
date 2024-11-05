package store.account;

import org.springframework.data.repository.CrudRepository;

/* 
 * See documentation at: https://www.baeldung.com/spring-data-jpa-query
 */
public interface AccountRepository extends CrudRepository<AccountModel, String> {

    // public AccountModel findByEmail(String email);

    // @Query("Select AccountModel am where am.email = :email")
    // public AccountModel findFirstByEmailAndSha256AndNameIsNotNull(String email)
    
}
