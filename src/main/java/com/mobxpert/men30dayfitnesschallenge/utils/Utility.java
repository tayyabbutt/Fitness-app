package com.mobxpert.men30dayfitnesschallenge.utils;

import android.animation.Animator;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Point;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Display;
import android.view.View;
import android.view.ViewPropertyAnimator;
import android.view.WindowManager;

public class Utility {
    public static final int DIALOG_WIDTH_MARGIN = 50;

    public static void launchActivity(Context mContext, Class<?> mClass, boolean shouldFinish, Bundle bundle) {
        Intent intent = new Intent(mContext, mClass);
        if (bundle != null) {
            intent.putExtras(bundle);
        }
        mContext.startActivity(intent);
        if (shouldFinish) {
            ((Activity) mContext).finish();
        }
    }

    public static boolean isOnline(Context context) {
        if (context != null) {
            ConnectivityManager connMgr = (ConnectivityManager)
                    context.getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo networkInfo = connMgr != null ? connMgr.getActiveNetworkInfo() : null;
            return (networkInfo != null && networkInfo.isConnected());
        } else return false;
    }


    public static String getFormattedTime(int durationInMillis) {
        long minute = (durationInMillis / (1000 * 60)) % 60;
        long hour = (durationInMillis / (1000 * 60 * 60)) % 24;
        long seconds = (durationInMillis / (1000)) % 60;

        if (hour > 0) {
            return String.format("%2d", hour) + ":" + String.format("%1d", minute);
        } else {
            return String.format("%2d", minute) + ":" + String.format("%1d", seconds);
        }
    }

    /***
     * Method will return screen size of device
     *
     * @param mContext
     * @return
     */
    public static Point getScreenSize(Context mContext) {
        Display display = ((Activity) mContext).getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        int width = size.x;
        int height = size.y;

        Log.e("screen size", width + " x " + height);
        return size;
    }

    public static void animateY(final Context context, final View view, int projectedHeight, boolean topToBottom, boolean show) {
        if (view == null)
            return;
        if (show) {
            ViewPropertyAnimator animator = view.animate();
            if (topToBottom) {
                animator.translationY(0);
            } else {
                animator.translationY(0);
            }
            view.animate().alpha(1.0f).setDuration(300).setListener(new Animator.AnimatorListener() {
                @Override
                public void onAnimationStart(Animator animation) {
                    //view.setVisibility(View.VISIBLE);
                }

                @Override
                public void onAnimationEnd(Animator animation) {
                }

                @Override
                public void onAnimationCancel(Animator animation) {
                }

                @Override
                public void onAnimationRepeat(Animator animation) {
                }
            });

        } else {
            ViewPropertyAnimator animator = view.animate();
            if (topToBottom) {
                animator.translationY(-view.getHeight());
            } else {
                animator.translationY((projectedHeight + view.getHeight()));
            }
            view.animate().alpha(0.0f).setDuration(300).setListener(new Animator.AnimatorListener() {
                @Override
                public void onAnimationStart(Animator animation) {
                }

                @Override
                public void onAnimationEnd(Animator animation) {
                    //view.setVisibility(View.GONE);
                }

                @Override
                public void onAnimationCancel(Animator animation) {
                }

                @Override
                public void onAnimationRepeat(Animator animation) {
                }
            });
        }
    }

    public static void animateYBannerOverlayAd(final View view, int projectedHeight) {
        ViewPropertyAnimator animator = view.animate();
        Log.e("height", "" + projectedHeight);
        animator.translationY(projectedHeight);
        view.animate().alpha(1.0f).setDuration(300).setListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {
            }

            @Override
            public void onAnimationEnd(Animator animation) {
                //view.setVisibility(View.GONE);
            }

            @Override
            public void onAnimationCancel(Animator animation) {
            }

            @Override
            public void onAnimationRepeat(Animator animation) {
            }
        });
    }

    public static float convertDpToPixel(float dp, Context context) {
        Resources resources = context.getResources();
        DisplayMetrics metrics = resources.getDisplayMetrics();
        return dp * ((float) metrics.densityDpi / DisplayMetrics.DENSITY_DEFAULT);
    }

    public static float convertPixelsToDp(float px, Context context) {
        Resources resources = context.getResources();
        DisplayMetrics metrics = resources.getDisplayMetrics();
        return px / ((float) metrics.densityDpi / DisplayMetrics.DENSITY_DEFAULT);
    }

    public static void hideSystemUI(View mDecorView) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
            mDecorView.setSystemUiVisibility(
                    View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                            | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                            | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                            | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                            | View.SYSTEM_UI_FLAG_FULLSCREEN
                            | View.SYSTEM_UI_FLAG_IMMERSIVE);
        }
    }

    public static int getScreenWidth(Context context) {
        WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        Display display = wm.getDefaultDisplay();
        DisplayMetrics metrics = new DisplayMetrics();
        display.getMetrics(metrics);
        return metrics.widthPixels;
    }

}
