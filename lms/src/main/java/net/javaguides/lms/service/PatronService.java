package net.javaguides.lms.service;

import net.javaguides.lms.dto.PatronDto;

import java.util.List;

public interface PatronService {
    PatronDto createPatron(PatronDto patronDto);
    PatronDto getPatronById(Long id);
    List<PatronDto> getAllPatrons();
    PatronDto updatePatron(Long id , PatronDto updatedPatron);
    void deletePatron (Long id);
}
