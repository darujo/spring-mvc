package ru.darujo.service;

import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import ru.darujo.model.Product;

import javax.annotation.PreDestroy;
import java.util.Collection;

@Service
@Primary
public class ProductServicePostgres implements ProductDao {

    private SessionFactoryUtils factoryUtils;

    @Autowired
    public void setFactoryUtils(SessionFactoryUtils factoryUtils) {
        this.factoryUtils = factoryUtils;
    }

    @Override
    public void saveOrUpdate(Product product) {
        try (Session session = factoryUtils.getSession()) {
            session.beginTransaction();
            session.saveOrUpdate(product);
            session.getTransaction().commit();
        }
    }

    @Override
    public Collection<Product> findAll() {
        try (Session session = factoryUtils.getSession()) {
            session.beginTransaction();
            Collection<Product> products = session.createQuery("select p from Product p").getResultList();
            session.getTransaction().commit();
            return products;
        }
    }

    @Override
    public Product findById(long id) {
        try (Session session = factoryUtils.getSession()) {
            session.beginTransaction();
            Product product = session.get(Product.class, id);
            session.getTransaction().commit();
            return product;
        }

    }

    @Override
    public void deleteById(long id) {
        try (Session session = factoryUtils.getSession()) {
            session.beginTransaction();
            session.createQuery("DELETE Product p WHERE p.id =:id ")
                    .setParameter("id",id)
                    .executeUpdate();
            session.getTransaction().commit();
        }
    }
    @PreDestroy
    private void preDestroy(){
        factoryUtils.shutdown();
    }
}
