package ru.darujo.model.publicmodel;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class ProductPublic {
    private long id;
    private String title;
    private double price;

}
