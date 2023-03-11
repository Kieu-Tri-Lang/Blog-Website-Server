package com.kieutrilang.blogwebsite.topic;

import com.kieutrilang.blogwebsite.exception.ExistedException;
import com.kieutrilang.blogwebsite.exception.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TopicServiceImpl implements TopicService{

    private final TopicRepository topicRepository;
    private boolean existedTopicName(String topicName){
        return topicRepository.findByName(topicName).isPresent();
    }


    private Topic mapToEntity(TopicRequest topicRequest){
        return Topic.builder()
                .name(topicRequest.getName())
                .build();
    }

    private TopicResponse mapToDto(Topic topic){
        return TopicResponse.builder()
                .name(topic.getName())
                .numberOfFollower(topic.getPosts().size())
                .numberOfFollower(topic.getFollowers().size())
                .build();
    }
    @Override
    public Page<TopicResponse> getAllTopic(Pageable pageable) {
        Page<Topic> topics = topicRepository.findAll(pageable);

        return new PageImpl<>(
            topics.getContent().stream().map(this::mapToDto).collect(Collectors.toList()),
                topics.getPageable(),
                topics.getTotalElements()
        );
    }

    @Override
    public Page<TopicResponse> getTopicByNameContaining(String name, Pageable pageable) {
        Page<Topic> topics = topicRepository.findTopicByNameContaining(name,pageable);
        return new PageImpl<>(
                topics.getContent().stream().map(this::mapToDto).collect(Collectors.toList()),
                topics.getPageable(),
                topics.getTotalElements()
        );
    }

    @Override
    public TopicResponse createTopic(TopicRequest topicRequest) {
        if(existedTopicName(topicRequest.getName())){
            throw new ExistedException("Topic existed");
        }
        Topic newTopic = mapToEntity(topicRequest);
        Topic topic = topicRepository.save(newTopic);
        return mapToDto(topic);
    }
}
