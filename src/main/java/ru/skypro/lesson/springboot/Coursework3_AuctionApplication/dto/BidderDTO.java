package ru.skypro.lesson.springboot.Coursework3_AuctionApplication.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class BidderDTO {
    private String name;
    private LocalDateTime bidDateTime;

    @Override
    public String toString() {
        return name;
    }
}
