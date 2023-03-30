package com.maxfeel.springweb.repositories;

import com.maxfeel.springweb.model.Order;
import com.maxfeel.springweb.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface OrdersRepository extends JpaRepository<Order, Long>, JpaSpecificationExecutor<Order> {
}
