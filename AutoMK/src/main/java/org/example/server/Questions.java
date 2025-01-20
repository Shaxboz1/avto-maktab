package org.example.server;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Questions {
    String question;
    Options options;
    String correct;
    String selectedAnswer;
    boolean isAnswered = false;

    public Questions(String question, Options options, String correct) {
        this.question = question;
        this.options = options;
        this.correct = correct;
    }
}
