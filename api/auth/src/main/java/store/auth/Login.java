package store.auth;

import lombok.Builder;
import lombok.experimental.Accessors;

@Builder @Accessors(fluent = true)
public record Login (
    String email,
    String password
) {
    
}
