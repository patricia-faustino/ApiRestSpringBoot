package br.com.alura.forum.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
public class TopicForm {

    @NotNull
    @NotEmpty
    @Length(min = 5)
    private String title;

    @NotNull
    @NotEmpty
    @Length(min = 10)
    private String message;

    @NotNull
    @NotEmpty
    private String courseName;
}
