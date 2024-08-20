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
    private ArrayAdapter<String> adapter;
    private OnItemSelectedListener listener;

    public interface OnItemSelectedListener {
        void onItemSelected(String item);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnItemSelectedListener) {
            listener = (OnItemSelectedListener) context;
        } else {
            throw new ClassCastException(context.toString() + " must implement OnItemSelectedListener");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_list, container, false);
        listView = view.findViewById(R.id.listView);

        // Пример данных
        String[] items = {"Италия", "Испания", "Германия"};
        adapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_list_item_1, items);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener((parent, view1, position, id) -> {
            String selectedItem = adapter.getItem(position);
            listener.onItemSelected(selectedItem);
        });

        return view;
    }
}
