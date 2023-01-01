package ru.darujo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.darujo.model.Order;

public interface OrderRepository extends JpaRepository<Order, Long> {
}