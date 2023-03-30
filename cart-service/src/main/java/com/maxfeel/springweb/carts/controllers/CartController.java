package com.maxfeel.springweb.carts.controllers;

import com.maxfeel.springweb.api.CartDto;
import com.maxfeel.springweb.carts.converters.CartConverter;
import com.maxfeel.springweb.carts.services.CartService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/cart")
@RequiredArgsConstructor
public class CartController {

    private final CartService cartService;
    private final CartConverter cartConverter;

    @GetMapping("/add/{id}")
    public void  addToCart(@PathVariable Long id){
        cartService.add(id);
    }

    @GetMapping
    public CartDto getCurrentCart(){
        return cartConverter.entityToDto(cartService.getCurrentCart());
    }

    @GetMapping("/delete/{id}")
    public void deleteFromCart(@PathVariable Long id){
        cartService.deleteProduct(id);
    }

    @GetMapping("/change_count")
    public void changeCount(@RequestParam Long productId, @RequestParam int delta) {
        cartService.changeCountProduct(productId, delta);
    }

    @GetMapping("/deleteAll")
    public void deleteAll(){
        cartService.deleteAll();
    }


}
