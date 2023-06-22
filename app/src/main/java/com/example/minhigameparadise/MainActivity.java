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
    Button main_btn_1to50, main_btn_soundgame, main_btn_samesame, main_btn_cardremember, main_btn_credit;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide(); // 타이틀 바 가리기
        // 페이지 호출
        setContentView(R.layout.activity_main);
        // 메인화면 버튼들 연결
        main_btn_1to50 = findViewById(R.id.main_btn_1to50);
        main_btn_soundgame = findViewById(R.id.main_btn_soundgame);
        main_btn_samesame = findViewById(R.id.main_btn_samesame);
        main_btn_cardremember = findViewById(R.id.main_btn_cardremember);
        main_btn_credit = findViewById(R.id.main_btn_credit);

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

        // 키패드 피아노 게임 이동 버튼
        main_btn_soundgame.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent intent = new Intent(MainActivity.this, soundgame_main.class);
                startActivity(intent);
            }
        });

        // 같은 카드 뒤집기 게임 이동 버튼
        main_btn_samesame.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent intent = new Intent(MainActivity.this, samesame_main.class);
                startActivity(intent);
            }
        });

        // 같은 숫자 찾기 게임 이동 버튼
        main_btn_cardremember.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent intent = new Intent(MainActivity.this, Card_remember_main.class);
                startActivity(intent);
            }
        });

        // 크레딧 이동 버튼
        main_btn_credit.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent intent = new Intent(MainActivity.this, CreditActivity.class);
                startActivity(intent);
            }
        });
    }
}