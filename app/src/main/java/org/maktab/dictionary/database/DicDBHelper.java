package org.maktab.dictionary.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DicDBHelper extends SQLiteOpenHelper {

    public DicDBHelper(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // TODO Auto-generated method stub
        db.execSQL(" CREATE TABLE " + DicDBSchema.WordTable.NAME + " (" +
                DicDBSchema.WordTable.Cols.ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                DicDBSchema.WordTable.Cols.ENGLISH + " TEXT, " +
                DicDBSchema.WordTable.Cols.PERSIAN + " TEXT, " +
                DicDBSchema.WordTable.Cols.FRENCH + " TEXT, " +
                DicDBSchema.WordTable.Cols.ARABIC + " TEXT);"
        );
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
