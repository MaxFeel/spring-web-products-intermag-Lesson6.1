package com.maxfeel.springweb.listener;

import com.maxfeel.springweb.model.Order;

public class CreatedOrdersDisplay implements Observer{

    public CreatedOrdersDisplay(Subject subject) {
        subject.registerObserver(this);
    }

    @Override
    public void update(Order data) {
        System.out.printf("Заказ " + data.getId() + " создан!");
    }
}
