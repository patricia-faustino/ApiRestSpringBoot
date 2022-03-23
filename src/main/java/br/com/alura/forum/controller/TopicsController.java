package br.com.alura.forum.controller;

import br.com.alura.forum.model.Course;
import br.com.alura.forum.model.Topic;
import br.com.alura.forum.model.dto.TopicDTO;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

//por padrão assume que o comportamento padrão dos métodos é response body
@RestController
public class TopicsController {

    //jackson: converte código Java <-> JSO
    @RequestMapping("/topics")
    public List<TopicDTO> list() {
        Topic topic = new Topic("Dúvida", "Dúvida com Spring",  new Course("Spring", "Programação"));

        return TopicDTO.mapper(Arrays.asList(topic, topic, topic));
    }
}
