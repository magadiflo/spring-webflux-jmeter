package dev.magadiflo.webflux.app.utils;

import dev.magadiflo.webflux.app.exceptions.MappingException;
import dev.magadiflo.webflux.app.model.dto.AuthorResponse;
import dev.magadiflo.webflux.app.model.dto.RegisterAuthor;
import dev.magadiflo.webflux.app.model.dto.UpdateAuthor;
import dev.magadiflo.webflux.app.persistence.entity.Author;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@Slf4j
@RequiredArgsConstructor
@Component
public class AuthorMapper {

    private final ModelMapper modelMapper;

    public Mono<Author> toAuthor(RegisterAuthor registerAuthor) {
        try {
            Author author = this.modelMapper.map(registerAuthor, Author.class);
            return Mono.just(author);
        } catch (Exception e) {
            log.error("Error al mapear author para inserción:: {}", e.getMessage());
            return Mono.error(new MappingException("Error en la conversión de Author: " + e.getMessage()));
        }
    }

    public Mono<Author> toAuthor(UpdateAuthor updateAuthor, Long authorId) {
        try {
            Author author = this.modelMapper.map(updateAuthor, Author.class);
            author.setId(authorId);
            return Mono.just(author);
        } catch (Exception e) {
            log.error("Error al mapear author para actualización :: {}", e.getMessage());
            return Mono.error(new MappingException("Error en la conversión de Author: " + e.getMessage()));
        }
    }

    public Mono<AuthorResponse> toAuthorResponse(Author author) {
        try {
            AuthorResponse authorResponse = this.modelMapper.map(author, AuthorResponse.class);
            return Mono.just(authorResponse);
        } catch (Exception e) {
            log.error("Error al mapear author para respuesta :: {}", e.getMessage());
            return Mono.error(new MappingException("Error en la conversión de Author: " + e.getMessage()));
        }
    }
}
