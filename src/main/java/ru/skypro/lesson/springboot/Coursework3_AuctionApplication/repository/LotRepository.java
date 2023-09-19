package ru.skypro.lesson.springboot.Coursework3_AuctionApplication.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.skypro.lesson.springboot.Coursework3_AuctionApplication.model.Lot;

@Repository
public interface LotRepository extends JpaRepository<Lot, Integer> {

}
