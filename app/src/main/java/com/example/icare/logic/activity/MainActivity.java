package com.example.icare.logic.activity;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.ViewAnimationUtils;
import android.view.animation.LinearInterpolator;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.example.icare.R;

public class MainActivity extends AppCompatActivity {

    private ConstraintLayout rootLayout;
    private View revealCircle;
    private ImageView logo;
    private TextView appName;
    private TextView slogan;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialize all views
        rootLayout = findViewById(R.id.root_layout);
        revealCircle = findViewById(R.id.reveal_circle);
        logo = findViewById(R.id.logo);
        appName = findViewById(R.id.app_name);
        slogan = findViewById(R.id.slogan);

        // Start animation after the layout is drawn
        rootLayout.post(() -> startCircularRevealAnimation());
    }

    private void startCircularRevealAnimation() {
        // Lấy tâm giữa màn hình
        int cx = rootLayout.getWidth() / 2;
        int cy = rootLayout.getHeight() / 2;

        // Bán kính đủ để phủ toàn bộ màn hình
        float finalRadius = (float) Math.hypot(rootLayout.getWidth(), rootLayout.getHeight());

//        // Circular reveal
//        Animator anim = ViewAnimationUtils.createCircularReveal(revealCircle, cx, cy, 0f, finalRadius);
//        anim.setDuration(3000); // 1.5 giây → lan ra từ từ
//        anim.setInterpolator(new AccelerateInterpolator()); // bắt đầu nhanh rồi chậm dần
////        anim.setInterpolator(new LinearInterpolator());

        Animator anim = ViewAnimationUtils.createCircularReveal(
                revealCircle, cx, cy, 0f, finalRadius);

        anim.setDuration(1800); // 1.8 giây
        anim.setInterpolator(new LinearInterpolator()); // tốc độ đều



        revealCircle.setVisibility(View.VISIBLE);

        anim.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);

                // Giữ lại màu nền
                rootLayout.setBackgroundColor(Color.parseColor("#FFD1DC"));
                revealCircle.setVisibility(View.GONE);

                // Sau khi nền đã phủ xong, mới hiện logo
                showLogoAndAppName();
            }
        });

        anim.start();
    }



    private void showLogoAndAppName() {
        logo.setVisibility(View.VISIBLE);
        ObjectAnimator logoFadeIn = ObjectAnimator.ofFloat(logo, "alpha", 0f, 1f);
        logoFadeIn.setDuration(500);

        logoFadeIn.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                // When logo is visible, move it and show the app name and slogan
                moveLogoAndShowText();
            }
        });
        logoFadeIn.start();
    }

    private void moveLogoAndShowText() {
        // Move logo to the left by 150 pixels (adjust as needed)
        float moveDistance = rootLayout.getWidth() / 4f; // 1/4 chiều ngang
        ObjectAnimator moveLogo = ObjectAnimator.ofFloat(logo, "translationX", 0f, -moveDistance);

//        ObjectAnimator moveLogo = ObjectAnimator.ofFloat(logo, "translationX", 0f, -150f);

        moveLogo.setDuration(700);

        appName.setVisibility(View.VISIBLE);
        ObjectAnimator showAppName = ObjectAnimator.ofFloat(appName, "alpha", 0f, 1f);
        showAppName.setDuration(700);

        AnimatorSet togetherSet = new AnimatorSet();
        togetherSet.playTogether(moveLogo, showAppName);

        togetherSet.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                slogan.setVisibility(View.VISIBLE);
                ObjectAnimator showSlogan = ObjectAnimator.ofFloat(slogan, "alpha", 0f, 1f);
                showSlogan.setDuration(500);

                showSlogan.addListener(new AnimatorListenerAdapter() {
                    @Override
                    public void onAnimationEnd(Animator animation) {
                        super.onAnimationEnd(animation);
                        // Đợi 1.5s rồi move logo lên góc trái
                        new Handler().postDelayed(() -> moveLogoToTopLeft(), 1500);
                    }
                });
                showSlogan.start();
            }
        });
        togetherSet.start();
    }

    private void moveLogoToTopLeft() {
        float targetX = - (rootLayout.getWidth() / 2f) + (logo.getWidth() / 2f);
        float targetY = - (rootLayout.getHeight() / 2f) + (logo.getHeight() / 2f);

        ObjectAnimator moveX = ObjectAnimator.ofFloat(logo, "translationX", logo.getTranslationX(), targetX);
        ObjectAnimator moveY = ObjectAnimator.ofFloat(logo, "translationY", logo.getTranslationY(), targetY);

        AnimatorSet moveSet = new AnimatorSet();
        moveSet.setDuration(800);
        moveSet.playTogether(moveX, moveY);

        moveSet.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                Intent intent = new Intent(MainActivity.this, LoginActivity.class);
                startActivity(intent);
                finish();
            }
        });
        moveSet.start();
    }

}
