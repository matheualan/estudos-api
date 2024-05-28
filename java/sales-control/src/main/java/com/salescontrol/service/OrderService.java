package com.salescontrol.service;

import com.salescontrol.dto.order.OrderGetDTO;
import com.salescontrol.dto.order.OrderPostDTO;
import com.salescontrol.mapper.OrderMapper;
import com.salescontrol.model.Client;
import com.salescontrol.model.Order;
import com.salescontrol.repository.ClientRepository;
import com.salescontrol.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;
    private final ClientService clientService;
    private final ClientRepository clientRepository;

    public List<Order> findAllOrders() {
        return orderRepository.findAll();
    }

    public Order saveOrder(Order order) {
        return orderRepository.save(order);
    }

    public OrderGetDTO createOrder(OrderPostDTO orderPostDTO) {
//        ClientGetDTO clientGetDTO = clientService.findByName(orderPostDTO.getNameClient());
//        Client client = ClientMapper.INSTANCE.toClient(clientGetDTO);

        Order order = OrderMapper.INSTANCE.toOrder(orderPostDTO);

        Client byName = clientRepository.findByName(orderPostDTO.getNameClient()).get();

        order.setClient(byName);

        orderRepository.save(order);

        return OrderMapper.INSTANCE.toOrderGet(order);
    }

}
