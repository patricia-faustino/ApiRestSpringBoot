package br.com.alura.forum.model.dto;

import br.com.alura.forum.model.entities.Topic;
import br.com.alura.forum.model.enums.TopicStatus;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class DetailsTopicDTO {

    private Long id;
    private String title;
    private String message;
    private LocalDateTime dateCreation;
    private String userName;
    private TopicStatus status;
    private List<AnswerDTO> answers;

    public DetailsTopicDTO(Topic topic) {
        this.id = topic.getId();
        this.title = topic.getTitle();
        this.message = topic.getMessage();
        this.dateCreation = topic.getDateCreation();
        this.userName = topic.getUser().getName();
        this.status = topic.getStatus();
        this.answers = new ArrayList<>();
        answers.addAll(topic.getAnswers().stream()
                .map(AnswerDTO::new)
                .collect(Collectors.toList()));
    }

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
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

    public TopicStatus getStatus() {
        return status;
    }

    public List<AnswerDTO> getAnswers() {
        return answers;
    }
}
