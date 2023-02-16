package com.kieutrilang.blogwebsite.comment;

import com.kieutrilang.blogwebsite.utils.PagingParam;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/comments")
@RequiredArgsConstructor
public class CommentController {

    private final CommentService commentService;

    @GetMapping("")
    @ResponseStatus(HttpStatus.OK)
    public Page<CommentResponse> getAllComments(@PagingParam Pageable pageable){
        return commentService.getAllComment(pageable);
    }

    @GetMapping("/user/{username}")
    @ResponseStatus(HttpStatus.OK)
    public Page<CommentResponse> getAllCommentsByUsername(@PathVariable String username,@PagingParam Pageable pageable){
        return commentService.getAllCommentByUsername(username, pageable);
    }

    @GetMapping("/post/{postCode}")
    @ResponseStatus(HttpStatus.OK)
    public Page<CommentResponse> getAllCommentByPostCode(@PathVariable String postCode,@PagingParam Pageable pageable){
        return commentService.getAllCommentByPostCode(postCode, pageable);
    }

    @GetMapping("/parent/{parentId}")
    @ResponseStatus(HttpStatus.OK)
    public Page<CommentResponse> getAllCommentHasParentId(@PathVariable Long parentId,@PagingParam Pageable pageable){
        return commentService.getAllCommentHasParentId(parentId, pageable);
    }

    @PostMapping("/post/{postCode}")
    @ResponseStatus(HttpStatus.CREATED)
    public CommentResponse addCommentToPost(@RequestBody CommentRequest commentRequest,@PathVariable String postCode){
        return commentService.addCommentToPost(commentRequest,postCode);
    }

    @PostMapping("/reply/{repliedCommentId}")
    @ResponseStatus(HttpStatus.CREATED)
    public CommentResponse replyComment(@RequestBody CommentRequest commentRequest,@PathVariable Long repliedCommentId){
        return commentService.replyComment(commentRequest,repliedCommentId);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteComment(@PathVariable Long id){
        commentService.deleteComment(id);
    }
}
