package task.flapKap.dto;

import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductDto {
    @NotEmpty
    private int amountAvailable;

    @NotEmpty
    private Long cost;

    @NotEmpty
    private String productName;

    @NotEmpty
    private Long sellerId;

}
