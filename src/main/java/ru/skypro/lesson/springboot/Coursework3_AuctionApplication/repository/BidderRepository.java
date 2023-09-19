package ru.skypro.lesson.springboot.Coursework3_AuctionApplication.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.skypro.lesson.springboot.Coursework3_AuctionApplication.dto.BidderDTO;
import ru.skypro.lesson.springboot.Coursework3_AuctionApplication.model.Bid;

import java.util.Optional;

@Repository
public interface BidderRepository extends JpaRepository<Bid, Integer> {
    @Query("SELECT new ru.skypro.lesson.springboot.Coursework3_AuctionApplication.dto.BidderDTO(b.name, b.bidDateTime) " +
            "FROM Bid b WHERE b.lot.id=:id AND b.id= " +
            "(SELECT b2.id " +
            "FROM Bid b2 " +
            "WHERE b2.lot.id = b.lot.id " +
            "ORDER BY b2.bidDateTime " +
            "ASC LIMIT 1)")
    Optional<BidderDTO> findFirstBidder(@Param("id") int id);

    @Query("SELECT new ru.skypro.lesson.springboot.Coursework3_AuctionApplication.dto.BidderDTO(b.name, b.bidDateTime) " +
            "FROM Bid b " +
            "WHERE b.lot.id=:id AND b.id= " +
            "(SELECT b2.id " +
            "FROM Bid b2 " +
            "WHERE b2.lot.id = b.lot.id " +
            "ORDER BY b2.bidDateTime " +
            "desc LIMIT 1)")
    Optional<BidderDTO> findLastBidder(@Param("id") int id);

    @Query("SELECT new ru.skypro.lesson.springboot.Coursework3_AuctionApplication.dto.BidderDTO(b.bidDateTime)) " +
            "FROM Bid b " +
            "WHERE b.lot.id=:id " +
            "GROUP BY b.name " +
            "ORDER BY count(*) " +
            "desc LIMIT 1")
    Optional<BidderDTO> findMaxBid(@Param("id") int id);
}
