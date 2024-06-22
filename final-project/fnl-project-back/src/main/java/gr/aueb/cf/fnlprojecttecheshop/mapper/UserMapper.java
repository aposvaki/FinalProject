package gr.aueb.cf.fnlprojecttecheshop.mapper;

import gr.aueb.cf.fnlprojecttecheshop.dto.UserInsertDTO;
import gr.aueb.cf.fnlprojecttecheshop.dto.UserReadOnlyDTO;
import gr.aueb.cf.fnlprojecttecheshop.dto.UserUpdateDTO;
import gr.aueb.cf.fnlprojecttecheshop.model.User;

public class UserMapper {
    private UserMapper(){}

    public static User mapToUser(UserInsertDTO dto) {
        return new User(null, dto.getUsername(), dto.getPassword(), dto.getRole());
    }

    public static User mapToUser(UserUpdateDTO dto) {
        return new User(dto.getId(), dto.getUsername(), dto.getUsername(), dto.getRole());
    }

    public static UserReadOnlyDTO mapToReadOnlyDTO(User user) {
        return new UserReadOnlyDTO(user.getId(), user.getUsername(), user.getPassword(), user.getRole());
    }
}
