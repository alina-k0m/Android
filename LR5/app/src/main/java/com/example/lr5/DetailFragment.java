package com.example.lr5;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

public class DetailFragment extends Fragment {
    private TextView textView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_detail, container, false);
        textView = rootView.findViewById(R.id.detailTextView);

        Bundle args = getArguments();
        if (args != null) {
            textView.setText(args.getString("item")); // отображение выбранного элемента
        }

        return rootView;
    }
}


