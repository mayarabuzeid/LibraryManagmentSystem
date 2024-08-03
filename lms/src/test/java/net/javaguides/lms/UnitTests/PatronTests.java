package net.javaguides.lms.UnitTests;

import net.javaguides.lms.dto.PatronDto;
import net.javaguides.lms.entity.Patron;
import net.javaguides.lms.exception.ResourceNotFoundException;
import net.javaguides.lms.repository.PatronRepository;
import net.javaguides.lms.service.PatronService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.nio.charset.Charset;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.never;

@SpringBootTest
public class PatronTests {
    @Autowired
    private PatronService patronService;

    @MockBean
    private PatronRepository patronRepository;

    @Test
    public void testCreatePatron() {
        Patron patron = getPatron();
        PatronDto patronDto = getPatronDto();
        when(patronRepository.save(any(Patron.class))).thenReturn(patron);
        PatronDto savedPatron = patronService.createPatron(patronDto);
        assertNotNull(savedPatron.getId());
    }

    @Test
    public void testGetPatron() {
        Patron patron = getPatron();
        when(patronRepository.findById(anyLong())).thenReturn(Optional.of(patron));
        PatronDto found = patronService.getPatronById(patron.getId());
        assertEquals("john Mark", found.getName());
    }

    @Test
    public void testGetPatronThrowsException() {
        Long dummyId = 1232344L;
        when(patronRepository.findById(dummyId)).thenThrow(ResourceNotFoundException.class);
        assertThrows(ResourceNotFoundException.class,()->patronService.getPatronById(dummyId));
    }

    @Test
    public void testGetAllPatrons() {

        Patron patron1 = getPatron();
        Patron patron2 = getPatron();
        when(patronRepository.findAll()).thenReturn(Stream.of(patron1,patron2).collect(Collectors.toList()));
        List<PatronDto> patrons = patronService.getAllPatrons();
        int size = patrons.size();
        assertEquals(2,size);
    }

    @Test
    public void testUpdatePatron() {
        Long patronId = 1L;
        Patron existingPatron = getPatron();
        PatronDto patronDto = new PatronDto();
        patronDto.setName("John Adam");
        patronDto.setEmail("john@email.com");
        patronDto.setNumber("01234557890");

        when(patronRepository.findById(anyLong())).thenReturn(Optional.of(existingPatron));
        when(patronRepository.save(any(Patron.class))).thenAnswer(invocation -> invocation.getArgument(0));

        PatronDto updatedPatron = patronService.updatePatron(patronId, patronDto);

        assertEquals("John Adam", updatedPatron.getName());
        assertEquals("john@email.com", updatedPatron.getEmail());
        assertEquals("01234557890", updatedPatron.getNumber());


    }

    @Test
    public void testDeletePatron() {
        Long patronId = 1L;
        Patron patron = getPatron();

        when(patronRepository.findById(anyLong())).thenReturn(Optional.of(patron));

        patronService.deletePatron(patronId);

        // Verify that deleteById was called with the correct id
        verify(patronRepository, times(1)).deleteById(patronId);
    }

    @Test
    public void testDeletePatronThrowsResourceNotFound() {
        Long patronId = 1L;

        when(patronRepository.findById(anyLong())).thenReturn(Optional.empty());

        // Assert that ResourceNotFoundException is thrown
        assertThrows(ResourceNotFoundException.class, () -> {
            patronService.deletePatron(patronId);
        });

        // Verify that deleteById was not called
        verify(patronRepository, never()).deleteById(patronId);
    }




    private String generateString (){
        byte[] array = new byte[7]; // length is bounded by 7
        new Random().nextBytes(array);
        String generatedString = new String(array, Charset.forName("UTF-8"));
        return generatedString;
    }
    private PatronDto getPatronDto(){
        PatronDto patronDto = new PatronDto();
        patronDto.setName("john Mark");
        patronDto.setEmail(generateString());
        patronDto.setNumber(generateString());
        return patronDto;
    }
    private Patron getPatron(){
        Patron patron = new Patron();
        patron.setName("john Mark");
        patron.setEmail(generateString());
        patron.setNumber(generateString());
        return patron;
    }
}
