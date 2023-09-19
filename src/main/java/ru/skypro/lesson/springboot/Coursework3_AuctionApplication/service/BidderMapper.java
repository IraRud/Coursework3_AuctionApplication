package ru.skypro.lesson.springboot.Coursework3_AuctionApplication.service;

import org.springframework.stereotype.Component;
import ru.skypro.lesson.springboot.Coursework3_AuctionApplication.dto.BidderDTO;
import ru.skypro.lesson.springboot.Coursework3_AuctionApplication.model.Bid;

@Component
public class BidderMapper {
    public BidderDTO toDtO(Bid bid){
        BidderDTO bidderDTO = new BidderDTO();
        bidderDTO.setName(bid.getName());
        bidderDTO.setBidDateTime(bid.getBidDateTime());

        return bidderDTO;
    }

    public Bid toEntity(BidderDTO bidderDTO){
        Bid bid = new Bid();
        bid.setName(bidderDTO.getName());
        bid.setBidDateTime(bid.getBidDateTime());

        return bid;
    }
}
