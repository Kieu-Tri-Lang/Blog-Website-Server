package com.kieutrilang.blogwebsite.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import com.kieutrilang.blogwebsite.utils.PagingParam;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping(value = "")
    @ResponseStatus(HttpStatus.OK)
    public Page<UserResponse> getAllUser(@PagingParam Pageable pageable) {

        return userService.getAllUser(pageable);

    }

    @GetMapping(value = "/{username}")
    @ResponseStatus(HttpStatus.OK)
    public Page<UserResponse> getUsersByName(@PathVariable String username, @PagingParam Pageable pageable){

        return userService.getUserByUsername(username,pageable);

    }


    //    TODO: Get private info from username in basic auth
    @GetMapping(value = "/info/{username}")
    @ResponseStatus(HttpStatus.OK)
    public UserResponse getPrivateInfo(@PathVariable String username){

        Boolean legalUsername = username != null && !username.trim().isEmpty();

        String usernameStr = legalUsername ? username : "";

        return userService.getPrivateInfoUserByUsername(usernameStr);
    }

    @PostMapping("/register")
    @ResponseStatus(HttpStatus.CREATED)
    public void register(@RequestBody UserRequest userRequest){
        userService.createUser(userRequest);
    }
}
