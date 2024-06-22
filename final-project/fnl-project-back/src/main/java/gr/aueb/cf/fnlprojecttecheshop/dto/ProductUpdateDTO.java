package gr.aueb.cf.fnlprojecttecheshop.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ProductUpdateDTO extends BaseDTO {
    private String productName;
    private String shortDesc;


}
