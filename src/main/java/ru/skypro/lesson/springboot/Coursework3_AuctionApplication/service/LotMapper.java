package ru.skypro.lesson.springboot.Coursework3_AuctionApplication.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.skypro.lesson.springboot.Coursework3_AuctionApplication.dto.LotDTO;
import ru.skypro.lesson.springboot.Coursework3_AuctionApplication.dto.LotFullInfoDTO;
import ru.skypro.lesson.springboot.Coursework3_AuctionApplication.model.Lot;
import ru.skypro.lesson.springboot.Coursework3_AuctionApplication.repository.BidderRepository;
import ru.skypro.lesson.springboot.Coursework3_AuctionApplication.dto.LotCSVDTO ;

import static ru.skypro.lesson.springboot.Coursework3_AuctionApplication.model.LotStatus.CREATED;

@Component
@RequiredArgsConstructor
public class LotMapper {
    private final BidderRepository bidderRepository;

    public LotDTO toDto(Lot lot){
        LotDTO lotDTO = new LotDTO();
        lotDTO.setTitle(lot.getTitle());
        lotDTO.setDescription(lot.getDescription());
        lotDTO.setStartPrice(lot.getStartPrice());
        lotDTO.setBidPrice(lot.getBidPrice());
        return lotDTO;
    }

    public Lot toEntity(LotDTO lotDTO){
        Lot lot = new Lot();
        lot.setTitle(lotDTO.getTitle());
        lot.setDescription(lotDTO.getDescription());
        lot.setStartPrice(lotDTO.getStartPrice());
        lot.setBidPrice(lotDTO.getBidPrice());
        lot.setStatus(CREATED);
        return lot;
    }

    public LotFullInfoDTO toFullInfo(Lot lot){
        LotFullInfoDTO lotFullInfoDTO = new LotFullInfoDTO();
        lotFullInfoDTO.setId(lot.getId());
        lotFullInfoDTO.setTitle(lot.getTitle());
        lotFullInfoDTO.setDescription(lot.getDescription());
        lotFullInfoDTO.setStartPrice(lot.getStartPrice());
        lotFullInfoDTO.setBidPrice(lot.getBidPrice());
        lotFullInfoDTO.setStatus(lot.getStatus().toString());
        lotFullInfoDTO.setCurrentPrice(lot.getBids().size() * lot.getBidPrice() + lot.getStartPrice());
        lotFullInfoDTO.setLastBid(bidderRepository.findLastBidder(lot.getId()).orElse(null));
        return lotFullInfoDTO;
    }

    public LotCSVDTO lotCSVDTO(Lot lot){
        LotCSVDTO lotCSVDTO = new LotCSVDTO();
        lotCSVDTO.setId(lot.getId());
        lotCSVDTO.setTitle(lot.getTitle());
        lotCSVDTO.setStatus(lot.getStatus().toString());
        lotCSVDTO.setCurrentPrice(lot.getBids().size() * lot.getBidPrice() + lot.getStartPrice());
        lotCSVDTO.setLastBid(bidderRepository.findLastBidder(lot.getId()).orElse(null));
        return lotCSVDTO;
    }
}
