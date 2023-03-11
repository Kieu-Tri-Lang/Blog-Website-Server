package com.kieutrilang.blogwebsite.topic;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface TopicService {

    Page<TopicResponse> getAllTopic(Pageable pageable);

    Page<TopicResponse> getTopicByNameContaining(String name,Pageable pageable);

    TopicResponse createTopic(TopicRequest topicRequest);
}
