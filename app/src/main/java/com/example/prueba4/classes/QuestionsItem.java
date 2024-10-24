package com.example.prueba4.classes;

public class QuestionsItem {
    String[] labels;
    int correct;

    public QuestionsItem(String[] labels, int correct) {
        this.labels = labels;
        this.correct = correct;
    }

    public String[] getLabels() {
        return labels;
    }

    public int getCorrect() {
        return correct;
    }

}
