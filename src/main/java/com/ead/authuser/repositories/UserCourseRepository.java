package com.ead.authuser.repositories;

import com.ead.authuser.models.UserCourseModel;
import com.ead.authuser.models.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.UUID;

public interface UserCourseRepository extends JpaRepository<UserCourseModel, UUID> {

    boolean existsByUserAndCourseId(UserModel userModel, UUID courseId);

    boolean existsByCourseId(UUID courseId);

    @Query("select u from UserCourseModel u where u.user.userId = :userId")
    List<UserCourseModel> findAllUserCourseIntoUser(UUID userId);

    void deleteAllByCourseId(UUID courseId);
}
