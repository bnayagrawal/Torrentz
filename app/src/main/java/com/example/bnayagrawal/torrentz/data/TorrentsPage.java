package com.example.bnayagrawal.torrentz.data;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by bnayagrawal on 9/3/18.
 */

public final class TorrentsPage implements Parcelable {
    private final int mTorrentCount;
    private final String mAge;
    private final boolean mAdult;
    private final boolean mVerified;
    private final OrderBy mOrderBy;
    private final int mPageCount;
    private HashMap<Integer,ArrayList<Torrent>> mPageTorrents;

    public TorrentsPage(int mTorrentCount, String mAge, boolean mAdult, boolean mVerified, OrderBy mOrderBy, int mPageCount, HashMap<Integer, ArrayList<Torrent>> mPageTorrents) {
        this.mTorrentCount = mTorrentCount;
        this.mAge = mAge;
        this.mAdult = mAdult;
        this.mVerified = mVerified;
        this.mOrderBy = mOrderBy;
        this.mPageCount = mPageCount;
        this.mPageTorrents = mPageTorrents;
    }

    /*
     * GETTER METHODS
     */
    public int getTorrentCount() {
        return mTorrentCount;
    }

    public String getAge() {
        return mAge;
    }

    public boolean isAdult() {
        return mAdult;
    }

    public boolean isVerified() {
        return mVerified;
    }

    public OrderBy getOrderBy() {
        return mOrderBy;
    }

    public int getPageCount() {
        return mPageCount;
    }

    public HashMap<Integer, ArrayList<Torrent>> getPageTorrents() {
        return mPageTorrents;
    }

    /**
     * Order torrents by
     */
    public enum OrderBy {
        PEERS,
        RATING,
        DATE,
        SIZE
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.mTorrentCount);
        dest.writeString(this.mAge);
        dest.writeByte(this.mAdult ? (byte) 1 : (byte) 0);
        dest.writeByte(this.mVerified ? (byte) 1 : (byte) 0);
        dest.writeInt(this.mOrderBy == null ? -1 : this.mOrderBy.ordinal());
        dest.writeInt(this.mPageCount);
        dest.writeSerializable(this.mPageTorrents);
    }

    protected TorrentsPage(Parcel in) {
        this.mTorrentCount = in.readInt();
        this.mAge = in.readString();
        this.mAdult = in.readByte() != 0;
        this.mVerified = in.readByte() != 0;
        int tmpMOrderBy = in.readInt();
        this.mOrderBy = tmpMOrderBy == -1 ? null : OrderBy.values()[tmpMOrderBy];
        this.mPageCount = in.readInt();
        this.mPageTorrents = (HashMap<Integer, ArrayList<Torrent>>) in.readSerializable();
    }

    public static final Parcelable.Creator<TorrentsPage> CREATOR = new Parcelable.Creator<TorrentsPage>() {
        @Override
        public TorrentsPage createFromParcel(Parcel source) {
            return new TorrentsPage(source);
        }

        @Override
        public TorrentsPage[] newArray(int size) {
            return new TorrentsPage[size];
        }
    };
}
