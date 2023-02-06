package com.ead.authuser.controllers;

import com.ead.authuser.dtos.InstructorDto;
import com.ead.authuser.enums.UserType;
import com.ead.authuser.models.UserModel;
import com.ead.authuser.services.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/instructors/subscription")
public class InstructorController {

    private final UserService userService;

    @PostMapping
    public ResponseEntity<Object> saveSubscriptionInstructor(@RequestBody @Valid InstructorDto instructorDto) {
        Optional<UserModel> userModelOptional = userService.findById(instructorDto.getUserId());
        if (userModelOptional.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found.");
        }
        var userModel = userModelOptional.get();
        userModel.setUserType(UserType.INSTRUCTOR);

        userService.save(userModel);

        return ResponseEntity.ok(userModel);
    }
}
