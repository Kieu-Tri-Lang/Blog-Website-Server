package com.kieutrilang.blogwebsite.comment;

import com.kieutrilang.blogwebsite.exception.NotFoundException;
import com.kieutrilang.blogwebsite.post.PostRepository;
import com.kieutrilang.blogwebsite.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService{

    private final UserRepository userRepository;

    private final PostRepository postRepository;

    private final CommentRepository commentRepository;

    private Comment mapToEntity(CommentRequest commentRequest){
        return Comment.builder()
                .content(commentRequest.getContent())
                .user(userRepository.findByUsername(commentRequest.getUsername())
                        .orElseThrow(() -> new NotFoundException("Not found user")))
                .build();
    }
    private CommentResponse mapToDto(Comment comment){
        return CommentResponse.builder()
                .id(comment.getId())
                .content(comment.getContent())
                .numberOfChildComment(getNumberOfChildComment(comment.getId()))
                .upVotes(comment.getUpVotes())
                .downVotes(comment.getDownVotes())
                .createdAt(comment.getCreatedAt())
                .updatedAt(comment.getUpdatedAt())
                .build();
    }

    private int getNumberOfChildComment(Long parentId){
        return commentRepository.countByParentId(parentId);
    }

    @Override
    public Page<CommentResponse> getAllComment(Pageable pageable) {
        Page<Comment> comments = commentRepository.findAll(pageable);

        return new PageImpl<>(
                comments.getContent().stream().map(this::mapToDto).collect(Collectors.toList()),
                comments.getPageable(),
                comments.getTotalElements());
    }

    @Override
    public Page<CommentResponse> getAllCommentByPostCode(String code, Pageable pageable) {
        Page<Comment> comments = commentRepository.findByPostCode(code,pageable);
        return new PageImpl<>(
                comments.getContent().stream().map(this::mapToDto).collect(Collectors.toList()),
                comments.getPageable(),
                comments.getTotalElements()
        );
    }

    @Override
    public Page<CommentResponse> getAllCommentHasParentId(Long id, Pageable pageable) {
        Page<Comment> comments = commentRepository.findByParentId(id,pageable);
        return new PageImpl<>(
                comments.getContent().stream().map(this::mapToDto).collect(Collectors.toList()),
                comments.getPageable(),
                comments.getTotalElements()
        );
    }

    @Override
    public Page<CommentResponse> getAllCommentByUsername(String username, Pageable pageable) {
        Page<Comment> comments = commentRepository.findByUserUsername(username,pageable);
        return new PageImpl<>(
                comments.getContent().stream().map(this::mapToDto).collect(Collectors.toList()),
                comments.getPageable(),
                comments.getTotalElements()
        );
    }

    @Override
    public CommentResponse addCommentToPost(CommentRequest commentRequest, String postCode) {
        Comment newComment = mapToEntity(commentRequest);
        newComment.setPost(
                postRepository.findByCode(postCode)
                        .orElseThrow(()-> new NotFoundException("Not found post")));
        Comment  savedComment  = commentRepository.save(newComment);
        return mapToDto(savedComment);
    }

    @Override
    public CommentResponse replyComment(CommentRequest commentRequest, Long repliedCommentId) {
        Comment newComment = mapToEntity(commentRequest);
        newComment.setParentId(repliedCommentId);

        Comment  savedComment  = commentRepository.save(newComment);
        return mapToDto(savedComment);
    }

    @Override
    public void deleteComment(Long id) {
        Comment recordComment = commentRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Not found comment"));
        recordComment.setIsDeleted(true);
        commentRepository.save(recordComment);
    }
}
