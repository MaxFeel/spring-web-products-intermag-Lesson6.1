package com.maxfeel.springweb.listener;

import com.maxfeel.springweb.model.Order;

import java.util.HashSet;
import java.util.Set;

public class OrderListener implements Subject{

    private Order data;

    private Set<Observer> observers = new HashSet<>();


    @Override
    public void registerObserver(Observer observer) {
        observers.add(observer);
    }

    @Override
    public void unregisterObserver(Observer observer) {
        observers.remove(observer);
    }

    @Override
    public void notifyObservers() {
        for (Observer observer : observers)
            observer.update(data);
    }

    Order getData(){
        return data;
    }

    public void onNewData(Order newData) {
        this.data = newData;
        notifyObservers();
    }
}
