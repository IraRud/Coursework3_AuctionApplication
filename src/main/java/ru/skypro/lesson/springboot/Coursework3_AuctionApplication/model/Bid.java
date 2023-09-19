package ru.skypro.lesson.springboot.Coursework3_AuctionApplication.model;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "bid")
@NoArgsConstructor
@Data
public class Bid {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "name")
    private String name;

    @Column(name = "date")
    private LocalDateTime bidDateTime;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "lot_id")
    private Lot lot;

    public Bid(String name) {
        this.name = name;
        this.bidDateTime = LocalDateTime.now();
    }
}
