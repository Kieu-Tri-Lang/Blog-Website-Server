package com.kieutrilang.blogwebsite.user;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class UserRequest {

    private Long id;
    private String username;
    private String email;
    private String password;
    private Boolean gender;
}
