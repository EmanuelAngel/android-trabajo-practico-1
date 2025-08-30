package com.example.trabajoprctico1;

import android.Manifest;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {
    private AirplaneModeAndCallReceiver airplaneModeAndCallReceiver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        EdgeToEdge.enable(this);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        requestCallPhonePermissions();

        airplaneModeAndCallReceiver = new AirplaneModeAndCallReceiver();
    }

    @Override
    protected void onResume() {
        super.onResume();

        registerReceiver(
                airplaneModeAndCallReceiver,
                new IntentFilter(Intent.ACTION_AIRPLANE_MODE_CHANGED)
        );
    }

    @Override
    protected void onStop() {
        super.onStop();

        unregisterReceiver(airplaneModeAndCallReceiver);
    }

    private void requestCallPhonePermissions() {
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M
                && checkSelfPermission(Manifest.permission.CALL_PHONE)
                != PackageManager.PERMISSION_GRANTED){
            requestPermissions(new String[] { Manifest.permission.CALL_PHONE },1000);
        }
    }
}