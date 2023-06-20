package com.example.minhigameparadise;

import androidx.appcompat.app.AppCompatActivity;

import android.media.AudioAttributes;
import android.media.AudioManager;
import android.media.SoundPool;
import android.os.Build;
import android.os.Bundle;
import android.view.View;

public class soundgame_main extends AppCompatActivity
{
    private SoundPool soundPool;
    private int sound1, sound2, sound3, sound4, sound5, sound6, sound7, sound8, sound9, sound0;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide(); // 타이틀 바 가리기
        setContentView(R.layout.soundgame_main);

        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP)
        {
            AudioAttributes audioAttributes = new AudioAttributes.Builder()
                    .setUsage(AudioAttributes.USAGE_ASSISTANCE_SONIFICATION)
                    .setContentType(AudioAttributes.CONTENT_TYPE_SONIFICATION)
                    .build();

            soundPool = new SoundPool.Builder()
                    .setMaxStreams(6)
                    .setAudioAttributes(audioAttributes)
                    .build();
        }
        else
        {
            soundPool = new SoundPool(6, AudioManager.STREAM_MUSIC, 0);
        }

        sound1 = soundPool.load(this, R.raw.sound1, 1 );
        sound2 = soundPool.load(this, R.raw.sound2, 1 );
        sound3 = soundPool.load(this, R.raw.sound3, 1 );
        sound4 = soundPool.load(this, R.raw.sound4, 1 );
        sound5 = soundPool.load(this, R.raw.sound5, 1 );
        sound6 = soundPool.load(this, R.raw.sound6, 1 );
        sound7 = soundPool.load(this, R.raw.sound7, 1 );
        sound8 = soundPool.load(this, R.raw.sound8, 1 );
        sound9 = soundPool.load(this, R.raw.sound9, 1 );
        sound0 = soundPool.load(this, R.raw.sound0, 1 );
    }

    public void playSound(View v)
    {
        switch (v.getId())
        {
            case R.id.btn_sound1:
                // 재생시킬파일, 왼쪽볼륨 크기, 오른쪽볼륨 크기, 우선순위, 재생횟수, 재생속도
                soundPool.play(sound1, 1, 1, 0, 0, 1);
                break;
            case R.id.btn_sound2:
                soundPool.play(sound2, 1, 1, 0, 0, 1);
                break;
            case R.id.btn_sound3:
                soundPool.play(sound3, 1, 1, 0, 0, 1);
                break;
            case R.id.btn_sound4:
                soundPool.play(sound4, 1, 1, 0, 0, 1);
                break;
            case R.id.btn_sound5:
                soundPool.play(sound5, 1, 1, 0, 0, 1);
                break;
            case R.id.btn_sound6:
                soundPool.play(sound6, 1, 1, 0, 0, 1);
                break;
            case R.id.btn_sound7:
                soundPool.play(sound7, 1, 1, 0, 0, 1);
                break;
            case R.id.btn_sound8:
                soundPool.play(sound8, 1, 1, 0, 0, 1);
                break;
            case R.id.btn_sound9:
                soundPool.play(sound9, 1, 1, 0, 0, 1);
                break;
            case R.id.btn_sound0:
                soundPool.play(sound0, 1, 1, 0, 0, 1);
                break;
        }
    }

    @Override
    protected void onDestroy()
    {
        super.onDestroy();
        //메모리 해제
        soundPool.release();
        soundPool = null;
    }
}
