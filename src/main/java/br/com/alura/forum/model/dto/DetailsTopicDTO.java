package br.com.alura.forum.model.dto;

import br.com.alura.forum.model.enums.TopicStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
public class DetailsTopicDTO {

    private Long id;
    private String title;
    private String message;
    private LocalDateTime dateCreation;
    private String userName;
    private TopicStatus status;
    private List<AnswerDTO> answers;
}
