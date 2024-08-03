package net.javaguides.lms.UnitTests;

import net.javaguides.lms.dto.PatronDto;
import net.javaguides.lms.dto.BookDto;
import net.javaguides.lms.entity.Book;
import net.javaguides.lms.entity.BorrowingRecord;
import net.javaguides.lms.entity.Patron;
import net.javaguides.lms.exception.ResourceNotFoundException;
import net.javaguides.lms.exception.ResourseNotAvailable;
import net.javaguides.lms.repository.BookRepository;
import net.javaguides.lms.repository.BorrowingRecordRepository;
import net.javaguides.lms.repository.PatronRepository;
import net.javaguides.lms.service.BookService;
import net.javaguides.lms.service.BorrowingRecordService;
import net.javaguides.lms.dto.BorrowingRecordDto;

import net.javaguides.lms.service.PatronService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.nio.charset.Charset;
import java.time.LocalDate;
import java.util.Optional;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

@SpringBootTest
public class BorrowingRecordTests {
    @Autowired
    private BorrowingRecordService borrowingRecordService;

    @MockBean
    private BorrowingRecordRepository borrowingRecordRepository;

    @Autowired
    private PatronService patronService;

    @MockBean
    private PatronRepository patronRepository;

    @Autowired
    private BookService bookService;

    @MockBean
    private BookRepository bookRepository;

    @Test
    public void testBorrowBookThrowsResourceNotAvailable() {
        Long patronId = 1L;
        Long bookId = 1L;
        BorrowingRecord record=getBorrowingRecord(bookId,patronId);
        when(borrowingRecordRepository.findAll()).thenReturn(Stream.of(record).collect(Collectors.toList()));
        // Assert that ResourceNotFoundException is thrown
        assertThrows(ResourseNotAvailable.class, () -> {
            borrowingRecordService.borrowBook(bookId,patronId);
        });
        assertThrows(ResourseNotAvailable.class,()->borrowingRecordService.borrowBook(bookId,patronId));
    }


    private String generateString (){
        byte[] array = new byte[7]; // length is bounded by 7
        new Random().nextBytes(array);
        String generatedString = new String(array, Charset.forName("UTF-8"));
        return generatedString;
    }
    private Book getBook(Long id){
        Book book = new Book();
        book.setId(id);
        book.setTitle("Spring Boot Guide");
        book.setPublicationYear(2020);
        book.setAuthor("John Doe");
        book.setISBN(generateString());
        return book;
    }

    private Patron getPatron(Long id){
        Patron patron = new Patron();
        patron.setId(id);
        patron.setName("john Mark");
        patron.setEmail(generateString());
        patron.setNumber(generateString());
        return patron;
    }
    private BorrowingRecord getBorrowingRecord(Long bookId,Long patronId){
            BorrowingRecord record = new BorrowingRecord();
            record.setPatron(getPatron(patronId));
            record.setBook(getBook(bookId));
           return record;
    }

    private BorrowingRecordDto getBorrowingRecordDto(Long bookId,Long patronId){
        BorrowingRecordDto record = new BorrowingRecordDto();
        record.setPatron(getPatron(patronId));
        record.setBook(getBook(bookId));
        return record;
    }
}
