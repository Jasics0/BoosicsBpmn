package unillanos.boosicsbpmn.delegations;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.camunda.bpm.engine.delegate.BpmnError;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.stereotype.Component;
import unillanos.boosicsbpmn.dto.UserDTO;
import unillanos.boosicsbpmn.interfaces.IEmailService;
import unillanos.boosicsbpmn.interfaces.IUserService;

@Slf4j
@Component
@RequiredArgsConstructor
public class UserDelegate implements JavaDelegate {

    private final IUserService userService;
    private final IEmailService emailService;

    @Override
    public void execute(DelegateExecution execution) throws Exception {

        String currentActivityId = execution.getCurrentActivityId();
        log.info("currentActivityId: " + currentActivityId);

        switch (currentActivityId) {

            case "createAccountServiceTask" -> {
                UserDTO userDTO = new UserDTO();
                userDTO.setUsername(execution.getProcessInstance().getVariables().get("username").toString());
                userDTO.setName(execution.getProcessInstance().getVariables().get("name").toString());
                userDTO.setEmail(execution.getProcessInstance().getVariables().get("email").toString());
                userDTO.setPassword(execution.getProcessInstance().getVariables().get("password").toString());
                userDTO.setActiveAccount(false);
                execution.setVariable("username", userDTO.getUsername());
                execution.setVariable("activeAccount", false);
                userService.save(userDTO);
            }

            case "validateCredentialsServiceTask" -> {
                boolean correctCredentials = userService.validateCredentials(
                        execution.getProcessInstance().getVariables().get("username").toString(),
                        execution.getProcessInstance().getVariables().get("password").toString());
                execution.setVariable("correctCredentials", correctCredentials);
            }

            case "validateAccountActivationServiceTask" -> {
                boolean activeAccount = userService.validateAccountActivation(
                        execution.getProcessInstance().getVariables().get("username").toString());
                execution.setVariable("activeAccount", activeAccount);
            }

            case "sentValidationEmailServiceTask" -> {
                String username = execution.getProcessInstance().getVariables().get("username").toString();
                String email = userService.getUserByUsername(username).getEmail();
                int code = userService.getCode(email);
                emailService.sendEmailConfirmation(email, code);
            }

            default -> throw new BpmnError("error_404");
        }
    }

}