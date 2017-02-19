package com.example.alexander.engsup.Structure;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by slava on 19.02.2017.
 */

public class Progress implements Parcelable {
    private int userId;
    private int wordId;
    private int points;
    private int status;

    public Progress(int userId, int wordId, int points, int status) {
        this.userId = userId;
        this.wordId = wordId;
        this.points = points;
        this.status = status;
    }

    public int getUserId() {
        return userId;
    }

    public int getWordId() {
        return wordId;
    }

    public int getPoints() {
        return points;
    }

    public int getStatus() {
        return status;
    }

    protected Progress(Parcel in) {
        userId = in.readInt();
        wordId = in.readInt();
        points = in.readInt();
        status = in.readInt();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(userId);
        dest.writeInt(wordId);
        dest.writeInt(points);
        dest.writeInt(status);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<Progress> CREATOR = new Creator<Progress>() {
        @Override
        public Progress createFromParcel(Parcel in) {
            return new Progress(in);
        }

        @Override
        public Progress[] newArray(int size) {
            return new Progress[size];
        }
    };
}
