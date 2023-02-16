package com.kieutrilang.blogwebsite.comment;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface CommentService {

    Page<CommentResponse> getAllComment(Pageable pageable);

    Page<CommentResponse> getAllCommentByPostCode(String code,Pageable pageable);

    Page<CommentResponse> getAllCommentHasParentId(Long id,Pageable pageable);

    Page<CommentResponse> getAllCommentByUsername(String username,Pageable pageable);

    CommentResponse addCommentToPost(CommentRequest commentRequest,String postCode);

    CommentResponse replyComment(CommentRequest commentRequest,Long repliedCommentId);

    void deleteComment(Long id);
}
