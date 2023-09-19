package ru.skypro.lesson.springboot.Coursework3_AuctionApplication.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class LotCSVDTO {
    private Integer id;
    private String status;
    private String title;
    private Integer currentPrice;
    private BidderDTO lastBid;

    @Override
    public String toString() {
        return id + "  " + "  " + title + "  " + status + "  " + lastBid + "  " + currentPrice;
    }
}
