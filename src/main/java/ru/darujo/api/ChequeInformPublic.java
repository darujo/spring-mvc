package ru.darujo.api;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.sql.Timestamp;

@AllArgsConstructor
@Data
public class ChequeInformPublic {
    private long chequeId;
    private long productId;
    private String productName;
    private double productPrice;
    private double productPriceInCheque;
    private Timestamp timestamp;
}
