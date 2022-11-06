package com.ead.authuser.controllers;

import com.ead.authuser.clients.UserClient;
import com.ead.authuser.dtos.CourseDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/users/{userId}/courses")
public class UserCourseController {

    @Autowired
    private UserClient userClient;

    @GetMapping
    public ResponseEntity<Page<CourseDto>> getAllCoursesByUser(@PageableDefault(sort = "courseId") Pageable pageable, @PathVariable UUID userId) {
        return ResponseEntity.ok(userClient.getAllCoursesByUser(userId, pageable));
    }
}
