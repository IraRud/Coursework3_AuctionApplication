package ru.skypro.lesson.springboot.Coursework3_AuctionApplication.model;


import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum LotStatus {
    STARTED("STARTED"),
    STOPPED("STOPPED"),
    CREATED("CREATED");

    private final String status;

    @Override
    public String toString() {
        return status;
    }
}