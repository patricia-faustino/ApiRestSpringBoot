package br.com.alura.forum.controller;

import br.com.alura.forum.model.dto.DetailsTopicDTO;
import br.com.alura.forum.model.dto.PutTopicForm;
import br.com.alura.forum.model.dto.TopicDTO;
import br.com.alura.forum.model.dto.TopicForm;
import br.com.alura.forum.service.TopicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/topics")
public class TopicsController {

    @Autowired
    private TopicService topicService;

    @GetMapping
    public List<TopicDTO> getTopics() {
        return topicService.getTopics();
    }

    @GetMapping("/getTopicsByCourseName")
    public List<TopicDTO> getTopicsByCourseName(@RequestParam String courseName) {
        return topicService.getTopicsByCourseName(courseName);
    }

    @PostMapping
    public ResponseEntity<TopicDTO> post(@RequestBody @Validated TopicForm topicForm, UriComponentsBuilder uriComponentsBuilder) {
        TopicDTO topicDTO = topicService.saveTopic(topicForm);
        URI uri = uriComponentsBuilder.path("/topics/{id}").buildAndExpand(topicDTO.getId()).toUri();
        return ResponseEntity.created(uri).body(topicDTO);
    }

    @GetMapping("/{id}")
    public DetailsTopicDTO getTopicById(@PathVariable Long id) {
        return topicService.getTopicById(id);
    }

    @PutMapping("/{id}")
    public ResponseEntity<TopicDTO> put(@PathVariable Long id, @RequestBody @Validated PutTopicForm topicForm) {
        TopicDTO topicDTO = topicService.putTopic(id, topicForm);
        return ResponseEntity.ok(topicDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> remove(@PathVariable Long id) {
        topicService.remove(id);
        return ResponseEntity.ok().build();
    }
}
