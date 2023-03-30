package com.maxfeel.springweb.carts.integrations;

import com.maxfeel.springweb.api.ProductDto;
import com.maxfeel.springweb.api.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import javax.management.relation.RelationServiceNotRegisteredException;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class ProductServiceIntegration {
    private final WebClient productServiceWebClient;

    public ProductDto getProductById(Long id) {
        return productServiceWebClient.get()
                .uri("/api/v1/products/" + id)
                .retrieve()
                .onStatus(
                        httpStatus -> httpStatus.value() == HttpStatus.NOT_FOUND.value(),
                        clientResponse -> Mono.error(new ResourceNotFoundException("Продукт не найден"))
                )
                .bodyToMono(ProductDto.class)
                .block();
    }

    //private final RestTemplate restTemplate;

    //public Optional<ProductDto> getProductById(Long id){
    //    return Optional.ofNullable(restTemplate.getForObject("http://localhost:8189/app/api/v1/products/" + id, ProductDto.class));
    //}
}
