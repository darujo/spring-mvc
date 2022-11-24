package ru.darujo.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "cheque")
public class Cheque {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(name = "datatime")
    private Timestamp timestamp;
    @ManyToOne
    @JoinColumn(name = "buyer_id")
    private Buyer buyer;
    @OneToMany(mappedBy = "cheque")
    private List<ChequeLine> chequeLines;



}
