package com.salescontrol.controller.order;

import com.salescontrol.service.OrderService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/order")
@RequiredArgsConstructor
@Log4j2
public class OrderController {

    private final OrderService orderService;

}
