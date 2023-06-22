package com.example.minhigameparadise;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Card_remember_main extends AppCompatActivity implements View.OnClickListener
{
    Button btnEasy, btnNormal, btnHard;
    double initTime;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide(); // 타이틀 바 가리기
        setContentView(R.layout.card_remember_main);
        setTitle("카드기억게임");

        btnEasy = (Button)findViewById(R.id.btnEasy);
        btnEasy.setOnClickListener(this);

        btnNormal = (Button)findViewById(R.id.btnNormal);
        btnNormal.setOnClickListener(this);

        btnHard = (Button)findViewById(R.id.btnHard);
        btnHard.setOnClickListener(this);
    }

    public void onClick(View view)
    {
        if(view == btnEasy)
        {
            Intent intent = new Intent(getApplicationContext(), Card_remember_easy.class);
            startActivity(intent);
            finish();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        super.onCreateOptionsMenu(menu);
        getMenuInflater().inflate(R.menu.rank,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item)
    {
        int id = item.getItemId();
        if(id == R.id.itemRank)
        {
            Intent intent = new Intent(getApplicationContext(),Card_remember_ranking.class);
            startActivity(intent);
            finish();
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event)
    {
        if(keyCode == KeyEvent.KEYCODE_BACK){
            if(System.currentTimeMillis() - initTime > 3000)
            {   //처음 누른 상태
                Toast.makeText(getApplicationContext(),"종료하려면 한 번 더 눌러주세요",Toast.LENGTH_SHORT).show();

                initTime = System.currentTimeMillis();
            }
            else
            {
                finish();
            }
        }
        return false;
    }
}
