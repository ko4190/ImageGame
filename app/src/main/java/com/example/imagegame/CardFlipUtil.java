package com.example.imagegame;

import android.animation.AnimatorInflater;
import android.animation.AnimatorSet;
import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Random;

public class CardFlipUtil {
    public static void setupCardFlip(final View dialogView, final Context context) {
        final boolean[] isFront = {false};
        final ImageView cardFront = dialogView.findViewById(R.id.card_front);
        final ImageView cardBack = dialogView.findViewById(R.id.card_back);
        final TextView cardText = dialogView.findViewById(R.id.card_text);

        cardFront.setImageResource(getRandomImage());
        cardText.setText(getRandomText());
        Button flipBtn = dialogView.findViewById(R.id.flip_btn);

        float scale = context.getResources().getDisplayMetrics().density;
        cardFront.setCameraDistance(8000 * scale);
        cardBack.setCameraDistance(8000 * scale);

        final AnimatorSet frontAnim = (AnimatorSet) AnimatorInflater.loadAnimator(context, R.anim.front_animator);
        final AnimatorSet backAnim = (AnimatorSet) AnimatorInflater.loadAnimator(context, R.anim.back_animator);
        final Handler handler = new Handler(Looper.getMainLooper());
        flipBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isFront[0]) {
                    frontAnim.setTarget(cardFront);
                    backAnim.setTarget(cardBack);
                    frontAnim.start();
                    backAnim.start();
                    isFront[0] = false;
                    cardText.setVisibility(View.INVISIBLE);

                } else {
                    frontAnim.setTarget(cardBack);
                    backAnim.setTarget(cardFront);
                    frontAnim.start();
                    backAnim.start();
                    isFront[0] = true;
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            cardText.setVisibility(View.VISIBLE);
                        }
                    }, 850);
                }
            }
        });
    }

    private static int getRandomImage() {
        int[] imageResources = {R.drawable.card_1, R.drawable.card_2, R.drawable.card_3, R.drawable.card_4, R.drawable.card_5, R.drawable.card_6};
        Random random = new Random();
        return imageResources[random.nextInt(imageResources.length)];
    }

    private static String getRandomText() {
        String[] textResources = {"Text 1", "Text 2", "Text 3"};
        Random random = new Random();
        return textResources[random.nextInt(textResources.length)];
    }
}
