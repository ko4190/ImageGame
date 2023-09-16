package com.example.imagegame;

import android.animation.AnimatorInflater;
import android.animation.AnimatorSet;
import android.content.Context;
import android.graphics.Color;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.core.content.ContextCompat;

import java.util.Random;

public class CardFlipUtil {
    public static int color;
    public static int[] flag= new int[22];
    public static void setupCardFlip(final View dialogView, final Context context) {
        final boolean[] isFront = {false};


        final ImageView cardFront = dialogView.findViewById(R.id.card_front);
        final ImageView cardBack = dialogView.findViewById(R.id.card_back);
        final TextView cardText = dialogView.findViewById(R.id.card_text);

        cardFront.setImageResource(getRandomImage());
        cardText.setTextColor(getTextColor(context,color));
        cardText.setText(getRandomImageText(context));
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
        color =random.nextInt(imageResources.length);
        return imageResources[color];
    }

    private static String getRandomImageText(Context context) {
        int num;
        int sum=0;
        for (int i=0;i<22;i++)
            sum+=flag[i];
        if (sum ==22)
                return "모든 카드를 확인하셨습니다.";

        while (true) {
            Random random = new Random();
            num = random.nextInt(22);
            if (flag[num] == 0) {
                flag[num] = 1;
                break;
            }
        }
        String resourceName = "Q" + num;
        int resourceId = context.getResources().getIdentifier(resourceName, "string", context.getPackageName());
        flag[num] = 1;
        return context.getString(resourceId);
    }


    private static int getTextColor(Context context, int i) {

        if (color == 0) return ContextCompat.getColor(context, R.color.card_1_color);
        else if (color == 1) return ContextCompat.getColor(context, R.color.card_2_color);
        else if (color == 2) return ContextCompat.getColor(context, R.color.card_3_color);
        else if (color == 3) return ContextCompat.getColor(context, R.color.card_4_color);
        else if (color == 4) return ContextCompat.getColor(context, R.color.card_5_color);
        else  return ContextCompat.getColor(context, R.color.card_6_color);


    }

}
