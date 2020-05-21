package com.example.edunomics.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AutoCompleteTextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.edunomics.WordSearchAdapter;
import com.example.edunomics.R;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment {

    private AutoCompleteTextView txtSearch;
    List<String> mList;
    WordSearchAdapter adapter;
    private String selectedPerson;
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_home, container, false);
        List<String> autolist = new ArrayList<>();
        autolist.add("Apple");
        autolist.add("Android");
        autolist.add("Microsoft");
        autolist.add("React");
        autolist.add("Swift");
        autolist.add("Flutter");
        autolist.add("Javascript");
        autolist.add("Android Studio");
        txtSearch = (AutoCompleteTextView) root.findViewById(R.id.auto_name);
        adapter = new WordSearchAdapter(getActivity(), R.layout.activity_main, R.id.lbl_name, autolist);
        txtSearch.setAdapter(adapter);
        txtSearch.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int pos, long id) {
                //this is the way to find selected object/item
                selectedPerson = (String) adapterView.getItemAtPosition(pos);
            }
        });


        return root;
    }


}
