package org.maktab.dictionary.controller.fragment;

import android.os.Build;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;

import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
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
    private ConstraintLayout mConsLayoutTranslation;
    private DicDBRepository mDicRepository;
    private Language mSrc;
    private Language mDst;
    Word mWord;
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
        setHasOptionsMenu(true);
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        inflater.inflate(R.menu.dic_fragment_menu,menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_item_add_word:
                AddDialogFragment addDialogFragment = AddDialogFragment.newInstance();
                addDialogFragment.show(getFragmentManager(), "addDialogFragment");
                return true;
            case R.id.menu_item_share:
                if(mWord!=null){
                    //implicit intent for share
                    return true;
                }
            default:
                return super.onOptionsItemSelected(item);
        }
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
        mConsLayoutTranslation = view.findViewById(R.id.cons_layout_translation);
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

        mConsLayoutTranslation.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                Log.d(TAG,"hiiiiiiiii");
                EditDialogFragment editDialogFragment = EditDialogFragment.newInstance(mWord.getID());
                editDialogFragment.show(getFragmentManager(),"edit_dialog");
                return false;
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

                String wordSrc;
                String wordDst;
                boolean LTRSrc;
                boolean LTRDst;

                switch (mSrc) {
                    case PERSIAN:
                        mWord = mDicRepository.getFromPersian(String.valueOf(s));
                        wordSrc = mWord == null ? null : mWord.getPersian();
                        LTRSrc = false;
                        break;
                    case ENGLISH:
                        mWord = mDicRepository.getFromEnglish(String.valueOf(s));
                        wordSrc = mWord == null ? null : mWord.getEnglish();
                        LTRSrc = true;
                        break;
                    case FRENCH:
                        mWord = mDicRepository.getFromFrench(String.valueOf(s));
                        wordSrc = mWord == null ? null : mWord.getFrench();
                        LTRSrc = true;
                        break;
                    case ARABIC:
                        mWord = mDicRepository.getFromArabic(String.valueOf(s));
                        wordSrc = mWord == null ? null : mWord.getArabic();
                        LTRSrc = false;
                        break;
                    default:
                        throw new IllegalStateException("Unexpected value: " + mSrc);
                }

                switch (mDst) {
                    case PERSIAN:
                        wordDst = mWord == null ? null : mWord.getPersian();
                        LTRDst = false;
                        break;
                    case ENGLISH:
                        wordDst = mWord == null ? null : mWord.getEnglish();
                        LTRDst = true;
                        break;
                    case FRENCH:
                        wordDst = mWord == null ? null : mWord.getFrench();
                        LTRDst = true;
                        break;
                    case ARABIC:
                        wordDst = mWord == null ? null : mWord.getArabic();
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
                } else {
                    mTextViewSrc.setText("");
                    mTextViewDst.setText("");
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