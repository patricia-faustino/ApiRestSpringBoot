package br.com.alura.forum.service;

import br.com.alura.forum.model.dto.*;
import br.com.alura.forum.model.entities.Answer;
import br.com.alura.forum.model.entities.Course;
import br.com.alura.forum.model.entities.Topic;
import br.com.alura.forum.repository.CourseRepository;
import br.com.alura.forum.repository.TopicRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service

@RequiredArgsConstructor
public class TopicService {

    private final TopicRepository topicRepository;

    private final CourseRepository courseRepository;

    public List<TopicDTO> getTopics() {
        List<Topic> topics = topicRepository.findAll();

        return topics.stream()
                .map(TopicDTO::new)
                .collect(Collectors.toList());
    }

    public Page<TopicDTO> getTopicsByCourseName(String courseName,  Pageable pageable) {
        Page<Topic> topics = courseNameIsNullOrEmpty(courseName, pageable);

        return topics.map(TopicDTO::new);
    }

    private Page<Topic> courseNameIsNullOrEmpty(String courseName, Pageable pageable) {
        Page<Topic> topics;
        if(courseName == null || courseName.trim().isEmpty()) {
            topics = topicRepository.findAll(pageable);
        } else{
            topics = topicRepository.findByCourseName(courseName, pageable);
        }
        return topics;
    }

    @Transactional
    public TopicDTO saveTopic(TopicForm topicForm) {
        Course course = findCourseByName(topicForm);
        Topic topic = new Topic(topicForm.getTitle(), topicForm.getMessage(), course);

        topicRepository.save(topic);

        return new TopicDTO(topic);
    }

    private Course findCourseByName(TopicForm topicForm) {
        Optional<Course> course = courseRepository.findByName(topicForm.getCourseName());
        return course.orElseThrow(() -> new RuntimeException("Topic not found."));
    }

    public DetailsTopicDTO getTopicById(Long id) {
        Topic topic = findTopicById(id);

        List<AnswerDTO> answerDTOS = buildAnswers(topic.getAnswers());

        return DetailsTopicDTO.builder()
                .id(topic.getId())
                .answers(answerDTOS)
                .message(topic.getMessage())
                .dateCreation(topic.getDateCreation())
                .title(topic.getTitle())
                .status(topic.getStatus())
                .userName(topic.getUser().getName())
                .build();
    }

    private List<AnswerDTO> buildAnswers(List<Answer> answers) {
        List<AnswerDTO> answerDTOS = new ArrayList<>();

        answers.forEach((answer) -> {
            AnswerDTO answerDTO = AnswerDTO.builder()
                    .id(answer.getId())
                    .dateCreation(answer.getDateCreation())
                    .message(answer.getMessage())
                    .userName(answer.getUser().getName())
                    .build();
            answerDTOS.add(answerDTO);
        });
        return answerDTOS;
    }

    private Topic findTopicById(Long id) {
        Optional<Topic> topic = topicRepository.findById(id);
        return topic.orElseThrow(() -> new RuntimeException("Topic not found."));
    }

    @Transactional
    public TopicDTO putTopic(Long id, PutTopicForm topicForm) {
        Topic topic = findTopicById(id);
        topic.setTitle(topicForm.getTitle());
        topic.setMessage(topicForm.getMessage());

        return new TopicDTO(topic);
    }

    @Transactional
    public void remove(Long id) {
        findTopicById(id);
        topicRepository.deleteById(id);
    }
}
