package com.example.geoquiz;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

public class QuizActivity extends AppCompatActivity implements View.OnClickListener {
    TextView textView1, textAnswer, tvTimer, tvPoints;
    Button button1, button2, button3, button4;
    ImageView imageView1;
    ImageButton imgButtonLeft, imgButtonRight;
    private int currentIndex = 0;    int points;
    CountDownTimer countDownTimer;
    long millisUntilFinished;



    private Question[] questionsBank = {
            new Question(R.string.quest1, R.drawable.img1),
            new Question(R.string.quest2, R.drawable.img2),
            new Question(R.string.quest3, R.drawable.img3),
            new Question(R.string.quest4, R.drawable.img4),
            new Question(R.string.quest5, R.drawable.img5),
            new Question(R.string.quest6, R.drawable.img6),
            new Question(R.string.quest7, R.drawable.img7),
            new Question(R.string.quest8, R.drawable.img8),
            new Question(R.string.quest9, R.drawable.img9),
            new Question(R.string.quest10, R.drawable.img10)
    };


    private Answer[] answersBank = {
            new Answer(R.string.textbutton1, R.string.textbutton2, R.string.textbutton3, R.string.textbutton4,
                    R.string.correct_toast, R.string.incorrect_toast, R.string.incorrect_toast, R.string.incorrect_toast),
            new Answer(R.string.textbutton5, R.string.textbutton6, R.string.textbutton7, R.string.textbutton8,
                    R.string.incorrect_toast, R.string.incorrect_toast, R.string.incorrect_toast, R.string.correct_toast),
            new Answer(R.string.textbutton9, R.string.textbutton10, R.string.textbutton11, R.string.textbutton12,
                    R.string.incorrect_toast, R.string.incorrect_toast, R.string.incorrect_toast, R.string.correct_toast),
            new Answer(R.string.textbutton13, R.string.textbutton14, R.string.textbutton15, R.string.textbutton16,
                    R.string.correct_toast, R.string.incorrect_toast, R.string.incorrect_toast, R.string.incorrect_toast),
            new Answer(R.string.textbutton17, R.string.textbutton18, R.string.textbutton19, R.string.textbutton20,
                    R.string.correct_toast, R.string.incorrect_toast, R.string.incorrect_toast, R.string.incorrect_toast),
            new Answer(R.string.textbutton21, R.string.textbutton22, R.string.textbutton23, R.string.textbutton24,
                    R.string.incorrect_toast, R.string.incorrect_toast, R.string.incorrect_toast, R.string.correct_toast),
            new Answer(R.string.textbutton25, R.string.textbutton26, R.string.textbutton27, R.string.textbutton28,
                    R.string.incorrect_toast, R.string.correct_toast, R.string.incorrect_toast, R.string.incorrect_toast),
            new Answer(R.string.textbutton29, R.string.textbutton30, R.string.textbutton31, R.string.textbutton32,
                    R.string.incorrect_toast, R.string.correct_toast, R.string.incorrect_toast, R.string.incorrect_toast),
            new Answer(R.string.textbutton33, R.string.textbutton34, R.string.textbutton35, R.string.textbutton36,
                    R.string.incorrect_toast, R.string.incorrect_toast, R.string.correct_toast, R.string.incorrect_toast),
            new Answer(R.string.textbutton37, R.string.textbutton38, R.string.textbutton39, R.string.textbutton40,
                    R.string.incorrect_toast, R.string.incorrect_toast, R.string.correct_toast, R.string.incorrect_toast),
    };



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);

        textView1 = findViewById(R.id.txtView1);
        button1 = findViewById(R.id.button1);
        button2 = findViewById(R.id.button2);
        button3 = findViewById(R.id.button3);
        button4 = findViewById(R.id.button4);
        imageView1 = findViewById(R.id.imageView1);
        imgButtonLeft = findViewById(R.id.nextLeftButton);
        imgButtonRight = findViewById(R.id.nextRightButton);
        textAnswer = findViewById(R.id.textAnswer);

        button1.setOnClickListener(this);
        button2.setOnClickListener(this);
        button3.setOnClickListener(this);
        button4.setOnClickListener(this);
        imgButtonRight.setOnClickListener(this);
        imgButtonLeft.setOnClickListener(this);

        tvTimer = findViewById(R.id.tvTimer);
        tvPoints = findViewById(R.id.tvPoints);

        points = 0;
        millisUntilFinished = 60000;

        startQuiz();
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.button1:
                int answer = answersBank[currentIndex].txtBtn1;
                textAnswer.setText(answer);
                if (answer == R.string.correct_toast) {
                    points++;
                }
                //Toast.makeText(this, "Ответ верный!", Toast.LENGTH_SHORT).show();
                break;
            case R.id.button2:
                int answer2 = answersBank[currentIndex].txtBtn2;
                textAnswer.setText(answer2);
                if (answer2 == R.string.correct_toast) {
                    points++;
                }
                //Toast.makeText(this, "Ошибка!", Toast.LENGTH_SHORT).show();
                break;
            case R.id.button3:
                int answer3 = answersBank[currentIndex].txtBtn3;
                textAnswer.setText(answer3);
                if (answer3 == R.string.correct_toast) {
                    points++;
                }
                //Toast.makeText(this, "Ошибка!", Toast.LENGTH_SHORT).show();
                break;
            case R.id.button4:
                int answer4 = answersBank[currentIndex].txtBtn4;
                textAnswer.setText(answer4);
                if (answer4 == R.string.correct_toast) {
                    points++;
                }
                //Toast.makeText(this, "Ошибка!", Toast.LENGTH_SHORT).show();
                break;
            case R.id.nextLeftButton:
                currentIndex = Math.abs((currentIndex - 1)) % questionsBank.length;
                updateQuestion();
                updateAnswer();
                textAnswer.setText("");
                break;
            case R.id.nextRightButton:
                currentIndex = (currentIndex + 1) % questionsBank.length;
                updateQuestion();
                updateAnswer();
                textAnswer.setText("");
                break;
        }
        tvPoints.setText("" + points);
    }


    private void updateQuestion() {
        int questionTextResID = questionsBank[currentIndex].textResID;
        textView1.setText(questionTextResID);
        int questionImageResID = questionsBank[currentIndex].imgResID;
        imageView1.setImageResource(questionImageResID);
    }

    private void updateAnswer() {
        int answer1ResID = answersBank[currentIndex].textButton1;
        int answer2ResID = answersBank[currentIndex].textButton2;
        int answer3ResID = answersBank[currentIndex].textButton3;
        int answer4ResID = answersBank[currentIndex].textButton4;
        button1.setText(answer1ResID);
        button2.setText(answer2ResID);
        button3.setText(answer3ResID);
        button4.setText(answer4ResID);
    }

    private void startQuiz() {
        tvTimer.setText("" + (millisUntilFinished / 1000) + "сек");
        tvPoints.setText("" + points);

        countDownTimer = new CountDownTimer(millisUntilFinished, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                tvTimer.setText("" + (millisUntilFinished / 1000) + "сек");
            }

            @Override
            public void onFinish() {
                button1.setClickable(false);
                button2.setClickable(false);
                button3.setClickable(false);
                button4.setClickable(false);
            }
        }.start();


    }
}