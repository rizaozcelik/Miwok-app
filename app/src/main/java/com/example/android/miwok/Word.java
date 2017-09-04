package com.example.android.miwok;

/**
 * Created by rizao on 8/31/2017.
 */

public class Word {
    private String mDefaultTranslation;
    private String mMiwokTranslation;
    private int mImageResourceID;
    private boolean hasImage;
    private int mMediaResourceID;

    public Word(String defaultTranslation, String miwokTranslation, int mediaResourceID) {
        this.mDefaultTranslation = defaultTranslation;
        this.mMiwokTranslation = miwokTranslation;
        this.mMediaResourceID = mediaResourceID;
    }

    public Word(String defaultTranslation, String miwokTranslation, int imageResourceID, int mediaSourceID) {
        this.mDefaultTranslation = defaultTranslation;
        this.mMiwokTranslation = miwokTranslation;
        this.mImageResourceID = imageResourceID;
        this.hasImage = true;
        this.mMediaResourceID = mediaSourceID;
    }

    public String getMiwokTranslation() {
        return mMiwokTranslation;
    }

    public String getDefaultTranslation() {
        return mDefaultTranslation;
    }

    public int getImageResourceID() {
        return mImageResourceID;
    }

    public boolean hasImage() {
        return this.hasImage;
    }

    public int getMediaResourceID() {
        return mMediaResourceID;
    }
}
