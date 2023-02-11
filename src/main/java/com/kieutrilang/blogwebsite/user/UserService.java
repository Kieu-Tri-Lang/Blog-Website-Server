package com.kieutrilang.blogwebsite.user;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.kieutrilang.blogwebsite.file.File;

public interface UserService {

    Page<User> getAllUser(Pageable pageable);

    User getUserByEmail(String email);

    User createUser(User newUser);

    User updateUser(User updatedUser);

    void updateProfilePicture(String email, File profilePicture);

    void updateCoverPicture(String email, File coverPicture);

}
