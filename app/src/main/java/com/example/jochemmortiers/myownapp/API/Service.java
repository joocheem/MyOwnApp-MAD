package com.example.jochemmortiers.myownapp.API;

import com.example.jochemmortiers.myownapp.QuestionsModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface Service {

    @GET("json/questions.json")
    Call<List<QuestionsModel>> getQuestions();
}
