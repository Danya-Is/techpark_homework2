package ru.mail.myapplication;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.Objects;

public class RecyclerViewFragment extends Fragment {

    private MyAdapter adapter;
    private static final String KEY_COUNT = "count";

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        Log.d("RecyclerViewFragment", "onCreateView");
        View view = inflater.inflate(R.layout.main_fragment, container, false);
        final RecyclerView recyclerView = view.findViewById(R.id.recycler);
        int spanCount = getResources().getInteger(R.integer.column_count);
        recyclerView.setLayoutManager(new GridLayoutManager(getActivity(), spanCount));
        int count = 100;
        if (savedInstanceState != null){
            count = savedInstanceState.getInt(KEY_COUNT);
        }
        FragmentManager fm = getActivity().getSupportFragmentManager();
        adapter = new MyAdapter(fm, count);
        recyclerView.setAdapter(adapter);

        Button btn = view.findViewById(R.id.addButton);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                adapter.addItem();
            }
        });
        return view;
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        if (adapter != null) {
            outState.putInt(KEY_COUNT, adapter.getItemCount());
        }
        else{
            outState.putInt(KEY_COUNT, 100);
        }
        Log.d("RecyclerViewFragment", "OnSaveInstanceState");
    }
}
