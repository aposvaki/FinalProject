package gr.aueb.cf.fnlprojecttecheshop.service;

import gr.aueb.cf.fnlprojecttecheshop.dto.UserInsertDTO;
import gr.aueb.cf.fnlprojecttecheshop.mapper.UserMapper;
import gr.aueb.cf.fnlprojecttecheshop.model.User;
import gr.aueb.cf.fnlprojecttecheshop.repository.UserRepository;
import gr.aueb.cf.fnlprojecttecheshop.service.exceptions.UserAlreadyExistsException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserServiceImpl implements IUserService{
    private final UserRepository userRepository;

    @Override
    public User getUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User with username " + username + " was not found"));
    }

    @Transactional
    @Override
    public User insertUser(UserInsertDTO dto) throws UserAlreadyExistsException {
        User user = null;

        try {
            user = userRepository.save(UserMapper.mapToUser(dto));
            if (user.getId() == null) {
                throw new Exception("Insert error");
            }
            log.info("Inser success for user with id: " + user.getId());
        } catch (UserAlreadyExistsException e) {
            log.error(e.getMessage());
            throw e;
        } catch (Exception e) {
            log.error(e.getMessage());
            throw new RuntimeException(e);
        }

        return user;
    }
}