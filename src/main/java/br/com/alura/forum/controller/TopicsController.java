package br.com.alura.forum.controller;

import br.com.alura.forum.model.dto.TopicDTO;
import br.com.alura.forum.model.dto.TopicForm;
import br.com.alura.forum.model.entities.Topic;
import br.com.alura.forum.repository.CourseRepository;
import br.com.alura.forum.repository.TopicRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/topics")
public class TopicsController {

    @Autowired
    private TopicRepository topicRepository;

    @Autowired
    private CourseRepository courseRepository;

    @GetMapping
    public List<TopicDTO> getTopics() {
        List<Topic> topics = topicRepository.findAll();

        return TopicDTO.mapper(topics);
    }

    @GetMapping
    @RequestMapping("/topicsByCourseName")
    public List<TopicDTO> getTopicsByCourseName(String courseName) {
        List<Topic> topics;

        if(courseName.trim().isEmpty()) {
            topics = topicRepository.findAll();
        } else{
            topics = topicRepository.findByCourseName(courseName);
        }

        return TopicDTO.mapper(topics);
    }

    @PostMapping
    public ResponseEntity<TopicDTO> post(@RequestBody TopicForm topicForm, UriComponentsBuilder uriComponentsBuilder) {
        Topic topic = topicForm.map(courseRepository);
        topicRepository.save(topic);

        URI uri = uriComponentsBuilder.path("/topics/{id}").buildAndExpand(topic.getId()).toUri();
        return ResponseEntity.created(uri).body(new TopicDTO(topic));
    }
}
