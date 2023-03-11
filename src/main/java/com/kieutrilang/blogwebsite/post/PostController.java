package com.kieutrilang.blogwebsite.post;

import com.kieutrilang.blogwebsite.utils.PagingParam;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/posts")
@RequiredArgsConstructor
public class PostController{
    private final PostService postService;

    @GetMapping("")
    @ResponseStatus(HttpStatus.OK)
    public Page<PostResponse> getAllPost(@PagingParam Pageable pageable){
        return postService.getAllPost(pageable);
    }

    @GetMapping("/author/{authorName}")
    @ResponseStatus(HttpStatus.OK)
    public Page<PostResponse> getAllPostByAuthorName(@PathVariable String authorName,@PagingParam Pageable pageable){
        return postService.getPostByAuthorName(authorName, pageable);
    }

    @GetMapping("/topic/{topicName}")
    @ResponseStatus(HttpStatus.OK)
    public Page<PostResponse> getAllPostByTopicName(@PathVariable String topicName,@PagingParam Pageable pageable){
        return postService.getPostByTopicName(topicName, pageable);
    }

    @PostMapping("")
    @ResponseStatus(HttpStatus.CREATED)
    public void createPost(@RequestBody PostRequest postRequest){
        postService.createPost(postRequest);
    }

    @PutMapping("/{postCode}/state/{postState}")
    @ResponseStatus(HttpStatus.OK)
    public void changePostState(@PathVariable String postCode,@PathVariable PostState postState){
        postService.changeStatePost(postCode, postState);
    }

    @DeleteMapping("/{postCode}")
    @ResponseStatus(HttpStatus.OK)
    public void deletePost(@PathVariable String postCode){
        postService.deletePost(postCode);
    }


}
