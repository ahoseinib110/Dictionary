package org.maktab.dictionary.controller.fragment;

import android.os.Build;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.Spinner;
import android.widget.TextView;

import org.maktab.dictionary.R;
import org.maktab.dictionary.model.Language;
import org.maktab.dictionary.model.Word;
import org.maktab.dictionary.repository.DicDBRepository;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link DicFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class DicFragment extends Fragment {

    private static final String TAG = "DF_bashir";
    private com.google.android.material.textfield.TextInputLayout mTILSearch;
    private EditText mEditTextSearch;
    private Spinner mSpinnerSrc;
    private Spinner mSpinnerDst;
    private TextView mTextViewSrc;
    private TextView mTextViewDst;
    private Button mButtonAdd;
    private Button mButtonSearch;
    private FrameLayout mResultContainer;

    private DicDBRepository mDicRepository;
    private Language mSrc;
    private Language mDst;

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
        mDicRepository = DicDBRepository.newInstance(getActivity().getApplicationContext());
        mSrc = Language.ENGLISH;
        mDst = Language.PERSIAN;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_dic, container, false);
        findViews(view);
        setOnClickListeners();
        configSpinners();
        setTextChangedListener();
        return view;
    }

    public void findViews(View view) {
        mTILSearch = view.findViewById(R.id.outlinedTextFieldSearch);
        mEditTextSearch = mTILSearch.getEditText();
        mSpinnerSrc = view.findViewById(R.id.spinnerFrom);
        mSpinnerDst = view.findViewById(R.id.spinnerTo);
        mTextViewSrc = view.findViewById(R.id.textViewSrc);
        mTextViewDst = view.findViewById(R.id.textViewDst);
        mButtonSearch = view.findViewById(R.id.buttonSearch);
        mButtonAdd = view.findViewById(R.id.buttonAdd);
        mResultContainer = view.findViewById(R.id.container_result);
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
                //Word word = new Word.Builder().inEnglish("cat").inPersian("گربه").build();
                //mDicRepository.insert(word);
                AddDialogFragment addDialogFragment = AddDialogFragment.newInstance();
                addDialogFragment.show(getFragmentManager(), "addDialogFragment");
                //Log.d("bashir",String.valueOf(mEditTextSearch.getText()));
            }
        });
    }

    private void setTextChangedListener() {
        mEditTextSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                //Log.d(TAG, "before " + s);
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                //Log.d(TAG, "on " + s);
                Word word;
                String wordSrc;
                String wordDst;
                boolean LTRSrc;
                boolean LTRDst;

                switch (mSrc) {
                    case PERSIAN:
                        word = mDicRepository.getFromPersian(String.valueOf(s));
                        wordSrc = word == null ? null : word.getPersian();
                        LTRSrc = false;
                        break;
                    case ENGLISH:
                        word = mDicRepository.getFromEnglish(String.valueOf(s));
                        wordSrc = word == null ? null : word.getEnglish();
                        LTRSrc = true;
                        break;
                    case FRENCH:
                        word = mDicRepository.getFromFrench(String.valueOf(s));
                        wordSrc = word == null ? null : word.getFrench();
                        LTRSrc = true;
                        break;
                    case ARABIC:
                        word = mDicRepository.getFromArabic(String.valueOf(s));
                        wordSrc = word == null ? null : word.getArabic();
                        LTRSrc = false;
                        break;
                    default:
                        throw new IllegalStateException("Unexpected value: " + mSrc);
                }

                switch (mDst) {
                    case PERSIAN:
                        wordDst = word == null ? null : word.getPersian();
                        LTRDst = false;
                        break;
                    case ENGLISH:
                        wordDst = word == null ? null : word.getEnglish();
                        LTRDst = true;
                        break;
                    case FRENCH:
                        wordDst = word == null ? null : word.getFrench();
                        LTRDst = true;
                        break;
                    case ARABIC:
                        wordDst = word == null ? null : word.getArabic();
                        LTRDst = false;
                        break;
                    default:
                        throw new IllegalStateException("Unexpected value: " + mDst);
                }

                mResultContainer.setVisibility(View.VISIBLE);

                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
                    if (LTRSrc) {
                        mTextViewSrc.setTextDirection(View.TEXT_DIRECTION_LTR);
                    } else {
                        mTextViewSrc.setTextDirection(View.TEXT_DIRECTION_RTL);
                    }

                    if (LTRDst) {
                        mTextViewDst.setTextDirection(View.TEXT_DIRECTION_LTR);
                    } else {
                        mTextViewDst.setTextDirection(View.TEXT_DIRECTION_RTL);
                    }
                }
                if (wordSrc != null && wordDst != null) {
                    mTextViewSrc.setText(wordSrc);
                    mTextViewDst.setText(wordDst);
                } else if (wordSrc == null) {
                    //Toast.makeText(getActivity(), "this word is not exist in database", Toast.LENGTH_SHORT).show();
                } else if (wordDst == null) {
                    //Toast.makeText(getActivity(), "translation of this word is not exist in database", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    public void configSpinners() {
        final String[] language = {"Persian", "English", "French", "Arabic"};
        ArrayAdapter aa = new ArrayAdapter(getActivity(), android.R.layout.simple_spinner_item, language);
        aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        mSpinnerSrc.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                mSrc = Language.valueOf(language[position].toUpperCase());
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        mSpinnerDst.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                mDst = Language.valueOf(language[position].toUpperCase());
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        mSpinnerSrc.setAdapter(aa);
        mSpinnerDst.setAdapter(aa);
        mSpinnerSrc.setSelection(1);
        mSpinnerDst.setSelection(0);
    }

}