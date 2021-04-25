package com.robpietryk.allegroreposlist;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.robpietryk.allegroreposlist.adapters.RepositoryListAdapter;
import com.robpietryk.allegroreposlist.gitapi.GetGithubDataService;
import com.robpietryk.allegroreposlist.gitapi.Repository;
import com.robpietryk.allegroreposlist.gitapi.RepositoryList;
import com.robpietryk.allegroreposlist.gitapi.RetrofitInstance;
import com.robpietryk.allegroreposlist.utils.Utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Array;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.core.Scheduler;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.schedulers.Schedulers;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FirstFragment extends Fragment {

    private final String GITHUB_ORGANISATION_PATH = "orgs";
    private final String GITHUB_USER_PATH = "users";

    View rootView;
    private RepositoryListAdapter adapter;
    private RecyclerView recyclerView;

    private RepositoryList repositoryList = null;;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        rootView = inflater.inflate(R.layout.fragment_first, container, false);

        if(!Utils.isNetworkAvailable(getActivity()) && repositoryList == null) {
            Toast.makeText(getActivity(), "Device is offline", Toast.LENGTH_SHORT).show();
            return rootView;
        }

        try {

            if (repositoryList == null) {

                repositoryList = new RepositoryList();

                GetGithubDataService githubService = RetrofitInstance.getRetrofitInstance().create(GetGithubDataService.class);

                Observable<ArrayList<Repository>> repoUserListObservable = githubService.getRepositoriesData(GITHUB_USER_PATH);
                Observable<ArrayList<Repository>> repoOrgsListObservable = githubService.getRepositoriesData(GITHUB_ORGANISATION_PATH);

                Observable.combineLatest(repoUserListObservable, repoOrgsListObservable, (userList, orgsList) -> {
                    userList.addAll(orgsList);
                    return userList;
                })
                        .subscribeOn(Schedulers.newThread())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(result -> {
                            repositoryList.setRepositoryList(result);
                            setUpRepositoryListAdapter(repositoryList);
                        }, throwable -> Toast.makeText(getActivity(), "Something went wrong: " + throwable.getMessage(), Toast.LENGTH_LONG).show());
            }
            else {
                setUpRepositoryListAdapter(repositoryList);
            }
        }
        catch (Exception e) {
            e.printStackTrace();
            Toast.makeText(getActivity(), "Something went wrong", Toast.LENGTH_SHORT).show();
        }

        return rootView;
    }

    private void setUpRepositoryListAdapter(RepositoryList repoList) {

        recyclerView = rootView.findViewById(R.id.repoList_recyclerView);
        adapter = new RepositoryListAdapter(repoList);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);

    }

}