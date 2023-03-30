package com.maxfeel.springweb.integrations;

import com.maxfeel.springweb.api.CartDto;
import com.maxfeel.springweb.api.ProductDto;
import com.maxfeel.springweb.api.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class CartServiceIntegration {

    private final WebClient cartServiceWebClient;

    public CartDto getCurrentCart() {
        return cartServiceWebClient.get()
                .uri("/api/v1/cart/")
                .retrieve()
                .onStatus(
                        httpStatus -> httpStatus.value() == HttpStatus.NOT_FOUND.value(),
                        clientResponse -> Mono.error(new ResourceNotFoundException("Корзина не найдена"))
                )
                .bodyToMono(CartDto.class)
                .block();
    }

    public void clear() {
        cartServiceWebClient.get()
                .uri("/api/v1/cart/deleteAll")
                .retrieve()
                .toBodilessEntity()
                .block();
    }

    //private final RestTemplate restTemplate;

//    public CartDto getCurrentCart(){
//        CartDto cartDto = restTemplate.getForObject("http://localhost:8190/app-cart/api/v1/cart/", CartDto.class);
//        return cartDto;
//    }
}
