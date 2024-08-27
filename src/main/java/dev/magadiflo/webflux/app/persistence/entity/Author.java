package dev.magadiflo.webflux.app.persistence.entity;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import java.time.LocalDate;

@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Setter
@Getter
@Table(name = "authors")
public class Author {
    @Id
    private Long id;
    private String firstName;
    private String lastName;
    private LocalDate birthdate;
}
