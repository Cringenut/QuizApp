package com.cringenut.quizapp.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;

@Data
@AllArgsConstructor
public class QuestionWrapper {
    private String questionTitle;
    private String question;
    private String option1;
    private String option2;
    private String option3;
    private String option4;
}
