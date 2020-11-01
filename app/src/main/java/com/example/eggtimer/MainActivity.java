package com.example.eggtimer;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    boolean timeIsRunning;
    CountDownTimer cdTimer;
    long timeLeft = 600000;
    TextView timerText;
    Button startBtn, softBtn, smileBtn, hardBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        timerText = (TextView) findViewById(R.id.textTimer);

        // get Button
        startBtn = (Button) findViewById(R.id.buttonStart);
        // Set What Happens When We Click On It
        startBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                StartStop();
            }
        });


        softBtn = (Button) findViewById(R.id.buttonSoft);
        softBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startBtn.setEnabled(true);
                timeLeft = 300000;
                timerText.setText(UpdateTimer(timeLeft));
            }
        });


        smileBtn = (Button) findViewById(R.id.buttonSmile);
        smileBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startBtn.setEnabled(true);
                timeLeft = 420000;
                timerText.setText(UpdateTimer(timeLeft));
            }
        });


        hardBtn = (Button) findViewById(R.id.buttonHard);
        hardBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startBtn.setEnabled(true);
                timeLeft = 600000;
                timerText.setText(UpdateTimer(timeLeft));

            }
        });
    }

    void StartStop(){
        if (timeIsRunning)
            StopTime();
        else
            StartTime();
    }

    void StartTime(){
        cdTimer = new CountDownTimer(timeLeft, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                // Removes one second in milliseconds from timeLeftInSeconds every seconds.
                timeLeft = millisUntilFinished;
                timerText.setText(UpdateTimer(timeLeft));
            }

            @Override
            public void onFinish() {

            }
        }.start();


        softBtn.setEnabled(false);
        smileBtn.setEnabled(false);
        hardBtn.setEnabled(false);
        startBtn.setText("Stop");
        timeIsRunning = true;
    }

    void  StopTime(){
        cdTimer.cancel();
        timeIsRunning = false;
        startBtn.setText("Start");
        softBtn.setEnabled(true);
        smileBtn.setEnabled(true);
        hardBtn.setEnabled(true);
    }

    String UpdateTimer(long timeLeft){
        int minutes = (int) timeLeft / 60000;
        int seconds = (int) timeLeft % 60000 / 1000;

        String timeLeftText;

        if (minutes < 10) timeLeftText = "0" + minutes;
        else timeLeftText = "" + minutes;
        timeLeftText += ":";
        if (seconds < 10) timeLeftText += "0";
        timeLeftText += seconds;

        StringBuilder returnString = new StringBuilder(timeLeftText);

        return timeLeftText;
    }
}