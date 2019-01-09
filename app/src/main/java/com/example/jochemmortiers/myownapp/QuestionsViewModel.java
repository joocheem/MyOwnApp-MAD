package com.example.jochemmortiers.myownapp;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;
import android.content.Context;
import android.os.AsyncTask;

import com.example.jochemmortiers.myownapp.Database.Question;
import com.example.jochemmortiers.myownapp.Database.QuestionsRepository;

import java.util.List;

public class QuestionsViewModel extends ViewModel {

    private QuestionsRepository mRepository;
    private LiveData<List<Question>> mAllQuestions;

    public QuestionsViewModel (Context context){
        mRepository = new QuestionsRepository(context);
        mAllQuestions = mRepository.getmAllQuestions();
    }

    LiveData<List<Question>> getAllQuestions(){
        return mAllQuestions;
    }

    public void insert(Question word){ mRepository.insert(word);}

    public void deleteAll(){
        AsyncTask.execute(new Runnable() {
            @Override
            public void run() {
                mRepository.deleteAll();
            }
        });

    }
}
