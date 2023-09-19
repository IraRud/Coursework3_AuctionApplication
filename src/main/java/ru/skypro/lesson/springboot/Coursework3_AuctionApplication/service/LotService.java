package ru.skypro.lesson.springboot.Coursework3_AuctionApplication.service;


import ru.skypro.lesson.springboot.Coursework3_AuctionApplication.dto.LotDTO;
import ru.skypro.lesson.springboot.Coursework3_AuctionApplication.dto.LotFullInfoDTO;
import ru.skypro.lesson.springboot.Coursework3_AuctionApplication.model.Lot;

import java.io.IOException;
import java.util.List;

public interface LotService {
    List<LotDTO> addNewLot(List<LotDTO>  lotDTO);
    void startBiddingForLotId(int id);
    void stopBiddingForLotId(int id);
    Lot getLotById(int id);
    LotFullInfoDTO getFullInfo(int id);
    void csvFile() throws IOException;
    String readTextFromFile(String fileName);
}
