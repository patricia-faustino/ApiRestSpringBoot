package br.com.alura.forum.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
public class AnswerDTO {

    private Long id;
    private String message;
    private LocalDateTime dateCreation;
    private String userName;
}
