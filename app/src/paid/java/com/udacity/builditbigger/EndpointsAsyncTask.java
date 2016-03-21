package com.udacity.builditbigger;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.api.client.extensions.android.http.AndroidHttp;
import com.google.api.client.extensions.android.json.AndroidJsonFactory;
import com.udacity.builditbigger.EndpointsTask;
import com.udacity.builditbigger.R;
import com.udacity.jokedisplay.JokeDisplayActivity;
import com.udacity.jokesbackend.jokeApi.JokeApi;

import java.io.IOException;

/**
 * Created by rahkhan1 on 2/16/16.
 */
public class EndpointsAsyncTask extends EndpointsTask {


    public EndpointsAsyncTask(Context context, ProgressBar progressBar) {
        super.context = context;
        super.progressBar = progressBar;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();

        if (this.progressBar!=null){
            this.progressBar.setVisibility(View.VISIBLE);
        }
    }


    @Override
    protected void onPostExecute(final String result) {
        super.onPostExecute(result);
    }

}