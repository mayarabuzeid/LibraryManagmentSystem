package net.javaguides.lms.service;

import net.javaguides.lms.dto.BookDto;
import net.javaguides.lms.entity.Book;

import java.util.List;

public interface BookService {
    BookDto createBook(BookDto bookDto);
    BookDto getBookById(Long id);
    List<BookDto> getAllBooks();
    BookDto updateBook(Long id, BookDto updatedBook);
    void deleteBook(Long id);
}
