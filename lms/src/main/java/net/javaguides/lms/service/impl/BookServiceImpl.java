package net.javaguides.lms.service.impl;

import lombok.AllArgsConstructor;
import net.javaguides.lms.dto.BookDto;
import net.javaguides.lms.entity.Book;
import net.javaguides.lms.exception.ResourceNotFoundException;
import net.javaguides.lms.mapper.BookMapper;
import net.javaguides.lms.repository.BookRepository;
import net.javaguides.lms.service.BookService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class BookServiceImpl implements BookService {
    private BookRepository bookRepository;
    @Override
    public BookDto createBook(BookDto bookDto) {
        Book book = BookMapper.mapToBook(bookDto);
        Book savedbook = bookRepository.save(book);
        return BookMapper.mapToBookDto(savedbook);
    }

    @Override
    public BookDto getBookById(Long id) {
        Book book = bookRepository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("Book does not exist with given id :" + id ));
        return BookMapper.mapToBookDto(book);
    }

    @Override
    public List<BookDto> getAllBooks() {
        List<Book> books = bookRepository.findAll();
        return books.stream().map((book -> BookMapper.mapToBookDto(book)))
                .collect(Collectors.toList());
    }

    @Override
    public BookDto updateBook(Long id, BookDto updatedBook) {
        Book book = bookRepository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("Book does not exist with given id :" + id ));
        book.setAuthor(updatedBook.getAuthor());
        book.setISBN(updatedBook.getISBN());
        book.setTitle(updatedBook.getTitle());
        book.setPublicationYear(updatedBook.getPublicationYear());
        Book updatedBookObj = bookRepository.save(book);
        return BookMapper.mapToBookDto(updatedBookObj);
    }

    @Override
    public void deleteBook(Long id) {
        Book book = bookRepository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("Book does not exist with given id :" + id ));
        bookRepository.deleteById(id);
    }

}
