package net.javaguides.lms.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "borrowing_record")
public class BorrowingRecord {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "book_id", nullable = false)
    private Book book;

    @ManyToOne
    @JoinColumn(name = "patron_id", nullable = false)
    private Patron patron;

    @NotBlank(message = "Must not be Null")
    @Column(name = "borrowingDate")
    private LocalDate borrowingDate;

    @Column(name = "returnDate")
    private LocalDate returnDate;
}
