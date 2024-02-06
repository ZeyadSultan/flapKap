package task.flapKap.service;

import task.flapKap.dto.ProductDto;
import task.flapKap.model.Product;
import task.flapKap.model.User;

import java.util.List;

public interface ProductService {
    public List<Product> getAllProducts();
    public String createProduct(Product product, User user);
    public ProductDto getProduct(Long productId);
    public ProductDto updateProduct(Long productId, ProductDto updatedProduct);
    public String deleteProduct(Long productId);
    public String deleteProductsOfUser(Long userId);
    public Product getById(Long productId);

    public Product save(Product product);
}
