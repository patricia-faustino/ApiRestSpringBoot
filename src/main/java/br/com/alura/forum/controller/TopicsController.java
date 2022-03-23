package br.com.alura.forum.controller;

import br.com.alura.forum.model.entities.Course;
import br.com.alura.forum.model.entities.Topic;
import br.com.alura.forum.model.dto.TopicDTO;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RestController
public class TopicsController {

    @RequestMapping("/topics")
    public List<TopicDTO> list() {
        Topic topic = new Topic("Dúvida", "Dúvida com Spring",  new Course("Spring", "Programação"));

        return TopicDTO.mapper(Arrays.asList(topic, topic, topic));
    }
}
