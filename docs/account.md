1.

Primeiro passo é criar o projeto no Spring Boot, para isso é aconselhável utilizar o gerador de projetos que pode ser acessado através do link [Spring Initializr](https://start.spring.io/){:target='_blank'}.



``` mermaid
classDiagram
    namespace account {
        class AccountController {
            +create(AccountIn accountIn): AccountOut
            +delete(String id): void
            +findAll(): List<AccountOut>
            +findById(String id): AccountOut
        }
        class AccountIn {
            -String name
            -String email
            -String password
        }
        class AccountOut {
            -String id
            -String name
            -String email
        }
    }
    namespace service {
        class AccountResource {
            +create(AccountIn accountIn): AccountOut
            +delete(String id): void
            +findAll(): List<AccountOut>
            +findById(String id): AccountOut
        }
        class AccountService {
            +create(AccountIn accountIn): AccountOut
            +delete(String id): void
            +findAll(): List<AccountOut>
            +findById(String id): AccountOut
        }
        class AccountRepository {
            +create(AccountIn accountIn): AccountOut
            +delete(String id): void
            +findAll(): List<AccountOut>
            +findById(String id): AccountOut
        }
        class Account {
            -String id
            -String name
            -String email
            -String password
        }
        class AccountModel {
            +create(AccountIn accountIn): AccountOut
            +delete(String id): void
            +findAll(): List<AccountOut>
            +findById(String id): AccountOut
        }
    }
    <<Interface>> AccountController
    AccountController ..> AccountIn
    AccountController ..> AccountOut

    <<Interface>> AccountRepository
    AccountController <|-- AccountResource
    AccountResource *-- AccountService
    AccountService *-- AccountRepository
    AccountService ..> Account
    AccountService ..> AccountModel
    AccountRepository ..> AccountModel
```

??? note "Account"


    ``` tree
    account
        src
            main
                java
                    store
                        account
                            AccountController.java
                            AccountIn.java
                            AccountOut.java
        pom.xml
    ```


    === "AccountController"

        ``` { .java title='AccountController.java' .copy .select linenums='1' }
        --8<-- "https://raw.githubusercontent.com/hsandmann/spring/refs/heads/main/account/src/main/java/store/account/AccountIn.java"
        ```

    === "AccountIn"

        ``` { .java title='AccountIn.java' .copy .select linenums='1' }
        --8<-- "https://raw.githubusercontent.com/hsandmann/spring/refs/heads/main/account/src/main/java/store/account/AccountIn.java"
        ```

    === "AccountOut"

        ``` { .java title='AccountOut.java' .copy .select linenums='1' }
        --8<-- "https://raw.githubusercontent.com/hsandmann/spring/refs/heads/main/account/src/main/java/store/account/AccountIn.java"
        ```

??? note "Service"

    ``` tree
    account-service
        src
            main
                java
                    store
                        account
                            AccountApplication.java
                            AccountResource.java
                            AccountService.java
                            AccountRepository.java
                            Account.java
                            AccountModel.java
                            AccountParser.java
                resources
                    application.yaml
        pom.xml
        Dockerfile
    ```

    <!-- === "pom"

        ``` { .yaml title='pom.yaml' .copy .select linenums="1" }
        --8<-- "https://raw.githubusercontent.com/hsandmann/spring/refs/heads/main/account-service/pom.xml"
        ```

    === "application"

        ``` { .yaml title='application.yaml' .copy .select linenums="1" }
        --8<-- "https://raw.githubusercontent.com/hsandmann/spring/refs/heads/main/account-service/src/main/resources/application.yaml"
        ```

    === "AccountApplication"

        ``` { .java title='AccountApplication.java' .copy .select linenums='1' }
        --8<-- "https://raw.githubusercontent.com/hsandmann/spring/refs/heads/main/account-service/src/main/java/store/account/AccountApplication.java"
        ```

    === "AccountResource"

        ``` { .java title='AccountResource.java' .copy .select linenums='1' }
        --8<-- "https://raw.githubusercontent.com/hsandmann/spring/refs/heads/main/account-service/src/main/java/store/account/AccountResource.java"
        ``` -->



<!-- ![type:video](https://odysee.com/$/embed/@RobBraxmanTech:6/fingerprint-vs-vpn) -->

