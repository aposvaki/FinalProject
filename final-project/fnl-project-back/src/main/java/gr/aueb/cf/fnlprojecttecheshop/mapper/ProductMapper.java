package gr.aueb.cf.fnlprojecttecheshop.mapper;

import gr.aueb.cf.fnlprojecttecheshop.dto.ProductInsertDTO;
import gr.aueb.cf.fnlprojecttecheshop.dto.ProductReadOnlyDTO;
import gr.aueb.cf.fnlprojecttecheshop.dto.ProductUpdateDTO;
import gr.aueb.cf.fnlprojecttecheshop.model.Product;

public class ProductMapper {
    private ProductMapper(){}

    public static Product mapToProduct(ProductInsertDTO dto) {
        return new Product(null, dto.getProductName(), dto.getShortDesc()
//                , dto.getCategory()
        );
    }

    public static Product mapToProduct(ProductUpdateDTO dto) {
        return new Product(dto.getId(), dto.getProductName(), dto.getShortDesc());
    }

    public static ProductReadOnlyDTO mapToReadOnlyDTO(Product product) {
        return new ProductReadOnlyDTO(product.getId(), product.getProductName(), product.getShortDesc()
//                , product.getCategory().getCategory()
                );
    }


}
