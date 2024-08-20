package com.example.lr5;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

public class DetailFragment extends Fragment {
    private TextView detailTextView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_detail, container, false);
        detailTextView = view.findViewById(R.id.detailTextView);

        if (getArguments() != null) {
            String selectedItem = getArguments().getString("selected_item");
            detailTextView.setText(selectedItem);
        }
        return view;
    }
}
