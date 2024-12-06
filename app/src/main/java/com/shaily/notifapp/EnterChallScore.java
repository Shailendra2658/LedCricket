package com.shaily.notifapp;

import static com.shaily.notifapp.util.SharedPreferencesHandler.KEY_CHALLENGE;
import static com.shaily.notifapp.util.SharedPreferencesHandler.KEY_OVER;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Window;
import android.view.WindowManager;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.shaily.notifapp.databinding.ActivityEnterOverBinding;
import com.shaily.notifapp.databinding.ActivityEnterScoreBinding;
import com.shaily.notifapp.util.SharedPreferencesHandler;

import java.util.regex.Pattern;

public class EnterChallScore extends AppCompatActivity {
    private static final String TAG = "EnterChallScore";
    private ActivityEnterScoreBinding binding;
    Pattern pattern = Pattern.compile("-?\\d+(\\.\\d+)?");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        binding = ActivityEnterScoreBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        // sendData(2);
        binding.next.setOnClickListener(view -> {
            if (isValid()) {
                try {
                    SharedPreferencesHandler.setIntValues(this, KEY_CHALLENGE, Integer.parseInt(binding.editTextScore.getText().toString()));
                } catch (Exception ex) {
                    SharedPreferencesHandler.setIntValues(this, KEY_CHALLENGE, 0);
                    Log.e(TAG, "Error " + ex);
                }
                Intent localIntent = new Intent(this, ChallScreen.class);
                startActivity(localIntent);
            } else errorChecks();
        });
    }


    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {

        if (keyCode == KeyEvent.KEYCODE_ENTER) {
            if (isValid()) {
                try {
                    SharedPreferencesHandler.setIntValues(this, KEY_CHALLENGE, Integer.parseInt(binding.editTextScore.getText().toString()));
                } catch (Exception ex) {
                    SharedPreferencesHandler.setIntValues(this, KEY_CHALLENGE, 0);
                    Log.e(TAG, "Error " + ex);
                }
                Intent localIntent = new Intent(this, ChallScreen.class);
                startActivity(localIntent);
            } else errorChecks();
            return true;
        }
        return false;
    }

    private void errorChecks() {

        if (!TextUtils.isEmpty(binding.editTextScore.getText()) &&
                !TextUtils.isEmpty(binding.editTextScore.getText().toString()) &&
                pattern.matcher(binding.editTextScore.getText().toString()).matches() &&
                !binding.editTextScore.getText().toString().startsWith("0") &&
                !binding.editTextScore.getText().toString().startsWith(".")) {
            double over = Double.parseDouble(binding.editTextScore.getText().toString());
            showError(over < 0, getString(R.string.error_zero_msg_over));
        } else
            showError(true, getString(R.string.error_msg_over));
    }

    private void showError(boolean isError, String errorMsg) {
        if (isError)
            binding.textLayout.setError(errorMsg);
        else
            binding.textLayout.setError(null);
    }

    private boolean isValid() {
        return (!(TextUtils.isEmpty(binding.editTextScore.getText()) &&
                TextUtils.isEmpty(binding.editTextScore.getText().toString())) &&
                pattern.matcher(binding.editTextScore.getText().toString()).matches() &&
                binding.editTextScore.getText().toString().length() > 0 &&
                Double.parseDouble(binding.editTextScore.getText().toString()) > 0 &&
                !binding.editTextScore.getText().toString().startsWith("0") &&
                !binding.editTextScore.getText().toString().startsWith("."));
    }
}