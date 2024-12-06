package com.shaily.notifapp;

import static com.shaily.notifapp.util.SharedPreferencesHandler.KEY_FOUR_URI;
import static com.shaily.notifapp.util.SharedPreferencesHandler.KEY_NAME;
import static com.shaily.notifapp.util.SharedPreferencesHandler.KEY_ONE_URI;
import static com.shaily.notifapp.util.SharedPreferencesHandler.KEY_OUT_URI;
import static com.shaily.notifapp.util.SharedPreferencesHandler.KEY_SIX_URI;
import static com.shaily.notifapp.util.SharedPreferencesHandler.KEY_TWO_URI;
import static com.shaily.notifapp.util.SharedPreferencesHandler.KEY_URI;
import static com.shaily.notifapp.util.SharedPreferencesHandler.KEY_WIDE_URI;

import android.Manifest;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.databinding.DataBindingUtil;

import com.shaily.notifapp.databinding.ActivityEnterNameBinding;
import com.shaily.notifapp.databinding.ActivitySelectBowlerBinding;
import com.shaily.notifapp.util.SharedPreferencesHandler;

public class SelectBowler extends AppCompatActivity {
    private static final String TAG = "SelectBowler";
    private static final int CODE_CAM_PER = 101;
    private ActivitySelectBowlerBinding binding;
    private String[] permissionList = {Manifest.permission.WRITE_EXTERNAL_STORAGE};//, Manifest.permission.BLUETOOTH_CONNECT, Manifest.permission.BLUETOOTH_SCAN};
    String filePath,fileSixPath,fileFourPath, fileTwoPath,fileOnePath,fileOutPath, fileWidePath;
    String filePathOne,fileSixPathOne,fileFourPathOne, fileTwoPathOne,fileOnePathOne,fileOutPathOne, fileWidePathOne;
    String filePathTwo,fileSixPathTwo,fileFourPathTwo, fileTwoPathTwo,fileOnePathTwo,fileOutPathTwo, fileWidePathTwo;
    String filePathThree,fileSixPathThree,fileFourPathThree, fileTwoPathThree,fileOnePathThree,fileOutPathThree, fileWidePathThree;
    String filePathFour,fileSixPathFour,fileFourPathFour, fileTwoPathFour,fileOnePathFour,fileOutPathFour, fileWidePathFour;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        binding = ActivitySelectBowlerBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        SharedPreferencesHandler.setStringValues(SelectBowler.this, KEY_URI, filePath);
        SharedPreferencesHandler.setStringValues(SelectBowler.this, KEY_SIX_URI, fileSixPath);
        SharedPreferencesHandler.setStringValues(SelectBowler.this, KEY_FOUR_URI, fileFourPath);
        SharedPreferencesHandler.setStringValues(SelectBowler.this, KEY_TWO_URI, fileTwoPath);
        SharedPreferencesHandler.setStringValues(SelectBowler.this, KEY_ONE_URI, fileOnePath);
        SharedPreferencesHandler.setStringValues(SelectBowler.this, KEY_OUT_URI, fileOutPath);
        SharedPreferencesHandler.setStringValues(SelectBowler.this, KEY_WIDE_URI, fileWidePath);

        selectBowler(binding.bowller1, getDrawable(R.drawable.one), getDrawable(R.drawable.runnerone));
        selectBowler(binding.bowller2, getDrawable(R.drawable.two), getDrawable(R.drawable.runner_two));
        selectBowler(binding.bowller3, getDrawable(R.drawable.three), getDrawable(R.drawable.runnerthree));
        selectBowler(binding.bowller4, getDrawable(R.drawable.four), getDrawable(R.drawable.runnerfour));
     //   selectBowler(binding.bowller5, getDrawable(R.drawable.five), getDrawable(R.drawable.selectedfive));
      //  selectBowler(binding.bowller6, getDrawable(R.drawable.six), getDrawable(R.drawable.runnersix));
        binding.bowller1.setChecked(true);


        binding.next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SelectBowler.this, EnterName.class);
                startActivity(intent);
            }
        });


    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {

        if (keyCode == KeyEvent.KEYCODE_ENTER) {
            Intent intent = new Intent(SelectBowler.this, EnterName.class);
            startActivity(intent);
            return true;
        }
        return false;
    }

    private void setUrls(int bowler) {
        filePath = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS).toString() + "/Videos/bowl"+bowler+".mp4";
        fileSixPath = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS).toString() + "/Videos/six"+bowler+".mp4";
        fileFourPath = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS).toString() + "/Videos/four"+bowler+".mp4";
        fileTwoPath = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS).toString() + "/Videos/two"+bowler+".mp4";
        fileOnePath = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS).toString() + "/Videos/one"+bowler+".mp4";
        fileOutPath = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS).toString() + "/Videos/out"+bowler+".mp4";
        fileWidePath = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS).toString() + "/Videos/wide"+bowler+".mp4";

        SharedPreferencesHandler.setStringValues(SelectBowler.this, KEY_URI, filePath);
        SharedPreferencesHandler.setStringValues(SelectBowler.this, KEY_SIX_URI, fileSixPath);
        SharedPreferencesHandler.setStringValues(SelectBowler.this, KEY_FOUR_URI, fileFourPath);
        SharedPreferencesHandler.setStringValues(SelectBowler.this, KEY_TWO_URI, fileTwoPath);
        SharedPreferencesHandler.setStringValues(SelectBowler.this, KEY_ONE_URI, fileOnePath);
        SharedPreferencesHandler.setStringValues(SelectBowler.this, KEY_OUT_URI, fileOutPath);
        SharedPreferencesHandler.setStringValues(SelectBowler.this, KEY_WIDE_URI, fileWidePath);
    }


    private void selectBowler(CheckBox bowler, Drawable selected, Drawable UnSelected) {
        bowler.setOnCheckedChangeListener((compoundButton, b) -> {
            resetBowler();
            if(b) {
                setUrls(updateUrls(compoundButton));
                bowler.setBackground(selected);
            }
            else
                bowler.setBackground(UnSelected);

        });
    }

    private int updateUrls(CompoundButton compoundButton) {
        if(compoundButton.getId()==binding.bowller2.getId()) {
            return 2;
        } else if(compoundButton.getId()==binding.bowller3.getId())
            return 3;
        else if(compoundButton.getId()==binding.bowller4.getId())
            return 4;
        else {
            return 1;
        }
    }

    private void resetBowler() {
        binding.bowller1.setBackground(getDrawable(R.drawable.runnerone));
        binding.bowller2.setBackground(getDrawable(R.drawable.runner_two));
        binding.bowller3.setBackground(getDrawable(R.drawable.runnerthree));
        binding.bowller4.setBackground(getDrawable(R.drawable.runnerfour));
       // binding.bowller5.setBackground(getDrawable(R.drawable.selectedfive));
       // binding.bowller6.setBackground(getDrawable(R.drawable.runnersix));
        binding.bowller1.setChecked(false);
        binding.bowller2.setChecked(false);
        binding.bowller3.setChecked(false);
        binding.bowller4.setChecked(false);
       // binding.bowller5.setChecked(false);
       // binding.bowller6.setChecked(false);
    }
}