package com.example.library.repositories;

import com.example.library.domain.BorrowingRecord;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BorrowingRepository extends JpaRepository<BorrowingRecord, Long> {
}
