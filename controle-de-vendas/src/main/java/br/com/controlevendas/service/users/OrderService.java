package br.com.controlevendas.service.users;

import br.com.controlevendas.dto.OrderDTO;
import br.com.controlevendas.model.Order;
import br.com.controlevendas.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    private OrderDTO createOrder(OrderDTO orderDTO) {
        var Order = new Order();
        return null;
    }

}
