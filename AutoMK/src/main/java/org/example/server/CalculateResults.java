package org.example.server;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CalculateResults {
    private int id;
    private int correct;
    private int incorrect;
    private String fname;
    private String lname;
}
