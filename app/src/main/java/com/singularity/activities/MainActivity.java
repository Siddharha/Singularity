package com.singularity.activities;

import android.animation.Animator;
import android.app.ActivityOptions;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Build;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.transition.Explode;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.singularity.R;

import in.creativelizard.androidpermission.CreativePermission;

public class MainActivity extends AppCompatActivity {

    private ImageView imgLogo;
    private ConstraintLayout clMain;
    private CardView cvUser,cvPass;
    private FloatingActionButton fbNext;
    private static final int PERMISSION_ALL = 100;
    private CreativePermission myPermission;
    private LinearLayout llLoginDesc, llSignUp;
    private TextView tvSignUp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityTransition();
        setContentView(R.layout.activity_main);
        initialize();
        checkPermission();
        onActionPerform();
    }

    private void activityTransition() {
        getWindow().requestFeature(Window.FEATURE_CONTENT_TRANSITIONS);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().setExitTransition(new Explode());
        }
    }

    private void checkPermission() {
        if (!myPermission.hasPermissions()) {
            myPermission.reqPermisions();
            }else {
            animateLogo();
        }
    }

    private void onActionPerform() {

        fbNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getBaseContext(),MapActivity.class);

                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                                ActivityOptions options = ActivityOptions
                                        .makeSceneTransitionAnimation(MainActivity.this, fbNext, "fbNext");
                        // start the new activity
                        startActivity(intent, options.toBundle());
                    }else {
                        startActivity(intent);
                    }
            }
        });

        llSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getBaseContext(),SignUpActivity.class));
            }
        });

    }

    private void animateLogo() {

        fbNext.setScaleX(0);
        fbNext.setScaleY(0);
        fbNext.setRotation(-90);
        imgLogo.setVisibility(View.VISIBLE);
        cvUser.setAlpha(0);
        cvPass.setAlpha(0);
        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                imgLogo.animate().scaleXBy(8).scaleYBy(8).
                        setDuration(500).setListener(new Animator.AnimatorListener() {
                    @Override
                    public void onAnimationStart(Animator animator) {

                    }

                    @Override
                    public void onAnimationEnd(Animator animator) {
                        llLoginDesc.setVisibility(View.VISIBLE);
                        llLoginDesc.animate().alpha(1).translationY(70).setDuration(1000);
                        imgLogo.clearAnimation();
                        clMain.setBackgroundColor(Color.WHITE);
                        imgLogo.setVisibility(View.GONE);

                        cvUser.animate().alpha(1);
                        cvPass.animate().alpha(1);
                        llSignUp.animate().alpha(1);
                        fbNext.animate().scaleX(1).scaleY(1).rotation(0).setDuration(1000);

                    }

                    @Override
                    public void onAnimationCancel(Animator animator) {

                    }

                    @Override
                    public void onAnimationRepeat(Animator animator) {

                    }
                });
            }
        }, 1000);

    }

    private void initialize() {
        llSignUp = findViewById(R.id.llSignUp);
        llSignUp.setAlpha(0);
        myPermission = new CreativePermission(this,PERMISSION_ALL);
        tvSignUp = findViewById(R.id.tvSignUp);
        tvSignUp.setPaintFlags(tvSignUp.getPaintFlags() |   Paint.UNDERLINE_TEXT_FLAG);
        imgLogo = findViewById(R.id.imgLogo);
        clMain = findViewById(R.id.clMain);
        cvUser = findViewById(R.id.cvUser);
        cvPass = findViewById(R.id.cvPass);
        fbNext = findViewById(R.id.fbNext);
        llLoginDesc = findViewById(R.id.llLoginDesc);
        llLoginDesc.setVisibility(View.GONE);
        llLoginDesc.setAlpha(0);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if(requestCode == PERMISSION_ALL) {
            animateLogo();

        }
    }
}
