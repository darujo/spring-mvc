package ru.darujo.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.darujo.model.publicmodel.ProductPublic;

import javax.persistence.*;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "product")
public class Product {
    @Id
    @Column(name = "id")
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private long id;
    @Column(name = "title")
    private String title;
    @Column(name = "price")
    private double price;
    @OneToMany(mappedBy = "product")
//    @Transient
    private List<ChequeLine> chequeLines;

    public ProductPublic getPublicProduct(){
        return new ProductPublic(id,title,price);
    }
}
