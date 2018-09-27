package com.example.fx504.myopencv;

import android.annotation.SuppressLint;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;

import org.opencv.android.BaseLoaderCallback;
import org.opencv.android.LoaderCallbackInterface;
import org.opencv.core.Mat;

public class CannyActivity extends AppCompatActivity {

    private static  final String TAG = "OCVSample : Activity";
    myJavaCamView cameraBrigeViewBase;
    Button btn_camera;

    Mat mat;
    Mat grey;
    Mat canny;

    private BaseLoaderCallback mLoaderCallBack = new BaseLoaderCallback(this) {
        @Override
        public void onManagerConnected(int status) {
            switch (status){
                case LoaderCallbackInterface.SUCCESS:{
                    Log.i(TAG, "Sukses");
                    cameraBrigeViewBase.enableView();
                }break;
                default:{
                    super.onManagerConnected(status);
                }
            }
        }
    };

    @SuppressLint("NewApi")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_canny);
    }
}
