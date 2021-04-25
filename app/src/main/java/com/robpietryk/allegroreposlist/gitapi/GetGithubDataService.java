package com.robpietryk.allegroreposlist.gitapi;

import java.util.ArrayList;

import io.reactivex.rxjava3.core.Observable;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface GetGithubDataService {

    @GET("{userOrg}/allegro/repos")
    Observable<ArrayList<Repository>> getRepositoriesData(@Path("userOrg") String userOrg);

}
