package dev.magadiflo.webflux.app.model.dto;

import lombok.*;

import java.time.LocalDate;

@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Setter
@Getter
public class UpdateAuthor {
    String firstName;
    String lastName;
    LocalDate birthdate;
}
