package ru.skypro.lesson.springboot.Coursework3_AuctionApplication.exeption;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.sql.SQLException;
import java.util.NoSuchElementException;

public class AuctionExceptionHandler {
    @ExceptionHandler
    public ResponseEntity<?> handleSQLException(SQLException sqlException) {
        String message = "Server error!";
        return new ResponseEntity<>(message, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler
    public ResponseEntity<?> handleNoSuchElementException(NoSuchElementException noSuchElementException) {
        String massage = "404 not found!";
        return new ResponseEntity<>(massage, HttpStatus.NOT_FOUND);
    }
}
