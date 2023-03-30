package com.maxfeel.springweb.controllers;

import com.maxfeel.springweb.services.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/v1/order")
@RequiredArgsConstructor
public class OrderController {


    private final OrderService orderService;


    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void createOrder(@RequestHeader String username){
        orderService.createOrder(username);
    }
    /*
    private final OrderService orderService;

    @GetMapping
    public List<Order> getCurrentOrder(){
        return orderService.findAll();
    }

    @PostMapping
    public void saveCartItems(@RequestBody List<CartItem> items) {
        orderService.save(items);
    }

     */
}
