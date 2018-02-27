package com.udacity.examples.popularmovie;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.udacity.examples.popularmovie.data.Movie;
import com.udacity.examples.popularmovie.utils.NetworkUtils;

import java.util.List;

/**
 * Created by Mahmoud Emam on 2/21/18.
 */

public class MoviesAdapter extends RecyclerView.Adapter<MoviesAdapter.MovieViewHolder> {
    private static final String TAG = MoviesAdapter.class.getSimpleName();

    private Context mContext;
    private List<Movie> mMovies;
    private OnItemClickListener mListener;

    public interface OnItemClickListener {
        void onClick(Movie movie);
    }

    public MoviesAdapter(Context context, OnItemClickListener listener, List<Movie> movies) {
        mContext = context;
        mMovies = movies;
        mListener = listener;
    }

    @Override
    public void onBindViewHolder(MovieViewHolder holder, int position) {
        holder.bind(mMovies.get(position));
    }

    @Override
    public MovieViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.movie_item, parent, false);
        return new MovieViewHolder(view);
    }

    @Override
    public int getItemCount() {
        return mMovies.size();
    }

    class MovieViewHolder extends RecyclerView.ViewHolder {
        private ImageView movieImageView;

        MovieViewHolder(View view) {
            super(view);

            movieImageView = view.findViewById(R.id.iv_movie_img);
            movieImageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    mListener.onClick((Movie) view.getTag());
                }
            });
        }

        void bind(Movie movie) {
            Log.d(TAG, "Poster: " + movie.getPosterPath());
            NetworkUtils.loadImage(mContext, movie.getPosterPath(), movieImageView);
            movieImageView.setTag(movie);
        }
    }
}