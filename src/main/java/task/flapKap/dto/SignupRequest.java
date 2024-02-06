package task.flapKap.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import task.flapKap.model.Role;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SignupRequest {
    @NotEmpty
    private String username;

    @NotEmpty
    private String password;

    @Email
    @NotEmpty
    private Long deposit;

    @NotEmpty
    private String role;

}
