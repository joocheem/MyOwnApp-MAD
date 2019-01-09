package com.example.jochemmortiers.myownapp;

import android.arch.lifecycle.Observer;
import android.content.Intent;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.jochemmortiers.myownapp.API.DataServiceGenerator;
import com.example.jochemmortiers.myownapp.API.Service;
import com.example.jochemmortiers.myownapp.Database.Question;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import java.util.Collections;
import java.util.List;

public class QuizActivity extends AppCompatActivity {

    // declare variables
    List<Question> questionList;
    int score, questionID;
    Question currentQuestion;
    TextView textView, numberText;
    RadioButton buttonA, buttonB, buttonC;
    Button button;
    private QuestionsViewModel mQuestionsViewModel;
    private RelativeLayout mRelativeLayout;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_questions);

        // initialize variables
        numberText = findViewById(R.id.questionNumber);
        mRelativeLayout = findViewById(R.id.progressLayout);

        fetchQuestions();

        // create a ViewModel
        mQuestionsViewModel = new QuestionsViewModel(this);
        mQuestionsViewModel.getAllQuestions().observe(this, new Observer<List<Question>>() {
            @Override
            public void onChanged(@Nullable List<Question> words) {
                questionList = words;
            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_quiz, menu);
        return true;
    }

    // when this method is called it gives the layout fields a value and displays the current question
    private void setQuestionView() {
        textView.setText(currentQuestion.getQuestion());
        buttonA.setText(currentQuestion.getOptA());
        buttonB.setText(currentQuestion.getOptB());
        buttonC.setText(currentQuestion.getOptC());
        numberText.setText("Question " + (questionID + 1) + " of " + questionList.size());
        questionID++;
    }

    // this method gets the questions and answers from the API service, and loads the result page when all questions are answered
    private void fetchQuestions() {
        mRelativeLayout.setVisibility(View.VISIBLE);
        DataServiceGenerator dataServiceGenerator = new DataServiceGenerator();
        Service service = dataServiceGenerator.createService(Service.class);
        Call<List<QuestionsModel>> call = service.getQuestions();
        call.enqueue(new Callback<List<QuestionsModel>>() {
            @Override
            public void onResponse(Call<List<QuestionsModel>> call, Response<List<QuestionsModel>> response) {
                if (response.isSuccessful()) {
                    if (response != null) {
                        List<QuestionsModel> questionsModelList = response.body();
                        mQuestionsViewModel.deleteAll();
                        for (int i = 0; i < questionsModelList.size(); i++) {
                            String question = questionsModelList.get(i).getQuestion();
                            String answer = questionsModelList.get(i).getAnswer();
                            String ansA = questionsModelList.get(i).getOptA();
                            String ansB = questionsModelList.get(i).getOptB();
                            String ansC = questionsModelList.get(i).getOptC();

                            Question questions = new Question(question, ansA, ansB, ansC, answer);
                            mQuestionsViewModel.insert(questions);
                        }

                        Handler handler = new Handler();
                        handler.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                takeAction();
                            }
                        }, 2000);
                    }
                }
            }

            @Override
            public void onFailure(Call<List<QuestionsModel>> call, Throwable t) {

            }
        });

    }

    // works in collaboration with the previous method
    public void takeAction() {
        mRelativeLayout.setVisibility(View.INVISIBLE);
        currentQuestion = questionList.get(questionID);
        textView = findViewById(R.id.questionText);
        buttonA = findViewById(R.id.radioA);
        buttonB = findViewById(R.id.radioB);
        buttonC = findViewById(R.id.radioC);
        button = findViewById(R.id.button);
        setQuestionView();
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RadioGroup radioGroup = findViewById(R.id.radioGroup);

                if (radioGroup.getCheckedRadioButtonId() == -1) {
                    return;
                }

                RadioButton answer = findViewById(radioGroup.getCheckedRadioButtonId());

                radioGroup.clearCheck();

                if (currentQuestion.getAnswer().equals(answer.getText())) {
                    score++;
                }

                // checks if all questions have been answered and opens the result page
                if (questionID < questionList.size()) {
                    currentQuestion = questionList.get(questionID);
                    setQuestionView();
                } else {
                    Intent intent = new Intent(QuizActivity.this, ResultActivity.class);
                    Bundle bundle = new Bundle();
                    ResultHolder answers = new ResultHolder(questionList);
                    bundle.putSerializable("answers", answers);
                    bundle.putInt("score", score);
                    intent.putExtras(bundle);
                    startActivity(intent);
                    finish();
                }
            }
        });

    }
}
