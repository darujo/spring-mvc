package ru.darujo.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table (name = "orders")
public class Order {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "username")
    private String username;

    @OneToMany(mappedBy = "order",cascade = {CascadeType.PERSIST,CascadeType.MERGE,CascadeType.REMOVE})
    private List<OrderItem> orderItems;
}
