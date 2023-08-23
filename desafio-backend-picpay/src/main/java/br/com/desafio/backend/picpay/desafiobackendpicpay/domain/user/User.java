package br.com.desafio.backend.picpay.desafiobackendpicpay.domain.user;

import br.com.desafio.backend.picpay.desafiobackendpicpay.dto.record.UserDTO;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "tb_users")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "idUser")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idUser;

    private String firstName;

    private String lastName;

    @Column(unique = true)
    private String document;

    @Column(unique = true)
    private String email;

    @Column
    private String password;

    private BigDecimal balance;

    @Enumerated(value = EnumType.STRING)
    private TypeUser userType;

    @JsonFormat(pattern = "dd/MM/yyyy HH:mm:ss")
    private final LocalDateTime timestamp = LocalDateTime.now();

    public User(UserDTO userDTO) {
        firstName = userDTO.firstName();
        lastName = userDTO.lastName();
        document = userDTO.document();
        email = userDTO.email();
        password = userDTO.password();
        balance = userDTO.balance();
        userType = userDTO.userType();
    }

}
