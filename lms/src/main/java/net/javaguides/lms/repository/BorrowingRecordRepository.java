package net.javaguides.lms.repository;

import net.javaguides.lms.entity.BorrowingRecord;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BorrowingRecordRepository extends JpaRepository<BorrowingRecord,Long> {
}
