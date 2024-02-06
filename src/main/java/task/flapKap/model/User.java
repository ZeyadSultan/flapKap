package task.flapKap.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "`user`")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty
    @Column(nullable = false)
    private String username;

    @NotEmpty
    private String password;

    @NotEmpty
    @Column(nullable = false)
    private Long deposit;


    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(nullable = false)
    private Role role;

    public User(String username, String password, Long deposit, Role role) {
        this.username = username;
        this.password = password;
        this.deposit = deposit;
        this.role = role;
    }
}