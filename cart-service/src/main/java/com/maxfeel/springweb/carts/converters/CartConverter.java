package com.maxfeel.springweb.carts.converters;

import com.maxfeel.springweb.api.CartDto;
import com.maxfeel.springweb.api.CartItemDto;
import com.maxfeel.springweb.carts.models.Cart;
import com.maxfeel.springweb.carts.models.CartItem;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class CartConverter {

    private final CartItemConverter cartItemConverter;
    public CartDto entityToDto(Cart cart){
        CartDto cartDto = new CartDto();
        cartDto.setTotalPrice(cart.getTotalPrice());
        cartDto.setItems(cart.getItems().stream().map(cartItemConverter::entityToDto).collect(Collectors.toList()));
        return cartDto;
    }
}
