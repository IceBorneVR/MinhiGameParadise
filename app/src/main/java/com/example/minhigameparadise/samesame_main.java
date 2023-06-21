package com.example.minhigameparadise;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collections;

public class samesame_main extends AppCompatActivity implements View.OnClickListener
{
    private ImageButton[] buttons = new ImageButton[10];
    private ArrayList<Integer> imageList;
    private ArrayList<MemoryCard> cards;
    private TextView resultText;
    private ImageButton resetBtn;
    int preCardPostiton =-1;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide(); // 타이틀 바 가리기
        setContentView(R.layout.samesame_main);

        resultText = findViewById(R.id.result_text);

        init();

        resetBtn = findViewById(R.id.resetBtn);
        resetBtn.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                init();
            }
        });

    }

    public void init()
    {
        imageList = new ArrayList<>();

        imageList.add(R.drawable.red);
        imageList.add(R.drawable.blue);
        imageList.add(R.drawable.green);
        imageList.add(R.drawable.yellow);
        imageList.add(R.drawable.black);
        imageList.add(R.drawable.red);
        imageList.add(R.drawable.blue);
        imageList.add(R.drawable.green);
        imageList.add(R.drawable.yellow);
        imageList.add(R.drawable.black);

        Collections.shuffle(imageList);
        cards = new ArrayList<>();
        for(int i = 0; i < buttons.length; i++)
        {
            String buttonID= "imageBtn" + i;
            int resourceID = getResources().getIdentifier(buttonID, "id", getPackageName());
            buttons[i] = findViewById(resourceID);
            buttons[i].setOnClickListener(this);
            cards.add(new MemoryCard(imageList.get(i),false, false ));
            buttons[i].setImageResource(R.drawable.cardback);
            buttons[i].setAlpha(1.0f);
        }
        resultText.setText("");
    }
    @Override
    public void onClick(View view)
    {
        int id = view.getId();

        int position = 0;
        if (id == R.id.imageBtn0){
            position = 0;
        }else if(id == R.id.imageBtn1){
            position = 1;
        }else if(id == R.id.imageBtn2){
            position = 2;
        }else if(id == R.id.imageBtn3){
            position = 3;
        }else if(id == R.id.imageBtn4){
            position = 4;
        }else if(id == R.id.imageBtn5){
            position = 5;
        }else if(id == R.id.imageBtn6){
            position = 6;
        }else if(id == R.id.imageBtn7){
            position = 7;
        }else if(id == R.id.imageBtn8){
            position = 8;
        }else if(id == R.id.imageBtn9){
            position = 9;
        }
        updateModel(position);
        updateView(position);
    }

    private  void updateModel(int position)
    {
        MemoryCard card = cards.get(position);
        if (card.isFaceUp())
        {
            Toast.makeText(this, " 이미 뒤집혔음", Toast.LENGTH_SHORT).show();
            return;
        }

        if (preCardPostiton == -1)
        {
            restoreCard();
            preCardPostiton = position;
        }
        else
        {
            checkForMatch(preCardPostiton, position);
            preCardPostiton = -1;
        }
        cards.get(position).setFaceUp(!card.isFaceUp());
    }

    private void updateView(int position)
    {
        MemoryCard card = cards.get(position);
        if (card.isFaceUp())
        {
            buttons[position].setImageResource(card.getImageld());
        }
        else
        {
            buttons[position].setImageResource(R.drawable.cardback);
        }
    }

    private void restoreCard()
    {
        for (int i = 0; i < cards.size(); i++)
        {
            if (!cards.get(i).isMatched())
            {
                buttons[i].setImageResource(R.drawable.cardback);
                cards.get(i).setFaceUp(false);
            }
        }
    }
    private void  checkForMatch(int prePosition, int position)
    {
        if (cards.get(prePosition).getImageld() == cards.get(position).getImageld())
        {
            resultText.setText("찾았습니다");
            cards.get(prePosition).setMatched(true);
            cards.get(position).setMatched(true);
            buttons[prePosition].setAlpha(0.1f);
            buttons[position].setAlpha(0.1f);
            checkCompletion();
        }
        else
        {
            resultText.setText("다시 찾아보세요");
        }
    }
    private void checkCompletion()
    {
        int count= 0;
        for(int i =0; i< cards.size(); i++)
        {
            if (cards.get(i).isMatched())
            {
                count++;
            }
        }
        if (count == cards.size()){
            resultText.setText("클리어!!");
        }
    }
}
