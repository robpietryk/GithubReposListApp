package com.robpietryk.allegroreposlist.adapters;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.robpietryk.allegroreposlist.R;
import com.robpietryk.allegroreposlist.gitapi.Repository;
import com.robpietryk.allegroreposlist.gitapi.RepositoryList;

import java.util.ArrayList;

public class RepositoryListAdapter extends RecyclerView.Adapter<RepositoryListAdapter.RepositoryListViewHolder> {

    private RepositoryList repositoriesList;

    public RepositoryListAdapter(RepositoryList repositoriesList) {
        this.repositoriesList = repositoriesList;
    }

    @NonNull
    @Override
    public RepositoryListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.single_row, parent, false);

        return new RepositoryListViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RepositoryListViewHolder holder, int position) {
        holder.repositoryName.setText(repositoriesList.getRepositoryList().get(position).getName());
        String lang = repositoriesList.getRepositoryList().get(position).getLanguage();
        holder.language.setText(lang == null ? "No data" : lang);
        holder.starCount.setText(Integer.toString(repositoriesList.getRepositoryList().get(position).getStarCount()));
        holder.forkCount.setText(Integer.toString(repositoriesList.getRepositoryList().get(position).getForkCount()));
        holder.bindRepository(repositoriesList.getRepositoryList().get(position));
    }

    @Override
    public int getItemCount() {
        return repositoriesList.getRepositoryList().size();
    }

    class RepositoryListViewHolder extends RecyclerView.ViewHolder {

        TextView repositoryName, language, starCount, forkCount;
        Repository repository;

        public RepositoryListViewHolder(@NonNull View itemView) {
            super(itemView);

            repositoryName = itemView.findViewById(R.id.repoName_textView);
            language = itemView.findViewById(R.id.language_textView);
            starCount = itemView.findViewById(R.id.starCount_textView);
            forkCount = itemView.findViewById(R.id.forkCount_textView);

            itemView.setOnClickListener(view -> {
                Bundle bundle = new Bundle();
                bundle.putParcelable("repository", repository);
                Navigation.findNavController(view).navigate(R.id.action_FirstFragment_to_SecondFragment, bundle);
            });
        }

        public void bindRepository(Repository repository) {
            this.repository = repository;
        }

    }

}
