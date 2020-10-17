package ru.mail.myapplication;

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

public class MyAdapter extends RecyclerView.Adapter<MyViewHolder>{

    private List<Integer> nums;
    private FragmentManager fm;

    public MyAdapter(FragmentManager fragmentManager, int count){
        nums = new ArrayList<>();
        fm = fragmentManager;
        for (int i = 1; i <= count; i++){
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
        holder.num.setText("" + number);

        int color = Color.RED;
        if (number % 2 != 0 ){
            color = Color.BLUE;
        }
        holder.num.setTextColor(color);
        holder.num.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BigNumberFragment fragment = new BigNumberFragment();
                Bundle bundle = new Bundle();
                bundle.putInt("pos", position + 1);
                fragment.setArguments(bundle);

                fm.beginTransaction()
                        .replace(R.id.container, fragment)
                        .addToBackStack(null)
                        .commit();
            }
        });
    }

    public void addItem(){
        nums.add(nums.size() + 1);
        this.notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return nums.size();
    }


}
