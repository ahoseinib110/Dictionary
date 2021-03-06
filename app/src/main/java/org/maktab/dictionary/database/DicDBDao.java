package org.maktab.dictionary.database;

import android.content.ContentValues;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import org.maktab.dictionary.model.Word;

import java.util.List;

@Dao
public interface DicDBDao {
    @Insert
    public void insert(Word word);

    @Update
    public void update(Word word);

    @Delete
    public void delete(Word word);

    @Query("SELECT * From wordTable where english=:englishWord")
    public Word getFromEnglish(String englishWord);

    @Query("SELECT * From wordTable where persian=:persianWord")
    public Word getFromPersian(String persianWord);

    @Query("SELECT * From wordTable where french=:frenchWord")
    public Word getFromFrench(String frenchWord);

    @Query("SELECT * From wordTable where arabic=:arabicWord")
    public Word getFromArabic(String arabicWord);

    @Query("Select * From wordTable Where id=:wordId")
    public Word get(int wordId);

    @Query("Select * From wordTable")
    public List<Word> getList();

}
