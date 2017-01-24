package me.stiky.widget;

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

        RatingBar ratingBar = new RatingBar(this);
        ratingBar.setRating(0);
        ratingBar.setSpacing(10);
        ratingBar.setIsIndicator(false);
        ratingBar.setNumStars(5);
        ratingBar.setStepSize(0.5F);
        ratingBar.setProgress(BitmapFactory.decodeResource(getResources(), R.drawable.btn_rating_star_off_selected));
        ratingBar.setProgressed(BitmapFactory.decodeResource(getResources(), R.drawable.btn_rating_star_off_normal));
        ratingBar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {

            }
        });

        container.addView(ratingBar);
    }
}