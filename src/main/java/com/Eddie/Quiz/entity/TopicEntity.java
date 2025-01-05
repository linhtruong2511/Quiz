package com.Eddie.Quiz.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.PrimaryKeyJoinColumn;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.security.PrivilegedExceptionAction;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class TopicEntity extends BaseEntity{
    String name;
    @OneToMany(mappedBy = "topicEntity")
    List<QuestionEntity> questionEntities;
}
