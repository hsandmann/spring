package store.account;

public class AccountParser {

    public static AccountOut to(Account a) {
        // if (a == null) { // isso eh um ternario
        //     return null;
        // } else {
        //     return AccountOut.builder()....
        // }
        return a == null ? null :
            AccountOut.builder()
                .id(a.id())
                .name(a.name())
                .email(a.email())
                .build();
    }

    public static Account to(AccountIn in) {
        return in == null ? null :
            Account.builder()
                .name(in.name())
                .email(in.email())
                .password(in.password())
                .build();
    }
    
}
