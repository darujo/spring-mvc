package ru.darujo.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "chequeline")
public class ChequeLine {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private double quantity;
    private double price;
    @ManyToOne
    @JoinColumn(name = "cheque_id")
    private Cheque cheque;
    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

}
