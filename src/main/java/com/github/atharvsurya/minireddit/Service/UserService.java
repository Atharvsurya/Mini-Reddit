package com.github.atharvsurya.minireddit.Service;

import com.github.atharvsurya.minireddit.Dto.ApiResponse;
import com.github.atharvsurya.minireddit.Dto.LoginRequest;
import com.github.atharvsurya.minireddit.Dto.RegisterRequest;
import com.github.atharvsurya.minireddit.Dto.UserResponse;
import com.github.atharvsurya.minireddit.Entity.User;
import com.github.atharvsurya.minireddit.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    private UserResponse toResponse(User u) {
        return new UserResponse(
                u.getUser_id(), u.getUsername(), u.getEmail()
        );
    }

    public ApiResponse<UserResponse> register(RegisterRequest req) {
        if(req.getEmail().isBlank() || req.getPassword().isBlank() || req.getUsername().isBlank())
            return ApiResponse.fail("All fields are required.");
        Optional<User> opt = userRepository.findByEmail(req.getEmail());
        if(opt.isPresent())
            return ApiResponse.fail("User with this email already exists.");
        opt = userRepository.findByUsername(req.getUsername());
        if(opt.isPresent())
            return ApiResponse.fail("Username already exists.");
        if(!req.getPassword().equals(req.getConfirmpassword()))
            return ApiResponse.fail("Password doesn't match");

        User user = new User();
        user.setUsername(req.getUsername());
        user.setEmail(req.getEmail());
        user.setPassword(req.getPassword());
        user.setDob(req.getDob());
        userRepository.save(user);
        return ApiResponse.ok("Registration successful", toResponse(user));
    }

    public ApiResponse<UserResponse> login(LoginRequest req){
        if(req.getEmail().isBlank() || req.getPassword().isBlank() || req.getConfirmpassword().isBlank())
            return ApiResponse.fail("All fields are required.");
        Optional<User> opt = userRepository.findByEmail(req.getEmail());
        if(opt.isEmpty())
            return ApiResponse.fail("User with this email not found");
        User user = opt.get();
        if(!req.getPassword().equals(user.getPassword()))
            return ApiResponse.fail("Incorrect password.");
        return ApiResponse.ok("Login Successful.", toResponse(user));
    }
}
