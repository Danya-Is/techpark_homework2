package ru.mail.myapplication;

import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.view.menu.MenuView;
import androidx.fragment.app.Fragment;

public class BigNumberFragment extends Fragment {

    public TextView num;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.big_number_fragment, container, false);
        num = view.findViewById(R.id.big_number);
        Bundle bundle = getArguments();
        int number = bundle.getInt("pos");
        if (bundle != null) {
            num.setText(String.valueOf(number));
        }
        int color = Color.RED;
        if (number % 2 != 0){
            color = Color.BLUE;
        }
        num.setTextColor(color);
        return view;
    }
}