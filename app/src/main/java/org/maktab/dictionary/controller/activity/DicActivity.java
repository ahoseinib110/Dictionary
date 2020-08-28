package org.maktab.dictionary.controller.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import android.os.Bundle;

import org.maktab.dictionary.R;
import org.maktab.dictionary.controller.fragment.DicFragment;

public class DicActivity extends AppCompatActivity {
    DicFragment mDicFragment;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dic);
        FragmentManager fragmentManager = getSupportFragmentManager();
        mDicFragment = (DicFragment) fragmentManager.findFragmentById(R.id.fragment_container);
        if(mDicFragment==null){
            mDicFragment = DicFragment.newInstance();
            fragmentManager.beginTransaction()
                    .add(R.id.fragment_container,mDicFragment)
                    .commit();
        }
    }

}