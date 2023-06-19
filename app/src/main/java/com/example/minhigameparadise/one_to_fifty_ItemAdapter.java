package com.example.minhigameparadise;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.minhigameparadise.databinding.OneToFiftyItemBinding;

import java.util.Vector;

public class one_to_fifty_ItemAdapter extends RecyclerView.Adapter<one_to_fifty_ItemAdapter.ItemViewHolder>
{
    private Vector<Integer> _1to50 = new Vector<>();
    private Vector<Integer> visible = new Vector<>();

    Context context;

    public one_to_fifty_ItemAdapter(Context context)
    {
        this.context = context;
        for (int i=0; i<25; i++)
        {
            visible.add(i, View.VISIBLE);
        }
    }

    @NonNull
    @Override
    public ItemViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType)
    {
        OneToFiftyItemBinding binding = OneToFiftyItemBinding.inflate(LayoutInflater.from(context), viewGroup, false);
        return new ItemViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ItemViewHolder itemViewHolder, int position)
    {
        OneToFiftyItemBinding binding = itemViewHolder.binding;
        int number = _1to50.get(position);
        binding.numBtn.setText(String.valueOf(number));
        binding.numBtn.setVisibility(visible.get(position));
    }

    @Override
    public int getItemCount()
    {
        return _1to50.size();
    }

    // 처음 1부터 25까지는 그냥 셋팅
    void init1to25(int number)
    {
        _1to50.add(number);
    }

    // 26부터 50까지는 기존 숫자가 사라진 위치에 셋팅
    void update26to50(int position, int number)
    {
        _1to50.remove(position);
        _1to50.add(position, number);
    }

    void setUpVisible(int position)
    {
        visible.remove(position);
        visible.add(position, View.INVISIBLE);
    }

    class ItemViewHolder extends RecyclerView.ViewHolder
    {
        OneToFiftyItemBinding binding;

        ItemViewHolder(OneToFiftyItemBinding binding)
        {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}