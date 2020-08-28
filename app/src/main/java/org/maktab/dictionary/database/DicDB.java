package org.maktab.dictionary.database;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import org.maktab.dictionary.model.Word;

@Database(entities = {Word.class},version = 1,exportSchema = false)
public abstract class DicDB extends RoomDatabase {
    public abstract DicDBDao dicDao();
}
