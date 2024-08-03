package net.javaguides.lms.mapper;


import net.javaguides.lms.dto.PatronDto;
import net.javaguides.lms.entity.Patron;

public class PatronMapper {
    public static PatronDto mapToPatronDto(Patron patron){
        return new PatronDto(
                patron.getId(),
                patron.getName(),
                patron.getNumber(),
                patron.getEmail()
        );
    }

    public static Patron mapToPatron(PatronDto patronDto){
        return new Patron(
                patronDto.getId(),
                patronDto.getName(),
                patronDto.getNumber(),
                patronDto.getEmail()
        );
    }
}
