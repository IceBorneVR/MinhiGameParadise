package com.example.minhigameparadise;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.os.SystemClock;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewTreeObserver;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.Toast;

import com.example.minhigameparadise.databinding.OneToFiftyItemBinding;
import com.example.minhigameparadise.databinding.OneToFiftyMainBinding;

import io.reactivex.disposables.Disposable;
import java.util.Vector;

public class one_to_fifty_main extends AppCompatActivity
{
    // 변수 선언
    Vector<Integer> _1to25, _26to50;
    OneToFiftyMainBinding mbinding;
    OneToFiftyItemBinding itemBinding;
    one_to_fifty_ItemAdapter adapter;
    int now;
    private Disposable disposable;
    Button numbtn;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        getSupportActionBar().hide(); // 타이틀 바 가리기
        setContentView(R.layout.one_to_fifty_main); // 페이지 호출

        View item = getLayoutInflater().inflate(R.layout.one_to_fifty_item, null, false);

        numbtn = (Button) item.findViewById(R.id.numBtn);

        mbinding = DataBindingUtil.setContentView(this, R.layout.one_to_fifty_main);

        mbinding.gridView.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener()
        {
            @Override
            public void onGlobalLayout()
            {
                numbtn.setWidth(mbinding.gridView.getWidth() / 8);
                numbtn.setHeight(mbinding.gridView.getWidth() / 8);
                mbinding.gridView.getViewTreeObserver().removeOnGlobalLayoutListener(this);
            }
        });
        mbinding.gridView.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener()
        {
            @Override
            public void onGlobalLayout()
            {
                // TODO 여기에서 Item 버튼 크기 지정
                mbinding.gridView.getViewTreeObserver().removeOnGlobalLayoutListener(this);
            }
        });
        mbinding.startBtn.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                mbinding.retryBtn.setVisibility(View.VISIBLE);
                mbinding.startBtn.setVisibility(View.INVISIBLE);
                mbinding.chronometer.setOnChronometerTickListener(new Chronometer.OnChronometerTickListener()
                {
                    @Override
                    public void onChronometerTick(Chronometer chronometer)
                    {
                        long time = SystemClock.elapsedRealtime() - chronometer.getBase();
                        int h = (int) (time / 3600000);
                        int m = (int) (time - h * 3600000) / 60000;
                        int s = (int) (time - h * 3600000 - m * 60000) / 1000;
                        String t = (m < 10 ? "0" + m : m) + ":" + (s < 10 ? "0" + s : s);
                        chronometer.setText(t);
                    }
                });
                mbinding.chronometer.setBase(SystemClock.elapsedRealtime());
                mbinding.chronometer.start();
                start();
            }
        });
    }

    private void start()
    {
        timer();
        mbinding.gridView.removeOnItemTouchListener(select);
        now = 1;
        mbinding.numberView.setText("Number" + "\n" + now);
        _1to25 = new Vector<>();
        _26to50 = new Vector<>();
        for (int i = 1; i <= 25; i++)
        {
            _1to25.add(i);
            _26to50.add(i + 25);
        }
        mbinding.gridView.setLayoutManager(new GridLayoutManager(this, 5));
        adapter = new one_to_fifty_ItemAdapter(this);
        mbinding.gridView.setAdapter(adapter);
        for (int i = 1; i <= 25; i++)
        {
            int random = (int) (Math.random() * _1to25.size());
            adapter.init1to25(_1to25.get(random));
            _1to25.remove(random);
            adapter.notifyDataSetChanged();
        }
        mbinding.gridView.addOnItemTouchListener(select);
    }

    private void timer()
    {
        mbinding.retryBtn.setOnClickListener(new Button.OnClickListener()
        {
            public void onClick(View v)
            {
                mbinding.chronometer.setBase(SystemClock.elapsedRealtime());
                mbinding.chronometer.start();
                start();
            }
        });
    }

    private RecyclerView.OnItemTouchListener select = new RecyclerView.OnItemTouchListener()
    {
        @Override
        public boolean onInterceptTouchEvent(@NonNull RecyclerView parent, @NonNull MotionEvent evt)
        {
            try
            {
                switch (evt.getAction())
                {
                    case MotionEvent.ACTION_UP:
                        Button child = (Button) parent.findChildViewUnder(evt.getX(), evt.getY());
                        if (child != null)
                        {
                            int selected = Integer.parseInt(child.getText().toString());
                            if (selected == now)
                            {
                                int position = parent.getChildAdapterPosition(child);
                                Log.e("position", " => " + selected);
                                if (selected >= 26 && selected == now)
                                    adapter.setUpVisible(position);
                                now++;
                                mbinding.numberView.setText("Number"+"\n"+now);
                                if (_26to50 != null)
                                {
                                    int rand = (int) (Math.random() * _26to50.size());
                                    adapter.update26to50(position, _26to50.get(rand));
                                    _26to50.remove(rand);
                                    if (_26to50.size() == 0)
                                        _26to50 = null;
                                }
                                adapter.notifyItemChanged(position);
                            }
                            else
                            {
                                Toast.makeText(one_to_fifty_main.this, "다시 시도해주세요.", Toast.LENGTH_SHORT).show();
                            }
                            if (now == 51)
                            {
                                mbinding.chronometer.stop();
                            }
                        }
                        break;
                }
            }
            catch (Exception e)
            {
                e.printStackTrace();
            }
            return false;
        }

        @Override
        public void onTouchEvent(@NonNull RecyclerView recyclerView, @NonNull MotionEvent motionEvent)
        {

        }
        @Override
        public void onRequestDisallowInterceptTouchEvent(boolean b)
        {

        }
    };
}
