package org.maktab.dictionary.database;

public class DicDBSchema {
    public static final String NAME ="DicDB.db";
    public static final int VERSION =1;
    public class WordTable{
        public static final String NAME ="wordTable";
        public class Cols{
            public static final String ID ="id";
            public static final String ENGLISH ="english";
            public static final String PERSIAN ="persian";
            public static final String FRENCH ="french";
            public static final String ARABIC ="arabic";
        }
    }
}
