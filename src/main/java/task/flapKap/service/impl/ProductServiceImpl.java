package task.flapKap.service.impl;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import task.flapKap.dto.ProductDto;
import task.flapKap.exception.ApiError;
import task.flapKap.model.Product;
import task.flapKap.model.User;
import task.flapKap.repository.ProductRepository;
import task.flapKap.service.ProductService;
import task.flapKap.service.UserService;

import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final ModelMapper modelMapper;
    private final UserService userService;

    public ProductServiceImpl(ProductRepository productRepository, ModelMapper modelMapper, UserService userService) {
        this.productRepository = productRepository;
        this.modelMapper = modelMapper;
        this.userService = userService;
    }

    @Override
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    @Override
    public String createProduct(Product product, User user) {
        Product existingProduct = productRepository.findByProductName(product.getProductName());
        if(existingProduct != null) {
            throw ApiError.badRequest("There is a product added before with the same name!");
        }
        product.setUser(user);
        productRepository.save(product);
        return "Product created successfully";
    }

    @Override
    public ProductDto getProduct(Long productId) {
        Product product = productRepository.findById(productId).orElseThrow(() -> ApiError.notFound("Product not found!"));
        ProductDto productDto = modelMapper.map(product, ProductDto.class);
        productDto.setSellerId(product.getUser().getId());
        return productDto;
    }

    @Override
    public ProductDto updateProduct(Long productId, ProductDto updatedProduct) {
        Product existingProduct = productRepository.findById(productId).orElseThrow(() -> ApiError.notFound("Product not found!"));
        existingProduct.setAmountAvailable(updatedProduct.getAmountAvailable());
        existingProduct.setCost(updatedProduct.getCost());
        existingProduct.setProductName(updatedProduct.getProductName());
        productRepository.save(existingProduct);
        ProductDto productDto = modelMapper.map(existingProduct, ProductDto.class);
        return productDto;
    }

    @Override
    public String deleteProduct(Long productId) {
        productRepository.findById(productId).orElseThrow(() -> ApiError.notFound("Product not found!"));
        productRepository.deleteById(productId);
        return "Product deleted Successfully";
    }

    @Override
    public String deleteProductsOfUser(Long userId) {
        User existingUser = userService.getById(userId);
        productRepository.deleteAllByUser(existingUser);
        return "products of this user deleted";
    }

    @Override
    public Product getById(Long productId) {
        Product product = productRepository.findById(productId).orElseThrow(() -> ApiError.notFound("Product not found!"));
        return product;
    }

    @Override
    public Product save(Product product) {
        return productRepository.save(product);
    }
}
