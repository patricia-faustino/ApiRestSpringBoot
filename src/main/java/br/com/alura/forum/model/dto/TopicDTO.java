package br.com.alura.forum.model.dto;

import br.com.alura.forum.model.entities.Topic;
import lombok.Data;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;

@Data
@SuperBuilder
public class TopicDTO {

    private Long id;
    private String title;
    private String message;
    private LocalDateTime dateCreation;

    public TopicDTO(Topic topic) {
        this.id = topic.getId();
        this.title = topic.getTitle();
        this.message = topic.getMessage();
        this.dateCreation = topic.getDateCreation();
    }
}
