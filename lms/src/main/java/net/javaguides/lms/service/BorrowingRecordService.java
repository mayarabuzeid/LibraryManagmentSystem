package net.javaguides.lms.service;

import net.javaguides.lms.dto.BorrowingRecordDto;

public interface BorrowingRecordService {
    BorrowingRecordDto borrowBook( Long bookId, Long patronId);
    BorrowingRecordDto returnBook( Long bookId, Long patronId);
}
