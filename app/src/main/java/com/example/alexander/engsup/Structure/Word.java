package com.example.alexander.engsup.Structure;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by slava on 18.02.2017.
 */

public class Word implements Parcelable {

    private int num;
    private String lang1;
    private String def1;
    private String lang2;
    private String def2;
    private int synId;
    private int oppId;
    private String pos;
    private String sent1;
    private String sent2;


    public Word(int num, String lang1, String def1, String lang2, String def2,
                int synId, int oppId, String pos, String sent1, String sent2) {
        this.num = num;
        this.lang1 = lang1;
        this.def1 = def1;
        this.lang2 = lang2;
        this.def2 = def2;
        this.synId = synId;
        this.oppId = oppId;
        this.pos = pos;
        this.sent1 = sent1;
        this.sent2 = sent2;
    }

    public int getNum() {
        return num;
    }

    public String getLang1() {
        return lang1;
    }

    public String getDef1() {
        return def1;
    }

    public String getLang2() {
        return lang2;
    }

    public String getDef2() {
        return def2;
    }

    public int getSynId() {
        return synId;
    }

    public int getOppId() {
        return oppId;
    }

    public String getPos() {
        return pos;
    }

    public String getSent1() {
        return sent1;
    }

    public String getSent2() {
        return sent2;
    }

    protected Word(Parcel in) {
        num = in.readInt();
        lang1 = in.readString();
        def1 = in.readString();
        lang2 = in.readString();
        def2 = in.readString();
        synId = in.readInt();
        oppId = in.readInt();
        pos = in.readString();
        sent1 = in.readString();
        sent2 = in.readString();
    }

    public static final Creator<Word> CREATOR = new Creator<Word>() {
        @Override
        public Word createFromParcel(Parcel in) {
            return new Word(in);
        }

        @Override
        public Word[] newArray(int size) {
            return new Word[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(num);
        dest.writeString(lang1);
        dest.writeString(def1);
        dest.writeString(lang2);
        dest.writeString(def2);
        dest.writeInt(synId);
        dest.writeInt(oppId);
        dest.writeString(pos);
        dest.writeString(sent1);
        dest.writeString(sent2);
    }
}
