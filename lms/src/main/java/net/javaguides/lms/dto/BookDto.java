package net.javaguides.lms.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.UniqueElements;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BookDto {
    private long id;

    @NotBlank(message = "title is mandatory.")
    private String title;
    private String author;
    private int publicationYear;

    @NotBlank(message = "ISBN is mandatory.")
    private String ISBN;
}
