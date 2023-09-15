package com.example.imagegame;

import android.animation.AnimatorInflater;
import android.animation.AnimatorSet;
import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Random;

public class CardOpen extends AppCompatActivity {
    private boolean isFront = true;

    @SuppressLint("ResourceType")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.card_open);

        final ImageView cardFront = findViewById(R.id.card_front);
        final ImageView cardBack = findViewById(R.id.card_back);
        cardBack.setImageResource(getRandomImage());
        Button flipBtn = findViewById(R.id.flip_btn);

        // Get screen density to scale camera distance
        float scale = getResources().getDisplayMetrics().density;

        cardFront.setCameraDistance(8000 * scale);
        cardBack.setCameraDistance(8000 * scale);

        // Initialize the animators
        final AnimatorSet frontAnim = (AnimatorSet) AnimatorInflater.loadAnimator(getApplicationContext(), R.anim.front_animator);
        final AnimatorSet backAnim = (AnimatorSet) AnimatorInflater.loadAnimator(getApplicationContext(), R.anim.back_animator);

        flipBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isFront) {
                    frontAnim.setTarget(cardFront);
                    backAnim.setTarget(cardBack);
                    frontAnim.start();
                    backAnim.start();
                    isFront = false;
                } else {
                    frontAnim.setTarget(cardBack);
                    backAnim.setTarget(cardFront);
                    frontAnim.start();
                    backAnim.start();
                    isFront = true;
                }
            }
        });
    }
    private int getRandomImage() {
        int[] imageResources = {R.drawable.card_1, R.drawable.card_2, R.drawable.card_3};
        Random random = new Random();
        return imageResources[random.nextInt(imageResources.length)];
    }
}