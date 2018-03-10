package com.example.bnayagrawal.torrentz.data;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by bnayagrawal on 9/3/18.
 */

public final class Torrent implements Parcelable {
    private final String mName;
    private final String mDetailsPageLink;
    private final String mAge;
    private final String mSize; //No need to store in bytes (the page already returns a formatted string)
    private final int mSeedsCount;
    private final int mPeersCount;
    private final String mHash;

    public Torrent(String mName, String mDetailsPageLink, String mAge, String mSize, int mSeedsCount, int mPeersCount, String mHash) {
        this.mName = mName;
        this.mDetailsPageLink = mDetailsPageLink;
        this.mAge = mAge;
        this.mSize = mSize;
        this.mSeedsCount = mSeedsCount;
        this.mPeersCount = mPeersCount;
        this.mHash = mHash;
    }

    public String getName() {
        return mName;
    }

    public String getDetailsPageLink() {
        return mDetailsPageLink;
    }

    public String getAge() {
        return mAge;
    }

    public String getSize() {
        return mSize;
    }

    public int getSeeds() {
        return mSeedsCount;
    }

    public int getPeers() {
        return mPeersCount;
    }

    public String getHash() {
        return mHash;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.mName);
        dest.writeString(this.mDetailsPageLink);
        dest.writeString(this.mAge);
        dest.writeString(this.mSize);
        dest.writeInt(this.mSeedsCount);
        dest.writeInt(this.mPeersCount);
        dest.writeString(this.mHash);
    }

    protected Torrent(Parcel in) {
        this.mName = in.readString();
        this.mDetailsPageLink = in.readString();
        this.mAge = in.readString();
        this.mSize = in.readString();
        this.mSeedsCount = in.readInt();
        this.mPeersCount = in.readInt();
        this.mHash = in.readString();
    }

    public static final Parcelable.Creator<Torrent> CREATOR = new Parcelable.Creator<Torrent>() {
        @Override
        public Torrent createFromParcel(Parcel source) {
            return new Torrent(source);
        }

        @Override
        public Torrent[] newArray(int size) {
            return new Torrent[size];
        }
    };
}
