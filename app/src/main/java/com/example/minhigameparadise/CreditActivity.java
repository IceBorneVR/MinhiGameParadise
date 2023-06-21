package com.example.minhigameparadise;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;


public class CreditActivity extends AppCompatActivity
{
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide(); // 타이틀 바 가리기
        // 페이지 호출
        setContentView(R.layout.activity_credit);
    }
}