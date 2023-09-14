package br.com.controlevendas.model;

import br.com.controlevendas.dto.AddressDTO;
import br.com.controlevendas.dto.OrderDTO;
import br.com.controlevendas.dto.UserDTO;
import br.com.controlevendas.dto.UserOrderDTO;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "tb_users")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "idUser")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idUser;

    @JsonFormat(pattern = "dd/MM/yyyy HH:mm:ss")
    private LocalDateTime createAt = LocalDateTime.now();

    @Column(nullable = false)
    private String firstName;

    private String lastName;

    @Column(unique = true)
    private String cpf;

    @JsonFormat(pattern = "dd/MM/yyyy")
    private Date birthDate;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Address> addresses = new ArrayList<>();

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Order> orders = new ArrayList<>();

    public User(UserDTO userDTO) {
        firstName = userDTO.getFirstName();
        lastName = userDTO.getLastName();
        cpf = userDTO.getCpf();
        birthDate = userDTO.getBirthDate();
        for (AddressDTO addressDTO : userDTO.getAddressesDTO()) {
            var address = new Address(addressDTO);
            address.setUser(this);
            addresses.add(address);
        }
    }

    public User(UserOrderDTO userOrderDTO) {
        firstName = userOrderDTO.getFirstName();
        lastName = userOrderDTO.getLastName();
//        cpf = userOrderDTO.getCpf();
    }

}
