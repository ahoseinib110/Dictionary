package org.maktab.dictionary.controller.fragment;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.FrameLayout;

import org.maktab.dictionary.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link AddDialogFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class AddDialogFragment extends DialogFragment {

    private static final String TAG = "ADF_bashir";
    private com.google.android.material.textfield.TextInputLayout mTILWord;
    private com.google.android.material.textfield.TextInputLayout mTILTranslation;

    public AddDialogFragment() {
        // Required empty public constructor
    }

    public static AddDialogFragment newInstance() {
        AddDialogFragment fragment = new AddDialogFragment();
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

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        LayoutInflater inflater = LayoutInflater.from(getActivity());
        View view = inflater.inflate(R.layout.fragment_add_dialog, null);
        findViews(view);
        return new AlertDialog.Builder(getActivity()).setTitle("Add New Word").setView(view).setPositiveButton("Add", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                EditText editTextWord = mTILWord.getEditText();
                EditText editTextTr = mTILTranslation.getEditText();
                Log.d(TAG, String.valueOf(editTextWord.getText()));
            }
        }).create();
    }

    private void findViews(View view) {
        mTILWord = view.findViewById(R.id.outlinedTextFieldWord);
        mTILTranslation = view.findViewById(R.id.outlinedTextFieldTranslation);
    }
}