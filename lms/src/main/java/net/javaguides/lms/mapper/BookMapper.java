package net.javaguides.lms.mapper;

import net.javaguides.lms.dto.BookDto;
import net.javaguides.lms.entity.Book;

public class BookMapper {
    public static BookDto mapToBookDto(Book book){
        return new BookDto(
                book.getId(),
                book.getTitle(),
                book.getAuthor(),
                book.getPublicationYear(),
                book.getISBN()
        );
    }

    public static Book mapToBook(BookDto bookDto){
        return new Book(
                bookDto.getId(),
                bookDto.getTitle(),
                bookDto.getAuthor(),
                bookDto.getPublicationYear(),
                bookDto.getISBN()
        );
    }
}
