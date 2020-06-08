package com.example.mapgame;

import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

public class GameActivity extends AppCompatActivity implements View.OnClickListener{

    private static final String TAG = GameActivity.class.getSimpleName();

    private Button button_attack;
    private Button button_left;
    private Button button_right;
    private Button button_down;
    private Button button_up;

    private ProgressBar health_bar;
    private TextView    attackedText;

    private GameFragment gameFragment;

    private int move = 30;
    private int health;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        button_attack = findViewById(R.id.buttonAttack);
        button_left = findViewById(R.id.buttonLeft);
        button_right = findViewById(R.id.buttonRight);
        button_down = findViewById(R.id.buttonDown);
        button_up = findViewById(R.id.buttonUp);

        // For now health bar is hooked up to attack button.
        health_bar   = findViewById(R.id.determinateBar);
        attackedText = findViewById(R.id.textViewAttachedText);


        button_attack.setOnClickListener(this);



        //Creating Game Fragment
        gameFragment = new GameFragment();
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.add(R.id.container, gameFragment, "gameFragment");
        transaction.commit();

        //Referenced https://stackoverflow.com/questions/4597513/onpress-onrelease-in-android
        button_left.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if(event.getAction() == MotionEvent.ACTION_DOWN){
                    Log.i(TAG, "Button Left Pressed Down");
                    gameFragment.gameView.button_left_pressed = true;
                    gameFragment.setButtonEvent( new ButtonEvent("Button Left Pressed Down"));
                }
                if(event.getAction() == MotionEvent.ACTION_UP){
                    Log.i(TAG, "Button Left Released");
                    gameFragment.gameView.button_left_pressed = false;
                    gameFragment.setButtonEvent( new ButtonEvent("Button Left Pressed Released"));
                }
                return true;
            }
        });
        button_right.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if(event.getAction() == MotionEvent.ACTION_DOWN){
                    Log.i(TAG, "Button Right Pressed Down");
                    gameFragment.gameView.button_right_pressed = true;
                    gameFragment.setButtonEvent( new ButtonEvent("Button Right Pressed Down"));
                }
                if(event.getAction() == MotionEvent.ACTION_UP){
                    Log.i(TAG, "Button Right Released");
                    gameFragment.gameView.button_right_pressed = false;
                    gameFragment.setButtonEvent( new ButtonEvent("Button Right Released"));
                }
                return true;
            }
        });
        button_down.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if(event.getAction() == MotionEvent.ACTION_DOWN){
                    Log.i(TAG, "Button Down Pressed Down");
                    gameFragment.gameView.button_down_pressed = true;
                    gameFragment.setButtonEvent( new ButtonEvent("Button Down Pressed Down"));
                }
                if(event.getAction() == MotionEvent.ACTION_UP){
                    gameFragment.gameView.button_down_pressed = false;
                    gameFragment.setButtonEvent( new ButtonEvent("Button Down Released"));
                }
                return true;
        }});
        button_up.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if(event.getAction() == MotionEvent.ACTION_DOWN){
                    Log.i(TAG, "Button Up Pressed Down");
                    gameFragment.gameView.button_up_pressed = true;
                    gameFragment.setButtonEvent( new ButtonEvent("Button Up Pressed Down"));
                }
                if(event.getAction() == MotionEvent.ACTION_UP){
                    Log.i(TAG, "Button Up Released");
                    gameFragment.gameView.button_up_pressed = false;
                    gameFragment.setButtonEvent( new ButtonEvent("Button Up Released"));
                }
                return true;
            }});
    }
    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.buttonAttack){
            // Set square to random color
//            int num = (int) (Math.random()*255);
//            gameFragment.gameView.myPaint.setColor(Color.rgb(0, 0, num));

            // onClick of attack button, lower health by 15 points
            health = health_bar.getProgress();
            health_bar.setProgress(health - 15);

            // Set text on attack button
            if(attackedText.getText().equals("Doh!")) {
                attackedText.setText("");
            }
            else {
                attackedText.setText(R.string.doh);
            }


            Log.i(TAG, "Attack Button Was Clicked");
            gameFragment.setButtonEvent( new ButtonEvent("Attack Button Clicked"));
        }
    }

    public class ButtonEvent {
        public String buttonEventString;
        public ButtonEvent(String buttonEventString){
            this.buttonEventString = buttonEventString;
        }
    }
}
