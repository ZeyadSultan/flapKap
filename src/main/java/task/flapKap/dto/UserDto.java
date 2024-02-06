package task.flapKap.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {
    @NotEmpty
    private String username;

    @NotEmpty
    private String password;

    @NotEmpty
    private Long deposit;

    @NotEmpty
    private String role;
}
