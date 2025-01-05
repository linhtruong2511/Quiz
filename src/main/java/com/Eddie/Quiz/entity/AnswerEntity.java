package com.Eddie.Quiz.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class AnswerEntity extends BaseEntity {
    Integer answered;

    @ManyToOne
    @JoinColumn(name = "questionID")
    QuestionEntity questionEntity;

    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(
            name = "user_answer",
            joinColumns = {@JoinColumn(name = "answerID")},
            inverseJoinColumns = {@JoinColumn(name = "userID")}
    )
    List<UserEntity> userEntities;
}
