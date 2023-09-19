package ru.skypro.lesson.springboot.Coursework3_AuctionApplication.service;

import ru.skypro.lesson.springboot.Coursework3_AuctionApplication.dto.BidderDTO;

import java.util.List;

public interface BidderService {
    List<BidderDTO> addNewBid(List<BidderDTO> bidderDTOS, int id);
    BidderDTO findFirstBidder(int id);
    BidderDTO lastBid(int id);
    BidderDTO findMaxBid(int id);
}