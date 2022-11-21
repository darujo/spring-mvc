package ru.darujo.service;

import org.springframework.stereotype.Service;
import ru.darujo.model.Product;

import java.util.Collection;
@Service
public interface ProductDao {

    void saveOrUpdate(Product product);

    Collection< Product> findAll();

    Product findById(long id);

    void deleteById(long id);

}
