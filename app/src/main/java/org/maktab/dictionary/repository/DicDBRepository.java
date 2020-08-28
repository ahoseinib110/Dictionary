package org.maktab.dictionary.repository;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import androidx.room.Room;

import org.maktab.dictionary.database.DicCursorWrapper;
import org.maktab.dictionary.database.DicDB;
import org.maktab.dictionary.database.DicDBHelper;
import org.maktab.dictionary.database.DicDBSchema;
import org.maktab.dictionary.model.Word;

public class DicDBRepository {
    public static DicDBRepository sDicDBRepository;

    private DicDB mDicDB;

    public static DicDBRepository newInstance(Context context) {
        if (sDicDBRepository == null) {
            sDicDBRepository = new DicDBRepository(context);
        }
        return sDicDBRepository;
    }

    private DicDBRepository(Context context) {
        mDicDB = Room.databaseBuilder(context,DicDB.class,"DicDB.db")
                .allowMainThreadQueries()
                .build();
    }

    public void insert(Word word) {
        mDicDB.dicDao().insert(word);
    }

    public void delete(Word word) {
        mDicDB.dicDao().delete(word);
    }


    public Word getFromEnglish(String englishWord) {
        return mDicDB.dicDao().getFromEnglish(englishWord);
    }

    public Word getFromPersian(String persianWord) {
        return mDicDB.dicDao().getFromPersian(persianWord);
    }

    public Word getFromFrench(String frenchWord) {
        return mDicDB.dicDao().getFromFrench(frenchWord);
    }

    public Word getFromArabic(String arabicWord) {
        return mDicDB.dicDao().getFromArabic(arabicWord);
    }

}
