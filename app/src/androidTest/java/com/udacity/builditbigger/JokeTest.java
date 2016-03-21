package com.udacity.builditbigger;

import android.test.AndroidTestCase;

import java.lang.Exception;
import java.lang.Override;
import java.util.concurrent.ExecutionException;

/**
 * Created by rahkhan1 on 3/16/16.
 */
public class JokeTest extends AndroidTestCase {

    @Override
    protected void setUp() throws Exception {
        super.setUp();
    }

    public void testJoke(){
        String joke = null;
        EndpointsAsyncTask task = new EndpointsAsyncTask(getContext(), null);
        task.execute();

        try {
            joke = task.get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        assertNotNull(joke);
    }
}
