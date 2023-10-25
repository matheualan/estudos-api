package br.com.controlevendas.service.users;

import br.com.controlevendas.dto.OrderDTO;
import br.com.controlevendas.dto.UserDTO;
import br.com.controlevendas.dto.UserOrderDTO;
import br.com.controlevendas.model.Order;
import br.com.controlevendas.model.User;
import br.com.controlevendas.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private UserService userService;

    public OrderDTO createOrder(OrderDTO orderDTO) {
        var order = new Order(orderDTO);
        orderRepository.save(order);
        Order orderById = orderRepository.findById(order.getIdOrder()).orElseThrow(() -> new RuntimeException("Not Found"));
        return new OrderDTO(orderById);
    }

    public OrderDTO createOrderByUserExisting(String cpf, double quantity, BigDecimal price) {
        User userByCpf = userService.encontreUsuarioPorCpf(cpf);

        Order order = new Order(userByCpf, quantity, price);
////        Order order = new Order(quantity, price);
////        order.setUser(userByCpf);

//        order.setTotalOrders(order.getTotalOrders() + 1);
//        order.setTotalPurchased(order.getTotalPurchased().add(price));

//        for (int i = 0; i < userByCpf.getOrders().size(); i++) {
//            userByCpf.getOrders().get(i).setTotalOrders(userByCpf.getOrders().get(i).getTotalOrders() + 1);
//            userByCpf.getOrders().get(i).setTotalPurchased(userByCpf.getOrders().get(i).getTotalPurchased().add(price));
//        }

//        for (Order o : order.getUser().getOrders()) {
//            o.setTotalOrders(o.getTotalOrders() + 1);
//            o.setTotalPurchased(o.getTotalPurchased().add(price));
//        }

        order.setTotalOrders(1);
        order.setTotalPurchased(price);

        for (Order o : userByCpf.getOrders()) {
            o.setTotalOrders(o.getTotalOrders() + 1);
            o.setTotalPurchased(o.getTotalPurchased().add(price));
        }

        orderRepository.save(order);

        return new OrderDTO(order);

////        UserDTO userByCpf = userService.findUserByCpf(cpf);
////        User user = new User(userByCpf);
////        UserOrderDTO userOrderDTO = new UserOrderDTO(user);
////        OrderDTO orderDTO = new OrderDTO(quantity, price, userOrderDTO);
////        Order order = new Order(orderDTO);
////        order.setUser(userByCpf);
////        orderRepository.save(order);
////        return orderDTO;
    }

    public List<OrderDTO> listAllOrders() {
        List<Order> listOfOrders = orderRepository.findAll();
        List<OrderDTO> listOrdersDTO = new ArrayList<>();

        for (Order order : listOfOrders) {
            var orderDTO = new OrderDTO(order);
            listOrdersDTO.add(orderDTO);
        }

        return listOrdersDTO;
    }

    public List<Order> listEntityOrder() {
        return orderRepository.findAll();
    }

}
