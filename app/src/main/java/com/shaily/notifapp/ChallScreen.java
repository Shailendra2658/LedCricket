package com.shaily.notifapp;

import static android.view.View.GONE;
import static android.view.View.VISIBLE;
import static com.shaily.notifapp.util.SharedPreferencesHandler.KEY_CHALLENGE;
import static com.shaily.notifapp.util.SharedPreferencesHandler.KEY_FOUR_URI;
import static com.shaily.notifapp.util.SharedPreferencesHandler.KEY_NAME;
import static com.shaily.notifapp.util.SharedPreferencesHandler.KEY_ONE_URI;
import static com.shaily.notifapp.util.SharedPreferencesHandler.KEY_OUT_URI;
import static com.shaily.notifapp.util.SharedPreferencesHandler.KEY_OVER;
import static com.shaily.notifapp.util.SharedPreferencesHandler.KEY_SIX_URI;
import static com.shaily.notifapp.util.SharedPreferencesHandler.KEY_TWO_URI;
import static com.shaily.notifapp.util.SharedPreferencesHandler.KEY_URI;
import static com.shaily.notifapp.util.SharedPreferencesHandler.KEY_WIDE_URI;

import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.text.TextUtils;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.AnimationUtils;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.shaily.notifapp.databinding.ActivityChallScreenBinding;
import com.shaily.notifapp.databinding.ActivityEnterScoreBinding;
import com.shaily.notifapp.util.SharedPreferencesHandler;

import java.util.regex.Pattern;

public class ChallScreen extends AppCompatActivity {
    private static final String TAG = "ChallScreen";
    private int ballsLeft;
    private int mainScore, scoreLeft;
    private int jDelay = 0,kDelay = 0;
    private ActivityChallScreenBinding binding;
    Pattern pattern = Pattern.compile("-?\\d+(\\.\\d+)?");
    StringBuffer strBuf = new StringBuffer();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        binding = ActivityChallScreenBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        setValues();
        binding.restart.setOnClickListener(view -> {
            Intent intent = new Intent(this, DashBoard.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
        });
        binding.tvBreak.setOnClickListener(view -> {
            Intent intent = new Intent(this, DashBoard
                    .class);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
        });

        binding.tvBowl.setOnClickListener(view -> {
            //sendData(9);
            //doWithDelay("K", kDelay);
            //doWithDelay("J", jDelay);
            // String str = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS).toString() + "/Videos/bowl.mp4";
            onBlowClick();
        });

        binding.videoView1.setOnCompletionListener(mediaPlayer -> {
             setVideoOff();
        });

        binding.tvZero.setOnClickListener(view -> onZeroClick());


        binding.tvOne.setOnClickListener(view -> onOneClick());

        binding.tvTwo.setOnClickListener(view -> onTwoClick());

        binding.tvFour.setOnClickListener(view -> onFourClick());

        binding.tvSix.setOnClickListener(view -> onSixClick());

        binding.tvOut.setOnClickListener(view -> onOutClick());

        binding.tvWide.setOnClickListener(view -> onWideClick());

        binding.tvZero.setOnClickListener(view -> onDeadBallClick());


    /*    while (true)
        {
            //binding.tvName.setText(code.name + " your final score is :");
            binding.flipper.startFlipping();
            binding.flipper.setInAnimation(AnimationUtils.loadAnimation(getApplicationContext(), R.anim.blink));
            binding.flipper.setOutAnimation(AnimationUtils.loadAnimation(getApplicationContext(), R.anim.blink));

            *//*this.fours.setText(Integer.toString(code.fours));
            this.sixes.setText(Integer.toString(code.sixes));
            this.balls.setText(Integer.toString(i));*//*

            return;
        }*/

    }

    public void onBlowClick() {
        String str = SharedPreferencesHandler.getStringValues(this, KEY_URI);
        setVideoOn();
        binding.videoView1.setVideoPath(str);
        binding.videoView1.requestFocus();
        binding.videoView1.start();
    }


    private void setScore(int score) {
       /* if (ballsLeft <= 0)
            return;*/
        mainScore = mainScore + score;
        binding.textViewUrScr.setText(mainScore + "");
     /*   scoreLeft = (score < scoreLeft) ? scoreLeft - score : 0;
        if (scoreLeft > 0) {
            //scoreLeft = scoreLeft - mainScore;
            binding.textViewUrScr.setText(scoreLeft + "");
        } else {
            scoreLeft = 0;
            //binding.textViewNeed.setText("Your Score");
            binding.textViewUrScr.setText(mainScore + "");
            //binding.textViewBalls.setText();
           // binding.textViewUrScr.setVisibility(GONE);
            binding.textViewYou.setText("Target Achieved");
            binding.imageViewTarget.setVisibility(VISIBLE);
           // binding.textViewIn.setVisibility(GONE);
           // binding.textViewLeft.setVisibility(VISIBLE);
        }*/
    }

    private int getRemainingBalls() {
        ballsLeft = ballsLeft - 1;
        if (ballsLeft <= 0) {
            new Handler().postDelayed(() -> {
                Intent intent = new Intent(this, CongratsScreen.class);
                intent.putExtra("EXTRA_SCORE", mainScore);
                startActivity(intent);
            }, 1000);
            return ballsLeft = 0;
        } else
            return ballsLeft;
    }

