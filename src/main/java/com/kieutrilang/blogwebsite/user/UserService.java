package com.kieutrilang.blogwebsite.user;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.kieutrilang.blogwebsite.file.File;

public interface UserService {

    Page<UserResponse> getAllUser(Pageable pageable);

    Page<UserResponse> getUserByUsername(String username,Pageable pageable);

    UserResponse getPrivateInfoUserByUsername(String username);

    UserResponse getUserByEmail(String email);

    void createUser(UserRequest newUser);

    UserResponse updateUser(UserRequest updatedUser);

    void updateProfilePictureUser(String email, File profilePicture);

    void updateCoverPictureUser(String email, File coverPicture);

}
