package unillanos.boosicsbpmn.exceptions;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class CamundaSpringBootDomainException extends Exception {

    public CamundaSpringBootDomainException(String message) {
        super(message);
        log.error(message);
    }

    public CamundaSpringBootDomainException(String message, Exception exception) {
        super(message, exception);
        log.error(message, exception);
    }

}
