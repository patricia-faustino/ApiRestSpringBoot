package br.com.alura.forum.model.dto;

import br.com.alura.forum.model.entities.Answer;

import java.time.LocalDateTime;

public class AnswerDTO {

    private Long id;
    private String message;
    private LocalDateTime dateCreation;
    private String userName;

    public AnswerDTO(Answer answer) {
        this.id = answer.getId();
        this.message = answer.getMessage();
        this.userName = answer.getUser().getName();
    }

    public Long getId() {
        return id;
    }

    public String getMessage() {
        return message;
    }

    public LocalDateTime getDateCreation() {
        return dateCreation;
    }

    public String getUserName() {
        return userName;
    }
}
