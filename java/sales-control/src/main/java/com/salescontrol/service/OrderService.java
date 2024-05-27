package com.salescontrol.service;

import com.salescontrol.dto.order.OrderPostDTO;
import com.salescontrol.model.Order;
import com.salescontrol.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;

    public List<Order> findAllOrders() {
        return orderRepository.findAll();
    }

    public Order saveOrder(Order order) {
        return orderRepository.save(order);
    }

    public OrderPostDTO createOrder(OrderPostDTO orderPostDTO) {
        return null;
    }
}
