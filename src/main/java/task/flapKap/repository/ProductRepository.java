package task.flapKap.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import task.flapKap.model.Product;
import task.flapKap.model.User;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    public void deleteAllByUser(User user);
    public Product findByProductName(String productName);
}
