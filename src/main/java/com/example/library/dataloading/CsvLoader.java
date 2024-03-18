package com.example.library.dataloading;

import com.example.library.domain.Book;
import com.example.library.domain.BorrowingRecord;
import com.example.library.domain.User;
import com.fasterxml.jackson.databind.MappingIterator;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.springframework.core.io.ClassPathResource;

import java.io.File;
import java.util.Collections;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CsvLoader {

    private static final Logger LOG = LoggerFactory.getLogger(CsvLoader.class);

    public List<Book> getBooks() {
        return loadObjectList(Book.class, "books.csv");
    }

    public List<User> getUsers() {
        return loadObjectList(User.class, "user.csv");
    }

    public List<BorrowingRecord> getBorrowingInfo() {
        return loadObjectList(BorrowingRecord.class, "borrowed.csv");
    }

    private <T> List<T> loadObjectList(Class<T> type, String fileName) {
        try {
            CsvSchema bootstrapSchema = CsvSchema.emptySchema().withHeader();
            CsvMapper mapper = CsvMapper.csvBuilder()
                    .addModule(new JavaTimeModule())
                    .build();
            File file = new ClassPathResource(fileName).getFile();
            MappingIterator<T> readValues = mapper.readerFor(type).with(bootstrapSchema).readValues(file);
            return readValues.readAll();

        } catch (Exception e) {
            LOG.error("Error occurred while loading object list from file " + fileName, e);
            return Collections.emptyList();
        }
    }
}


