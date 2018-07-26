The RatingBar can set spacing between items.
But, It can't set more status now.
You can set progress and progressed for RatingBar's status.
If you have more question, please call by Issue [Click Here][1].

[1]:https://github.com/liyonghuan/RatingBar/issues

![Demo](image/demo1.gif)

## How to use?

### 1，In xml

It's a demo by *.xml*:

```xml
<com.klavor.widget.RatingBar
	android:layout_width="wrap_content"
	android:layout_height="wrap_content"
	app:progress="@drawable/btn_rating_star_off_normal"
	app:progressed="@drawable/btn_rating_star_off_selected"
	app:numStars="6"
	app:rating="0"
	app:stepSize="0.2"
	app:spacing="10dp"
	app:isIndicator="false"
	app:automatic="fully"/>
```

### 2，In Java code

Like custom RatingBar of Android.

```java
RatingBar ratingBar = new RatingBar(this);
ratingBar.setRating(0);
ratingBar.setSpacing(10);
ratingBar.setIsIndicator(false);
ratingBar.setNumStars(5);
ratingBar.setStepSize(0.5F);
ratingBar.setProgress(null);
ratingBar.setProgressed(null);
ratingBar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
	@Override
	public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
			
	}
});
```

## About

In my project, I need a RatingBar that can set spacing between items, but some open source project and developer anwser can't resolve mine requirement.</br>
Then I create this project.

## Unsolved

But,the property "automatic" can't be effective.</br>
RatingBar's method like RatingBar of Android, so you can easy to get started.

## Thank

Thank me, A HaHa~

## License

Copyright 2016 LiHuan
