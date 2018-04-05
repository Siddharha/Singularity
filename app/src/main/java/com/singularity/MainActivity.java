package com.singularity;

import android.animation.Animator;
import android.content.Intent;
import android.graphics.Color;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.View;
import android.widget.ImageView;

import in.creativelizard.androidpermission.CreativePermission;

public class MainActivity extends AppCompatActivity {

    private ImageView imgLogo;
    private ConstraintLayout clMain;
    private CardView cvUser,cvPass;
    private FloatingActionButton fbNext;
    private static final int PERMISSION_ALL = 100;
    private CreativePermission myPermission;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initialize();
        checkPermission();
        onActionPerform();
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
                startActivity(new Intent(getBaseContext(),MapActivity.class));
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

                        imgLogo.clearAnimation();
                        clMain.setBackgroundColor(Color.WHITE);
                        imgLogo.setVisibility(View.GONE);

                        cvUser.animate().alpha(1);
                        cvPass.animate().alpha(1);
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
        myPermission = new CreativePermission(this,PERMISSION_ALL);
        imgLogo = findViewById(R.id.imgLogo);
        clMain = findViewById(R.id.clMain);
        cvUser = findViewById(R.id.cvUser);
        cvPass = findViewById(R.id.cvPass);
        fbNext = findViewById(R.id.fbNext);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if(requestCode == PERMISSION_ALL) {
            animateLogo();

        }
    }
}
