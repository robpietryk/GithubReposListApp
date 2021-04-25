package com.robpietryk.allegroreposlist.gitapi;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

public class Repository implements Parcelable {

    @SerializedName("name")
    private String name;
    @SerializedName("description")
    private String description;
    @SerializedName("html_url")
    private String repositoryUrl;
    @SerializedName("language")
    private String language;
    @SerializedName("stargazers_count")
    private int starCount;
    @SerializedName("forks_count")
    private int forkCount;

    public Repository(String name, String description, String repositoryUrl, String language, int starCount, int forkCount) {
        this.name = name;
        this.description = description;
        this.repositoryUrl = repositoryUrl;
        this.language = language;
        this.starCount = starCount;
        this.forkCount = forkCount;
    }

    protected Repository(Parcel in) {
        name = in.readString();
        description = in.readString();
        repositoryUrl = in.readString();
        language = in.readString();
        starCount = in.readInt();
        forkCount = in.readInt();
    }

    public static final Creator<Repository> CREATOR = new Creator<Repository>() {
        @Override
        public Repository createFromParcel(Parcel in) {
            return new Repository(in);
        }

        @Override
        public Repository[] newArray(int size) {
            return new Repository[size];
        }
    };

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getRepositoryUrl() {
        return repositoryUrl;
    }

    public void setRepositoryUrl(String repositoryUrl) {
        this.repositoryUrl = repositoryUrl;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public int getStarCount() {
        return starCount;
    }

    public void setStarCount(int starCount) {
        this.starCount = starCount;
    }

    public int getForkCount() {
        return forkCount;
    }

    public void setForkCount(int forkCount) {
        this.forkCount = forkCount;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeString(description);
        dest.writeString(repositoryUrl);
        dest.writeString(language);
        dest.writeInt(starCount);
        dest.writeInt(forkCount);
    }
}
