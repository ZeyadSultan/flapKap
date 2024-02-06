package task.flapKap.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import task.flapKap.model.Product;

import java.util.Map;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PurchaseResponse {
    private long totalSpent;
    private ProductDto productDto;
    private int quantity;
    private Map<String, Long> change;
}