package com.example.bnayagrawal.torrentz.data;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by bnayagrawal on 9/3/18.
 */

public final class TorrentDownloadLocation implements Parcelable {
    private final String mLinkIcon;
    private final String mLinkUrl;
    private final String mAge;

    public TorrentDownloadLocation(String mLinkIcon, String mLinkUrl, String mAge) {
        this.mLinkIcon = mLinkIcon;
        this.mLinkUrl = mLinkUrl;
        this.mAge = mAge;
    }

    public String getLinkIcon() {
        return mLinkIcon;
    }

    public String getLinkUrl() {
        return mLinkUrl;
    }

    public String getAge() {
        return mAge;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.mLinkIcon);
        dest.writeString(this.mLinkUrl);
        dest.writeString(this.mAge);
    }

    protected TorrentDownloadLocation(Parcel in) {
        this.mLinkIcon = in.readString();
        this.mLinkUrl = in.readString();
        this.mAge = in.readString();
    }

    public static final Parcelable.Creator<TorrentDownloadLocation> CREATOR = new Parcelable.Creator<TorrentDownloadLocation>() {
        @Override
        public TorrentDownloadLocation createFromParcel(Parcel source) {
            return new TorrentDownloadLocation(source);
        }

        @Override
        public TorrentDownloadLocation[] newArray(int size) {
            return new TorrentDownloadLocation[size];
        }
    };
}
