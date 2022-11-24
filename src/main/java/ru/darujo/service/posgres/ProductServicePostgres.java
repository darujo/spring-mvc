package ru.darujo.service.posgres;

import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import ru.darujo.model.Product;
import ru.darujo.model.publicmodel.BuyerPublic;
import ru.darujo.model.publicmodel.ProductPublic;
import ru.darujo.service.ProductDao;

import javax.annotation.PreDestroy;
import java.util.*;

@Service
@Primary
public class ProductServicePostgres implements ProductDao {

    private SessionFactoryUtils factoryUtils;

    @Autowired
    public void setFactoryUtils(SessionFactoryUtils factoryUtils) {
        this.factoryUtils = factoryUtils;
    }

    @Override
    public void saveOrUpdate(ProductPublic productPublic) {
        Product product = new Product();
        product.setId(productPublic.getId());
        product.setPrice(productPublic.getPrice());
        product.setTitle(productPublic.getTitle());
        try (Session session = factoryUtils.getSession()) {
            session.beginTransaction();
            session.saveOrUpdate(product);
            session.getTransaction().commit();
        }
    }

    @Override
    public Collection<ProductPublic> findAll() {
        List<ProductPublic> productPublics = new Vector<>();
        try (Session session = factoryUtils.getSession()) {
            session.beginTransaction();
            Collection<Product> products = session.createQuery("select p from Product p order by p.title").getResultList();
            products.forEach(product -> productPublics.add(product.getPublicProduct()));
            session.getTransaction().commit();
            System.out.println(products);
            return productPublics;
        }
    }

    @Override
    public ProductPublic findById(long id) {
        try (Session session = factoryUtils.getSession()) {
            session.beginTransaction();
            ProductPublic product = session.get(Product.class, id).getPublicProduct();
            session.getTransaction().commit();
            return product;

        }
    }

    @Override
    public void deleteById(long id) {
        try (Session session = factoryUtils.getSession()) {
            session.beginTransaction();
            session.createQuery("DELETE Product p WHERE p.id =:id ")
                    .setParameter("id", id)
                    .executeUpdate();
            session.getTransaction().commit();
        }
    }

    public Set<BuyerPublic> findBuyerByProductId(long id) {
        try (Session session = factoryUtils.getSession()) {
            Set<BuyerPublic> buyers = Collections.synchronizedSet(new HashSet<>());
            session.beginTransaction();
            Product product = session.get(Product.class, id);
            if (product != null) {
                product.getChequeLines().forEach(chequeLine -> buyers.add(chequeLine.getCheque().getBuyer().getBuyerPublic()));
            }
            session.getTransaction().commit();
            return buyers;
        }
    }

    @PreDestroy
    private void preDestroy() {
        factoryUtils.shutdown();
    }
}
