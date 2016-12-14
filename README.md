# GeyekRatingBar
The GeyekRatingBar can set spacing between items.</br>
But, It can set more status now.
You can set progress and progressed for GeyekRatingBar's status.</br>
If you have more question, please call by Issues on the <Github.com>.

![Demo](Image/demo1.gif)

# How to use

###1，In xml
It's a demo by *.xml*:

	<com.geyek.widget.GeyekRatingBar
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

###2，In Java code
Like custom RatingBar of Android.

#about
In my project, I need a RatingBar that can set spacing between items, but some open source project and developer anwser can't resolve mine requirement.</br>
Then I create this project.

#unsolved
But,the property "automatic" can be effective.</br>
GeyekRatingBar's method like RatingBar of Android, so you can easy to get started.

#Thank
Thank me, A HaHa~

#License
	Copyright 2016 LiHuan