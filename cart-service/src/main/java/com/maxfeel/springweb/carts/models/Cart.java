package com.maxfeel.springweb.carts.models;

import com.maxfeel.springweb.api.ProductDto;
import lombok.Data;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Data
public class Cart {

    private List<CartItem> items;
    private int totalPrice;

    public Cart(){
        this.items = new ArrayList<>();
    }

    public List<CartItem> getItems(){
        return Collections.unmodifiableList(items);
    }

    public void add(ProductDto product){ //TODO доработать изменение количества при одинаковом продукте
        boolean isExists = false;
        for (CartItem item : items){
            if (item.getProductTitle().equals(product.getTitle())){
                item.setQuantity(item.getQuantity() + 1);
                item.setPrice(item.getPricePerProduct() * item.getQuantity());
                isExists = true;
            }
        }
        if(!isExists) {
            items.add(new CartItem(product.getId(), product.getTitle(), 1, product.getPrice(), product.getPrice()));
        }
        recalculate();
    }

    private void recalculate(){
        totalPrice = 0;
        for(CartItem item: items){
            totalPrice += item.getPrice();
        }
    }

    public void deleteById(ProductDto product){
        CartItem deleteItem = null;
        for (CartItem item : items){
            if(item.getProductId().equals(product.getId())){
                deleteItem = item;
            }
        }
        items.remove(deleteItem);
        recalculate();
    }

    public void changeCountProduct(ProductDto product, int delta){
        for(CartItem item: items){
            if(item.getProductId().equals(product.getId())) {
                item.setQuantity(item.getQuantity() + delta);
                item.setPrice(item.getPricePerProduct() * item.getQuantity());
            }
        }
        recalculate();
    }

    public void deleteAllProduct(){
        items.clear();
        recalculate();
    }

}
