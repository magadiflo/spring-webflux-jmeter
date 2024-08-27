package dev.magadiflo.webflux.app.model.dto;

import lombok.*;

import java.time.LocalDate;

@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Setter
@Getter
public class RegisterAuthor {
    private String firstName;
    private String lastName;
    private LocalDate birthdate;
}
