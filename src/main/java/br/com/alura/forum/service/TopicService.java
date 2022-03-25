package br.com.alura.forum.service;

import br.com.alura.forum.model.dto.*;
import br.com.alura.forum.model.entities.Course;
import br.com.alura.forum.model.entities.Topic;
import br.com.alura.forum.repository.CourseRepository;
import br.com.alura.forum.repository.TopicRepository;
import lombok.RequiredArgsConstructor;
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

    public List<TopicDTO> getTopicsByCourseName(String courseName) {
        List<Topic> topics = courseNameIsNullOrEmpty(courseName);

        return topics.stream()
                .map(TopicDTO::new)
                .collect(Collectors.toList());
    }

    private List<Topic> courseNameIsNullOrEmpty(String courseName) {
        List<Topic> topics;
        if(courseName == null || courseName.trim().isEmpty()) {
            topics = topicRepository.findAll();
        } else{
            topics = topicRepository.findByCourseName(courseName);
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

        List<AnswerDTO> answerDTOS = new ArrayList<>();

        topic.getAnswers().forEach((answer) -> {
            AnswerDTO answerDTO = AnswerDTO.builder()
                    .id(answer.getId())
                    .dateCreation(answer.getDateCreation())
                    .message(answer.getMessage())
                    .userName(answer.getUser().getName())
                    .build();
            answerDTOS.add(answerDTO);
        });

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
