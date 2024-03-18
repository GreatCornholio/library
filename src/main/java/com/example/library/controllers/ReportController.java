package com.example.library.controllers;

import com.example.library.services.ReportService;
import com.example.library.domain.Book;
import com.example.library.domain.User;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/reports")
public class ReportController {

    private final ReportService reportService;

    public ReportController(ReportService reportService) {
        this.reportService = reportService;
    }

    //    a) returns all users who have actually borrowed at least one book
    @GetMapping("/users-who-borrowed-at-least-one-book")
    public List<User> getUsersWhoBorrowedAtLeastOneBook() {

        return reportService.getUsersWhoBorrowedAtLeastOneBook();
    }

    //    b) returns all non-terminated users who have not currently borrowed anything
    @GetMapping("/non-terminated-users-who-have-not-currently-borrowed-anything")
    public List<User> getNonTerminatedUsersWhoHaveNotCurrentlyBorrowedAnything() {

        return reportService.getNonTerminatedUsersWhoHaveNotCurrentlyBorrowedAnything();
    }

    //    c) returns all users who have borrowed a book on a given date
    @GetMapping("/users-who-have-borrowed-book-on-a-date")
    public List<User> getUsersWhoHaveBorrowedBook(@RequestParam("date")
                                                  @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
                                                  LocalDate date) {

        return reportService.getUsersWhoHaveBorrowedBook(date);
    }

    //    d) returns all books borrowed by a given user in a given date range
    @GetMapping("/books-borrowed-by-user-by-date-range")
    public List<Book> getBooksBorrowedByUserByDateBetween(
            @Validated @RequestParam("user") String user,
            @RequestParam("date_from") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dateFrom,
            @RequestParam("date_to") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dateTo) {

        return reportService.getBooksBorrowedByUserByDateBetween(user, dateFrom, dateTo);
    }

    //    e) returns all available (not borrowed) books
    @GetMapping("/all-available-books")
    public List<Book> getAllAvailableBooks() {
        return reportService.getAllAvailableBooks();
    }

}
