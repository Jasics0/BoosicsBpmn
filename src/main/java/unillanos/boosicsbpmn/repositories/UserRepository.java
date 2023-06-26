package unillanos.boosicsbpmn.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import unillanos.boosicsbpmn.model.User;

public interface UserRepository extends JpaRepository<User, Long> {

    User findFirstByUsername(String username);

    User findFirstByUsernameAndPassword(String username, String password);

    User findFirstByEmail(String email);

    User findUserByConfirmationCode(int code);

}
