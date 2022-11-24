package ru.darujo.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.darujo.model.Product;
import ru.darujo.service.BuyerDao;

import java.util.Set;

@RestController
public class BuyerController {

    private BuyerDao buyerDao;

    @Autowired
    public void setProductDao(BuyerDao buyerDao) {
        this.buyerDao = buyerDao;
    }

    @GetMapping("/findProductByBuyerId")
    public Set<Product> findProductByBuyerId(long id) {
        return buyerDao.findProductByBuyerId(id);
    }
    // Пока оставим здесь но надо вынести в друго контролер и другие классы
    @GetMapping("/findCheque")
    public Set<ChequeInformPublic> findChequeByBuyerAndProduct(long buyerId, long productId) {
        return buyerDao.findChequeByBuyerAndProduct(buyerId,productId);
    }
}
