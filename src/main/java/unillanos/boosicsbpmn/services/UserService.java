package unillanos.boosicsbpmn.services;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import unillanos.boosicsbpmn.dto.UserDTO;
import unillanos.boosicsbpmn.exceptions.CamundaSpringBootDomainException;
import unillanos.boosicsbpmn.interfaces.IUserService;
import unillanos.boosicsbpmn.model.User;
import unillanos.boosicsbpmn.repositories.UserRepository;

import javax.transaction.Transactional;
import java.security.SecureRandom;
import java.util.Random;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserService implements IUserService {

    private final UserRepository userRepository;

    @Override
    @Transactional
    public void save(UserDTO userDTO) throws CamundaSpringBootDomainException {
        log.info("Entrando al service de crear Usuario.");
        User user = new User();
        Random generatorCodes = new SecureRandom();
        try {
            user.setUsername(userDTO.getUsername());
            user.setName(userDTO.getName());
            user.setEmail(userDTO.getEmail());
            user.setPassword(userDTO.getPassword());
            user.setActiveAccount(userDTO.isActiveAccount());
            user.setConfirmationCode(generatorCodes.nextInt(9998) + 1);
            User createdUser = userRepository.save(user);
            log.info("El usuario {} ha sido creado de manera exitosa.", createdUser);
        } catch (Exception e) {
            throw new CamundaSpringBootDomainException("Error al momento de crear el usuario.", e);
        }
    }

    @Override
    public boolean validateCredentials(String username, String password) throws CamundaSpringBootDomainException {
        User user;
        try {
            user = userRepository.findFirstByUsernameAndPassword(username, password);
            if (user != null) {
                log.info("Las credenciales del usuario son correctas.");
                return true;
            }
        } catch (Exception e) {
            throw new CamundaSpringBootDomainException("Error al momento de validar las credenciales del usuario.", e);
        }
        return false;
    }

    @Override
    public boolean validateAccountActivation(String username) throws CamundaSpringBootDomainException {
        User user;
        try {
            user = userRepository.findFirstByUsername(username);
            if (user != null && user.isActiveAccount()) {
                log.info("La cuenta del usuario está activa.");
                return true;
            }
        } catch (Exception e) {
            throw new CamundaSpringBootDomainException("Error al momento de validar la validez de la activación de la cuenta del usuario.", e);
        }
        return false;
    }

    @Override
    public int getCode(String email) throws CamundaSpringBootDomainException {
        try {
            return userRepository.findFirstByEmail(email).getConfirmationCode();
        } catch (Exception e) {
            throw new CamundaSpringBootDomainException("Error al momento de enviar el código de validación del usuario.", e);
        }
    }

    @Override
    @Transactional
    public String activateAccount(int code) throws CamundaSpringBootDomainException {
        try {
            User user = userRepository.findUserByConfirmationCode(code);
            user.setActiveAccount(true);
            user.setConfirmationCode(0);
            userRepository.save(user);
            return "Account activated!";
        } catch (Exception e) {
            throw new CamundaSpringBootDomainException("Error al momento de enviar el correo de validación al usuario.", e);
        }
    }

    @Override
    public UserDTO getUserByUsername(String username) throws CamundaSpringBootDomainException {
        UserDTO userDTO = new UserDTO();
        try {
            User user = userRepository.findFirstByUsername(username);
            if (user != null) {
                userDTO.setId(user.getId());
                userDTO.setName(user.getName());
                userDTO.setEmail(user.getEmail());
                userDTO.setPassword(user.getPassword());
                userDTO.setUsername(user.getUsername());
                userDTO.setActiveAccount(user.isActiveAccount());
                return userDTO;
            }
        } catch (Exception e) {
            throw new CamundaSpringBootDomainException("Error al momento de enviar el correo de validación al usuario.", e);
        }
        return userDTO;
    }

}
