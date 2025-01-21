package org.example.server;

public class CalculateResults {
    private int id;
    private int correct;
    private int incorrect;
    private String fname;
    private String lname;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCorrect() {
        return correct;
    }

    public void setCorrect(int correct) {
        this.correct = correct;
    }

    public int getIncorrect() {
        return incorrect;
    }

    public void setIncorrect(int incorrect) {
        this.incorrect = incorrect;
    }

    public String getFname() {
        return fname;
    }

    public void setFname(String fname) {
        this.fname = fname;
    }

    public String getLname() {
        return lname;
    }

    public void setLname(String lname) {
        this.lname = lname;
    }

    public CalculateResults(int id, int correct, int incorrect, String fname, String lname) {
        this.id = id;
        this.correct = correct;
        this.incorrect = incorrect;
        this.fname = fname;
        this.lname = lname;
    }
}
