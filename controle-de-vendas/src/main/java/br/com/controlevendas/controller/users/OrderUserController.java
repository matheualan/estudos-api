package br.com.controlevendas.controller.users;

import br.com.controlevendas.dto.OrderDTO;
import br.com.controlevendas.dto.UserOrderDTO;
import br.com.controlevendas.service.users.OrderService;
import br.com.controlevendas.service.users.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

//classe controller para registrar pedidos dos usuarios
@RestController
@RequestMapping(path = "/orders")
public class OrderUserController {

    @Autowired
    private OrderService orderService;

    @Autowired
    private UserService userService;

    @PostMapping(path = "/create")
    public ResponseEntity<OrderDTO> createOrder(@RequestBody OrderDTO orderDTO) {
        return ResponseEntity.status(HttpStatus.CREATED).body(orderService.createOrder(orderDTO));
    }

    @PostMapping(path = "/create/{cpf}")
    public ResponseEntity<OrderDTO> createOrderByUserExisting(@PathVariable String cpf, double quantity, BigDecimal price) {
        return ResponseEntity.status(HttpStatus.CREATED).body(orderService.createOrderByUserExisting(cpf, quantity, price));
    }

    @GetMapping(path = "/listOrders")
    public ResponseEntity<List<OrderDTO>> listOrders() {
        return ResponseEntity.status(HttpStatus.OK).body(orderService.listAllOrders());
    }

    @GetMapping(path = "/listarUsuariosEPedidos")
    public ResponseEntity<List<UserOrderDTO>> listarUsuariosEPedidos() {
        return ResponseEntity.status(HttpStatus.OK).body(userService.listarUsuariosEPedidos());
    }

}
