package dev.magadiflo.webflux.app.persistence.repository;

import dev.magadiflo.webflux.app.persistence.entity.Book;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;

public interface BookRepository extends ReactiveCrudRepository<Book, Long> {
}
