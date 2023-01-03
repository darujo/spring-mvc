package ru.darujo.model;


import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class Basket {
   private String name;
   private List<BasketProductInform> productInforms = new ArrayList<>();

   public void setName(String name) {
      this.name = name;
   }

   public void setProductInforms(List<BasketProductInform> productInforms) {
      this.productInforms = productInforms;
   }

   public String getName() {
      return name;
   }

   public List<BasketProductInform> getProductInforms() {
      return productInforms;
   }

   public Basket() {
   }
}
