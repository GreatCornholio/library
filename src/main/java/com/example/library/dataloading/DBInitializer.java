package com.example.library.dataloading;


import com.example.library.domain.Book;
import com.example.library.domain.BorrowingRecord;
import com.example.library.domain.User;
import com.example.library.repositories.BookRepository;
import com.example.library.repositories.BorrowingRepository;
import com.example.library.repositories.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

@Component
public class DBInitializer implements ApplicationListener<ContextRefreshedEvent> {

    private static final Logger LOG = LoggerFactory.getLogger(DBInitializer.class);

    private final UserRepository userRepository;
    private final BookRepository bookRepository;
    private final BorrowingRepository borrowingRepository;


    public DBInitializer(UserRepository userRepository, BookRepository bookRepository,
                         BorrowingRepository borrowingRepository) {
        this.userRepository = userRepository;
        this.bookRepository = bookRepository;
        this.borrowingRepository = borrowingRepository;
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {

        CsvLoader loader = new CsvLoader();

        for (Book b : loader.getBooks()) {
            if (b.getTitle() != null && !b.getTitle().isEmpty()) {
                bookRepository.save(b);
            }
        }

        for (User u : loader.getUsers()) {
            if (u.getName() != null && !u.getName().isEmpty()) {
                userRepository.save(u);
            }
        }

        for (BorrowingRecord b : loader.getBorrowingInfo()) {
            if (b.getBorrower() != null && !b.getBorrower().isEmpty()) {
                borrowingRepository.save(b);
            }
        }
        LOG.info("DB loading finished");
    }
}
