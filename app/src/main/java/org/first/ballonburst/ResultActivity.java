package org.first.ballonburst;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class ResultActivity extends AppCompatActivity {
    private TextView textViewInfo,textViewScore,textViewHighestScore;
    private Button buttonPlayAgain,buttonQuit;
    int myScore;
    SharedPreferences sharedPreferences;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);
        textViewInfo = findViewById(R.id.textViewInfo);
        textViewHighestScore = findViewById(R.id.textViewHieghestScore);
        textViewScore = findViewById(R.id.textViewMyScore);
        buttonPlayAgain = findViewById(R.id.buttonPlayAgain);
        buttonQuit = findViewById(R.id.buttonQuit);

        myScore = getIntent().getIntExtra("score" ,0);
        textViewScore.setText("Your Score : "+myScore);

       sharedPreferences = this.getSharedPreferences("score" , Context.MODE_PRIVATE);
        int highestScore = sharedPreferences.getInt("highestScore",0);

/*        if (myScore> highestScore){
            sharedPreferences.edit().putInt("highestScore",myScore);
            textViewHighestScore.setText("Highest Score : "+myScore);
            textViewInfo.setText("Congratulations.The New High Score.Do you want get better Score?");
        }else {
           00000000 textViewHighestScore.setText("Highest Score : "+highestScore);
            if ((highestScore - myScore) > 10){
                textViewInfo.setText("You must get a little faster!");
            }
            if ((highestScore - myScore) > 3 && (highestScore - myScore) <= 10){
                textViewInfo.setText("Good. how about getting a little faster?");
            }
            if ((highestScore - myScore) <= 3){
                textViewInfo.setText("Excellent.if you get a little faster,you can reach the high score");
            }
        }*/
        if (myScore> highestScore) {
            sharedPreferences.edit().putInt("highestScore", myScore);
            textViewHighestScore.setText("Highest Score : " + myScore);
            textViewInfo.setText("Congratulations.The New High Score.Do you want get better Score?");
        }
        if ((highestScore - myScore) > 10){
            textViewInfo.setText("You must get a little faster!");
        }
        if ((highestScore - myScore) > 3 && (highestScore - myScore) <= 10){
            textViewInfo.setText("Good. how about getting a little faster?");
        }
        if ((highestScore - myScore) < 3){
            textViewInfo.setText("Excellent.if you get a little faster,you can reach the high score");
        }

        buttonPlayAgain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ResultActivity.this,MainActivity.class);
                startActivity(intent);
                finish();
            }
        });

        buttonQuit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                moveTaskToBack(true);
                android.os.Process.killProcess(android.os.Process.myPid());
                System.exit(0);
            }
        });
    }
}