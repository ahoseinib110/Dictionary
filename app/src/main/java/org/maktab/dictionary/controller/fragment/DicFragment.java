package org.maktab.dictionary.controller.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SearchView;
import android.widget.Spinner;
import android.widget.TextView;

import org.maktab.dictionary.R;

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
        View view = inflater.inflate(R.layout.fragment_dic, container, false);
        findViews(view);
        return view;
    }

    public void findViews(View view){
        mSearchView = view.findViewById(R.id.searchView);
        mSpinnerSrc= view.findViewById(R.id.spinnerSrc);
        mSpinnerDst= view.findViewById(R.id.spinnerDst);
        mTextViewSrc= view.findViewById(R.id.textViewSrc);
        mTextViewDst= view.findViewById(R.id.textViewDst);
    }
}