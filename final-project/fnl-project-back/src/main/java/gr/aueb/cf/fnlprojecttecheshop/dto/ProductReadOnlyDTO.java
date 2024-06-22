package gr.aueb.cf.fnlprojecttecheshop.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class ProductReadOnlyDTO extends BaseDTO{

    private String productName;
    private String shortDesc;
//    private String category;

    public ProductReadOnlyDTO(Long id, String productName, String shortDesc
//            , String category
    ) {
        setId(id);
        this.productName = productName;
        this.shortDesc = shortDesc;
//        this.category = category;
    }

}
