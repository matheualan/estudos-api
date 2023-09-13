package br.com.controlevendas.model;

import br.com.controlevendas.dto.OrderDTO;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "tb_orders")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idOrder;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "id_user", referencedColumnName = "idUser")
    private User user;

//    tipos primitivos são mais eficientes em desempenho(nao recebe null), classes wrapper sao tratadas como objeto(recebe null)
    private double quantity;

    private BigDecimal price;

    @JsonFormat(pattern = "dd/MM/yyyy HH:mm:ss")
    private LocalDateTime orderDate = LocalDateTime.now();

    public Order(OrderDTO orderDTO) {
        quantity = orderDTO.getQuantity();
        price = orderDTO.getPrice();
//        verificar esse new User() pq vai da errado kkk
        user = new User(orderDTO.getUserOrderDTO());
    }


}
