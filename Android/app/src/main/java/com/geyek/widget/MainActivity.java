package com.geyek.widget;

import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.LinearLayout;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        LinearLayout container = (LinearLayout) findViewById(R.id.container);

        GeyekRatingBar geyekRatingBar = new GeyekRatingBar(this);
        geyekRatingBar.setRating(0);
        geyekRatingBar.setSpacing(10);
        geyekRatingBar.setIsIndicator(false);
        geyekRatingBar.setNumStars(5);
        geyekRatingBar.setStepSize(0.5F);
        geyekRatingBar.setProgress(BitmapFactory.decodeResource(getResources(), R.drawable.btn_rating_star_off_selected));
        geyekRatingBar.setProgressed(BitmapFactory.decodeResource(getResources(), R.drawable.btn_rating_star_off_normal));
        geyekRatingBar.setOnRatingBarChangeListener(new GeyekRatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(GeyekRatingBar ratingBar, float rating, boolean fromUser) {

            }
        });

        container.addView(geyekRatingBar);
    }
}
