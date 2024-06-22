package gr.aueb.cf.fnlprojecttecheshop.repository;

import gr.aueb.cf.fnlprojecttecheshop.model.Role;
import gr.aueb.cf.fnlprojecttecheshop.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByRole(Role role);
    Optional<User> findByUsername(String username);
    Long countByRole(Role role);
}
