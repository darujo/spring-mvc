package ru.darujo.model;


import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@Data
@Component
public class Basket {
   private String name;
   private List<BasketProductInform> productInforms = new ArrayList<>();
}
