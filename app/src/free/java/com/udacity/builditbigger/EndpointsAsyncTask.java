package com.udacity.builditbigger;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.InterstitialAd;
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
    protected InterstitialAd interstitialAd;

    public EndpointsAsyncTask(Context context, ProgressBar progressBar) {
        super.context = context;
        super.progressBar = progressBar;

        interstitialAd = new InterstitialAd(context);
        interstitialAd.setAdUnitId(context.getString(R.string.interstitial_ad_unit_id));

    }

    @Override
    protected void onPreExecute() {
        System.out.println("Free EndpointsAsyncTask.onPreExecute");
        super.onPreExecute();

        if (this.progressBar!=null){
            this.progressBar.setVisibility(View.VISIBLE);
        }
    }


    @Override
    protected void onPostExecute(final String result) {
        System.out.println("Free EndpointsAsyncTask.onPostExecute abcd");

        AdRequest adRequest = new AdRequest
                .Builder()
                .addTestDevice(AdRequest.DEVICE_ID_EMULATOR)
                .addTestDevice("79360F05BF26B1BCA24F0E6515AF6851")
                .build();
        interstitialAd.loadAd(adRequest);

        interstitialAd.setAdListener(new AdListener() {
            @Override
            public void onAdLoaded() {
                System.out.println("EndpointsAsyncTask.onAdLoaded");
                if (EndpointsAsyncTask.this.progressBar != null) {
                    EndpointsAsyncTask.this.progressBar.setVisibility(View.INVISIBLE);
                }
                super.onAdLoaded();
                interstitialAd.show();
            }

            @Override
            public void onAdOpened() {
                System.out.println("EndpointsAsyncTask.onAdOpened");
                if (EndpointsAsyncTask.this.progressBar != null) {
                    EndpointsAsyncTask.this.progressBar.setVisibility(View.INVISIBLE);
                }
                super.onAdOpened();
            }

            @Override
            public void onAdClosed() {
                System.out.println("EndpointsAsyncTask.onAdClosed");
                super.onAdClosed();
                EndpointsAsyncTask.super.onPostExecute(result);
            }

            @Override
            public void onAdFailedToLoad(int errorCode) {
                System.out.println("EndpointsAsyncTask.onAdFailedToLoad");
                System.out.println("errorCode = " + errorCode);
                super.onAdFailedToLoad(errorCode);
                EndpointsAsyncTask.super.onPostExecute(result);
            }
        });
    }

}