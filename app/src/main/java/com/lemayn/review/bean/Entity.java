package com.lemayn.review.bean;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * author: ly
 * date  : 2019/5/20 11:31
 * desc  :
 */
public class Entity implements Parcelable {

    private int id;

    public Entity(int id) {
        this.id = id;
    }

    protected Entity(Parcel in) {
        id = in.readInt();
    }

    public static final Creator<Entity> CREATOR = new Creator<Entity>() {
        @Override
        public Entity createFromParcel(Parcel in) {
            return new Entity(in);
        }

        @Override
        public Entity[] newArray(int size) {
            return new Entity[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
    }
}
