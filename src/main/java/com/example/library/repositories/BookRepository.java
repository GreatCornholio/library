package com.example.library.repositories;


import com.example.library.domain.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;


public interface BookRepository extends JpaRepository<Book, Long> {
    String BOOKS_BORROWED_BY_USER_BY_DATE_BETWEEN = """
            SELECT B.*
            FROM BORROWING_RECORD AS BR
                INNER JOIN BOOKS AS B ON (B.TITLE = BR.BOOK)
            WHERE BORROWER = :user
                    AND (
                        (BORROWED_FROM >= :dt_from  AND BORROWED_TO <= :dt_to)
                        OR (BORROWED_TO >= :dt_from  AND BORROWED_TO <= :dt_to ))
            """;

    String ALL_AVAILABLE_BOOKS = """
            SELECT DISTINCT B.*
            FROM BOOKS  AS B
              LEFT JOIN BORROWING_RECORD AS BR ON (B.TITLE = BR.BOOK)
            WHERE BR.ID IS NULL
            """;

    //    d) returns all books borrowed by a given user in a given date range
    @Query(value = BOOKS_BORROWED_BY_USER_BY_DATE_BETWEEN, nativeQuery = true)
    List<Book> getBooksBorrowedByUserByDateBetween(
            @Param("user") String user, @Param("dt_from") LocalDate dateFrom, @Param("dt_to") LocalDate dateTo);

    //    e) returns all available (not borrowed) books
    @Query(value = ALL_AVAILABLE_BOOKS, nativeQuery = true)
    List<Book> getAllAvailableBooks();

}
