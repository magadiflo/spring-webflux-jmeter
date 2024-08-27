package dev.magadiflo.webflux.app.controller;

import dev.magadiflo.webflux.app.model.dto.AuthorResponse;
import dev.magadiflo.webflux.app.model.dto.RegisterAuthor;
import dev.magadiflo.webflux.app.model.dto.UpdateAuthor;
import dev.magadiflo.webflux.app.persistence.entity.Author;
import dev.magadiflo.webflux.app.service.AuthorService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping(path = "/api/v1/authors")
public class AuthorController {

    private final AuthorService authorService;

    @GetMapping
    public Mono<ResponseEntity<Flux<AuthorResponse>>> findAllAuthors() {
        return Mono.just(ResponseEntity.ok(this.authorService.findAllAuthors()));
    }

    @GetMapping(path = "/pages")
    public Mono<ResponseEntity<Page<Author>>> findAllPage(@RequestParam(value = "query", defaultValue = "", required = false) String query,
                                                          @RequestParam(value = "page", defaultValue = "0", required = false) int pageNumber,
                                                          @RequestParam(value = "size", defaultValue = "5", required = false) int pageSize) {
        return this.authorService.findAllToPage(query, pageNumber, pageSize)
                .map(ResponseEntity::ok);
    }

    @GetMapping(path = "/{authorId}")
    public Mono<ResponseEntity<AuthorResponse>> getAuthor(@PathVariable Long authorId) {
        return this.authorService.findAuthor(authorId)
                .map(ResponseEntity::ok);
    }

    @PostMapping
    public Mono<ResponseEntity<Void>> registerAuthor(@RequestBody RegisterAuthor registerAuthor) {
        return this.authorService.saveAuthor(registerAuthor)
                .flatMap(rowsAffected -> Mono.just(new ResponseEntity<>(HttpStatus.CREATED)));
    }

    @PutMapping(path = "/{authorId}")
    public Mono<ResponseEntity<AuthorResponse>> updateAuthor(@RequestBody UpdateAuthor updateAuthor,
                                                             @PathVariable Long authorId) {
        return this.authorService.updateAuthor(updateAuthor, authorId)
                .map(ResponseEntity::ok);
    }

    @DeleteMapping(path = "/{authorId}")
    public Mono<ResponseEntity<Void>> deleteAuthor(@PathVariable Long authorId) {
        return this.authorService.deleteAuthor(authorId)
                .map(wasDeleted -> ResponseEntity.noContent().build());
    }

}
