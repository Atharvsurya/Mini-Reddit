package com.github.atharvsurya.minireddit.Controller;

import com.github.atharvsurya.minireddit.Dto.ApiResponse;
import com.github.atharvsurya.minireddit.Dto.LoginRequest;
import com.github.atharvsurya.minireddit.Dto.RegisterRequest;
import com.github.atharvsurya.minireddit.Dto.UserResponse;
import com.github.atharvsurya.minireddit.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin("*")
public class AuthController {
    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public ResponseEntity<ApiResponse<UserResponse>> register(@RequestBody RegisterRequest req){
        ApiResponse<UserResponse> res = userService.register(req);
        return res.isSuccess()?ResponseEntity.ok(res):ResponseEntity.badRequest().body(res);
    }

    @PostMapping("/login")
    public ResponseEntity<ApiResponse<UserResponse>> login(@RequestBody LoginRequest req){
        ApiResponse<UserResponse> res = userService.login(req);
        return res.isSuccess()?ResponseEntity.ok(res):ResponseEntity.status(401).body(res);
    }

    @GetMapping("/users")
    public ResponseEntity<ApiResponse<List<UserResponse>>> getUsers(){
        ApiResponse<List<UserResponse>> res = userService.getUsers();
        return res.isSuccess()?ResponseEntity.ok(res):ResponseEntity.status(401).body(res);
    }

    @GetMapping("/users/search/{id}")
    public ResponseEntity<ApiResponse<UserResponse>> getUsersById(@PathVariable Long id){
        ApiResponse<UserResponse> res = userService.getUserById(id);
        return res.isSuccess()?ResponseEntity.ok(res):ResponseEntity.status(401).body(res);
    }

    @DeleteMapping("/users/delete/{id}")
    public ResponseEntity<ApiResponse<Void>> deleteuser(@PathVariable Long id){
        ApiResponse<Void> res = userService.deleteuser(id);
        return res.isSuccess()?ResponseEntity.ok(res):ResponseEntity.status(401).body(res);
    }
}
