package unillanos.boosicsbpmn.exceptions;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class CamundaSpringBootRuntimeException extends RuntimeException {

    public CamundaSpringBootRuntimeException(String message) {
        super(message);
        log.error(message);
    }

    public CamundaSpringBootRuntimeException(String message, Exception exception) {
        super(message, exception);
        log.error(message, exception);
    }

}
