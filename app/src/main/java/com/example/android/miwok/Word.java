package com.example.android.miwok;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by rizao on 8/31/2017.
 */

public class Word implements Parcelable {
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

    public Word(Parcel in) {
        this.mDefaultTranslation = in.readString();
        this.mMiwokTranslation = in.readString();
        this.mImageResourceID = in.readInt();
        if (in.dataSize() == 4) {
            this.hasImage = true;
            this.mMediaResourceID = in.readInt();
        }
    }

    public static final Parcelable.Creator CREATOR = new Parcelable.Creator() {
        public Word createFromParcel(Parcel in) {
            return new Word(in);
        }

        public Word[] newArray(int size) {
            return new Word[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.mDefaultTranslation);
        parcel.writeString(this.mMiwokTranslation);
        parcel.writeInt(this.mImageResourceID);
        if (parcel.dataSize() == 4) {
            parcel.writeInt(this.mMediaResourceID);
        }

    }
}
