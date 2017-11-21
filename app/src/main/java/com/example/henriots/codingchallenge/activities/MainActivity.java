package com.example.henriots.codingchallenge.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.henriots.codingchallenge.R;
import com.example.henriots.codingchallenge.network.VolleyClient;

public class MainActivity extends AppCompatActivity {

    /** The class tag. */
    private static final String TAG = MainActivity.class.getSimpleName();

    /** Client for handling network requests. */
    private VolleyClient mVolleyClient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mVolleyClient = new VolleyClient(this);
    }
}
