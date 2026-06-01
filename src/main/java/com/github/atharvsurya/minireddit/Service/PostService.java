package com.github.atharvsurya.minireddit.Service;

import com.github.atharvsurya.minireddit.Dto.ApiResponse;
import com.github.atharvsurya.minireddit.Dto.PostRequest;
import com.github.atharvsurya.minireddit.Dto.PostResponse;
import com.github.atharvsurya.minireddit.Entity.Post;
import com.github.atharvsurya.minireddit.Entity.User;
import com.github.atharvsurya.minireddit.Repository.PostRepository;
import com.github.atharvsurya.minireddit.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class PostService {
    @Autowired
    PostRepository postRepository;
    @Autowired
    UserRepository userRepository;

    public PostResponse toResponse(Post p){
        return new PostResponse(
                p.getId(),p.getTitle(), p.getContent(), p.getLikes(), p.getTime(), p.getAuthor().getUsername());
    }

    public ApiResponse<PostResponse> createPost(PostRequest req) {
        if(req.getTitle()==null || req.getTitle().isBlank())
            return ApiResponse.fail("Title cannot be empty.");
        if(req.getContent()==null || req.getContent().isBlank())
            return ApiResponse.fail("Content cannot be empty.");
        Optional<User> opt = userRepository.findById(req.getAuthor_id());
        if(opt.isEmpty())
            return ApiResponse.fail("Author do not exist in database.");
        User author = opt.get();
        Post post = new Post();
        post.setAuthor(author);
        post.setTitle(req.getTitle());
        post.setContent(req.getContent());
        post.setTime(LocalDateTime.now());
        postRepository.save(post);
        return ApiResponse.ok("Post Created", toResponse(post));
    }

    public ApiResponse<List<PostResponse>> getAllPosts() {
        List<Post> allposts = postRepository.findAll();
        List<PostResponse> res = allposts.stream().map(this::toResponse).toList();
        return ApiResponse.ok("Posts fetched successfully.", res);
    }

    public ApiResponse<PostResponse> getPostByID(Long id) {
        Optional<Post> opt = postRepository.findById(id);
        if(opt.isEmpty())
            return ApiResponse.fail("Post not found.");
        Post post = opt.get();
        return ApiResponse.ok("Post fetched.", toResponse(post));
    }

    public ApiResponse<PostResponse> likePost(long id) {
        Optional<Post> opt = postRepository.findById(id);
        if(opt.isEmpty())
            return ApiResponse.fail("Post not found");
        Post post = opt.get();
        post.setLikes(post.getLikes()+1);
        postRepository.save(post);
        return ApiResponse.ok("Liked Successfully",null);
    }

    public ApiResponse<PostResponse> unlikepost(long id) {
        Optional<Post> opt = postRepository.findById(id);
        if(opt.isEmpty())
            return ApiResponse.fail("Post not found");
        Post post = opt.get();
        post.setLikes(post.getLikes()-1);
        postRepository.save(post);
        return ApiResponse.ok("Unliked Successfully",null);
    }

    public ApiResponse<Void> deletePost(Long id) {
        Optional<Post> opt = postRepository.findById(id);
        if(opt.isEmpty())
            return ApiResponse.fail("Post not found.");
        postRepository.deleteById(id);
        return ApiResponse.ok("Delete Successfully.",null);
    }
}
