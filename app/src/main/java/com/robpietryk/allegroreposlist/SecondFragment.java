package com.robpietryk.allegroreposlist;

import android.os.Bundle;
import android.text.Html;
import android.text.method.LinkMovementMethod;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import com.robpietryk.allegroreposlist.gitapi.Repository;

public class SecondFragment extends Fragment {

    View mView;
    Repository repository;

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {
        // Inflate the layout for this fragment
        mView = inflater.inflate(R.layout.fragment_second, container, false);

        this.repository = getArguments().getParcelable("repository");

        TextView repoNameDetails = (TextView)mView.findViewById(R.id.repoNameDetails_textView);
        TextView descriptionDetails = (TextView)mView.findViewById(R.id.descriptionDetails_textView);
        TextView languageDetails = (TextView)mView.findViewById(R.id.languageDetails_textView);
        TextView starCountDetails = (TextView)mView.findViewById(R.id.starCountDetails_textView);
        TextView forkCountDetails = (TextView)mView.findViewById(R.id.forkCountDetails_textView);
        TextView repoUrlDetails = (TextView)mView.findViewById(R.id.repoUrlDetails_textView);

        repoNameDetails.setText(repository.getName());
        descriptionDetails.setText(repository.getDescription());
        String lang = repository.getLanguage();
        languageDetails.setText(lang == null ? "No data" : lang);
        starCountDetails.setText(Integer.toString(repository.getStarCount()));
        forkCountDetails.setText(Integer.toString(repository.getForkCount()));

        String repoURLLink = getString(R.string.repoLink, repository.getRepositoryUrl());
        repoUrlDetails.setText(Html.fromHtml(repoURLLink, 0));
        repoUrlDetails.setMovementMethod(LinkMovementMethod.getInstance());


        return mView;
    }
}