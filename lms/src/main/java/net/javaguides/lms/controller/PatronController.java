package net.javaguides.lms.controller;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import net.javaguides.lms.dto.BookDto;
import net.javaguides.lms.dto.PatronDto;
import net.javaguides.lms.service.BookService;
import net.javaguides.lms.service.PatronService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/api/patrons")
public class PatronController {
    @Autowired
    private PatronService patronService;
    //Add Patron REST API
    @PostMapping
    public ResponseEntity<PatronDto> createPatron(@Valid @RequestBody PatronDto patronDto){
        PatronDto savedPatron = patronService.createPatron(patronDto);
        return new ResponseEntity<>(savedPatron, HttpStatus.CREATED);
    }
    //get Patron by Id REST API
    @GetMapping ("{id}")
    public ResponseEntity<PatronDto> getPatronById(@PathVariable("id") Long id){
        PatronDto patronDto = patronService.getPatronById(id);
        return ResponseEntity.ok(patronDto);
    }

    //get All Patrons REST API
    @GetMapping
    public ResponseEntity<List<PatronDto>> getAllPatrons(){
        List<PatronDto> patrons = patronService.getAllPatrons();
        return ResponseEntity.ok(patrons);
    }

    //update Patron by Id REST API
    @PutMapping ("{id}")
    public ResponseEntity<PatronDto> updatePatron(@PathVariable("id") Long id,@Valid @RequestBody PatronDto patronDto){
        PatronDto patron = patronService.updatePatron(id,patronDto);
        return ResponseEntity.ok(patron);
    }

    @DeleteMapping ("{id}")
    public ResponseEntity<String> deletePatronById(@PathVariable("id") Long id){
         patronService.deletePatron(id);
        return ResponseEntity.ok("Patron Deleted Successfully!.");
    }
}
