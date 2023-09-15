package com.example.imagegame;

import android.animation.AnimatorInflater;
import android.animation.AnimatorSet;
import android.content.Context;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import java.util.Random;

public class CardFlipUtil {
    public static void setupCardFlip(final View dialogView, final Context context) {
        final boolean[] isFront = {false};
        final ImageView cardFront = dialogView.findViewById(R.id.card_front);
        final ImageView cardBack = dialogView.findViewById(R.id.card_back);
        cardBack.setImageResource(getRandomImage());
        Button flipBtn = dialogView.findViewById(R.id.flip_btn);

        float scale = context.getResources().getDisplayMetrics().density;
        cardFront.setCameraDistance(8000 * scale);
        cardBack.setCameraDistance(8000 * scale);

        final AnimatorSet frontAnim = (AnimatorSet) AnimatorInflater.loadAnimator(context, R.anim.front_animator);
        final AnimatorSet backAnim = (AnimatorSet) AnimatorInflater.loadAnimator(context, R.anim.back_animator);

        flipBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isFront[0]) {
                    frontAnim.setTarget(cardFront);
                    backAnim.setTarget(cardBack);
                    frontAnim.start();
                    backAnim.start();
                    isFront[0] = false;
                } else {
                    frontAnim.setTarget(cardBack);
                    backAnim.setTarget(cardFront);
                    frontAnim.start();
                    backAnim.start();
                    isFront[0] = true;
                }
            }
        });
    }

    private static int getRandomImage() {
        int[] imageResources = {R.drawable.card_1, R.drawable.card_2, R.drawable.card_3, R.drawable.card_4, R.drawable.card_5, R.drawable.card_6};
        Random random = new Random();
        return imageResources[random.nextInt(imageResources.length)];
    }
}
