package org.example.server;

public class ResultInfo {
    private int id;
    private String firstName;
    private String lastName;
    private String trueAnswer;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getTrueAnswer() {
        return trueAnswer;
    }

    public void setTrueAnswer(String trueAnswer) {
        this.trueAnswer = trueAnswer;
    }

    public ResultInfo(int id, String firstName, String lastName, String trueAnswer) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.trueAnswer = trueAnswer;
    }
}
