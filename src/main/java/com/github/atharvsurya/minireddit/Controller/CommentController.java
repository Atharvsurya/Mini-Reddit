package com.github.atharvsurya.minireddit.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.github.atharvsurya.minireddit.Dto.ApiResponse;
import com.github.atharvsurya.minireddit.Dto.CommentRequest;
import com.github.atharvsurya.minireddit.Dto.CommentResponse;
import com.github.atharvsurya.minireddit.Service.CommentService;

@RestController
@RequestMapping("/api/comment")
@CrossOrigin("*")
public class CommentController {
    @Autowired
    CommentService commentService;

    @PostMapping("/create")
    public ResponseEntity<ApiResponse<CommentResponse>> createComment(@RequestBody CommentRequest req){
        ApiResponse<CommentResponse> res = commentService.createcomment(req);
        return res.isSuccess()? ResponseEntity.ok(res): ResponseEntity.badRequest().body(res);
    }

    @GetMapping("/post/{id}")
    public ResponseEntity<ApiResponse<List<CommentResponse>>> showAllComments(@PathVariable Long id){
        ApiResponse<List<CommentResponse>> res = commentService.showAlComments(id);
        return res.isSuccess()? ResponseEntity.ok(res): ResponseEntity.badRequest().body(res);
    }

    @PostMapping("/post/{post_id}/{comm_id}/like")
    public ResponseEntity<ApiResponse<CommentResponse>> likecomment(@PathVariable Long post_id, @PathVariable Long comm_id){
        ApiResponse<CommentResponse> res = commentService.likeComment(post_id,comm_id);
        return res.isSuccess()?ResponseEntity.ok(res):ResponseEntity.badRequest().body(res);
    }

    @PostMapping("/post/{post_id}/{comm_id}/unlike")
    public ResponseEntity<ApiResponse<CommentResponse>> unlikecomment(@PathVariable Long post_id, @PathVariable Long comm_id){
        ApiResponse<CommentResponse> res = commentService.unlikeComment(post_id,comm_id);
        return res.isSuccess()?ResponseEntity.ok(res):ResponseEntity.badRequest().body(res);
    }

    @DeleteMapping("/post/{post_id}/delete/{comm_id}")
    public ResponseEntity<ApiResponse<CommentResponse>> deleteComment(@PathVariable Long post_id, @PathVariable Long comm_id) {
        ApiResponse<CommentResponse> res = commentService.deleteComment(post_id, comm_id);
        return res.isSuccess() ? ResponseEntity.ok(res) : ResponseEntity.badRequest().body(res);
    }
}
