package br.com.alura.forum.controller;

import br.com.alura.forum.model.dto.TopicDTO;
import br.com.alura.forum.model.entities.Topic;
import br.com.alura.forum.repository.TopicRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class TopicsController {

    @Autowired
    private TopicRepository topicRepository;

    @RequestMapping("/topics")
    public List<TopicDTO> getTopics() {
        List<Topic> topics = topicRepository.findAll();

        return TopicDTO.mapper(topics);
    }

    @RequestMapping("/topicsByNameCourse")
    public List<TopicDTO> getTopicsByNameCourse(String nameCourse) {
        List<Topic> topics;

        if(nameCourse.trim().isEmpty()) {
            topics = topicRepository.findAll();
        } else{
            topics = topicRepository.findByCourseName(nameCourse);
        }

        return TopicDTO.mapper(topics);
    }
}
