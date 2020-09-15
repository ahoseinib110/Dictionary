package org.maktab.dictionary.controller.fragment;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.Spinner;
import android.widget.Toast;

import org.maktab.dictionary.R;
import org.maktab.dictionary.model.Language;
import org.maktab.dictionary.model.Word;
import org.maktab.dictionary.repository.DicDBRepository;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link AddDialogFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class AddDialogFragment extends DialogFragment implements DialogInterface.OnClickListener {

    private static final String TAG = "ADF_bashir";
    private com.google.android.material.textfield.TextInputLayout mTILWord;
    private com.google.android.material.textfield.TextInputLayout mTILTranslation;
    private Spinner mSpinnerFrom;
    private Spinner mSpinnerTo;
    private Language mFrom;
    private Language mTo;

    private DicDBRepository mDicRepository;

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
        mDicRepository = DicDBRepository.newInstance(getActivity().getApplicationContext());
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        LayoutInflater inflater = LayoutInflater.from(getActivity());
        View view = inflater.inflate(R.layout.fragment_add_dialog, null);
        findViews(view);
        configSpinners();
        return new AlertDialog.Builder(getActivity())
                .setTitle("Add New Word")
                .setView(view)
                .setPositiveButton("Add", this)
                .setNegativeButton("cancel", null)
                .create();
    }

    private void findViews(View view) {
        mTILWord = view.findViewById(R.id.outlinedTextFieldWord);
        mTILTranslation = view.findViewById(R.id.outlinedTextFieldTranslation);
        mSpinnerFrom = view.findViewById(R.id.spinnerFrom);
        mSpinnerTo = view.findViewById(R.id.spinnerTo);
    }

    public void configSpinners() {
        final String[] languageShort = {"Per", "Eng", "Fre", "Ar"};
        final String[] language =  {"Persian", "English", "French", "Arabic"};
        ArrayAdapter aa = new ArrayAdapter(getActivity(), android.R.layout.simple_spinner_item, languageShort);
        aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        mSpinnerFrom.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                mFrom = Language.valueOf(language[position].toUpperCase());
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        mSpinnerTo.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                mTo = Language.valueOf(language[position].toUpperCase());
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        mSpinnerFrom.setAdapter(aa);
        mSpinnerTo.setAdapter(aa);
        mSpinnerFrom.setSelection(1);
        mSpinnerTo.setSelection(0);
    }

    @Override
    public void onClick(DialogInterface dialog, int which) {
        EditText editTextWord = mTILWord.getEditText();
        EditText editTextTr = mTILTranslation.getEditText();
        Log.d(TAG, String.valueOf(editTextWord.getText()));
        Word word = new Word();
        setWord(word,mFrom,editTextWord);
        setWord(word,mTo,editTextTr);
        mDicRepository.insert(word);
        getTargetFragment().onActivityResult(DicFragment.REQUEST_CODE_ADD, Activity.RESULT_OK,null);
    }

    private void setWord(Word word, Language language, EditText editText) {
        switch (language) {
            case PERSIAN:
                word.setPersian(String.valueOf(editText.getText()));
                break;
            case ENGLISH:
                word.setEnglish(String.valueOf(editText.getText()));
                break;
            case FRENCH:
                word.setFrench(String.valueOf(editText.getText()));
                break;
            case ARABIC:
                word.setArabic(String.valueOf(editText.getText()));
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + language);
        }
    }
}