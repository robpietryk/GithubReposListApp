package com.robpietryk.allegroreposlist.gitapi;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class RepositoryList {

    @SerializedName("")
    private ArrayList<Repository> repositoryList = null;

    public ArrayList<Repository> getRepositoryList() {
        return repositoryList;
    }

    public void setRepositoryList(ArrayList<Repository> repositoryList) {
        this.repositoryList = repositoryList;
    }
}
