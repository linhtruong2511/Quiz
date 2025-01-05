package com.Eddie.Quiz.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.Collections;
import java.util.List;

@Setter
@NoArgsConstructor
@Getter
@FieldDefaults(level = AccessLevel.PRIVATE)
@AllArgsConstructor
@Builder
@Entity
public class QuestionEntity extends BaseEntity{
    String content;
    List<String> answers = Collections.emptyList();
    Integer correctAnswer;
    @ManyToOne
    @JoinColumn(name = "topicID")
    TopicEntity topicEntity;

    @OneToMany(mappedBy = "questionEntity", orphanRemoval = true)
    List<AnswerEntity> answerEntity;
}
