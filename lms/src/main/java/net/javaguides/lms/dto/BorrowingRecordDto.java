package net.javaguides.lms.dto;

import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import net.javaguides.lms.entity.Book;
import net.javaguides.lms.entity.Patron;

import java.time.LocalDate;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BorrowingRecordDto {
    private Long id;
    private Book book;
    private Patron patron;
    @NotBlank(message = "borrowingDate is mandatory.")
    private LocalDate borrowingDate;
    private LocalDate returnDate;
}
