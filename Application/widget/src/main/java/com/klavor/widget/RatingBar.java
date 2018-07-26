package com.klavor.widget;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;

/**
 * Created by LiHuan on 2016/12/13.
 */
public class RatingBar extends View {
    private static final String TAG = "RatingBar";

    private int mWidth;
    private int mHeight;

    private int mNumStars = 5;
    private float mRating;
    private float mStepSize = 0.5F;
    private boolean mIsIndicator;
    private float mSpacing;
    private int mAutomatic = 0;

    private Bitmap mProgress;
    private float mStarWidth;
    private float mStarHeight;
    private Bitmap mProgressed;

    private Canvas mCanvas;

    /**
     * 暂时未设置padding支持
     */
    private float mPaddingLeft;
    private float mPaddingTop;
    private float mPaddingRight;
    private float mPaddingBottom;

    private OnRatingBarChangeListener mOnRatingBarChangeListener;

    public RatingBar(Context context) {
        this(context, null);
    }

    public RatingBar(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public RatingBar(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        final Resources.Theme theme = context.getTheme();
        TypedArray appearance = theme.obtainStyledAttributes(attrs, R.styleable.RatingBar, defStyleAttr, 0);
        if (appearance != null) {
            int indexCount = appearance.getIndexCount();
            for (int i = 0; i < indexCount; i++) {
                int index = appearance.getIndex(i);
                if (index == R.styleable.RatingBar_numStars) {
                    mNumStars = appearance.getInt(index, mNumStars);

                } else if (index == R.styleable.RatingBar_rating) {
                    mRating = appearance.getFloat(index, mRating);

                } else if (index == R.styleable.RatingBar_stepSize) {
                    mStepSize = appearance.getFloat(index, mStepSize);

                } else if (index == R.styleable.RatingBar_isIndicator) {
                    mIsIndicator = appearance.getBoolean(index, mIsIndicator);

                } else if (index == R.styleable.RatingBar_spacing) {
                    mSpacing = appearance.getDimension(index, mSpacing);

                } else if (index == R.styleable.RatingBar_automatic) {
                    mAutomatic = appearance.getInt(index, mAutomatic);

                } else if (index == R.styleable.RatingBar_progress) {
                    int bgResId = appearance.getResourceId(index, -1);
                    Bitmap bgBitmap = BitmapFactory.decodeResource(getResources(), bgResId);
                    setProgress(bgBitmap);

                } else if (index == R.styleable.RatingBar_progressed) {
                    int pgResId = appearance.getResourceId(index, -1);
                    Bitmap pgBitmap = BitmapFactory.decodeResource(getResources(), pgResId);
                    setProgressed(pgBitmap);

                } else {
                }
            }
        }
    }

    /*@TargetApi(21)
    @SuppressWarnings("deprecation")
    public RatingBar(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        Log.d(TAG, "RatingBar: " + BuildConfig.VERSION_CODE);
    }*/

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int bgWidth = mProgress == null ? 0 :mProgress.getWidth();
        int pgWidth = mProgressed == null ? 0 : mProgressed.getWidth();
        if (bgWidth > pgWidth) {
            mStarWidth = pgWidth;
        } else {
            mStarWidth = bgWidth;
        }
        int width = (int) (mStarWidth * mNumStars + getPaddingLeft() + getPaddingRight() + mSpacing * (mNumStars - 1));
        int tempWidth = widthMeasureSpec & MEASURED_SIZE_MASK;
        int w = width - tempWidth;
        mWidth = w > 0 ? tempWidth : width;
        float x = 1;
        if (w > 0) {
            x =  1 - (w / mNumStars) / mStarWidth;
        }
        widthMeasureSpec = mWidth | MeasureSpec.AT_MOST;

        int bgHeight = mProgress == null ? 0 :mProgress.getHeight();
        int pgHeight = mProgressed == null ? 0 : mProgressed.getHeight();
        if (bgHeight > pgHeight) {
            mStarHeight = pgHeight;
        } else {
            mStarHeight = bgHeight;
        }
        int height = (int) (mStarHeight + getPaddingTop() + getPaddingBottom());
        int tempHeight = heightMeasureSpec & MEASURED_SIZE_MASK;
        int h = height - tempHeight;
        mHeight = tempHeight != 0 ? (h > 0 ? tempHeight : height) : height;
        float y = 1;
        if (h > 0 && h != height) {
            y = 1 - h / mStarHeight;
        }
        /*mHeight = h > 0 ? tempHeight : height;
        float y = 1;
        if (h > 0) {
            y = 1 - h / mStarHeight;
        }*/
        heightMeasureSpec = mHeight | MeasureSpec.AT_MOST;

        if (x > y) {
            mStarWidth *= y;
            mStarHeight *= y;
        } else {
            mStarWidth *= x;
            mStarHeight *= x;
        }
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        /*mPaddingLeft = getPaddingLeft();
        mPaddingTop = getPaddingTop();
        mPaddingRight = getPaddingRight();
        mPaddingBottom = getPaddingBottom();*/
        super.onLayout(changed, left, top, right, bottom);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        mCanvas = canvas;
        for (int i = 1; i <= mNumStars; i++) {
            Rect rect1;
            Rect rect2;
            if (i <= mRating) {
                rect1 = new Rect(0, 0, mProgressed.getWidth(), mProgressed.getHeight());
                rect2 = new Rect(
                        (int) (mPaddingLeft + mStarWidth * (i - 1) + mSpacing * (i - 1)),
                        (int) mPaddingTop,
                        (int) (mPaddingLeft + mStarWidth * i + mSpacing * (i - 1)),
                        (int) (mPaddingTop + mStarHeight)
                );
                canvas.drawBitmap(mProgressed, rect1, rect2, new Paint());
            } else {
                if (i - 1 < mRating) {
                    float portion = mRating - (i - 1);
                    rect1 = new Rect(0, 0, (int) (mProgressed.getWidth() * portion), mProgressed.getHeight());
                    rect2 = new Rect(
                            (int) (mPaddingLeft + mStarWidth * (i - 1) + mSpacing * (i - 1)),
                            (int) mPaddingTop,
                            (int) (mPaddingLeft + mStarWidth * (i - 1) + mSpacing * (i - 1) + mStarWidth * portion),
                            (int) (mPaddingTop + mStarHeight)
                    );
                    canvas.drawBitmap(mProgressed, rect1, rect2, new Paint());

                    rect1 = new Rect((int) (mProgress.getWidth() * portion), 0, mProgress.getWidth(), mProgress.getHeight());
                    rect2 = new Rect(
                            (int) (mPaddingLeft + mStarWidth * i + mSpacing * (i - 1) - mStarWidth * (1 - portion)),
                            (int) mPaddingTop,
                            (int) (mPaddingLeft + mStarWidth * i + mSpacing * (i - 1)),
                            (int) (mPaddingTop + mStarHeight)
                    );
                    canvas.drawBitmap(mProgress, rect1, rect2, new Paint());
                } else {
                    rect1 = new Rect(0, 0, mProgress.getWidth(), mProgress.getHeight());
                    rect2 = new Rect(
                            (int) (mPaddingLeft + mStarWidth * (i - 1) + mSpacing * (i - 1)),
                            (int) mPaddingTop,
                            (int) (mPaddingLeft + mStarWidth * i + mSpacing * (i - 1)),
                            (int) (mPaddingTop + mStarHeight)
                    );
                    canvas.drawBitmap(mProgress, rect1, rect2, new Paint());
                }
            }
        }
    }

