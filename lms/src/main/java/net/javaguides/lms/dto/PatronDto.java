package net.javaguides.lms.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PatronDto {
    private long id;
    @NotBlank(message = "name is mandatory.")
    private String name;
    @NotBlank(message = "number is mandatory.")
    private String number;
    private String email;
}
