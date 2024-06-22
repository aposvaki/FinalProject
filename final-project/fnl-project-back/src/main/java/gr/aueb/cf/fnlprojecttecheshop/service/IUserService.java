package gr.aueb.cf.fnlprojecttecheshop.service;

import gr.aueb.cf.fnlprojecttecheshop.dto.UserInsertDTO;
import gr.aueb.cf.fnlprojecttecheshop.model.User;
import gr.aueb.cf.fnlprojecttecheshop.service.exceptions.UserAlreadyExistsException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public interface IUserService {
    User insertUser(UserInsertDTO dto) throws UserAlreadyExistsException;
    User getUserByUsername(String username);

}
