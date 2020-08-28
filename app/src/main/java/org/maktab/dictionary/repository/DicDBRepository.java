package org.maktab.dictionary.repository;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import org.maktab.dictionary.database.DicCursorWrapper;
import org.maktab.dictionary.database.DicDBHelper;
import org.maktab.dictionary.database.DicDBSchema;
import org.maktab.dictionary.model.Word;

public class DicDBRepository {
    public static DicDBRepository sDicDBRepository;

    private SQLiteDatabase mSqLiteDatabase;

    public DicDBRepository newInstance(Context context) {
        if (sDicDBRepository == null) {
            sDicDBRepository = new DicDBRepository(context);
        }
        return sDicDBRepository;
    }

    private DicDBRepository(Context context) {
        DicDBHelper dicDBHelper = new DicDBHelper(context, DicDBSchema.NAME, null, DicDBSchema.VERSION);
        mSqLiteDatabase = dicDBHelper.getWritableDatabase();
    }

    public void insert(Word word) {
        ContentValues contentValues = getContentValues(word);
        mSqLiteDatabase.insert(DicDBSchema.WordTable.NAME, null, contentValues);
    }

    public void delete(Word word) {
        String where = DicDBSchema.WordTable.Cols.ID + "=?";
        String[] whereArgs = {String.valueOf(word.getID())};
        mSqLiteDatabase.delete(DicDBSchema.WordTable.NAME, where, whereArgs);
    }

    private Word get(String stringWord,String where){
        String[] whereArgs ={stringWord};
        DicCursorWrapper cursor = queryInDic(where,whereArgs);
        if(cursor==null || cursor.getCount()==0){
            return null;
        }
        try {
            return cursor.getWord();
        }
        finally {
            cursor.close();
        }
    }

    private DicCursorWrapper queryInDic(String where,String[] whereArgs) {
        DicCursorWrapper cursor = (DicCursorWrapper) mSqLiteDatabase.query(DicDBSchema.WordTable.NAME, null, where, whereArgs, null, null, null);
        return cursor;
    }

    public Word getFromEnglish(String englishWord) {
        String where = DicDBSchema.WordTable.Cols.ENGLISH+"=?" ;
        return get(englishWord,where);
    }

    public Word getFromPersian(String persianWord) {
        String where = DicDBSchema.WordTable.Cols.PERSIAN+"=?" ;
        return get(persianWord,where);
    }

    public Word getFromFrench(String frenchWord) {
        String where = DicDBSchema.WordTable.Cols.FRENCH+"=?" ;
        return get(frenchWord,where);
    }

    public Word getFromArabic(String arabicWord) {
        String where = DicDBSchema.WordTable.Cols.ARABIC+"=?" ;
        return get(arabicWord,where);
    }


    private ContentValues getContentValues(Word word) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(DicDBSchema.WordTable.Cols.ENGLISH, word.getEnglish());
        contentValues.put(DicDBSchema.WordTable.Cols.PERSIAN, word.getPersian());
        contentValues.put(DicDBSchema.WordTable.Cols.FRENCH, word.getFrench());
        contentValues.put(DicDBSchema.WordTable.Cols.ARABIC, word.getArabic());
        return contentValues;
    }

}
