package com.geyek.widget;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        GeyekRatingBar geyekRatingBar = new GeyekRatingBar(this);
        geyekRatingBar.setRating(0);
        geyekRatingBar.setSpacing(10);
        geyekRatingBar.setIsIndicator(false);
        geyekRatingBar.setNumStars(5);
        geyekRatingBar.setStepSize(0.5F);
        geyekRatingBar.setProgress(null);
        geyekRatingBar.setProgressed(null);
        geyekRatingBar.setOnRatingBarChangeListener(new GeyekRatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(GeyekRatingBar ratingBar, float rating, boolean fromUser) {

            }
        });
    }
}
