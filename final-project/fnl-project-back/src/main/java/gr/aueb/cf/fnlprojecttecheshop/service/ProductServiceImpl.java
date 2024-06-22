package gr.aueb.cf.fnlprojecttecheshop.service;

import gr.aueb.cf.fnlprojecttecheshop.dto.ProductInsertDTO;
import gr.aueb.cf.fnlprojecttecheshop.dto.ProductUpdateDTO;
import gr.aueb.cf.fnlprojecttecheshop.mapper.ProductMapper;
import gr.aueb.cf.fnlprojecttecheshop.model.Product;
import gr.aueb.cf.fnlprojecttecheshop.repository.ProductRepository;
import gr.aueb.cf.fnlprojecttecheshop.service.exceptions.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class ProductServiceImpl implements IProductService{
    private final ProductRepository productRepository;
    @Transactional
    @Override
    public Product insertProduct(ProductInsertDTO dto) throws Exception {
        Product product = null;
        try {
            product = productRepository.save(ProductMapper.mapToProduct(dto));
            if (product.getId() == null) {
                throw new Exception("Insert Error");
            }
            log.info("Insert success for product with id: " + product.getId());
        } catch (Exception e) {
            log.error(e.getMessage());
            throw e;
        }
        return product;
    }

    @Override
    public List<Product> getProductByProductName(String productName) throws EntityNotFoundException {
        List<Product> products = new ArrayList<>();

        try {
            products = productRepository.findByProductNameContainingIgnoreCase(productName);
            if (products.isEmpty()) {
                throw new EntityNotFoundException(Product.class, 0L);
            }
            log.info("Products with product name containing " + productName + " were found.");
        } catch (EntityNotFoundException e) {
            log.error(e.getMessage());
            throw e;
        }

        return products;
    }

    @Override
    public Product getProductById(Long id) throws EntityNotFoundException {
        Product product = null;
        try {
            product = productRepository.getReferenceById(id);
            if (product == null) {
                throw new EntityNotFoundException(Product.class, id);
            }
            log.info("Product with id: " + id + " was found.");
        } catch (EntityNotFoundException e) {
            log.error(e.getMessage());
            throw e;
        }

        return product;
    }

    @Transactional
    @Override
    public Product updateProduct(ProductUpdateDTO dto) throws EntityNotFoundException {
        Product product;
        Product updatedProduct;

        try {
            product = productRepository.findProductById(dto.getId());
            if (product == null) throw new EntityNotFoundException(Product.class, dto.getId());
            updatedProduct = productRepository.save(ProductMapper.mapToProduct(dto));
            log.info("Product with id: " + updatedProduct.getId() + " updated.");
        } catch (EntityNotFoundException e) {
            log.error(e.getMessage());
            throw e;
        }
        return updatedProduct;
    }

    @Transactional
    @Override
    public Product deleteProduct(Long id) throws EntityNotFoundException {
        Product product = null;

        try {
            product = productRepository.findProductById(id);
            if (product == null) throw new EntityNotFoundException(Product.class, id);
            productRepository.deleteById(id);
        } catch (EntityNotFoundException e) {
            log.error(e.getMessage());
            throw e;
        }
        return product;
    }
}
