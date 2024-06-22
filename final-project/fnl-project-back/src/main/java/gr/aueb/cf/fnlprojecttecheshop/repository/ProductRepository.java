package gr.aueb.cf.fnlprojecttecheshop.repository;

import gr.aueb.cf.fnlprojecttecheshop.model.Product;
import org.springframework.data.domain.Example;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {
    List<Product> findByProductNameContainingIgnoreCase(String name);
    Product findProductById(Long id);
}
