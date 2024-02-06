package task.flapKap.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty
    @Column(nullable = false)
    private int amountAvailable;

    @NotEmpty
    @Column(nullable = false)
    private Long cost;

    @NotEmpty
    @Column(nullable = false)
    private String productName;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
    @JoinColumn(name = "sellerId")
    private User user;

    public Product(int amountAvailable, Long cost, String productName , User user) {
        this.amountAvailable = amountAvailable;
        this.cost = cost;
        this.productName = productName;
        this.user = user;
    }
}
