package gr.aueb.cf.fnlprojecttecheshop.dto;

import gr.aueb.cf.fnlprojecttecheshop.model.Role;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class UserUpdateDTO extends BaseDTO{
    private String username;
    private String password;
    private Role role;
}
