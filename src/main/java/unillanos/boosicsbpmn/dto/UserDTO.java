package unillanos.boosicsbpmn.dto;

import lombok.Data;

@Data
public class UserDTO {

    private long id;
    private String username;
    private String name;
    private String email;
    private String password;
    private boolean activeAccount;

}
