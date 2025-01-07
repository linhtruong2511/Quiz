package com.Eddie.Quiz.dto.request;

import lombok.*;
import lombok.experimental.FieldDefaults;
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserRegisterRequest {
    String username;
    String password;
    String name;
    String avatar;
    String email;
}