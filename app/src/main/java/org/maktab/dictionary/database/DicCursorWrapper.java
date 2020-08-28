package org.maktab.dictionary.database;

import android.database.Cursor;
import android.database.CursorWrapper;

import org.maktab.dictionary.model.Word;

import java.util.UUID;

public class DicCursorWrapper extends CursorWrapper {
    /**
     * Creates a cursor wrapper.
     *
     * @param cursor The underlying cursor to wrap.
     */
    public DicCursorWrapper(Cursor cursor) {
        super(cursor);
    }

    public Word getWord(){
        int id = Integer.parseInt(getString(getColumnIndex(DicDBSchema.WordTable.Cols.ID)));
        String english = getString(getColumnIndex(DicDBSchema.WordTable.Cols.ENGLISH));
        String persian = getString(getColumnIndex(DicDBSchema.WordTable.Cols.PERSIAN));
        String french = getString(getColumnIndex(DicDBSchema.WordTable.Cols.FRENCH));
        String arabic = getString(getColumnIndex(DicDBSchema.WordTable.Cols.ARABIC));

        Word word = new Word.Builder()
                .setID(id)
                .inEnglish(english)
                .inPersian(persian)
                .inFrench(french)
                .inArabic(arabic)
                .build();

        return word;
    }
}
