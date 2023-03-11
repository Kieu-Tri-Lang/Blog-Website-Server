package com.kieutrilang.blogwebsite.post;

import com.kieutrilang.blogwebsite.exception.NotFoundException;
import com.kieutrilang.blogwebsite.topic.TopicRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PostServiceImpl implements PostService {

    private final  PostRepository postRepository;

    private final TopicRepository topicRepository;

    private Post mapToEntity(PostRequest postRequest){
        return Post.builder()
                .title(postRequest.getTitle())
                .content(postRequest.getContent())
                .topic(topicRepository.findByName(postRequest.getTopicName())
                        .orElseThrow(()-> new NotFoundException("Not found topic")))
                .postState(postRequest.getPostState())
                .build();
    }
    private PostResponse mapToDto(Post post){
        PostResponse postResponse =  PostResponse.builder()
                .code(post.getCode())
                .title(post.getTitle())
                .content(post.getContent())
                .thumbnailPictureLink("")
                .topicName(post.getTopic().getName())
                .views(post.getViews())
                .upVotes(post.getUpVotes())
                .downVotes(post.getDownVotes())
                .postedTime(post.getCreatedAt())
                .build();
        postResponse.setAuthor(post.getAuthor().getUsername(),"");
        return postResponse;
    }

    @Override
    public Page<PostResponse> getAllPost(Pageable pageable) {
        Page<Post> posts = postRepository.findAll(pageable);

        return new PageImpl<>(
                posts.getContent().stream().map(this::mapToDto).collect(Collectors.toList()),
                posts.getPageable(),
                posts.getTotalElements()
        );
    }

    @Override
    public Page<PostResponse> getPostByTopicName(String topicName, Pageable pageable) {
        Page<Post> posts = postRepository.findByTopicName(topicName,pageable);

        return new PageImpl<>(
                posts.getContent().stream().map(this::mapToDto).collect(Collectors.toList()),
                posts.getPageable(),
                posts.getTotalElements()
        );
    }

    @Override
    public Page<PostResponse> getPostByAuthorName(String username, Pageable pageable) {
        Page<Post> posts = postRepository.findByAuthorUsername(username,pageable);

        return new PageImpl<>(
                posts.getContent().stream().map(this::mapToDto).collect(Collectors.toList()),
                posts.getPageable(),
                posts.getTotalElements()
        );
    }

    @Override
    public PostResponse createPost(PostRequest postRequest) {
        Post newPost = mapToEntity(postRequest);
        Post post = postRepository.save(newPost);
        return mapToDto(post);
    }

    @Override
    public void changeStatePost(String postCode,PostState postState) {

        Post post = postRepository.findByCode(postCode)
                .orElseThrow(() -> new NotFoundException("Not found post"));
        post.setPostState(postState);
        postRepository.save(post);

    }

    @Override
    public void deletePost(String postCode) {
        Post post = postRepository.findByCode(postCode)
                .orElseThrow(() -> new NotFoundException("Not found post"));
        post.setIsDeleted(true);
        postRepository.save(post);
    }
}
