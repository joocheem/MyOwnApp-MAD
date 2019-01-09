package com.example.jochemmortiers.myownapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.RatingBar;
import android.widget.TextView;

import com.example.jochemmortiers.myownapp.Database.Question;

import java.util.List;


public class ResultActivity extends AppCompatActivity {

    RecyclerView mRecyclerView;
    ResultsRecyclerAdapter mAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // initialize objects
        setContentView(R.layout.activity_results);
        RatingBar ratingBar = findViewById(R.id.ratingbar);
        TextView textView = findViewById(R.id.textresult);
        mRecyclerView = findViewById(R.id.recycler);

        // set rating bar information
        ratingBar.setNumStars(5);
        ratingBar.setStepSize(1);

        // get the questions from the activity
        Bundle bundle = getIntent().getExtras();
        ResultHolder answers = (ResultHolder) bundle.getSerializable("answers");
        setUpRecyclerView(answers.getQuestions());
        int score = bundle.getInt("score");

        ratingBar.setRating(score);

        // display score according to how many answers were right
        switch (score) {
            case 0:
                textView.setText(R.string.nulProcent);
                break;
            case 1:
                textView.setText(R.string.twintigProcent);
                break;
            case 2:
                textView.setText(R.string.veertigProcent);
                break;
            case 3:
                textView.setText(R.string.zestigProcent);
                break;
            case 4:
                textView.setText(R.string.tachtigProcent);
                break;
            case 5:
                textView.setText(R.string.honderdProcent);
                break;
        }
    }

    //inflate menu option
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_result, menu);
        return true;
    }

    // if menu item is selected, it will create a new game
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.result_settings) {
            Intent settingsIntent = new Intent(this, QuizActivity.class);
            startActivity(settingsIntent);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    // set up the RecyclerView
    void setUpRecyclerView(List<Question> questions) {
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mAdapter = new ResultsRecyclerAdapter(this, questions);
        mRecyclerView.setAdapter(mAdapter);
    }
}
