package com.example.jochemmortiers.myownapp.Database;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

@Entity(tableName = "questions_table")
public class Question implements Serializable{

    @PrimaryKey(autoGenerate = true)
    @NonNull
    @ColumnInfo(name = "id")
    private int id;

    @SerializedName("question")
    @Expose
    private String question;

    @SerializedName("optA")
    @Expose
    private String optA;

    @SerializedName("optB")
    @Expose
    private String optB;

    @SerializedName("optC")
    @Expose
    private String optC;

    @SerializedName("answer")
    @Expose
    private String answer;

    // getters and setters for each variable
    @NonNull
    public int getId() {
        return id;
    }

    public void setId(@NonNull int id) {
        this.id = id;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getOptA() {
        return optA;
    }

    public void setOptA(String optA) {
        this.optA = optA;
    }

    public String getOptB() {
        return optB;
    }

    public void setOptB(String optB) {
        this.optB = optB;
    }

    public String getOptC() {
        return optC;
    }

    public void setOptC(String optC) {
        this.optC = optC;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    // constructors for object
    public Question() {
        question = "";
        optA = "";
        optB = "";
        optC = "";
        answer = "";
    }

    public Question(@NonNull String question, @NonNull String optA, @NonNull String optB, @NonNull String optC, @NonNull String answer) {
        this.question = question;
        this.optA = optA;
        this.optB = optB;
        this.optC = optC;
        this.answer = answer;
    }


}