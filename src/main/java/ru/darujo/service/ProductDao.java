package ru.darujo.service;

import org.springframework.stereotype.Service;
import ru.darujo.model.publicmodel.BuyerPublic;
import ru.darujo.model.publicmodel.ProductPublic;

import java.util.Collection;
import java.util.Set;

@Service
public interface ProductDao {

    void saveOrUpdate(ProductPublic product);

    Collection< ProductPublic> findAll();

    ProductPublic findById(long id);

    void deleteById(long id);

    default Set<BuyerPublic> findBuyerByProductId(long id){
        throw new RuntimeException("Метод не реализован");
    }

}
