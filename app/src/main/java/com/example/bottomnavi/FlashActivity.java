package com.example.bottomnavi;

import android.content.pm.PackageManager;
import android.hardware.camera2.CameraAccessException;
import android.hardware.camera2.CameraManager;
import android.os.Bundle;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class FlashActivity extends AppCompatActivity {

    Switch flashControl;
    CameraManager cameraManager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        flashControl = findViewById(R.id.switchFlashLight);
        cameraManager = (CameraManager) getSystemService(CAMERA_SERVICE);

        if(getPackageManager().hasSystemFeature(PackageManager.FEATURE_CAMERA_ANY)){
           //work with flash
            if(getPackageManager().hasSystemFeature(PackageManager.FEATURE_CAMERA_FLASH)) {
                //Turn flash ON
                Toast.makeText(FlashActivity.this, "This device has flash", Toast.LENGTH_SHORT).show();
                flashControl.setEnabled(true);
            }else{
                    Toast.makeText(FlashActivity.this, "This device has no flash", Toast.LENGTH_SHORT).show();
            }
        }else{
            Toast.makeText(FlashActivity.this, "This device has no camera", Toast.LENGTH_SHORT).show();
        }

        flashControl.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    try {
                        cameraManager.setTorchMode("0", true);
                    } catch (CameraAccessException e) {
                        e.printStackTrace();
                    }
                    flashControl.setText("Flash ON");
                }else{
                    try {
                        cameraManager.setTorchMode("0", false);
                    } catch (CameraAccessException e) {
                        e.printStackTrace();
                    }
                    flashControl.setText("Flash OFF");

                }
            }
        });

    }
}