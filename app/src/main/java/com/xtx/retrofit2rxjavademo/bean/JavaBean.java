package com.xtx.retrofit2rxjavademo.bean;

import android.os.Parcel;
import android.os.Parcelable;

public class JavaBean implements Parcelable {
    private String name;
    private int    age;

    protected JavaBean(Parcel in) {
        name = in.readString();
        age = in.readInt();
    }

    public static final Creator<JavaBean> CREATOR = new Creator<JavaBean>() {
        @Override
        public JavaBean createFromParcel(Parcel in) {
            return new JavaBean(in);
        }

        @Override
        public JavaBean[] newArray(int size) {
            return new JavaBean[size];
        }
    };

    private String getName() {
        return name;
    }

    private void setName(String name) {
        this.name = name;
    }

    private int getAge() {
        return age;
    }

    private void setAge(int age) {
        this.age = age;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.name);
        parcel.writeInt(this.age);
    }

    @Override
    public String toString() {
        return "JavaBean{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}
