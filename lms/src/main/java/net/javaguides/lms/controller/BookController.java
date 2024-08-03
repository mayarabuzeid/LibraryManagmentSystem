package net.javaguides.lms.controller;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import net.javaguides.lms.dto.BookDto;
import net.javaguides.lms.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/api/books")
public class BookController {
    @Autowired
    private BookService bookService;
    //Add Book REST API
    @PostMapping
    public ResponseEntity<BookDto> createBook(@Valid @RequestBody BookDto bookDto){
        BookDto savedBook = bookService.createBook(bookDto);
        return new ResponseEntity<>(savedBook, HttpStatus.CREATED);
    }

    //get Book by Id REST API
    @GetMapping ("{id}")
    public ResponseEntity<BookDto> getBookById(@PathVariable("id") Long id){
        BookDto book = bookService.getBookById(id);
        return ResponseEntity.ok(book);
    }

    //get all Books REST API
    @GetMapping
    public ResponseEntity<List<BookDto>> getAllBooks(){
        List<BookDto> books = bookService.getAllBooks();
        return ResponseEntity.ok(books);
    }
    //Update Book by Id REST API
    @PutMapping ("{id}")
    public ResponseEntity<BookDto> updateBook(@PathVariable("id") Long id, @Valid @RequestBody BookDto bookDto){
        BookDto book = bookService.updateBook(id,bookDto);
        return ResponseEntity.ok(book);
    }

    //delete Book by Id REST API
    @DeleteMapping ("{id}")
    public ResponseEntity<String> deleteBookById(@PathVariable("id") Long id){
        bookService.deleteBook(id);
        return ResponseEntity.ok("Book deleted Successfully!.");
    }
}
