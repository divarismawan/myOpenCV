package com.example.fx504.myopencv;

import android.content.Context;
import android.hardware.Camera;
import android.util.AttributeSet;
import android.util.Log;

import org.opencv.android.JavaCameraView;

import java.io.FileOutputStream;

public class myJavaCamView extends JavaCameraView implements android.hardware.Camera.PictureCallback {

//    static {
//        System.loadLibrary("native-lib");
//    }

    private static final String TAG = "OpenCV";
    private String pictureName;

    public myJavaCamView(Context context, int cameraId) {
        super(context, cameraId);
    }

    public void takePicture(final String fileName){
        Log.i(TAG, "Taking Picture");
        this.pictureName = fileName;
        mCamera.setPreviewCallback(null);

        mCamera.takePicture(null,null,this);
    }

    @Override
    public void onPictureTaken(byte[] data, Camera camera) {
        Log.i(TAG, "Saving");
        mCamera.startPreview();
        mCamera.setPreviewCallback(this);

        try {
            FileOutputStream fos = new FileOutputStream(pictureName);

            fos.write(data);
            fos.close();
        }catch (java.io.IOException e){
            Log.e("Picture Demo","Exception",e);
        }
    }

    public myJavaCamView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }
}
