package com.shaily.notifapp;

import static com.shaily.notifapp.util.SharedPreferencesHandler.KEY_FOUR_URI;
import static com.shaily.notifapp.util.SharedPreferencesHandler.KEY_ONE_URI;
import static com.shaily.notifapp.util.SharedPreferencesHandler.KEY_OUT_URI;
import static com.shaily.notifapp.util.SharedPreferencesHandler.KEY_OVER;
import static com.shaily.notifapp.util.SharedPreferencesHandler.KEY_SIX_URI;
import static com.shaily.notifapp.util.SharedPreferencesHandler.KEY_TWO_URI;
import static com.shaily.notifapp.util.SharedPreferencesHandler.KEY_URI;
import static com.shaily.notifapp.util.SharedPreferencesHandler.KEY_WIDE_URI;

import android.Manifest;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.PowerManager;
import android.text.Editable;
import android.text.InputType;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.databinding.DataBindingUtil;

import com.google.firebase.crashlytics.FirebaseCrashlytics;
import com.google.firebase.crashlytics.internal.common.CrashlyticsCore;
import com.shaily.notifapp.databinding.ActivityDashboardBinding;
import com.shaily.notifapp.databinding.ActivityEnterOverBinding;
import com.shaily.notifapp.util.SharedPreferencesHandler;

import java.util.regex.Pattern;

public class DashBoard extends AppCompatActivity {
    private static final String TAG = "DashBoard";
    private static final int CODE_CAM_PER = 101;

    private ActivityDashboardBinding binding;
    Pattern pattern = Pattern.compile("-?\\d+(\\.\\d+)?");
    private PowerManager.WakeLock mWakeLock;
    String hello;
    private String[] permissionList = {Manifest.permission.WRITE_EXTERNAL_STORAGE};//, Manifest.permission.BLUETOOTH_CONNECT, Manifest.permission.BLUETOOTH_SCAN};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
           binding = DataBindingUtil.setContentView(this, R.layout.activity_dashboard);

        //Bluetooth connections
        //mBluetoothAdapter = BluetoothAdapter.getDefaultAdapter();

       /* getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        mWakeLock = ((PowerManager) getSystemService(Context.POWER_SERVICE))
                .newWakeLock(PowerManager.SCREEN_BRIGHT_WAKE_LOCK, getClass().getName());
        mWakeLock.acquire();*/

        // If the adapter is null, then Bluetooth is not supported
        /*if (mBluetoothAdapter == null) {
            Toast.makeText(this, "Bluetooth is not available", Toast.LENGTH_LONG).show();
            finish();
            return;
        }*/


       // FirebaseCrashlytics.getInstance().setCrashlyticsCollectionEnabled(true);
      //  FirebaseCrashlytics.getInstance().recordException(new RuntimeException("Invalidtoken"));

        binding.start.setOnClickListener(view -> {
            checkForPermission(permissionList);
            //if (checkForPermission(permissionList))
            //  sendData(2);
            // Permission is granted. Continue the action or workflow in your
            // app.
            startActivity(new Intent(DashBoard.this, SelectBowler.class));

        });
        String filePath = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS).toString() + "/Videos/bowl.mp4";
        String fileSixPath = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS).toString() + "/Videos/six.mp4";
        String fileFourPath = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS).toString() + "/Videos/four.mp4";
        String fileTwoPath = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS).toString() + "/Videos/two.mp4";
        String fileOnePath = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS).toString() + "/Videos/one.mp4";
        String fileOutPath = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS).toString() + "/Videos/out.mp4";
        String fileWidePath = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS).toString() + "/Videos/wide.mp4";


        SharedPreferencesHandler.setStringValues(DashBoard.this, KEY_URI, filePath);
        SharedPreferencesHandler.setStringValues(DashBoard.this, KEY_SIX_URI, fileSixPath);
        SharedPreferencesHandler.setStringValues(DashBoard.this, KEY_FOUR_URI, fileFourPath);
        SharedPreferencesHandler.setStringValues(DashBoard.this, KEY_TWO_URI, fileTwoPath);
        SharedPreferencesHandler.setStringValues(DashBoard.this, KEY_ONE_URI, fileOnePath);
        SharedPreferencesHandler.setStringValues(DashBoard.this, KEY_OUT_URI, fileOutPath);
        SharedPreferencesHandler.setStringValues(DashBoard.this, KEY_WIDE_URI, fileWidePath);

    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {

        if (keyCode == KeyEvent.KEYCODE_ENTER) {
            checkForPermission(permissionList);

            startActivity(new Intent(DashBoard.this, SelectBowler.class));

            return true;
        }
        return false;
    }

    private boolean checkForPermission(String... permissionList) {
        for (String permission : permissionList) {
            if (ContextCompat.checkSelfPermission(this, permission) !=
                    PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(this, new String[]{permission}, CODE_CAM_PER);
                requestPermissionLauncher.launch(permission);
                return false;
            }
        }
        return true;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case CODE_CAM_PER:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    Toast.makeText(getApplicationContext(), "Permission Granted", Toast.LENGTH_SHORT).show();

                    // main logic
                } else {
                    Toast.makeText(getApplicationContext(), "Permission Denied", Toast.LENGTH_SHORT).show();
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                        if (ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA)
                                != PackageManager.PERMISSION_GRANTED) {
                            showMessageOKCancel("You need to allow access permissions",
                                    new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialog, int which) {
                                            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                                                requestPermission();
                                            }
                                        }
                                    });
                        }
                    }
                }
                break;
        }
    }

    private void showMessageOKCancel(String message, DialogInterface.OnClickListener okListener) {
        new AlertDialog.Builder(DashBoard.this)
                .setMessage(message)
                .setPositiveButton("OK", okListener)
                .setNegativeButton("Cancel", null)
                .create()
                .show();
    }

    // Register the permissions callback, which handles the user's response to the
// system permissions dialog. Save the return value, an instance of
// ActivityResultLauncher, as an instance variable.
    private ActivityResultLauncher<String> requestPermissionLauncher =
            registerForActivityResult(new ActivityResultContracts.RequestPermission(), isGranted -> {
                if (isGranted) {
                    // Permission is granted. Continue the action or workflow in your
                    // app.

                } else {
                    // Explain to the user that the feature is unavailable because the
                    // features requires a permission that the user has denied. At the
                    // same time, respect the user's decision. Don't link to system
                    // settings in an effort to convince the user to change their
                    // decision.
                    Toast.makeText(this, "Cant use this app without giving permission of storage. Please grant permission to proceed.", Toast.LENGTH_LONG).show();
                }
            });

    private void requestPermission() {

        ActivityCompat.requestPermissions(this,
                new String[]{Manifest.permission.CAMERA},
                CODE_CAM_PER);
    }
}