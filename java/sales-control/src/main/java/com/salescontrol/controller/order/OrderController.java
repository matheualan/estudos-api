package com.salescontrol.controller.order;

import com.salescontrol.dto.order.OrderPostDTO;
import com.salescontrol.service.OrderService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/order")
@RequiredArgsConstructor
@Log4j2
public class OrderController {

    private final OrderService orderService;

    @PostMapping(value = "/create-order")
    public ResponseEntity<OrderPostDTO> createOrder(@RequestBody OrderPostDTO orderPostDTO) {
        return ResponseEntity.status(HttpStatus.CREATED).body(orderService.createOrder(orderPostDTO));
    }

}
