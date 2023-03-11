package com.kieutrilang.blogwebsite.post;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface PostService {

    Page<PostResponse> getAllPost(Pageable pageable);

    Page<PostResponse> getPostByTopicName(String topicName,Pageable pageable);

    Page<PostResponse> getPostByAuthorName(String username,Pageable pageable);

    PostResponse createPost(PostRequest postRequest);

    void changeStatePost(String postCode,PostState postState);

    void deletePost(String postCode);


}
