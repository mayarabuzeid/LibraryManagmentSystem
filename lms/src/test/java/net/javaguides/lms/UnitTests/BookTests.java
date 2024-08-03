package net.javaguides.lms.UnitTests;


import net.javaguides.lms.exception.ResourceNotFoundException;
import net.javaguides.lms.repository.BookRepository;
import net.javaguides.lms.service.BookService;
import net.javaguides.lms.dto.BookDto;
import net.javaguides.lms.entity.Book;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.nio.charset.Charset;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

@SpringBootTest
public class BookTests {
    @Autowired
    private BookService bookService;

    @MockBean
    private BookRepository bookRepository;

    @Test
    public void testCreateBook() {
        Book book = getBook();
        BookDto bookDto = getBookDto();
        when(bookRepository.save(any(Book.class))).thenReturn(book);
        BookDto savedBook = bookService.createBook(bookDto);
        assertNotNull(savedBook.getId());
    }

    @Test
    public void testGetBook() {
        Book book = getBook();
        when(bookRepository.findById(anyLong())).thenReturn(Optional.of(book));
        BookDto found = bookService.getBookById(book.getId());
        assertEquals("Spring Boot Guide", found.getTitle());
    }

    @Test
    public void testGetBookThrowsException() {
        Long dummyId = 1232344L;
        when(bookRepository.findById(dummyId)).thenThrow(ResourceNotFoundException.class);
        assertThrows(ResourceNotFoundException.class,()->bookService.getBookById(dummyId));
    }

    @Test
    public void testGetAllBooks() {

        Book book1 = getBook();
        Book book2 = getBook();
        when(bookRepository.findAll()).thenReturn(Stream.of(book1,book2).collect(Collectors.toList()));
        List<BookDto> books = bookService.getAllBooks();
        int size = books.size();
        assertEquals(2,size);
    }

    @Test
    public void testUpdateBook() {
            Long bookId = 1L;
            Book existingBook = getBook();
            BookDto bookDto = new BookDto();
            bookDto.setTitle("Updated Spring Boot Guide");
            bookDto.setPublicationYear(2021);
            bookDto.setAuthor("Jane Doe");
            bookDto.setISBN("987654321");

            when(bookRepository.findById(anyLong())).thenReturn(Optional.of(existingBook));
            when(bookRepository.save(any(Book.class))).thenAnswer(invocation -> invocation.getArgument(0));

            BookDto updatedBook = bookService.updateBook(bookId, bookDto);

            assertEquals("Updated Spring Boot Guide", updatedBook.getTitle());
            assertEquals(2021, updatedBook.getPublicationYear());
            assertEquals("Jane Doe", updatedBook.getAuthor());
            assertEquals("987654321", updatedBook.getISBN());


    }

    @Test
    public void testDeleteBook() {
        Long bookId = 1L;
        Book book = getBook();

        when(bookRepository.findById(anyLong())).thenReturn(Optional.of(book));

        bookService.deleteBook(bookId);

        // Verify that deleteById was called with the correct id
        verify(bookRepository, times(1)).deleteById(bookId);
    }

    @Test
    public void testDeleteBookThrowsResourceNotFound() {
        Long bookId = 1L;

        when(bookRepository.findById(anyLong())).thenReturn(Optional.empty());

        // Assert that ResourceNotFoundException is thrown
        assertThrows(ResourceNotFoundException.class, () -> {
            bookService.deleteBook(bookId);
        });

        // Verify that deleteById was not called
        verify(bookRepository, never()).deleteById(bookId);
    }




    private BookDto getBookDto(){
        BookDto bookDto = new BookDto();
        bookDto.setTitle("Spring Boot Guide");
        bookDto.setPublicationYear(2020);
        bookDto.setAuthor("John Doe");
        byte[] array = new byte[7]; // length is bounded by 7
        new Random().nextBytes(array);
        String generatedString = new String(array, Charset.forName("UTF-8"));
        bookDto.setISBN(generatedString);
        return bookDto;
    }
    private Book getBook(){
        Book book = new Book();
        book.setTitle("Spring Boot Guide");
        book.setPublicationYear(2020);
        book.setAuthor("John Doe");
        byte[] array = new byte[7]; // length is bounded by 7
        new Random().nextBytes(array);
        String generatedString = new String(array, Charset.forName("UTF-8"));
        book.setISBN(generatedString);
        return book;
    }
}
