package store.account;

import java.text.ParseException;
import java.text.SimpleDateFormat;

public class AccountParser {

    private static final SimpleDateFormat sdfDate = new SimpleDateFormat("yyyy-MM-dd");
    private static final SimpleDateFormat sdfDateTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");

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
                // como vou exibir a data
                .birthdate(sdfDate.format(a.birthdate()))
                .creation(sdfDateTime.format(a.creation()))
                .build();
    }

    public static Account to(AccountIn in) {
        try {
            return in == null ? null :
                Account.builder()
                    .name(in.name())
                    .email(in.email())
                    .password(in.password())
                    .birthdate(sdfDate.parse(in.birthdate()))
                    .build();
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }
    
}
