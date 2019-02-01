package com.target.assignment.features.trendlist.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

public class TrendResponse implements Parcelable {

    @SerializedName("username")
    private String username;
    @SerializedName("name")
    private String name;
    @SerializedName("url")
    private String url;
    @SerializedName("avatar")
    private String avatar;
    @SerializedName("repo")
    private Repo repo;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public Repo getRepo() {
        return repo;
    }

    public void setRepo(Repo repo) {
        this.repo = repo;
    }


    protected TrendResponse(Parcel in) {
        username = in.readString();
        name = in.readString();
        url = in.readString();
        avatar = in.readString();
        repo = (Repo) in.readValue(Repo.class.getClassLoader());
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(username);
        dest.writeString(name);
        dest.writeString(url);
        dest.writeString(avatar);
        dest.writeValue(repo);
    }

    @SuppressWarnings("unused")
    public static final Parcelable.Creator<TrendResponse> CREATOR = new Parcelable.Creator<TrendResponse>() {
        @Override
        public TrendResponse createFromParcel(Parcel in) {
            return new TrendResponse(in);
        }

        @Override
        public TrendResponse[] newArray(int size) {
            return new TrendResponse[size];
        }
    };
}