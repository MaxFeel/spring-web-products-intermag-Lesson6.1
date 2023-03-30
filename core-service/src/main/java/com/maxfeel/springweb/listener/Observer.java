package com.maxfeel.springweb.listener;

import com.maxfeel.springweb.model.Order;

public interface Observer {
    void update(Order data);
}
