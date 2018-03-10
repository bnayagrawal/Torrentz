package com.example.bnayagrawal.torrentz.data;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;

/**
 * Created by bnayagrawal on 9/3/18.
 */

public final class TorrentDetailsPage implements Parcelable {
    private final ArrayList<TorrentDownloadLocation> mTorrentDownloadLocations;
    private final ArrayList<TorrentTracker> mTorrentTrackers;
    private final boolean mGoodTorrent;
    private final int mVerifiedCount;
    private final int mFakeCount;
    private final int mPasswordCount;
    private final int mLowQualityCount;
    private final int mVirusCount;


    public TorrentDetailsPage(ArrayList<TorrentDownloadLocation> mTorrentDownloadLocations, ArrayList<TorrentTracker> mTorrentTrackers, boolean mGoodTorrent, int mVerifiedCount, int mFakeCount, int mPasswordCount, int mLowQualityCount, int mVirusCount) {
        this.mTorrentDownloadLocations = mTorrentDownloadLocations;
        this.mTorrentTrackers = mTorrentTrackers;
        this.mGoodTorrent = mGoodTorrent;
        this.mVerifiedCount = mVerifiedCount;
        this.mFakeCount = mFakeCount;
        this.mPasswordCount = mPasswordCount;
        this.mLowQualityCount = mLowQualityCount;
        this.mVirusCount = mVirusCount;
    }

    public ArrayList<TorrentDownloadLocation> getTorrentDownloadLocations() {
        return mTorrentDownloadLocations;
    }

    public ArrayList<TorrentTracker> getTorrentTrackers() {
        return mTorrentTrackers;
    }

    public boolean isGoodTorrent() {
        return mGoodTorrent;
    }

    public int getVerifiedCount() {
        return mVerifiedCount;
    }

    public int getFakeCount() {
        return mFakeCount;
    }

    public int getPasswordCount() {
        return mPasswordCount;
    }

    public int getLowQualityCount() {
        return mLowQualityCount;
    }

    public int getVirusCount() {
        return mVirusCount;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeList(this.mTorrentDownloadLocations);
        dest.writeList(this.mTorrentTrackers);
        dest.writeByte(this.mGoodTorrent ? (byte) 1 : (byte) 0);
        dest.writeInt(this.mVerifiedCount);
        dest.writeInt(this.mFakeCount);
        dest.writeInt(this.mPasswordCount);
        dest.writeInt(this.mLowQualityCount);
        dest.writeInt(this.mVirusCount);
    }

    protected TorrentDetailsPage(Parcel in) {
        this.mTorrentDownloadLocations = new ArrayList<TorrentDownloadLocation>();
        in.readList(this.mTorrentDownloadLocations, TorrentDownloadLocation.class.getClassLoader());
        this.mTorrentTrackers = new ArrayList<TorrentTracker>();
        in.readList(this.mTorrentTrackers, TorrentTracker.class.getClassLoader());
        this.mGoodTorrent = in.readByte() != 0;
        this.mVerifiedCount = in.readInt();
        this.mFakeCount = in.readInt();
        this.mPasswordCount = in.readInt();
        this.mLowQualityCount = in.readInt();
        this.mVirusCount = in.readInt();
    }

    public static final Parcelable.Creator<TorrentDetailsPage> CREATOR = new Parcelable.Creator<TorrentDetailsPage>() {
        @Override
        public TorrentDetailsPage createFromParcel(Parcel source) {
            return new TorrentDetailsPage(source);
        }

        @Override
        public TorrentDetailsPage[] newArray(int size) {
            return new TorrentDetailsPage[size];
        }
    };
}
