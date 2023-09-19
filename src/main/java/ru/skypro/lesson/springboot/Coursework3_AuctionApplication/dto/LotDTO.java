package ru.skypro.lesson.springboot.Coursework3_AuctionApplication.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class LotDTO {
    private String title;
    private String description;
    private Integer startPrice;
    private Integer bidPrice;

    @Override
    public String toString() {
        return title;
    }
}
