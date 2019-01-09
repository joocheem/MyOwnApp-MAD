package com.example.jochemmortiers.myownapp;

import com.example.jochemmortiers.myownapp.Database.Question;

import java.io.Serializable;
import java.util.List;

public class ResultHolder implements Serializable {
    // this class is there to make the questions serializable so they can be displayed on the result page
    private List<Question> questions;

    public List<Question> getQuestions() {
        return questions;
    }

    public void setQuestions(List<Question> questions) {
        this.questions = questions;
    }

    public ResultHolder(List<Question> questions) {
        this.questions = questions;
    }
}
