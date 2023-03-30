package com.maxfeel.springweb.carts.services;

import com.maxfeel.springweb.api.ProductDto;
import com.maxfeel.springweb.api.ResourceNotFoundException;

import com.maxfeel.springweb.carts.integrations.ProductServiceIntegration;
import com.maxfeel.springweb.carts.models.Cart;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.PostConstruct;

@Service
@RequiredArgsConstructor
public class CartService {

    private final ProductServiceIntegration productServiceIntegration;
    Cart tempCart;


    @PostConstruct
    public void init(){
        tempCart = new Cart();
    }

    public Cart getCurrentCart(){
        return tempCart;
    }

    public void add(Long productId){
        ProductDto product = productServiceIntegration.getProductById(productId);
        tempCart.add(product);
    }

    public void deleteProduct(Long productId){
        ProductDto product = productServiceIntegration.getProductById(productId);
        tempCart.deleteById(product);
    }

    public void deleteAll(){
        tempCart.deleteAllProduct();
    }

    public void changeCountProduct(Long productId, int delta){
        ProductDto product = productServiceIntegration.getProductById(productId);
        tempCart.changeCountProduct(product,delta);
    }
}
