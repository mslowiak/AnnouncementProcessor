package api.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class RegisterRequest {
    private final String username;
    private final String password;
    private final String name;
    private final String email;
}
