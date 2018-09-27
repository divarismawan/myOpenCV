package com.example.fx504.myopencv;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class HomeActivity extends AppCompatActivity {

    Button  btn_captureRBG;
    Button  btn_canny;
    Button  btn_brightness;

    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        btn_captureRBG = findViewById(R.id.btn_captureRGB);
        btn_brightness = findViewById(R.id.btn_brightness);
        btn_canny      = findViewById(R.id.btn_canny);

        setBtn_captureRBG();
        setBtn_canny();
    }

    public void setBtn_captureRBG(){
        btn_captureRBG.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent = new Intent(HomeActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }

    public void setBtn_canny() {
        btn_canny.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent = new Intent(HomeActivity.this, CannyActivity.class);
                startActivity(intent);
            }
        });
    }
}
