package org.maktab.dictionary.model;

import java.util.UUID;

public class Word {
    private int mID;
    private String mEnglish;
    private String mPersian;
    private String mFrench;
    private String mArabic;

    public static class Builder {
        private String english;
        private String persian;
        private String french;
        private String arabic;
        private int id;

        public Builder() {
        }

        public Builder inEnglish(String english) {
            this.english = english;
            return this;
        }

        public Builder inPersian(String persian) {
            this.persian = persian;
            return this;
        }

        public Builder inArabic(String arabic) {
            this.arabic = arabic;
            return this;
        }

        public Builder inFrench(String french) {
            this.french = french;
            return this;
        }

        public Builder setID(int id) {
            this.id = id;
            return this;
        }

        public Word build() {
            Word word = new Word();
            word.mID = this.id;
            word.mEnglish = this.english;
            word.mPersian = this.persian;
            word.mFrench = this.french;
            word.mArabic = this.arabic;
            return word;
        }
    }

    private Word() {

    }

    public String getEnglish() {
        return mEnglish;
    }

    public void setEnglish(String english) {
        mEnglish = english;
    }

    public String getPersian() {
        return mPersian;
    }

    public void setPersian(String persian) {
        mPersian = persian;
    }

    public String getFrench() {
        return mFrench;
    }

    public void setFrench(String french) {
        mFrench = french;
    }

    public String getArabic() {
        return mArabic;
    }

    public void setArabic(String arabic) {
        mArabic = arabic;
    }

    public int getID() {
        return mID;
    }

    public void setID(int ID) {
        mID = ID;
    }
}