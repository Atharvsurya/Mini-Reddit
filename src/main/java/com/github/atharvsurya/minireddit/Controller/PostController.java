package com.github.atharvsurya.minireddit.Controller;

import com.github.atharvsurya.minireddit.Dto.ApiResponse;
import com.github.atharvsurya.minireddit.Dto.PostRequest;
import com.github.atharvsurya.minireddit.Dto.PostResponse;
import com.github.atharvsurya.minireddit.Service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/post")
@CrossOrigin("*")
public class PostController {
    @Autowired
    PostService postService;

    @PostMapping("/create")
    public ResponseEntity<ApiResponse<PostResponse>> createost(@RequestBody PostRequest req){
        ApiResponse<PostResponse> res = postService.createPost(req);
        return res.isSuccess()?ResponseEntity.ok(res):ResponseEntity.badRequest().body(res);
    }

    @GetMapping("/all")
    public ResponseEntity<ApiResponse<List<PostResponse>>> getallposts(){
        ApiResponse<List<PostResponse>> res = postService.getAllPosts();
        return res.isSuccess()?ResponseEntity.ok(res):ResponseEntity.badRequest().body(res);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<PostResponse>> getpostbyid(@PathVariable Long id){
        ApiResponse<PostResponse> res = postService.getPostByID(id);
        return res.isSuccess()?ResponseEntity.ok(res):ResponseEntity.badRequest().body(res);
    }

    @PostMapping("/{id}/like")
    public ResponseEntity<ApiResponse<PostResponse>> like(@PathVariable Long id){
        ApiResponse<PostResponse> res = postService.likePost(id);
        return res.isSuccess()?ResponseEntity.ok(res):ResponseEntity.badRequest().body(res);
    }

    @PostMapping("/{id}/unlike")
    public ResponseEntity<ApiResponse<PostResponse>> unlike(@PathVariable Long id){
        ApiResponse<PostResponse> res = postService.unlikepost(id);
        return res.isSuccess()?ResponseEntity.ok(res):ResponseEntity.badRequest().body(res);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<ApiResponse<Void>> deletePost(@PathVariable Long id){
        ApiResponse<Void> res = postService.deletePost(id);
        return res.isSuccess()?ResponseEntity.ok(res):ResponseEntity.badRequest().body(res);
    }
}
