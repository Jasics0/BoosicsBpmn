package unillanos.boosicsbpmn.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import unillanos.boosicsbpmn.exceptions.CamundaSpringBootDomainException;
import unillanos.boosicsbpmn.interfaces.IUserService;

@RestController
@RequestMapping("/activeAccount")
@RequiredArgsConstructor
public class ActiveAccountController {

    private final IUserService userService;

    @RequestMapping("/{code}")
    public String activeAccount(@PathVariable("code") int code) throws CamundaSpringBootDomainException {
        return userService.activateAccount(code);
    }

}
