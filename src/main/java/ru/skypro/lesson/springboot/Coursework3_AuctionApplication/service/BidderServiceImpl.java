package ru.skypro.lesson.springboot.Coursework3_AuctionApplication.service;

import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import ru.skypro.lesson.springboot.Coursework3_AuctionApplication.dto.BidderDTO;
import ru.skypro.lesson.springboot.Coursework3_AuctionApplication.model.Lot;
import ru.skypro.lesson.springboot.Coursework3_AuctionApplication.model.LotStatus;
import ru.skypro.lesson.springboot.Coursework3_AuctionApplication.repository.BidderRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BidderServiceImpl implements BidderService {
    private final BidderRepository bidderRepository;
    private final BidderMapper bidderMapper;
    private final LotService lotService;
    Logger logger = LoggerFactory.getLogger(BidderServiceImpl.class);

    @Override
    public List<BidderDTO> addNewBid(List<BidderDTO> bidderDTOS, int id) {
        logger.debug("Создание ставки на лот id=" + id);
        LocalDateTime localDateTime = LocalDateTime.now();
        Lot lot = lotService.getLotById(id);

        if (lot.getStatus().toString().equals(LotStatus.CREATED.getStatus())
                || lot.getStatus().toString().equals(LotStatus.STOPPED.getStatus())) {
            throw new RuntimeException();
        }

        Optional<BidderDTO> bidders = bidderDTOS.stream().filter(bidderDTO -> bidderDTO.getName().isEmpty()).findFirst();

        if (bidders.isPresent()) {
            throw new RuntimeException();
        }

        return bidderRepository.saveAll(bidderDTOS.stream().map(bidderMapper::toEntity).peek(bid -> {
            bid.setLot(lot);
            bid.setBidDateTime(localDateTime);
        }).collect(Collectors.toList())).stream().map(bidderMapper::toDtO).collect(Collectors.toList());
    }

    @Override
    public BidderDTO findFirstBidder(int id) {
        return bidderRepository.findFirstBidder(id).orElseThrow(() -> {
            logger.error("ставка не найдена. id =" + id);
            return new RuntimeException();
        });
    }

    @Override
    public BidderDTO lastBid(int id) {
        return bidderRepository.findLastBidder(id).orElseThrow(() -> {
            logger.error("ставка не найдена. id =" + id);
            return new RuntimeException();
        });
    }

    @Override
    public BidderDTO findMaxBid(int id) {
        return bidderRepository.findMaxBid(id).orElseThrow(() -> {
            logger.error("ставка не найдена. id =" + id);
            return new RuntimeException();
        });
    }
}
