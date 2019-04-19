package com.example.android.premierleaguequiz;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import butterknife.BindView;
import butterknife.ButterKnife;


public class MainActivity extends AppCompatActivity {

    private final int[] layoutResourceId = new int[]{R.id.question_one_layout, R.id.question_two_layout, R.id.question_three_layout,
            R.id.question_four_layout, R.id.question_five_layout};
    @BindView(R.id.App_briefing)
    TextView textBreifing;
    @BindView(R.id.main_seperator_line)
    View mainSeperatorLine;
    @BindView(R.id.start_quiz_button)
    Button startButton;
    @BindView(R.id.next_question_button)
    Button nextQuestionButton;
    @BindView(R.id.previous_question_button)
    Button previousQuestionButton;
    @BindView(R.id.submit_button)
    Button submitButton;
    @BindView(R.id.exit_button)
    Button exitButton;
    @BindView(R.id.radio_group_01)
    RadioGroup radioGroup_01;
    @BindView(R.id.radio_group_02)
    RadioGroup radioGroup_02;
    @BindView(R.id.check_box_01)
    CheckBox checkBox_01;
    @BindView(R.id.check_box_02)
    CheckBox checkBox_02;
    @BindView(R.id.check_box_03)
    CheckBox checkBox_03;
    @BindView(R.id.check_box_04)
    CheckBox checkBox_04;
    @BindView(R.id.goals_edit_text)
    EditText editText_01;
    @BindView(R.id.club_edit_text)
    EditText editText_02;
    @BindView(R.id.background_image)
    ImageView backGroundImage;
    private int questionNumber = -1;
    private int quizTotalScore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_items, menu);
        return true;
    }


    public void startQuiz(View view) {

        makeViewInvisible(textBreifing);
        makeViewVisible(mainSeperatorLine);
        makeViewGone(startButton);
        makeViewVisible(nextQuestionButton);
        previousQuestionButton.setClickable(false);
        previousQuestionButton.setTextColor(ContextCompat.getColor(this, R.color.inactive_button));
        makeViewVisible(previousQuestionButton);
        nextQuestion(nextQuestionButton);

    }

    public void nextQuestion(View view) {
        if (questionNumber >= 0) {
            makeViewGone(findViewById(layoutResourceId[questionNumber]));
            previousQuestionButton.setClickable(true);
            previousQuestionButton.setTextColor(ContextCompat.getColor(this, R.color.buttonTextColor));
        }
        questionNumber++;
        if (questionNumber < 4) {
            makeViewVisible(findViewById(layoutResourceId[questionNumber]));
        } else if (questionNumber == 4) {
            makeViewVisible(findViewById(layoutResourceId[questionNumber]));
            nextQuestionButton.setClickable(false);
            nextQuestionButton.setTextColor(ContextCompat.getColor(this, R.color.inactive_button));
            makeViewVisible(submitButton);
        }
    }

    public void previousQuestion(View view) {
        if (questionNumber > 0) {
            makeViewGone(findViewById(layoutResourceId[questionNumber]));
            nextQuestionButton.setClickable(true);
            nextQuestionButton.setTextColor(ContextCompat.getColor(this, R.color.buttonTextColor));
            questionNumber--;
            makeViewVisible(findViewById(layoutResourceId[questionNumber]));
        }
        if (questionNumber == 0) {
            previousQuestionButton.setClickable(false);
            previousQuestionButton.setTextColor(ContextCompat.getColor(this, R.color.inactive_button));
        }
    }

    public void calculateScore(View view) {
        int radioId_01 = radioGroup_01.getCheckedRadioButtonId();
        if (radioId_01 == R.id.radio_button_01) {
            quizTotalScore++;
        }
        int radioId_02 = radioGroup_02.getCheckedRadioButtonId();
        if (radioId_02 == R.id.radio_button_02) {
            quizTotalScore++;
        }

        if (checkBox_01.isChecked() && checkBox_04.isChecked() && !checkBox_02.isChecked() && !checkBox_03.isChecked()) {
            quizTotalScore++;
        }

        if (editText_01.getText().toString().equals("32")) {
            quizTotalScore++;
        }

        if (editText_02.getText().toString().equalsIgnoreCase("manchester united") || editText_02.getText().toString().equalsIgnoreCase("man united")) {
            quizTotalScore++;
        }

        makeViewGone(findViewById(layoutResourceId[questionNumber]));
        makeViewGone(nextQuestionButton);
        makeViewGone(previousQuestionButton);
        makeViewGone(view);
        makeViewGone(mainSeperatorLine);
        makeViewVisible(exitButton);

        backGroundImage.setImageResource(R.drawable.premier_league_background_02);
        backGroundImage.getLayoutParams().width = ViewGroup.LayoutParams.MATCH_PARENT;
        backGroundImage.getLayoutParams().height = ViewGroup.LayoutParams.WRAP_CONTENT;
        backGroundImage.setScaleType(ImageView.ScaleType.CENTER_INSIDE);
        Toast.makeText(getApplicationContext(), "Your Final Score is " + quizTotalScore + " out of 5", Toast.LENGTH_LONG).show();
    }

    public void exitApp(View view) {
        android.os.Process.killProcess(android.os.Process.myPid());
        System.exit(1);
    }

    private void makeViewVisible(final View view) {
        view.setAlpha(0.0f);
        view.animate().alpha(1.0f).setDuration(300).setListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                view.setVisibility(View.VISIBLE);
            }
        });
    }

    private void makeViewInvisible(final View view) {
        view.setAlpha(1.0f);
        view.animate().alpha(0.0f).setDuration(300).setListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                view.setVisibility(View.INVISIBLE);
            }
        });
    }

    private void makeViewGone(final View view) {
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
