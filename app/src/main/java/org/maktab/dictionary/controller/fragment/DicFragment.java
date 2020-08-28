package org.maktab.dictionary.controller.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.maktab.dictionary.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link DicFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class DicFragment extends Fragment {


    public DicFragment() {
        // Required empty public constructor
    }


    public static DicFragment newInstance() {
        DicFragment fragment = new DicFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_dic, container, false);
    }
}