package com.example.minhigameparadise;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;

public class MainActivity extends AppCompatActivity
{
    Button main_btn_1to50;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_HIDE_NAVIGATION | View.SYSTEM_UI_FLAG_IMMERSIVE); // 전체화면
        getSupportActionBar().hide(); // 타이틀 바 가리기
        // 페이지 호출
        setContentView(R.layout.activity_main);
        // 메인화면 버튼들 연결
        main_btn_1to50 = findViewById(R.id.main_btn_1to50);

        // 1 TO 50 게임 이동 버튼
        main_btn_1to50.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent intent = new Intent(MainActivity.this, one_to_fifty_main.class);
                startActivity(intent);
            }
        });
    }
}