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
@Table(name = "books")
public class Book {
    @Id
    private Long id;
    private String title;
    private LocalDate publicationDate;
    private Boolean onlineAvailability;
}
