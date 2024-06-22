package gr.aueb.cf.fnlprojecttecheshop.dto;


//import gr.aueb.cf.fnlprojecttecheshop.model.Category;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProductInsertDTO{
    @NotNull
    private String productName;

    private String shortDesc;

//    @NotNull
//    private Category category;

}
