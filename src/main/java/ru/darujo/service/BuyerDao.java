package ru.darujo.service;

import org.springframework.stereotype.Service;
import ru.darujo.api.ChequeInformPublic;
import ru.darujo.model.Product;

import java.util.Set;

@Service
public interface BuyerDao {
    Set<Product> findProductByBuyerId(long id);

    Set<ChequeInformPublic> findChequeByBuyerAndProduct(long buyerId, long productId);
}
