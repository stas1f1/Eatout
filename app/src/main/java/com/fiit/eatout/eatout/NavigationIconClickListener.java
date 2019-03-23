package com.fiit.eatout.eatout;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.annotation.Nullable;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.animation.Interpolator;
import android.widget.ImageView;

/**
 * {@link View.OnClickListener} used to translate the product grid sheet downward on
 * the Y-axis when the navigation icon in the toolbar is pressed.
 */
public class NavigationIconClickListener implements View.OnClickListener {

    private final AnimatorSet animatorSet = new AnimatorSet();
    private Context context;
    private View toolbar;
    private View sheet;
    private Interpolator interpolator;
    private int height;
    private int width;
    private boolean backdropShown = false;
    private Drawable openIcon;
    private Drawable closeIcon;

    NavigationIconClickListener(Context context, View toolbar,  View sheet) {
        this(context, toolbar, sheet, null);
    }

    NavigationIconClickListener(Context context, View toolbar, View sheet, @Nullable Interpolator interpolator) {
        this(context, toolbar, sheet, interpolator, null, null);
    }

    NavigationIconClickListener(
            Context context, View toolbar, View sheet, @Nullable Interpolator interpolator,
            @Nullable Drawable openIcon, @Nullable Drawable closeIcon) {
        this.context = context;
        this.toolbar = toolbar;
        this.sheet = sheet;
        this.interpolator = interpolator;
        this.openIcon = openIcon;
        this.closeIcon = closeIcon;

        DisplayMetrics displayMetrics = new DisplayMetrics();
        ((Activity) context).getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        height = displayMetrics.heightPixels;
        width = displayMetrics.widthPixels;
    }

    @Override
    public void onClick(View view) {
        backdropShown = !backdropShown;

        // Cancel the existing animations
        animatorSet.removeAllListeners();
        animatorSet.end();
        animatorSet.cancel();

        updateIcon(view);

        final int translateX = width -
                context.getResources().getDimensionPixelSize(R.dimen.eout_product_grid_reveal_width);

        ObjectAnimator tbanimator = ObjectAnimator.ofFloat(toolbar, "translationX", backdropShown ? translateX : 0);
        ObjectAnimator animator = ObjectAnimator.ofFloat(sheet, "translationX", backdropShown ? translateX : 0);
        tbanimator.setDuration(500);
        animator.setDuration(500);
        if (interpolator != null) {
            tbanimator.setInterpolator(interpolator);
            animator.setInterpolator(interpolator);
        }
        animatorSet.play(tbanimator);
        animatorSet.play(animator);
        tbanimator.start();
        animator.start();
    }

    private void updateIcon(View view) {
        if (openIcon != null && closeIcon != null) {
            if (!(view instanceof ImageView)) {
                throw new IllegalArgumentException("updateIcon() must be called on an ImageView");
            }
            if (backdropShown) {
                ((ImageView) view).setImageDrawable(closeIcon);
            } else {
                ((ImageView) view).setImageDrawable(openIcon);
            }
        }
    }
}
