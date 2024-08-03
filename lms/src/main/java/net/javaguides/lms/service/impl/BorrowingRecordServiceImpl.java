package net.javaguides.lms.service.impl;

import lombok.AllArgsConstructor;
import net.javaguides.lms.dto.BorrowingRecordDto;
import net.javaguides.lms.entity.Book;
import net.javaguides.lms.entity.BorrowingRecord;
import net.javaguides.lms.entity.Patron;
import net.javaguides.lms.exception.ResourceNotFoundException;
import net.javaguides.lms.exception.ResourseNotAvailable;
import net.javaguides.lms.mapper.BorrowingRecordMapper;
import net.javaguides.lms.repository.BookRepository;
import net.javaguides.lms.repository.BorrowingRecordRepository;
import net.javaguides.lms.repository.PatronRepository;
import net.javaguides.lms.service.BorrowingRecordService;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class BorrowingRecordServiceImpl implements BorrowingRecordService {
    private BorrowingRecordRepository borrowingRecordRepository;
    private BookRepository bookRepository;
    private PatronRepository patronRepository;
    @Override
    public BorrowingRecordDto borrowBook(Long bookId, Long patronId) {
        List<BorrowingRecord> records = borrowingRecordRepository.findAll();

        for (BorrowingRecord record : records) {
            if (record.getBook().getId() == (bookId)  && record.getReturnDate() == null) {
                throw new ResourseNotAvailable("Book is not returned yet");
            }
        }
        Optional<Book> bookOptional = bookRepository.findById(bookId);
        Optional<Patron> patronOptional = patronRepository.findById(patronId);

        if (bookOptional.isPresent() && patronOptional.isPresent()) {
            Book book = bookOptional.get();
            Patron patron = patronOptional.get();

            BorrowingRecord borrowingRecord = new BorrowingRecord();
            borrowingRecord.setBook(book);
            borrowingRecord.setPatron(patron);
            borrowingRecord.setBorrowingDate(LocalDate.now());
            BorrowingRecord savedBorrowingRecord = borrowingRecordRepository.save(borrowingRecord);
            return BorrowingRecordMapper.mapToPatronDto(savedBorrowingRecord);
        } else {

            throw new ResourceNotFoundException("Book or Patron not found");
        }
    }

    @Override
    public BorrowingRecordDto returnBook(Long bookId, Long patronId) {
        List<BorrowingRecord> records = borrowingRecordRepository.findAll();

        for (BorrowingRecord record : records) {
            if (record.getBook().getId()==bookId && record.getPatron().getId()==patronId && record.getReturnDate() == null) {
                record.setReturnDate(LocalDate.now());
                BorrowingRecord savedRecord = borrowingRecordRepository.save(record);
                return BorrowingRecordMapper.mapToPatronDto(savedRecord);
            }
        }

        throw new ResourseNotAvailable("Borrowing record not found or already returned");

    }
}
