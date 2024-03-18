package com.example.library.repositories;


import com.example.library.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    String USERS_WHO_BORROWED_AT_LEAST_ONE_BOOK = """
                SELECT DISTINCT U.*
                FROM USERS AS U
                    INNER JOIN BORROWING_RECORD AS B
                    ON (CONCAT_WS( ',', U.NAME, U.FIRST_NAME) = BORROWER)
            """;

    String NON_TERMINATED_USERS_WHO_HAVE_NOT_CURRENTLY_BORROWED_ANYTHING = """
                SELECT DISTINCT U.*
                FROM USERS AS U
                    LEFT JOIN BORROWING_RECORD AS B
                    ON (CONCAT_WS( ',', U.NAME, U.FIRST_NAME) = BORROWER)
                WHERE (U.MEMBER_TILL IS NULL OR U.MEMBER_TILL >= NOW())
                    AND B.ID IS NULL
            """;
    String USERS_WHO_HAVE_BORROWED_BOOK = """
                SELECT DISTINCT U.*
                FROM USERS AS U
                  INNER JOIN BORROWING_RECORD AS B
                  ON (CONCAT_WS( ',', U.NAME, U.FIRST_NAME) = BORROWER)
                WHERE :dt BETWEEN BORROWED_FROM AND BORROWED_TO
            """;

    //    a returns all users who have actually borrowed at least one book
    @Query(value = USERS_WHO_BORROWED_AT_LEAST_ONE_BOOK, nativeQuery = true)
    List<User> getUsersWhoBorrowedAtLeastOneBook();

    //    b) returns all non-terminated users who have not currently borrowed anything
    @Query(value = NON_TERMINATED_USERS_WHO_HAVE_NOT_CURRENTLY_BORROWED_ANYTHING, nativeQuery = true)
    List<User> getNonTerminatedUsersWhoHaveNotCurrentlyBorrowedAnything();

    //    // c) returns all users who have borrowed a book on a given date
    @Query(value = USERS_WHO_HAVE_BORROWED_BOOK, nativeQuery = true)
    List<User> getUsersWhoHaveBorrowedBook(@Param("dt") LocalDate date);

}