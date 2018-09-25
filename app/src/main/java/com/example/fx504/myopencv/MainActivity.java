package com.example.fx504.myopencv;

import android.annotation.SuppressLint;
import android.media.MediaActionSound;
import android.os.Environment;
import android.support.v4.app.LoaderManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Surface;
import android.view.SurfaceView;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import org.opencv.android.BaseLoaderCallback;
import org.opencv.android.CameraBridgeViewBase;
import org.opencv.android.CameraBridgeViewBase.CvCameraViewFrame2;
import org.opencv.android.JavaCameraView;
import org.opencv.android.LoaderCallbackInterface;
import org.opencv.android.OpenCVLoader;
import org.opencv.core.Core;
import org.opencv.core.CvType;
import org.opencv.core.Mat;
import org.opencv.imgproc.Imgproc;

import java.util.Date;

public class MainActivity extends AppCompatActivity implements CameraBridgeViewBase.CvCameraViewListener2{

//    static {
//        System.loadLibrary("native-libs");
//    }

    private static  final String TAG = "OCVSample : Activity";
    myJavaCamView cameraBrigeViewBase;
    Button btn_camera;
    Mat mat;

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
        setContentView(R.layout.activity_main);

        cameraBrigeViewBase = findViewById(R.id.java_cam);
        cameraBrigeViewBase.setVisibility(SurfaceView.VISIBLE);
        cameraBrigeViewBase.setCvCameraViewListener(this);

        btn_camera  = findViewById(R.id.btn_camera);
        btn_camera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MediaActionSound sound = new MediaActionSound();
                sound.play(MediaActionSound.SHUTTER_CLICK);
                Log.i(TAG, "On Click Button");
                Date date = new Date();
                String curretDateTime = date.toString();
                String fileName = Environment.getExternalStorageDirectory().getPath()+
                        "/sample_picture_"+curretDateTime+".jpeg";
                cameraBrigeViewBase.takePicture(fileName);
            }
        });

    }

    @Override
    protected void onResume(){
        super.onResume();
        if (!OpenCVLoader.initDebug()){
            Toast.makeText(this, "Error OpenCV", Toast.LENGTH_SHORT).show();
        }else {
            mLoaderCallBack.onManagerConnected(mLoaderCallBack.SUCCESS);
        }
    }

    @Override
    protected void onPause(){
        super.onPause();
        cameraBrigeViewBase.disableView();
    }

    @Override
    protected void onDestroy(){
        super.onDestroy();
        cameraBrigeViewBase.disableView();
    }


    @Override
    public void onCameraViewStarted(int width, int height) {
        mat = new Mat(width,height,CvType.CV_8UC4);
    }

    @Override
    public void onCameraViewStopped() {
        mat.release();
    }

    @Override
    public Mat onCameraFrame(CameraBridgeViewBase.CvCameraViewFrame inputFrame) {
        mat = inputFrame.rgba();
        Mat mrgbaT = mat.t();
        Core.flip(mat.t(),mrgbaT,1);
        Imgproc.resize(mrgbaT,mrgbaT,mat.size());
        return mrgbaT;
    }
}
