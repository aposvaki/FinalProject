package gr.aueb.cf.fnlprojecttecheshop.service;

import gr.aueb.cf.fnlprojecttecheshop.dto.ProductInsertDTO;
import gr.aueb.cf.fnlprojecttecheshop.dto.ProductReadOnlyDTO;
import gr.aueb.cf.fnlprojecttecheshop.dto.ProductUpdateDTO;
import gr.aueb.cf.fnlprojecttecheshop.model.Product;
import gr.aueb.cf.fnlprojecttecheshop.service.exceptions.EntityNotFoundException;

import java.util.List;

public interface IProductService {
    Product insertProduct(ProductInsertDTO dto) throws Exception;
    List<Product> getProductByProductName(String productName) throws EntityNotFoundException;
    Product getProductById(Long id) throws EntityNotFoundException;
    Product updateProduct(ProductUpdateDTO dto) throws EntityNotFoundException;
    Product deleteProduct(Long id) throws EntityNotFoundException;
}
