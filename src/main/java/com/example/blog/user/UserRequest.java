package com.example.blog.user;

import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

public class UserRequest {

    @Data
    public class LoginDTO {
        @NotEmpty
        private String username;
        @NotEmpty
        private String password;
    }

    @Data
    public class JoinDTO {
        @NotEmpty
        private String username;
        @NotEmpty
        private String password;
        @NotEmpty
        private String email;
    }
}
