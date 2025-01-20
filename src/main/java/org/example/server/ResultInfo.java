package org.example.server;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ResultInfo {
    private int id;
    private String firstName;
    private String lastName;
    private String trueAnswer;
}
