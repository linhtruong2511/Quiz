package com.Eddie.Quiz.entity;

import com.Eddie.Quiz.common.RoleEnum;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.List;
import java.util.Set;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserEntity extends BaseEntity{
    String username;
    String password;
    String email;
    String avatar;
    String name;
    @ManyToMany(mappedBy = "userEntities")
    List<AnswerEntity> answerEntities;
    @ManyToMany(mappedBy = "userEntities", cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    Set<RoleEntity> roleEntities;
    Boolean isDeleted = false;
}
