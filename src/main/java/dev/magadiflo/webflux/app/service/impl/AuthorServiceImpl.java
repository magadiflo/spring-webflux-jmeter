package dev.magadiflo.webflux.app.service.impl;

import dev.magadiflo.webflux.app.exceptions.UserNotFoundException;
import dev.magadiflo.webflux.app.model.dto.AuthorResponse;
import dev.magadiflo.webflux.app.model.dto.RegisterAuthor;
import dev.magadiflo.webflux.app.model.dto.UpdateAuthor;
import dev.magadiflo.webflux.app.persistence.entity.Author;
import dev.magadiflo.webflux.app.persistence.repository.AuthorRepository;
import dev.magadiflo.webflux.app.service.AuthorService;
import dev.magadiflo.webflux.app.utils.AuthorMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Slf4j
@RequiredArgsConstructor
@Service
@Transactional(readOnly = true)
public class AuthorServiceImpl implements AuthorService {

    private final AuthorRepository authorRepository;
    private final AuthorMapper authorMapper;

    @Override
    @Transactional
    public Mono<Integer> saveAuthor(RegisterAuthor registerAuthor) {
        return Mono.just(registerAuthor)
                .flatMap(this.authorMapper::toAuthor)
                .flatMap(this.authorRepository::saveAuthor)
                .doOnNext(authorDB -> log.info("Filas afectadas en el insert: {}", authorDB));
    }

    @Override
    @Transactional
    public Mono<AuthorResponse> updateAuthor(UpdateAuthor updateAuthor, Long authorId) {
        return this.authorRepository.findById(authorId)
                .flatMap(author -> this.authorMapper.toAuthor(updateAuthor, authorId))
                .flatMap(this.authorRepository::updateAuthor)
                .flatMap(affectedRows -> this.authorRepository.findById(authorId))
                .flatMap(this.authorMapper::toAuthorResponse)
                .switchIfEmpty(Mono.error(new UserNotFoundException("No se encontró el author con id %s para actualizar".formatted(authorId))));
    }

    @Override
    @Transactional
    public Mono<Boolean> deleteAuthor(Long authorId) {
        return this.authorRepository.findById(authorId)
                .flatMap(authorDB -> this.authorRepository.deleteById(authorId).then(Mono.just(true)))
                .switchIfEmpty(Mono.error(new UserNotFoundException("No se encontró el author con id %s para eliminar".formatted(authorId))));
    }

    @Override
    public Flux<AuthorResponse> findAllAuthors() {
        return this.authorRepository.findAll()
                .flatMap(this.authorMapper::toAuthorResponse);
    }

    @Override
    public Mono<AuthorResponse> findAuthor(Long authorId) {
        return this.authorRepository.findById(authorId)
                .flatMap(this.authorMapper::toAuthorResponse)
                .switchIfEmpty(Mono.error(new UserNotFoundException("No se encontró el author con id %s".formatted(authorId))));
    }

    @Override
    public Mono<Page<Author>> findAllToPage(String query, int pageNumber, int pageSize) {
        Pageable pageable = PageRequest.of(pageNumber, pageSize);
        Mono<Integer> totalRecords = this.authorRepository.totalByQueryContaining(query);

        return this.authorRepository.findByQueryContaining(query, pageable)
                .collectList()
                .zipWith(totalRecords, (authors, total) -> new PageImpl<>(authors, pageable, total));
    }

}
