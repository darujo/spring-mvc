package ru.darujo.service.posgres;

import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.darujo.api.ChequeInformPublic;
import ru.darujo.model.Buyer;
import ru.darujo.model.Product;
import ru.darujo.service.BuyerDao;

import java.util.*;

@Service
public class BuyerDaoPosgres implements BuyerDao {
    private SessionFactoryUtils factoryUtils;
    @Autowired
    public void setFactoryUtils(SessionFactoryUtils factoryUtils) {
        this.factoryUtils = factoryUtils;
    }

    @Override
    public Set<Product> findProductByBuyerId(long id){
        try (Session session = factoryUtils.getSession()) {
            Set<Product> products = Collections.synchronizedSet(new HashSet<>());
            session.beginTransaction();
            Buyer buyer = session.get(Buyer.class, id);
            if (buyer != null) {
                buyer.getCheques().forEach(cheque -> cheque.getChequeLines().forEach(chequeLine -> products.add(chequeLine.getProduct())));
            }
            session.getTransaction().commit();
            return products;
        }
    }

    @Override
    public Set<ChequeInformPublic> findChequeByBuyerAndProduct(long buyerId, long productId) {
        try (Session session = factoryUtils.getSession()) {
            Set<ChequeInformPublic> chequeInformPublics = Collections.synchronizedSet(new HashSet<>());
            session.beginTransaction();
            Buyer buyer = session.get(Buyer.class, buyerId);
            if (buyer != null) {
                buyer.getCheques().forEach(cheque -> cheque.getChequeLines()
                                  .stream()
                                  .filter(chequeLine -> chequeLine.getProduct().getId() == productId)
                                  .forEach(chequeLine -> chequeInformPublics.add(new ChequeInformPublic(chequeLine.getCheque().getId(),
                                                                                                        chequeLine.getProduct().getId(),
                                                                                                        chequeLine.getProduct().getTitle(),
                                                                                                        chequeLine.getProduct().getPrice(),
                                                                                                        chequeLine.getPrice(),
                                                                                                        cheque.getTimestamp()))));
            }
            session.getTransaction().commit();
            return chequeInformPublics;
        }
    }
}
