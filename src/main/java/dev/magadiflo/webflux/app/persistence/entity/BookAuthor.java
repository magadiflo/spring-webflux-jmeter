package dev.magadiflo.webflux.app.persistence.entity;

import lombok.*;
import org.springframework.data.relational.core.mapping.Table;

@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Setter
@Getter
@Table(name = "book_authors")
public class BookAuthor {
    private Long bookId;
    private Long authorId;
}
