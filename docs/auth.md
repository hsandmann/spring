Register
``` mermaid
sequenceDiagram
    actor Alice
    Alice->>+Auth: register
    Auth->>+Account: create(AccountIn)
    Account->>Account: findByEmail
    Account-->>-Auth: AccountOut
    Auth-->>-Alice: 201
```

Login
``` mermaid
sequenceDiagram
    actor Alice
    Alice->>+Auth: login(LoginIn)
    Auth->>+Account: login(AccountIn)
    Account->>Account: calcSha256
    Account->>Account: findByEmailAndSha256
    Account-->>-Auth: AccountOut
    Auth-->>Auth: generate JWT
    Auth-->>-Alice: 201 (LoginOut)
```
