package com.github.atharvsurya.minireddit.Service;

import com.github.atharvsurya.minireddit.Dto.ApiResponse;
import com.github.atharvsurya.minireddit.Dto.CommentRequest;
import com.github.atharvsurya.minireddit.Dto.CommentResponse;
import com.github.atharvsurya.minireddit.Entity.Comment;
import com.github.atharvsurya.minireddit.Entity.Post;
import com.github.atharvsurya.minireddit.Entity.User;
import com.github.atharvsurya.minireddit.Repository.CommentRepository;
import com.github.atharvsurya.minireddit.Repository.PostRepository;
import com.github.atharvsurya.minireddit.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class CommentService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private CommentRepository commentRepository;
    @Autowired
    private PostRepository postRepository;

    public CommentResponse toResponse(Comment c){
        return new CommentResponse(
                c.getComment_id(),
                c.getContent(),
                c.getLikes(),
                c.getTime(),
                c.getUser().getUsername(),
                c.getPost().getId()
        );
    }

    public ApiResponse<CommentResponse> createcomment(CommentRequest req) {
        if(req.getContent() == null || req.getContent().isBlank())
            return ApiResponse.fail("Comment cannot be empty.");

        Optional<User> optuser = userRepository.findByUsername(req.getAuthorUsername());

        // FIX 1: Double check your CommentRequest.java file getter name.
        // If it's getPostId(), change this to: req.getPostId()
        Optional<Post> optpost = postRepository.findById(req.getId());

        if(optuser.isEmpty())
            return ApiResponse.fail("User not found.");
        if(optpost.isEmpty())
            return ApiResponse.fail("Post not found");

        User user = optuser.get();
        Post post = optpost.get();

        Comment comment = new Comment();
        comment.setUser(user);
        comment.setContent(req.getContent());
        comment.setTime(LocalDateTime.now());
        comment.setPost(post);

        // FIX 2: Explicitly initialize likes to 0
        comment.setLikes(0L);

        commentRepository.save(comment);
        return ApiResponse.ok("Comment successful", toResponse(comment));
    }

    public ApiResponse<List<CommentResponse>> showAlComments(Long id) {
        Optional<Post> optpost = postRepository.findById(id);
        if(optpost.isEmpty())
            return ApiResponse.fail("Post not found");

        List<Comment> comments = commentRepository.findAllByPostId(id);
        List<CommentResponse> list = comments.stream()
                .map(this::toResponse)
                .toList();

        return ApiResponse.ok("Showing all comments.", list);
    }
}