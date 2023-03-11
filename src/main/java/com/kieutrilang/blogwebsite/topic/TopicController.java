package com.kieutrilang.blogwebsite.topic;

import com.kieutrilang.blogwebsite.utils.PagingParam;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/topics")
@RequiredArgsConstructor
public class TopicController {

    private final TopicService topicService;

    @GetMapping("")
    @ResponseStatus(HttpStatus.OK)
    public Page<TopicResponse> getAllTopic(@PagingParam Pageable pageable){
        return topicService.getAllTopic(pageable);
    }

    @GetMapping("/{name}")
    @ResponseStatus(HttpStatus.OK)
    public Page<TopicResponse> getTopicByNameContain(@PathVariable String name,@PagingParam Pageable pageable){
        return topicService.getTopicByNameContaining(name,pageable);
    }

    @PostMapping("")
    @ResponseStatus(HttpStatus.CREATED)
    public void createTopic(@RequestBody TopicRequest topicRequest){
        topicService.createTopic(topicRequest);
    }
}
