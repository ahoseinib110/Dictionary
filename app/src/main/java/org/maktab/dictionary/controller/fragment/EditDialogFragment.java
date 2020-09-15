package org.maktab.dictionary.controller.fragment;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

import org.maktab.dictionary.R;
import org.maktab.dictionary.model.Language;
import org.maktab.dictionary.model.Word;
import org.maktab.dictionary.repository.DicDBRepository;

import java.util.Date;


public class EditDialogFragment extends DialogFragment  {

    private static final String ARG_WORD_ID = "wordId";

    private static final String TAG = "ADF_bashir";
    private com.google.android.material.textfield.TextInputLayout mTILWord;
    private com.google.android.material.textfield.TextInputLayout mTILTranslation;
    private Spinner mSpinnerFrom;
    private Spinner mSpinnerTo;
    private EditText mEditTextWord;
    private EditText mEditTextTr;

    private Language mFrom;
    private Language mTo;

    private int mWordId;
    private Word mWord;

    private DicDBRepository mDicRepository;

    public EditDialogFragment() {
        // Required empty public constructor
    }


    public static EditDialogFragment newInstance(int wordId) {
        EditDialogFragment fragment = new EditDialogFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_WORD_ID, wordId);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mWordId = getArguments().getInt(ARG_WORD_ID);
            mDicRepository = DicDBRepository.newInstance(getActivity().getApplicationContext());
            mWord = mDicRepository.get(mWordId);
        }
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.fragment_edit_dialog, null);
        findViews(view);
        configSpinners();

        mEditTextWord = mTILWord.getEditText();
        mEditTextTr = mTILTranslation.getEditText();
        setText(mWord, Language.ENGLISH, mEditTextWord);
        setText(mWord, Language.PERSIAN, mEditTextTr);

        return new AlertDialog.Builder(getActivity())
                .setTitle("Edit Word")
                .setView(view)
                .setPositiveButton("Edit", new ListenerEdit())
                .setNeutralButton("Delete",new ListenerDC(true))
                .setNegativeButton("Cancel", new ListenerDC(false))
                .create();
    }

    private void findViews(View view) {
        mTILWord = view.findViewById(R.id.outlinedTextFieldWordE);
        mTILTranslation = view.findViewById(R.id.outlinedTextFieldTranslationE);
        mSpinnerFrom = view.findViewById(R.id.spinnerFromE);
        mSpinnerTo = view.findViewById(R.id.spinnerToE);
    }

    public void configSpinners() {
        final String[] languageShort = {"Per", "Eng", "Fre", "Ar"};
        final String[] language = {"Persian", "English", "French", "Arabic"};
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

    private class ListenerEdit implements DialogInterface.OnClickListener{
        @Override
        public void onClick(DialogInterface dialog, int which) {
            setWord(mWord, mFrom, mEditTextWord);
            setWord(mWord, mTo, mEditTextTr);
            mDicRepository.update(mWord);
            setResult();
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

    private class ListenerDC implements DialogInterface.OnClickListener{

      private  boolean mDelete ;

        public ListenerDC(boolean delete) {
            mDelete = delete;
        }

        @Override
        public void onClick(DialogInterface dialog, int which) {
            if(mDelete){
                mDicRepository.delete(mWord);
                setResult();
            }
            dismiss();
        }
    }
    private void setResult() {
        Fragment fragment = getTargetFragment();
        fragment.onActivityResult(getTargetRequestCode(), Activity.RESULT_OK, null);
    }
    private void setText(Word word, Language language, EditText editText) {
        switch (language) {
            case PERSIAN:
                editText.setText(word.getPersian());
                break;
            case ENGLISH:
                editText.setText(word.getEnglish());
                break;
            case FRENCH:
                editText.setText(word.getFrench());
                break;
            case ARABIC:
                editText.setText(word.getArabic());
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + language);
        }
    }
}