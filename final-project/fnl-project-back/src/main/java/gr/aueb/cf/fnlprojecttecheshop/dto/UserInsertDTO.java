package gr.aueb.cf.fnlprojecttecheshop.dto;

import gr.aueb.cf.fnlprojecttecheshop.model.Role;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserInsertDTO extends BaseDTO{

    @NotNull
    private String username;
    @NotNull
    private String password;
    @NotNull
    private Role role;



}
