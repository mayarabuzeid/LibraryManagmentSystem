package net.javaguides.lms.service.impl;

import lombok.AllArgsConstructor;
import net.javaguides.lms.dto.PatronDto;
import net.javaguides.lms.entity.Patron;
import net.javaguides.lms.exception.ResourceNotFoundException;
import net.javaguides.lms.mapper.PatronMapper;
import net.javaguides.lms.repository.PatronRepository;
import net.javaguides.lms.service.PatronService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class PatronServiceImpl implements PatronService {
    private PatronRepository patronRepository;
    @Override
    public PatronDto createPatron(PatronDto patronDto) {
        Patron patron = PatronMapper.mapToPatron(patronDto);
        Patron savedPatron = patronRepository.save(patron);
        return PatronMapper.mapToPatronDto(savedPatron);
    }

    @Override
    public PatronDto getPatronById(Long id) {
        Patron patron=patronRepository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("Patron does not exist with given id:"+ id));
        return  PatronMapper.mapToPatronDto(patron);
    }

    @Override
    public List<PatronDto> getAllPatrons() {
        List<Patron> patrons =patronRepository.findAll();
        return patrons.stream().map((patron -> PatronMapper.mapToPatronDto(patron)))
                .collect(Collectors.toList());
    }

    @Override
    public PatronDto updatePatron(Long id, PatronDto updatedPatron) {
        Patron patron=patronRepository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("Patron does not exist with given id:"+ id));
        patron.setEmail(updatedPatron.getEmail());
        patron.setName(updatedPatron.getName());
        patron.setNumber(updatedPatron.getNumber());
        Patron updatedPatronObj = patronRepository.save(patron);
        return PatronMapper.mapToPatronDto(updatedPatronObj);
    }

    @Override
    public void deletePatron(Long id) {
        Patron patron=patronRepository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("Patron does not exist with given id:"+ id));
        patronRepository.deleteById(id);
    }
}
