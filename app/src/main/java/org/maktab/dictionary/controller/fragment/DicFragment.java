package org.maktab.dictionary.controller.fragment;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.SearchView;
import android.widget.Spinner;
import android.widget.TextView;

import org.maktab.dictionary.R;
import org.maktab.dictionary.repository.DicDBRepository;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link DicFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class DicFragment extends Fragment {

    private SearchView mSearchView;
    private Spinner mSpinnerSrc;
    private Spinner mSpinnerDst;
    private TextView mTextViewSrc;
    private TextView mTextViewDst;
    private Button mButtonAdd;
    private Button mButtonSearch;

    private DicDBRepository mDicRepository;

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
        mDicRepository=DicDBRepository.newInstance(getActivity().getApplicationContext());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_dic, container, false);
        findViews(view);
        setOnClickListeners();
        return view;
    }

    public void findViews(View view){
        mSearchView = view.findViewById(R.id.searchView);
        mSpinnerSrc= view.findViewById(R.id.spinnerSrc);
        mSpinnerDst= view.findViewById(R.id.spinnerDst);
        mTextViewSrc= view.findViewById(R.id.textViewSrc);
        mTextViewDst= view.findViewById(R.id.textViewDst);
        mButtonSearch = view.findViewById(R.id.buttonSearch);
        mButtonAdd = view.findViewById(R.id.buttonSearch);
    }

    private void setOnClickListeners() {
        mButtonSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        mButtonAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }


}