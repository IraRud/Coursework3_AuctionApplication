package ru.skypro.lesson.springboot.Coursework3_AuctionApplication.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.core.io.PathResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.skypro.lesson.springboot.Coursework3_AuctionApplication.dto.BidderDTO;
import ru.skypro.lesson.springboot.Coursework3_AuctionApplication.dto.LotDTO;
import ru.skypro.lesson.springboot.Coursework3_AuctionApplication.dto.LotFullInfoDTO;
import ru.skypro.lesson.springboot.Coursework3_AuctionApplication.service.BidderService;
import ru.skypro.lesson.springboot.Coursework3_AuctionApplication.service.LotService;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/lot")
@RequiredArgsConstructor
public class AuctionController {
    private final LotService lotService;
    private final BidderService bidderService;

    @GetMapping("/{id}/first")
    public BidderDTO firstBidderInfo(@PathVariable int id) {
        return bidderService.findFirstBidder(id);
    }

    @GetMapping("/{id}/frequent")
    public BidderDTO maxBidForLot(@PathVariable int id) {
        return bidderService.findMaxBid(id);
    }

    @GetMapping("/{id}")
    public LotFullInfoDTO getFullInfo(@PathVariable int id) {
        return lotService.getFullInfo(id);
    }

    @PostMapping("/{id}/start")
    public void startBiddingForLotId(@PathVariable int id) {
        lotService.startBiddingForLotId(id);
    }

    @PostMapping("/{id}/bid")
    public List<BidderDTO> sendBidForLot(@RequestBody List<BidderDTO> bidderDTOS, @PathVariable int id) {
        return bidderService.addNewBid(bidderDTOS, id);
    }

    @PostMapping("/{id}/stop")
    public void stopBiddingForLotId(@PathVariable int id) {
        lotService.stopBiddingForLotId(id);
    }

    @PostMapping
    public List<LotDTO> addNewLot(@RequestBody List<LotDTO> lotDTOS) {
        return lotService.addNewLot(lotDTOS);
    }

    @GetMapping("/export")
    public ResponseEntity<Resource> csvFile() throws IOException {
        lotService.csvFile();
        Resource resource = new PathResource("LotInfo.csv");
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + "LotInfo.csv" + "\"")
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .body(resource);
    }
}
