package task.flapKap.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import task.flapKap.dto.ProductDto;
import task.flapKap.dto.UserDto;
import task.flapKap.model.Product;
import task.flapKap.model.User;
import task.flapKap.service.ProductService;
import task.flapKap.util.AuthenticationUser;

import java.util.List;

@RestController
@RequestMapping("/api/product")
public class ProductController {


    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/")
    public ResponseEntity<List<Product>> getAllProducts() {
        return ResponseEntity.ok(productService.getAllProducts());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductDto> getProduct(@PathVariable("id") Long productId) {
        return ResponseEntity.ok(productService.getProduct(productId));
    }

    @PostMapping("/")
    @PreAuthorize("hasRole('ROLE_SELLER')")
    public ResponseEntity<String> createProduct(@RequestBody Product product, Authentication authentication) {
        User user = AuthenticationUser.get(authentication);
        return ResponseEntity.ok(productService.createProduct(product, user));
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ROLE_SELLER')")
    public ResponseEntity<ProductDto> updateProduct(@PathVariable("id") Long userId,
                                           @RequestBody ProductDto updatedProduct) {
        return ResponseEntity.ok(productService.updateProduct(userId, updatedProduct));
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ROLE_SELLER')")
    public ResponseEntity<String> deleteProduct(@PathVariable("id") Long productId) {
        return ResponseEntity.ok(productService.deleteProduct(productId));
    }

}
