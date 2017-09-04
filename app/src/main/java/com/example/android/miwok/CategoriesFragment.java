package com.example.android.miwok;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class CategoriesFragment extends Fragment {

    public CategoriesFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Bundle args = getArguments();
        ArrayList<Word> words = args.getParcelableArrayList("words");
        int color = args.getInt("color");
        View rootView = inflater.inflate(R.layout.word_list, container, false);
        WordAdapter adapter = new WordAdapter(getActivity(), words, color);
        ListView listView = (ListView) rootView.findViewById(R.id.list);
        listView.setAdapter(adapter);
        return rootView;
    }

}
