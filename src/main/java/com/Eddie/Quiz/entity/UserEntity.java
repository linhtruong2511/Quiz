package com.Eddie.Quiz.entity;

import com.Eddie.Quiz.common.RoleEnum;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class UserEntity extends BaseEntity {
    private String username;
    @JsonIgnore
    private String password;
    private String email;
    private String avatar;
    private String name;
    @ManyToMany(mappedBy = "userEntities")
    List<AnswerEntity> answerEntities;
    @ManyToMany(mappedBy = "userEntities", cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    Set<RoleEntity> roleEntities = new HashSet<>();
    Boolean isDeleted = false;
}
