package unillanos.boosicsbpmn.interfaces;

import org.springframework.stereotype.Service;
import unillanos.boosicsbpmn.dto.UserDTO;
import unillanos.boosicsbpmn.exceptions.CamundaSpringBootDomainException;

@Service
public interface IUserService {

    void save(UserDTO userDTO) throws CamundaSpringBootDomainException;

    boolean validateCredentials(String username, String password) throws CamundaSpringBootDomainException;

    boolean validateAccountActivation(String username) throws CamundaSpringBootDomainException;

    int getCode(String email) throws CamundaSpringBootDomainException;

    String activateAccount(int code) throws CamundaSpringBootDomainException;

    UserDTO getUserByUsername(String username) throws CamundaSpringBootDomainException;
}
