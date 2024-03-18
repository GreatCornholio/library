package com.example.library.services;

import com.example.library.domain.Book;
import com.example.library.domain.User;
import com.example.library.repositories.BookRepository;
import com.example.library.repositories.UserRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class ReportService {

    private final BookRepository bookRepository;
    private final UserRepository userRepository;

    public ReportService(BookRepository bookRepository, UserRepository userRepository) {
        this.bookRepository = bookRepository;
        this.userRepository = userRepository;
    }

    public List<User> getUsersWhoBorrowedAtLeastOneBook() {
        return userRepository.getUsersWhoBorrowedAtLeastOneBook();
    }

    public List<User> getUsersWhoHaveBorrowedBook(LocalDate date) {
        return userRepository.getUsersWhoHaveBorrowedBook(date);
    }

    public List<User> getNonTerminatedUsersWhoHaveNotCurrentlyBorrowedAnything() {
        return userRepository.getNonTerminatedUsersWhoHaveNotCurrentlyBorrowedAnything();
    }

    public List<Book> getBooksBorrowedByUserByDateBetween(String user, LocalDate dateFrom, LocalDate dateTo) {
        return bookRepository.getBooksBorrowedByUserByDateBetween(user, dateFrom, dateTo);
    }

    public List<Book> getAllAvailableBooks() {
        return bookRepository.getAllAvailableBooks();
    }

}
