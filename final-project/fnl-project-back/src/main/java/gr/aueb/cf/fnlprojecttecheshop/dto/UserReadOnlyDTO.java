package gr.aueb.cf.fnlprojecttecheshop.dto;

import gr.aueb.cf.fnlprojecttecheshop.model.Role;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class UserReadOnlyDTO extends BaseDTO{

    @NotNull
    private String username;
    private String password;
    private Role role;

    public UserReadOnlyDTO(Long id, String username, String password, Role role) {
        setId(id);
        this.username = username;
        this.password = password;
        this.role = role;
    }

}
