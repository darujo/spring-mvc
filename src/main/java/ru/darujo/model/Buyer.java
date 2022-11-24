package ru.darujo.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.darujo.model.publicmodel.BuyerPublic;

import javax.persistence.*;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "buyer")
public class Buyer {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(name = "name")
    private String name;
    @OneToMany(mappedBy = "buyer")
    private List<Cheque> cheques;
    public BuyerPublic getBuyerPublic(){
        return new BuyerPublic(id,name);
    }
}