    private void setVideoOff() {
        binding.videoView1.setVisibility(GONE);
        /*binding.linearScores.setVisibility(GONE);
        binding.tvWide.setVisibility(GONE);
        binding.tvOut.setVisibility(GONE);
        binding.tvBreak.setVisibility(VISIBLE);*/
    }

    private void setVideoOn() {
        binding.videoView1.setVisibility(VISIBLE);
        binding.linearScores.setVisibility(View.VISIBLE);
        binding.tvWide.setVisibility(VISIBLE);
        binding.tvOut.setVisibility(VISIBLE);
        binding.tvBreak.setVisibility(GONE);
    }

    private void setOtherVideoOn() {
        binding.videoView1.setVisibility(VISIBLE);
//        binding.linearScores.setVisibility(View.VISIBLE);
//        binding.tvWide.setVisibility(GONE);
//        binding.tvOut.setVisibility(GONE);
//        binding.tvBreak.setVisibility(GONE);
    }

    private void setValues() {
        binding.textViewName.setText(SharedPreferencesHandler.getStringValues(this, KEY_NAME).trim());
        binding.textViewScore.setText(SharedPreferencesHandler.getIntValues(this, KEY_CHALLENGE) + "");
        scoreLeft = SharedPreferencesHandler.getIntValues(this, KEY_CHALLENGE);
        ballsLeft = (int) (SharedPreferencesHandler.getFloatValues(this, KEY_OVER) * 6);
        binding.textViewBalls.setText(ballsLeft + "");
        binding.textViewUrScr.setText("0");
       /* try {
            jDelay = Integer.parseInt(SharedPreferencesHandler.getStringValues(this, "J"));
            kDelay = Integer.parseInt(SharedPreferencesHandler.getStringValues(this, "K"));
        }catch(Exception ex){
            Log.e(TAG, "Error"+ex);
        }*/
    }

    private void onOneClick() {
       // sendData(1);
        setVideoOff();
        binding.textViewBalls.setText(getRemainingBalls() + "");
        setScore(1);
        String str = SharedPreferencesHandler.getStringValues(this, KEY_ONE_URI);
        setOtherVideoOn();
        binding.videoView1.setVideoPath(str);
        binding.videoView1.requestFocus();
        binding.videoView1.start();
    }

    private void onTwoClick() {
       // sendData(2);
        setVideoOff();
        binding.textViewBalls.setText(getRemainingBalls() + "");
        setScore(2);
        String str = SharedPreferencesHandler.getStringValues(this, KEY_TWO_URI);
        setOtherVideoOn();
        binding.videoView1.setVideoPath(str);
        binding.videoView1.requestFocus();
        binding.videoView1.start();
    }

    private void onFourClick() {
       // sendData(4);
        setVideoOff();
        binding.textViewBalls.setText(getRemainingBalls() + "");
        setScore(4);
        String str = SharedPreferencesHandler.getStringValues(this, KEY_FOUR_URI);
        setOtherVideoOn();
        binding.videoView1.setVideoPath(str);
        binding.videoView1.requestFocus();
        binding.videoView1.start();

    }

    private void onSixClick() {
       // sendData(6);
        setVideoOff();
        binding.textViewBalls.setText(getRemainingBalls() + "");
        setScore(6);
        String str = SharedPreferencesHandler.getStringValues(this, KEY_SIX_URI);
        setOtherVideoOn();
        binding.videoView1.setVideoPath(str);
        binding.videoView1.requestFocus();
        binding.videoView1.start();
    }

    private void onOutClick() {
       // sendData(-5);
        setVideoOff();
        setScore(-5);
        binding.textViewBalls.setText(getRemainingBalls() + "");
        String str = SharedPreferencesHandler.getStringValues(this, KEY_OUT_URI);
        setOtherVideoOn();
        binding.videoView1.setVideoPath(str);
        binding.videoView1.requestFocus();
        binding.videoView1.start();
    }

    private void onWideClick() {
       // sendData(1);
        setVideoOff();
        setScore(1);
        String str = SharedPreferencesHandler.getStringValues(this, KEY_WIDE_URI);
        setOtherVideoOn();
        binding.videoView1.setVideoPath(str);
        binding.videoView1.requestFocus();
        binding.videoView1.start();
    }

    private void onZeroClick() {
        setVideoOff();
        binding.textViewBalls.setText(getRemainingBalls() + "");

    }

    private void onDeadBallClick() {
        setVideoOff();
        //binding.textViewBalls.setText(getRemainingBalls() + "");

    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {

        switch (keyCode) {
            case KeyEvent.KEYCODE_B:
            {
                onBlowClick();
                return true;
            }
            case KeyEvent.KEYCODE_W:
            {
                onWideClick();
                return true;
            }
            case KeyEvent.KEYCODE_D:
            {
                onDeadBallClick();
                return true;
            }
            case KeyEvent.KEYCODE_O:
            {
                onOutClick();
                return true;
            }
            case KeyEvent.KEYCODE_0:
            {
                onZeroClick();
                return true;
            }
            case KeyEvent.KEYCODE_1:
            {
                onOneClick();
                return true;
            }
            case KeyEvent.KEYCODE_2:
            {
                onTwoClick();
                return true;
            }
            case KeyEvent.KEYCODE_4:
            {
                onFourClick();
                return true;
            }
            case KeyEvent.KEYCODE_6:
            {
                onSixClick();
                return true;
            }
        }
        return super.onKeyDown(keyCode, event);
    }
}