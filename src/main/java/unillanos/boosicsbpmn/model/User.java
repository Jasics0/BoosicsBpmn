package unillanos.boosicsbpmn.model;

import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@ToString
@RequiredArgsConstructor
@Entity
@Table(name = "users_app")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String username;
    private String name;
    private String email;
    private String password;
    @Column(name = "active_account",columnDefinition = "boolean default false")
    private boolean activeAccount;
    private int confirmationCode;

}
