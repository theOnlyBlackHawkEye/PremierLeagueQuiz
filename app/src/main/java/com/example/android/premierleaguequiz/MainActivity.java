package com.example.android.premierleaguequiz;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    int questionNumber = -1;
    int quizTotalScore;
    int[] layoutResourceId = new int[]{R.id.question_one_layout, R.id.question_two_layout, R.id.question_three_layout,
            R.id.question_four_layout, R.id.question_five_layout};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }


    public void startQuiz(View view) {

        final TextView textBreifing = findViewById(R.id.App_briefing);
        final View mainSeperatorLine = findViewById(R.id.main_seperator_line);
        final Button startButton = findViewById(R.id.start_quiz_button);
        final Button nextQuestionButton = findViewById(R.id.next_question_button);
        final Button previousQuestionButton = findViewById(R.id.previous_question_button);

        makeViewInvisible(textBreifing);
        makeViewVisible(mainSeperatorLine);
        makeViewGone(startButton);
        makeViewVisible(nextQuestionButton);
        previousQuestionButton.setClickable(false);
        previousQuestionButton.setTextColor(getResources().getColor(R.color.inactive_button));
        makeViewVisible(previousQuestionButton);
        nextQuestion(nextQuestionButton);

    }

    public void nextQuestion(View view) {
        if (questionNumber >= 0) {
            makeViewGone(findViewById(layoutResourceId[questionNumber]));
            final Button previousQuestionButton = findViewById(R.id.previous_question_button);
            previousQuestionButton.setClickable(true);
            previousQuestionButton.setTextColor(getResources().getColor(R.color.buttonTextColor));
        }
        questionNumber++;
        if (questionNumber < 4) {
            makeViewVisible(findViewById(layoutResourceId[questionNumber]));
        } else if (questionNumber == 4) {
            makeViewVisible(findViewById(layoutResourceId[questionNumber]));
            final Button nextQuestionButton = findViewById(R.id.next_question_button);
            nextQuestionButton.setClickable(false);
            nextQuestionButton.setTextColor(getResources().getColor(R.color.inactive_button));
            final Button submitButton = findViewById(R.id.submit_button);
            makeViewVisible(submitButton);
        }
    }

    public void previousQuestion(View view) {
        if (questionNumber > 0) {
            makeViewGone(findViewById(layoutResourceId[questionNumber]));
            final Button nextQuestionButton = findViewById(R.id.next_question_button);
            nextQuestionButton.setClickable(true);
            nextQuestionButton.setTextColor(getResources().getColor(R.color.buttonTextColor));
            questionNumber--;
            makeViewVisible(findViewById(layoutResourceId[questionNumber]));
        }
        if (questionNumber == 0) {
            final Button previousQuestionButton = findViewById(R.id.previous_question_button);
            previousQuestionButton.setClickable(false);
            previousQuestionButton.setTextColor(getResources().getColor(R.color.inactive_button));
        }
    }

    public void calculateScore(View view) {
        RadioGroup radioGroup_01 = findViewById(R.id.radio_group_01);
        int radioId_01 = radioGroup_01.getCheckedRadioButtonId();
        if (radioId_01 == R.id.radio_button_01) {
            quizTotalScore++;
        }

        RadioGroup radioGroup_02 = findViewById(R.id.radio_group_02);
        int radioId_02 = radioGroup_02.getCheckedRadioButtonId();
        if (radioId_02 == R.id.radio_button_02) {
            quizTotalScore++;
        }

        CheckBox checkBox_01 = findViewById(R.id.check_box_01);
        CheckBox checkBox_02 = findViewById(R.id.check_box_02);
        CheckBox checkBox_03 = findViewById(R.id.check_box_03);
        CheckBox checkBox_04 = findViewById(R.id.check_box_04);
        if (checkBox_01.isChecked() && checkBox_04.isChecked() && !checkBox_02.isChecked() && !checkBox_03.isChecked()) {
            quizTotalScore++;
        }

        EditText editText_01 = findViewById(R.id.goals_edit_text);
        if (editText_01.getText().toString().equals("32")) {
            quizTotalScore++;
        }

        EditText editText_02 = findViewById(R.id.club_edit_text);
        if (editText_02.getText().toString().toLowerCase().equals("manchester united") || editText_02.getText().toString().toLowerCase().equals("man united")) {
            quizTotalScore++;
        }

        makeViewGone(findViewById(layoutResourceId[questionNumber]));
        makeViewGone(findViewById(R.id.next_question_button));
        makeViewGone(findViewById(R.id.previous_question_button));
        makeViewGone(view);
        makeViewGone(findViewById(R.id.main_seperator_line));
        makeViewVisible(findViewById(R.id.exit_button));

        ImageView backGroundImage = findViewById(R.id.background_image);
        backGroundImage.setImageResource(R.drawable.premier_league_background_02);
        backGroundImage.getLayoutParams().width = ViewGroup.LayoutParams.MATCH_PARENT;
        backGroundImage.getLayoutParams().height = ViewGroup.LayoutParams.WRAP_CONTENT;
        backGroundImage.setScaleType(ImageView.ScaleType.CENTER_INSIDE);
        Toast.makeText(this, "Your Final Score is " + quizTotalScore + " out of 5", Toast.LENGTH_LONG).show();
    }

    public void exitApp(View view) {
        android.os.Process.killProcess(android.os.Process.myPid());
        System.exit(1);
    }

    public void makeViewVisible(final View view) {
        view.setAlpha(0.0f);
        view.animate().alpha(1.0f).setDuration(300).setListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                view.setVisibility(View.VISIBLE);
            }
        });
    }

    public void makeViewInvisible(final View view) {
        view.setAlpha(1.0f);
        view.animate().alpha(0.0f).setDuration(300).setListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                view.setVisibility(View.INVISIBLE);
            }
        });
    }

    public void makeViewGone(final View view) {
        view.setAlpha(1.0f);
        view.animate().alpha(0.0f).setDuration(300).setListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                view.setVisibility(View.GONE);
            }
        });
    }
}
