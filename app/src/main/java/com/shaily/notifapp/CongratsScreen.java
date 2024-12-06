package com.shaily.notifapp;

import static com.shaily.notifapp.util.SharedPreferencesHandler.KEY_OVER;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.AnimationUtils;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.shaily.notifapp.databinding.ActivityCongratsBinding;
import com.shaily.notifapp.databinding.ActivityEnterOverBinding;
import com.shaily.notifapp.util.SharedPreferencesHandler;

import java.util.regex.Pattern;

public class CongratsScreen extends AppCompatActivity {
    private static final String TAG = "CongratsScreen";
    private ActivityCongratsBinding binding;
    private int mainScore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_congrats);
        mainScore = getIntent().getIntExtra("EXTRA_SCORE", 0);
        binding.tvFinalScore.setText(mainScore+"");

        binding.restart.setOnClickListener(view -> {
            Intent intent = new Intent(this, DashBoard.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
        });

        while (true)
        {
            //binding.tvName.setText(code.name + " your final score is :");
            binding.flipper.startFlipping();
            binding.flipper.setInAnimation(AnimationUtils.loadAnimation(getApplicationContext(), R.anim.blink));
            binding.flipper.setOutAnimation(AnimationUtils.loadAnimation(getApplicationContext(), R.anim.blink));

            /*this.fours.setText(Integer.toString(code.fours));
            this.sixes.setText(Integer.toString(code.sixes));
            this.balls.setText(Integer.toString(i));*/

            return;
        }
    }
}