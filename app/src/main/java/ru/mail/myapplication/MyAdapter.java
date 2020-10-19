package ru.mail.myapplication;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class MyAdapter extends RecyclerView.Adapter<MyViewHolder> {

    private List<Integer> nums;
    private Activity activity;
    private static final String KEY = "pos";

    public MyAdapter(Activity act, int count) {
        nums = new ArrayList<>();
        activity = act;
        for (int i = 1; i <= count; i++) {
            nums.add(i);
        }
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Log.d("MyAdapter", "onCreateViewHolder");
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.number_layout, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final MyViewHolder holder, final int position) {
        Log.d("MyAdapter", "onBindViewHolder with position " + position);
        int number = nums.get(position);
        holder.num.setText(String.valueOf(number));

        int color = Color.RED;
        if (number % 2 != 0) {
            color = Color.BLUE;
        }
        holder.num.setTextColor(color);
        holder.num.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (activity instanceof MainActivity) {
                    ((MainActivity) activity).onNumberDisplayed(position + 1);
                }
            }
        });
    }

    public void addItem() {
        nums.add(nums.size() + 1);
        this.notifyItemInserted(nums.size() - 1);
    }

    @Override
    public int getItemCount() {
        return nums.size();
    }


}
