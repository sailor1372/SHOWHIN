package com.example.bottomnavi;

import android.content.pm.PackageManager;
import android.hardware.camera2.CameraAccessException;
import android.hardware.camera2.CameraManager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import static android.content.Context.CAMERA_SERVICE;


public class Fragment3 extends Fragment {

    Switch flashControl;
    CameraManager cameraManager;

    public Fragment3(){


    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.flash_switch,container,false);

        flashControl = view.findViewById(R.id.switchFlashLight);
        cameraManager = (CameraManager) getActivity().getSystemService(CAMERA_SERVICE);

        if(getActivity().getPackageManager().hasSystemFeature(PackageManager.FEATURE_CAMERA_ANY)){
            //work with flash
            if(getActivity().getPackageManager().hasSystemFeature(PackageManager.FEATURE_CAMERA_FLASH)) {
                //Turn flash ON
                Toast.makeText(getContext(), "This device has flash", Toast.LENGTH_SHORT).show();
                flashControl.setEnabled(true);
            }else{
                Toast.makeText(getContext(), "This device has no flash", Toast.LENGTH_SHORT).show();
            }
        }else{
            Toast.makeText(getContext(), "This device has no camera", Toast.LENGTH_SHORT).show();
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


        return view;


    }
}
