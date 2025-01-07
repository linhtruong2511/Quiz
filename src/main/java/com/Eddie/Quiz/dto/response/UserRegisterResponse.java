package com.Eddie.Quiz.dto.response;

import lombok.*;
import lombok.experimental.FieldDefaults;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserRegisterResponse {
    String username;
    String name;
    String avatar;
    String email;
}
