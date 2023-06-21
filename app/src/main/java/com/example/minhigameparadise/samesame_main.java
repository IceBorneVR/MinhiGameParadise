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
    private ImageButton[] buttons = new ImageButton[10]; // ImageButton 배열
    private ArrayList<Integer> imageList; // 이미지 리소스 ID 리스트
    private ArrayList<MemoryCard> cards; // 메모리 카드 리스트
    private TextView resultText; // 결과를 표시하는 TextView
    private ImageButton resetBtn; // 리셋 버튼
    int preCardPostiton =-1; // 이전 카드 위치

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide(); // 타이틀 바 가리기
        setContentView(R.layout.samesame_main);

        resultText = findViewById(R.id.result_text);

        init();

        resetBtn = findViewById(R.id.resetBtn);

        // 리셋 버튼을 클릭하면 게임을 초기화
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
        // 이미지 리소스 ID 리스트 초기화
        imageList = new ArrayList<>();
        // 이미지 리소스 ID 리스트에 이미지 추가
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
        // 이미지 리스트를 섞음
        Collections.shuffle(imageList);
        // 메모리 카드 리스트 초기화
        cards = new ArrayList<>();
        for(int i = 0; i < buttons.length; i++)
        {
            String buttonID= "imageBtn" + i;
            int resourceID = getResources().getIdentifier(buttonID, "id", getPackageName());
            buttons[i] = findViewById(resourceID);
            buttons[i].setOnClickListener(this); // 버튼에 클릭 리스너 등록
            cards.add(new MemoryCard(imageList.get(i),false, false )); // 메모리 카드 생성 및 리스트에 추가
            buttons[i].setImageResource(R.drawable.cardback); // ImageButton의 이미지 설정
            buttons[i].setAlpha(1.0f); // ImageButton의 투명도 설정
        }
        resultText.setText(""); // 결과 텍스트 초기화
    }
    @Override
    public void onClick(View view)
    {
        int id = view.getId();
        // 클릭된 ImageButton의 ID에 따라 위치 결정
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
        updateModel(position); // 모델 업데이트
        updateView(position); // 뷰 업데이트
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
            // 이전 카드 위치가 없으면 모든 카드를 뒷면으로 복원
            restoreCard();
            preCardPostiton = position;
        }
        else
        {
            // 이전 카드와 현재 카드를 비교하여 맞는지 확인
            checkForMatch(preCardPostiton, position);
            preCardPostiton = -1;
        }
        // 현재 카드를 뒤집거나 다시 뒤집음
        cards.get(position).setFaceUp(!card.isFaceUp());
    }

    // 카드 이미지 설정
    private void updateView(int position)
    {
        MemoryCard card = cards.get(position);
        if (card.isFaceUp())
        {
            // 현재 카드가 뒷면인 경우
            buttons[position].setImageResource(card.getImageld());
        }
        else
        {
            // 현재 카드가 앞면인 경우
            buttons[position].setImageResource(R.drawable.cardback);
        }
    }

    // 매치되지 않은 카드를 뒷면으로 복원
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

    // 카드가 매치되었는지 확인 후 처리
    private void  checkForMatch(int prePosition, int position)
    {
        if (cards.get(prePosition).getImageld() == cards.get(position).getImageld())
        {
            resultText.setText("찾았습니다"); // 매치되는 경우 결과 텍스트 출력
            // 카드를 매치된 상태로 설정
            cards.get(prePosition).setMatched(true);
            cards.get(position).setMatched(true);
            // 매치된 카드의 투명도 조정
            buttons[prePosition].setAlpha(0.1f);
            buttons[position].setAlpha(0.1f);
            // 모든 카드가 매치되었는지 확인
            checkCompletion();
        }
        else
        {
            // 매치되지 않은 경우
            resultText.setText("다시 찾아보세요");
        }
    }

    // 모든 카드가 매치되었을 때 결과 출력
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
