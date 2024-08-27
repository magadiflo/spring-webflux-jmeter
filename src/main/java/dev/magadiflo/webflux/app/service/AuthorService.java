package dev.magadiflo.webflux.app.service;

import dev.magadiflo.webflux.app.model.dto.AuthorResponse;
import dev.magadiflo.webflux.app.model.dto.RegisterAuthor;
import dev.magadiflo.webflux.app.model.dto.UpdateAuthor;
import dev.magadiflo.webflux.app.persistence.entity.Author;
import org.springframework.data.domain.Page;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface AuthorService {
    Mono<Integer> saveAuthor(RegisterAuthor registerAuthor);

    Mono<AuthorResponse> updateAuthor(UpdateAuthor updateAuthor, Long authorId);

    Mono<Boolean> deleteAuthor(Long authorId);

    Flux<AuthorResponse> findAllAuthors();

    Mono<AuthorResponse> findAuthor(Long authorId);

    Mono<Page<Author>> findAllToPage(String query, int pageNumber, int pageSize);
}
