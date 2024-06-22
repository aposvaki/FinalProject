package gr.aueb.cf.fnlprojecttecheshop.dto;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CategoryInsertDTO extends BaseDTO{
    @NotNull
    private String category;
}