    public void setProgress(Bitmap bitmap) {
        if (bitmap == null) {
            return;
        }
        mProgress = bitmap;
    }

    public Bitmap getProgress() {
        return mProgress;
    }

    public void setProgressed(Bitmap bitmap) {
        if (bitmap == null) {
            return;
        }
        mProgressed = bitmap;
    }
    
    public Bitmap getProgressed() {
        return mProgressed;
    }

    public void setIsIndicator(boolean isIndicator) {
        mIsIndicator = isIndicator;
        setFocusable(!isIndicator);
    }

    public boolean isIndicator() {
        return mIsIndicator;
    }

    public void setNumStars(final int numStars) {
        if (numStars <= 0) {
            return;
        }
        mNumStars = numStars;
        requestLayout();
    }

    public int getNumStars() {
        return mNumStars;
    }

    public boolean setRating(float rating) {
        if (rating < 0 || mRating == rating) {
            return false;
        }
        mRating = rating;
        return true;
    }

    public float getRating() {
        return mRating;
    }

    public void setStepSize(float stepSize) {
        if (stepSize <= 0) {
            return;
        }
        mStepSize = stepSize;
    }

    public float getStepSize() {
        return mStepSize;
    }

    public void setSpacing(float spacing) {
        mSpacing = spacing;
    }

    public float getSpacing() {
        return mSpacing;
    }

    public void refreshUI() {
        invalidate();
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        float y = event.getY();
        if (!mIsIndicator && y >= 0 && y <= mHeight) {
            float x = event.getX();
            float v = x - mPaddingLeft;
            int v1 = (int) (v / (mStarWidth + mSpacing));
            float v2 = ((v - v1 * (mStarWidth + mSpacing)) / mStarWidth);
            float v3 = v1 + v2;

            int step = (int) (v3 / mStepSize);
            mRating = (step + 1) * mStepSize;
            invalidate();

            dispatchRatingChange(true);
            return true;
        }
        return false;
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (isEnabled()) {
            float increment = mRating;
            switch (keyCode) {
                case KeyEvent.KEYCODE_DPAD_LEFT:
                    increment = -increment;
                case KeyEvent.KEYCODE_DPAD_RIGHT:
                    if (setRating(mRating + increment)) {
                        onKeyChange();
                        return true;
                    }
                    break;
            }
        }

        return super.onKeyDown(keyCode, event);
    }

    void onKeyChange() {
        dispatchRatingChange(true);
    }

    void dispatchRatingChange(boolean fromUser) {
        if (mOnRatingBarChangeListener != null) {
            mOnRatingBarChangeListener.onRatingChanged(this, getRating(), fromUser);
        }
    }

    public interface OnRatingBarChangeListener {
        void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser);
    }

    public void setOnRatingBarChangeListener(OnRatingBarChangeListener listener) {
        mOnRatingBarChangeListener = listener;
    }

    public OnRatingBarChangeListener getOnRatingBarChangeListener() {
        return mOnRatingBarChangeListener;
    }
}
