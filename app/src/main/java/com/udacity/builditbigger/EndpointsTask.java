package com.udacity.builditbigger;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.api.client.extensions.android.http.AndroidHttp;
import com.google.api.client.extensions.android.json.AndroidJsonFactory;
import com.udacity.jokedisplay.JokeDisplayActivity;
import com.udacity.jokesbackend.jokeApi.JokeApi;

import java.io.IOException;

/**
 * Created by rahkhan1 on 2/16/16.
 */
public abstract class EndpointsTask extends AsyncTask<Void, Void, String> {
    protected static final String ERROR = "ERROR";
    protected static JokeApi jokeApiService = null;

    protected Context context;
    protected ProgressBar progressBar;


    @Override
    protected void onPreExecute() {
        System.out.println("General EndpointsAsyncTask.onPreExecute");
        super.onPreExecute();
    }

    @Override
    protected String doInBackground(Void... params) {

        if (jokeApiService == null) {
            JokeApi.Builder builder = new JokeApi.Builder(AndroidHttp.newCompatibleTransport(),
                    new AndroidJsonFactory(), null)
                    .setRootUrl(context.getString(R.string.joke_api_root_url));
            jokeApiService = builder.build();
        }


        try {
            return jokeApiService.tellJoke().execute().getData();
        } catch (IOException e) {
            e.printStackTrace();
            return ERROR;
        }
    }

    @Override
    protected void onPostExecute(String result) {
        System.out.println("General EndpointsAsyncTask.onPostExecute");
        if (ERROR.equals(result)) {
            Toast.makeText(context, "There was an error retrieving the joke.", Toast.LENGTH_LONG).show();
        }
        else {
            Intent intent = new Intent(context, JokeDisplayActivity.class);
            intent.putExtra(JokeDisplayActivity.JOKE_INTENT, result);
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(intent);
        }

        if (this.progressBar!=null){
            this.progressBar.setVisibility(View.INVISIBLE);
        }
    }

}