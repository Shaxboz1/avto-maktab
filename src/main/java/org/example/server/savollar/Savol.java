package org.example.server.savollar;

public class Savol {
    private String question;
    private String A;
    private String B;
    private String C;
    private String correct;
    String selectedAnswer;
    boolean isAnswered = false;

    public String getA() {
        return A;
    }

    public String getB() {
        return B;
    }

    public String getC() {
        return C;
    }

    public void setQuestion(String question) {
        this.question = question;
    }


    public void setCorrect(String correct) {
        this.correct = correct;
    }

    public void setSelectedAnswer(String selectedAnswer) {
        this.selectedAnswer = selectedAnswer;
    }

    public void setAnswered(boolean answered) {
        isAnswered = answered;
    }

    public String getQuestion() {
        return question;
    }

    public String getSelectedAnswer() {
        return selectedAnswer;
    }

    public boolean isAnswered() {
        return isAnswered;
    }

    public Savol(String savolMatni, String variant1, String variant2, String variant3, String togriJavob) {
        this.question = savolMatni;
        this.A = variant1;
        this.B = variant2;
        this.C = variant3;
        this.correct = togriJavob;
    }

    public String getSavolMatni() {
        return question;
    }



    public String getCorrect() {
        return correct;
    }

    @Override
    public String toString() {
        return "Savol: " + question + "\n" +
                "1. " + A + "\n" +
                "2. " + B + "\n" +
                "3. " + C + "\n" +
                "To'g'ri javob: " + correct;
    }
}