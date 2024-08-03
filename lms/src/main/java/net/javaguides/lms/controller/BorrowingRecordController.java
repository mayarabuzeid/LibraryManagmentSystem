package net.javaguides.lms.controller;

import lombok.AllArgsConstructor;
import net.javaguides.lms.dto.BorrowingRecordDto;
import net.javaguides.lms.service.BorrowingRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@AllArgsConstructor
@RestController
@RequestMapping("/api")
public class BorrowingRecordController {
    @Autowired
    private BorrowingRecordService borrowingRecordService;

    //add borrowing record
    @PostMapping("/borrow/{bookId}/patron/{patronId}")
    public ResponseEntity<BorrowingRecordDto> borrowBook(@PathVariable Long bookId, @PathVariable Long patronId) {
        BorrowingRecordDto borrowingRecordDto= borrowingRecordService.borrowBook(bookId,patronId);
        return new ResponseEntity<>(borrowingRecordDto, HttpStatus.CREATED);
    }

    //Return a book by a patron
    @PutMapping("/return/{bookId}/patron/{patronId}")
    public ResponseEntity<BorrowingRecordDto> returnBook(@PathVariable Long bookId, @PathVariable Long patronId) {
        BorrowingRecordDto borrowingRecordDto = borrowingRecordService.returnBook(bookId,patronId);
        return ResponseEntity.ok(borrowingRecordDto);
    }

}
