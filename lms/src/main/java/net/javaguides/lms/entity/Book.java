package net.javaguides.lms.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.*;
import org.antlr.v4.runtime.misc.NotNull;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "books")
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotBlank(message = "Must not be Null")
    @Column(name = "title")
    private String title;

    @Column(name = "author")
    private String author;

    @Column(name = "publicationYear")
    private int publicationYear;

    @NotBlank(message ="ISBN cannot be Null")
    @Column(name = "ISBN")
    private String ISBN;
}
