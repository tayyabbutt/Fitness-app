package com.mobxpert.men30dayfitnesschallenge.utils;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.LinearLayout;

import com.facebook.ads.AdSize;
import com.facebook.ads.AdView;
import com.mobxpert.men30dayfitnesschallenge.R;
import com.mobxpert.men30dayfitnesschallenge.interfaces.OnNetworkDialogClickListener;

public class MyDialog {

    public static void showNativeAdDialogOnExit(Context context, final OnNetworkDialogClickListener listener) {

        final Dialog dialog = new Dialog(context);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.setCancelable(true);
        dialog.setContentView(R.layout.dialog);
        /*   final AdView mAdView = (AdView) dialog.findViewById(R.id.adView);*/
        final AdView adView = new AdView(context, context.getString(R.string.debugFbPlacementId), AdSize.RECTANGLE_HEIGHT_250);

        // Find the Ad Container
        final LinearLayout adContainer = (LinearLayout) dialog.findViewById(R.id.banner_container);

        // Add the ad view to your activity layout
        adContainer.addView(adView);

        // Request an ad
        adView.loadAd();
      /*  String id = Settings.Secure.getString(context.getContentResolver(), Settings.Secure.ANDROID_ID);
        //MobileAds.initialize(this, getString(R.string.bannerappid));
        MobileAds.initialize(context, context.getString(R.string.bannerappid));
        final AdRequest adRequest = new AdRequest.Builder()
                .addTestDevice(id)
                .build();
        adRequest.isTestDevice(context);
        boolean istestdeviice = adRequest.isTestDevice(context);
        mAdView.loadAd(adRequest);
        boolean shown = mAdView.isShown();*/
        WindowManager.LayoutParams lp = new WindowManager.LayoutParams();
        Window window = dialog.getWindow();
        lp.copyFrom(window.getAttributes());
        lp.width = (int) (Utility.getScreenWidth(context) - Utility.convertDpToPixel(Utility.DIALOG_WIDTH_MARGIN, context));
        lp.height = WindowManager.LayoutParams.WRAP_CONTENT;
        window.setAttributes(lp);
        Button okBtn = dialog.findViewById(R.id.exit_button);
        Button rateUsBtn = dialog.findViewById(R.id.rateus_btn);
        rateUsBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (listener != null && adView.isShown()) {
                    listener.onRateUsButtonClick();
                }

                if (adView.isShown()) {

                    dialog.dismiss();
                    adView.destroy();
                }
            }
        });
        okBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (listener != null && adView.isShown()) {
                    listener.onExitButtonClick(true);
                    dialog.dismiss();
                }

            }
        });
        dialog.show();
    }
}
