package com.maxfeel.springweb.services;

import com.maxfeel.springweb.api.CartDto;
import com.maxfeel.springweb.integrations.CartServiceIntegration;
import com.maxfeel.springweb.listener.CreatedOrdersDisplay;
import com.maxfeel.springweb.listener.OrderListener;
import com.maxfeel.springweb.model.Order;
import com.maxfeel.springweb.model.OrderItem;
import com.maxfeel.springweb.repositories.OrdersRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrderService {
    private final ProductService productService;
    private final OrdersRepository ordersRepository;

    private final CartServiceIntegration cartServiceIntegration;


    @Transactional
    public void createOrder(String username){
        CartDto cartDto = null;

        cartDto = cartServiceIntegration.getCurrentCart();

        Order order = new Order();
        order.setUsername(username);
        order.setTotalPrice(cartDto.getTotalPrice());
        order.setItems(cartDto.getItems().stream().map(
            cartItem-> new OrderItem(
                order,
                productService.findById(cartItem.getProductId()).get(),
                cartItem.getPricePerProduct(),
                cartItem.getPrice(),
                cartItem.getQuantity()
            )
        ).collect(Collectors.toList()));
        ordersRepository.save(order);
        OrderListener orderListener = new OrderListener();
        CreatedOrdersDisplay createdOrdersDisplay = new CreatedOrdersDisplay(orderListener);
        orderListener.onNewData(order);
        cartServiceIntegration.clear();
    }



}
