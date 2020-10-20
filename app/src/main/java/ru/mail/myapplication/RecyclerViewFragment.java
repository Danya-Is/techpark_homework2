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
        Bundle bundle = getArguments();
        if (savedInstanceState != null) {
            count = savedInstanceState.getInt(KEY_COUNT);
        }
        else if (bundle != null){
            count = bundle.getInt(KEY_COUNT);
        }
        adapter = new MyAdapter(getActivity(), count);
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
    public void onPause() {
        super.onPause();
        Log.d("RecyclerViewFragment", "OnPause");
        Bundle bundle = new Bundle();
        bundle.putInt(KEY_COUNT, adapter.getItemCount());
        setArguments(bundle);
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        Bundle bundle = getArguments();
        if (bundle != null){
            int c = bundle.getInt(KEY_COUNT);
            outState.putInt(KEY_COUNT, c);
        }
        Log.d("RecyclerViewFragment", "OnSaveInstanceState");
    }
}
