package ru.skypro.lesson.springboot.Coursework3_AuctionApplication.service;

import lombok.RequiredArgsConstructor;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import ru.skypro.lesson.springboot.Coursework3_AuctionApplication.dto.LotCSVDTO;
import ru.skypro.lesson.springboot.Coursework3_AuctionApplication.dto.LotDTO;
import ru.skypro.lesson.springboot.Coursework3_AuctionApplication.dto.LotFullInfoDTO;
import ru.skypro.lesson.springboot.Coursework3_AuctionApplication.model.Lot;
import ru.skypro.lesson.springboot.Coursework3_AuctionApplication.model.LotStatus;
import ru.skypro.lesson.springboot.Coursework3_AuctionApplication.repository.LotRepository;

import java.io.IOException;
import java.io.Writer;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class LotServiceImpl implements LotService {
    private final LotRepository lotRepository;
    private final LotMapper lotMapper;
    Logger logger = LoggerFactory.getLogger(LotServiceImpl.class);

    @Override
    public List<LotDTO> addNewLot(List<LotDTO> lotDTOS) {
        logger.debug("Создание нового лота");

        Optional<LotDTO> lots = lotDTOS.stream().filter(lotDTO -> lotDTO.getBidPrice() < 1 || lotDTO.getStartPrice() < 1 || lotDTO.getDescription().isEmpty() || lotDTO.getTitle().isEmpty()).findFirst();

        if (lots.isPresent()) {
            throw new RuntimeException();
        }

        return lotRepository.saveAll(lotDTOS.stream().map(lotMapper::toEntity).collect(Collectors.toList())).stream().map(lotMapper::toDto).collect(Collectors.toList());
    }

    @Override
    public void startBiddingForLotId(int id) {
        logger.debug("Начало приёма ставок на лот id=" + id);

        Lot lot = lotRepository.findById(id).orElseThrow(() -> {
            logger.error("ставка не найдена. id =" + id);
            return new RuntimeException();
        });

        lot.setStatus(LotStatus.STARTED);
        lotRepository.save(lot);
    }

    @Override
    public void stopBiddingForLotId(int id) {
        logger.debug("Окончание приёма ставок на лот id=" + id);

        Lot lot = lotRepository.findById(id).orElseThrow(() -> {
            logger.error("ставка не найдена. id =" + id);
            return new RuntimeException();
        });

        lot.setStatus(LotStatus.STOPPED);
        lotRepository.save(lot);
    }

    @Override
    public Lot getLotById(int id) {
        return lotRepository.findById(id).orElseThrow(() -> {
            logger.error("ставка не найдена. id =" + id);
            return new RuntimeException();
        });
    }

    @Override
    public LotFullInfoDTO getFullInfo(int id) {
        return lotRepository.findById(id).stream().map(lotMapper::toFullInfo).findFirst().orElseThrow(() -> {
            logger.error("ставка не найдена. id =" + id);
            return new RuntimeException();
        });
    }

    @Override
    public void csvFile() {
        List<LotCSVDTO> lotCsv = lotRepository.findAll().stream().map(lotMapper::lotCSVDTO).toList();

        try {
            Writer writer = Files.newBufferedWriter(Paths.get("LotInfo.csv"));
            CSVPrinter printer = CSVFormat.DEFAULT.withHeader("Id ", "title ", "Status ", "lastBidderName ", "currentPrice ").print(writer);

            printer.printRecords(lotCsv);
            printer.flush();
            writer.close();

        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public String readTextFromFile(String fileName) {
        try {
            return Files.lines(Paths.get(fileName), StandardCharsets.UTF_8).collect(Collectors.joining());
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
        return null;
    }
}
