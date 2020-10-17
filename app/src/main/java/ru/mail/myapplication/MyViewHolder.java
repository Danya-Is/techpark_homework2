package ru.mail.myapplication;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class MyViewHolder extends RecyclerView.ViewHolder{

    public final TextView num;


    public MyViewHolder(@NonNull View itemView) {
        super(itemView);
        num = itemView.findViewById(R.id.number);
    }
}
