package br.com.controlevendas.model;

import br.com.controlevendas.dto.OrderDTO;
import com.fasterxml.jackson.annotation.JsonBackReference;
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
    @JoinColumn(name = "user_id", referencedColumnName = "idUser")
//    @JsonBackReference
    private User user;

    //    tipos primitivos são mais eficientes em desempenho(nao recebe null), classes wrapper sao tratadas como objeto(recebe null)
    private double quantity;

    private BigDecimal price;

    @JsonFormat(pattern = "dd/MM/yyyy HH:mm:ss")
    private LocalDateTime orderDate = LocalDateTime.now();

    public Order(OrderDTO orderDTO) {
        quantity = orderDTO.getQuantity();
        price = orderDTO.getPrice();
//        user = new User(orderDTO.getUserOrderDTO());
    }

    public Order(double quantity, BigDecimal price) {
        this.quantity = quantity;
        this.price = price;
    }

//    public Order(User user, double quantity, BigDecimal price) {
//        this.user = user;
//        this.quantity = quantity;
//        this.price = price;
//    }

}
