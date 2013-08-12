package com.anuj.android.moviesearchapp.widget;

import android.app.PendingIntent;
import android.app.Service;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.IBinder;
import android.widget.RemoteViews;

import com.anuj.android.moviesearchapp.R;
import com.anuj.android.moviesearchapp.model.Movie;
import com.anuj.android.moviesearchapp.model.Person;
import com.anuj.android.moviesearchapp.services.MovieSeeker;

import java.util.ArrayList;

/**
 * Created by anuj on 8/11/13.
 */

public class MovieSearchWidget extends AppWidgetProvider {

    private static final String IMDB_BASE_URL = "http://m.imdb.com/title/";

    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
        // To prevent any ANR timeouts, we perform the update in a service
        super.onUpdate(context, appWidgetManager, appWidgetIds);

        RemoteViews updateViews = new RemoteViews(context.getPackageName(), R.layout.widget_layout);
        updateViews.setTextViewText(R.id.app_name, context.getString(R.string.app_name));

        ComponentName thisWidget = new ComponentName(context, MovieSearchWidget.class);

        PerformMovieSeekTask fetchTask=new PerformMovieSeekTask();

        AppWidgetManager.getInstance(context.getApplicationContext());
        fetchTask.setContext(context);
        fetchTask.setRemoteViews(updateViews);
        fetchTask.execute();

        AppWidgetManager.getInstance(context).updateAppWidget(thisWidget, updateViews);

    }

    private static class PerformMovieSeekTask extends AsyncTask<String, Void, Movie> {
        private MovieSeeker movieSeeker = new MovieSeeker();
        private Movie movie = new Movie();
        private RemoteViews updateViews;
        private Context context;

        private void setContext(Context context) {
            this.context = context;
        }

        private void setRemoteViews(RemoteViews updateViews) {
            this.updateViews = updateViews;
        }

        @Override
        protected Movie doInBackground(String... params) {
            return movieSeeker.findLatest();
        }

        @Override
        protected void onPostExecute(Movie movie) {
            this.movie = movie;

            String imdbUrl = IMDB_BASE_URL + movie.imdbId;
            updateViews.setTextViewText(R.id.app_name, context.getString(R.string.app_name));
            updateViews.setTextViewText(R.id.movie_name, movie.name);

            Intent intent = new Intent();
            intent.setAction("android.intent.action.VIEW");
            intent.addCategory("android.intent.category.BROWSABLE");
            intent.setData(Uri.parse(imdbUrl));

            PendingIntent pendingIntent =
                    PendingIntent.getActivity(context, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);

            updateViews.setOnClickPendingIntent(R.id.movie_name, pendingIntent);

            ComponentName thisWidget = new ComponentName(context,MovieSearchWidget.class);
            AppWidgetManager manager = AppWidgetManager.getInstance(context);
            manager.updateAppWidget(thisWidget, updateViews);
        }
     }

}