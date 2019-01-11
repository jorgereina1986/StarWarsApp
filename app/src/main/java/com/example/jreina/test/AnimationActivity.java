package com.example.jreina.test;

import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.graphics.Rect;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.KeyCharacterMap;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.Window;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.jreina.test.starwars.StarWarsActivity;

public class AnimationActivity extends AppCompatActivity {
    private TextView textView;
    private ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_animation);


        textView = findViewById(R.id.hw);
        imageView = findViewById(R.id.image);

        int actionBarHeight = getSupportActionBar().getHeight();
        int navBarHeight = getNavBarHeight(this);

        Rect rectgle = new Rect();
        Window window = getWindow();
        window.getDecorView().getWindowVisibleDisplayFrame(rectgle);
        int statusBarHeight = rectgle.top;

        int reduce = actionBarHeight + statusBarHeight + navBarHeight;

        DisplayMetrics displayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        final int height = displayMetrics.heightPixels - reduce;
        final int width = displayMetrics.widthPixels;

        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                if (imageView.getAlpha() == 0) {
//                    alphaAnimation(1, height, width);
//                    textView.setText(imageView.getX() + " " + imageView.getY() + "translation: " + imageView.getTranslationX() + " " + imageView.getTranslationY());
//                } else {
//                    alphaAnimation(0, height, width);
//                    textView.setText(imageView.getX() + " " + imageView.getY() + "translation: " + imageView.getTranslationX() + " " + imageView.getTranslationY());
//                }

                Intent intent = new Intent(getBaseContext(), StarWarsActivity.class);
                startActivity(intent);
            }
        });


//        imageView.setX
    }

    private void alphaAnimation(int value, int height, int width) {
        ObjectAnimator animator = ObjectAnimator.ofPropertyValuesHolder(
                imageView,
                getPropertyValuesHolders(value, imageView, height, width));
        animator.setDuration(2000);
        animator.start();
    }

    @NonNull
    private PropertyValuesHolder[] getPropertyValuesHolders(int value, ImageView image, int height, int width) {
        return new PropertyValuesHolder[]{
                PropertyValuesHolder.ofFloat("alpha", 1),
                PropertyValuesHolder.ofFloat("scaleX", 1),
                PropertyValuesHolder.ofFloat("scaleY", 1),
                PropertyValuesHolder.ofFloat("X", width - image.getWidth()),
                PropertyValuesHolder.ofFloat("Y", height - (image.getHeight() + getStatusBarHeight())),
        };
    }

    public int getStatusBarHeight() {
        int result = 0;
        int resourceId = getResources().getIdentifier("status_bar_height", "dimen", "android");
        if (resourceId > 0) {
            result = getResources().getDimensionPixelSize(resourceId);
        }
        return result;
    }

    public int getNavBarHeight(Context c) {
        int result = 0;
        boolean hasMenuKey = ViewConfiguration.get(c).hasPermanentMenuKey();
        boolean hasBackKey = KeyCharacterMap.deviceHasKey(KeyEvent.KEYCODE_BACK);

        if (!hasMenuKey && !hasBackKey) {
            //The device has a navigation bar
            Resources resources = c.getResources();

            int orientation = resources.getConfiguration().orientation;
            int resourceId;
            if (isTablet(c)) {
                resourceId = resources.getIdentifier(orientation == Configuration.ORIENTATION_PORTRAIT ? "navigation_bar_height" : "navigation_bar_height_landscape", "dimen", "android");
            } else {
                resourceId = resources.getIdentifier(orientation == Configuration.ORIENTATION_PORTRAIT ? "navigation_bar_height" : "navigation_bar_width", "dimen", "android");
            }

            if (resourceId > 0) {
                return resources.getDimensionPixelSize(resourceId);
            }
        }
        return result;
    }

    private boolean isTablet(Context c) {
        return (c.getResources().getConfiguration().screenLayout
                & Configuration.SCREENLAYOUT_SIZE_MASK)
                >= Configuration.SCREENLAYOUT_SIZE_LARGE;
    }
}
