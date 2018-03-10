package com.example.bnayagrawal.torrentz.data;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by bnayagrawal on 9/3/18.
 */

public final class TorrentTracker implements Parcelable {
    private final String mUrl;
    private final int mSeedsCount;
    private final int mPeersCount;

    public TorrentTracker(String mUrl, int mSeedsCount, int mPeersCount) {
        this.mUrl = mUrl;
        this.mSeedsCount = mSeedsCount;
        this.mPeersCount = mPeersCount;
    }

    public String getUrl() {
        return mUrl;
    }

    public int getSeedsCount() {
        return mSeedsCount;
    }

    public int getPeersCount() {
        return mPeersCount;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.mUrl);
        dest.writeInt(this.mSeedsCount);
        dest.writeInt(this.mPeersCount);
    }

    protected TorrentTracker(Parcel in) {
        this.mUrl = in.readString();
        this.mSeedsCount = in.readInt();
        this.mPeersCount = in.readInt();
    }

    public static final Parcelable.Creator<TorrentTracker> CREATOR = new Parcelable.Creator<TorrentTracker>() {
        @Override
        public TorrentTracker createFromParcel(Parcel source) {
            return new TorrentTracker(source);
        }

        @Override
        public TorrentTracker[] newArray(int size) {
            return new TorrentTracker[size];
        }
    };
}
