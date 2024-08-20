package com.example.lr5;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.fragment.app.Fragment;

public class ListFragment extends Fragment {
    private ListView listView;
    private String[] items = {"Item 1", "Item 2", "Item 3"};
    private OnItemSelectedListener listener;

    public interface OnItemSelectedListener {
        void onItemSelected(String item);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        listener = (OnItemSelectedListener) context;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_list, container, false);
        listView = rootView.findViewById(R.id.listView);
        listView.setAdapter(new ArrayAdapter<>(getContext(), android.R.layout.simple_list_item_1, items));

        listView.setOnItemClickListener((parent, view, position, id) -> {
            listener.onItemSelected(items[position]); // передаем выбранный элемент
        });

        return rootView;
    }
}
