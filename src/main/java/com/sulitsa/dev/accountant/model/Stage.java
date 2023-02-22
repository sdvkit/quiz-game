package com.sulitsa.dev.accountant.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class Stage {
    private Integer id;
    private String question;
    private String[] answerVariants;
    private Answer correctAnswer;
}
