package com.maxfeel.springweb.carts.converters;

import com.maxfeel.springweb.api.CartItemDto;
import com.maxfeel.springweb.carts.models.CartItem;
import org.springframework.stereotype.Component;

@Component
public class CartItemConverter {
    public CartItemDto entityToDto(CartItem cartItem){
        CartItemDto cartItemDto = new CartItemDto();
        cartItemDto.setPrice(cartItem.getPrice());
        cartItemDto.setPricePerProduct(cartItem.getPricePerProduct());
        cartItemDto.setQuantity(cartItem.getQuantity());
        cartItemDto.setProductTitle(cartItem.getProductTitle());
        cartItemDto.setProductId(cartItem.getProductId());
        return cartItemDto;
    }
}
