package com.example.minhigameparadise;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.SystemClock;
import android.os.VibrationEffect;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.Toast;
import java.util.Random;

public class Card_remember_easy extends AppCompatActivity implements View.OnClickListener
{
    Button[] btn = new Button[8];
    boolean red[] = new boolean[8];
    int btnId[] = {R.id.btn1, R.id.btn2, R.id.btn3, R.id.btn4, R.id.btn5, R.id.btn6, R.id.btn7, R.id.btn8};
    Chronometer chronometer;
    int r[] = new int[4], t[] = new int[4], num[] = new int[8];
    double initTime;
    int check, num1=-1, num2=-1;
    public static String time="";

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide(); // 타이틀 바 가리기
        setContentView(R.layout.card_remember_easy);
        // 최대 100까지의 랜덤한 수 출력
        Random random = new Random();
        for (int i = 0; i < 4; i++)
        {
            r[i] = random.nextInt(90) + 10;
            for (int j = 0; j < i; j++)
            {
                if (r[i] == r[j])
                {
                    i--;
                }
            }
        }
        for (int i = 0; i < 8; i++)
        {
            while (true)
            {
                int n = random.nextInt(4);

                if (t[n] == 0)
                {
                    t[n] = 1;
                    num[i] = r[n];
                    break;
                }
                else if (t[n] == 1)
                {
                    t[n] = 2;
                    num[i] = r[n];
                    break;
                }
            }
        }
        // 게임 타이머 구현
        chronometer = (Chronometer) findViewById(R.id.chronometer);
        chronometer.setBase(SystemClock.elapsedRealtime());
        chronometer.start();

        for (int i = 0; i < 8; i++)
        {
            btn[i] = (Button) findViewById(btnId[i]);
            btn[i].setOnClickListener(this);
            red[i] = false;
        }
    }


    public void onClick(View view)
    {
        for (int i = 0; i < 8; i++)
        {
            if (view == btn[i])
            {
                btn[i].setText(String.valueOf(num[i]));

                if (num1 == -1 && num2 == -1)
                {
                    num1 = i;
                }
                else if (num1 != -1 && num2 == -1)
                {
                    if (num1 != i)
                    {
                        num2 = i;
                    }
                }
            }
        }
        if (num1 != -1 && num2 != -1)
        {
            compare(num1, num2);
            num1 = -1;
            num2 = -1;
        }
    }

    public void compare(final int num1, final int num2)
    {
        if (btn[num1].getText().toString().equals(btn[num2].getText().toString()))
        {
            btn[num1].setBackgroundColor(Color.RED);
            btn[num1].setClickable(false);
            red[num1] = true;

            btn[num2].setBackgroundColor(Color.RED);
            btn[num2].setClickable(false);
            red[num2] = true;

            check++;
            if (check == 4)
            {
                chronometer.stop();

                time = chronometer.getText().toString();

                Intent intent = new Intent(getApplicationContext(), Card_remember_ranking.class);
                startActivity(intent);
                finish();
            }
        }
        else
        {
            for (int i = 0; i < 8; i++)
            {
                if (red[i] == false)
                {
                    btn[i].setClickable(false);
                }
            }

            new Handler().postDelayed(new Runnable()
            {
                @Override
                public void run()
                {
                    btn[num1].setText("");
                    btn[num2].setText("");

                    for (int i = 0; i < 8; i++)
                    {
                        if (red[i] == false)
                        {
                            btn[i].setClickable(true);
                        }
                    }
                }
            }, 2000);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        super.onCreateOptionsMenu(menu);
        getMenuInflater().inflate(R.menu.rank, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item)
    {
        int id = item.getItemId();

        if (id == R.id.itemRank)
        {
            Intent intent = new Intent(getApplicationContext(), Card_remember_ranking.class);
            startActivity(intent);
            finish();
        }

        return super.onOptionsItemSelected(item);
    }

    public boolean onKeyDown(int keyCode, KeyEvent event)
    {
        if (keyCode == KeyEvent.KEYCODE_BACK)
        {
            if (System.currentTimeMillis() - initTime > 3000) {//처음 누른 상태
                Toast.makeText(getApplicationContext(), "홈 화면으로 돌아가려면 한 번 더 눌러주세요", Toast.LENGTH_SHORT).show();

                initTime = System.currentTimeMillis();
            }
            else
            {
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
                finish();
            }
        }
        return false;
    }
}
