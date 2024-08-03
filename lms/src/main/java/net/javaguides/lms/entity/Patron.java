package net.javaguides.lms.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "patrons")
public class Patron {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotBlank(message = "Must not be Null")
    @Column(name = "name")
    private String name;

    @NotBlank(message = "Must not be Null")
    @Column(name = "number", unique = true)
    private String number;


    @Column(name = "email", unique = true)
    private String email;
}
