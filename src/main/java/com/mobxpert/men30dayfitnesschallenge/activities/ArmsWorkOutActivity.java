package com.mobxpert.men30dayfitnesschallenge.activities;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.provider.Settings;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;
import com.mobxpert.men30dayfitnesschallenge.R;
import com.mobxpert.men30dayfitnesschallenge.activities.armsWorkout.BicepsActivity;
import com.mobxpert.men30dayfitnesschallenge.activities.armsWorkout.ForearmActivity;
import com.mobxpert.men30dayfitnesschallenge.activities.armsWorkout.TricepsActivity;
import com.mobxpert.men30dayfitnesschallenge.utils.SharedPrefUtility;
import com.mobxpert.men30dayfitnesschallenge.utils.Utility;
import com.unity3d.ads.IUnityAdsListener;
import com.unity3d.ads.UnityAds;
import com.unity3d.ads.metadata.MediationMetaData;
import com.unity3d.ads.metadata.MetaData;
import com.unity3d.ads.metadata.PlayerMetaData;
import com.unity3d.services.core.log.DeviceLog;
import com.unity3d.services.core.misc.Utilities;

public class ArmsWorkOutActivity extends AppCompatActivity {
    CardView biceps, triceps, forearms;
    AdView mAdView;
    ProgressBar bicepsProgress, tricepsProgress, forearmsProgress;
    TextView bicepsProgressText, tricepsTextProgress, forearmTextProgress;
    public static int totalSumOfResults = 0;
    final private String gameId = "3094662";
    ImageView menu_back_button, menu_image;
    UnityAdsListener unityAdsListener;//= new UnityAdsListener();
    private static int ordinal = 1;
    private String interstitialPlacementId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_arms_work_out);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        unityAdsListener = new UnityAdsListener();
        initializeInterstitialAd();
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        bicepsProgress = (ProgressBar) findViewById(R.id.bicepsProgress);
        tricepsProgress = findViewById(R.id.tricepsProgress);
        forearmsProgress = findViewById(R.id.forearmsProgress);
        menu_image = findViewById(R.id.menu_image);
        menu_back_button = findViewById(R.id.menu_back_button);
        menu_back_button.hasOverlappingRendering();
        menu_back_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        tricepsTextProgress = findViewById(R.id.textTricepsProgress);
        forearmTextProgress = findViewById(R.id.textForearmsProgress);
        bicepsProgressText = findViewById(R.id.textBicepsProgress);
        bicepsProgress.setMax(100);
        tricepsProgress.setMax(100);
        forearmsProgress.setMax(100);

        //    showUnityAd();

        if (SharedPrefUtility.getInstance(this).getIntValue("BicepsArm") > 0) {
            bicepsProgressText.setText(SharedPrefUtility.getInstance(this).getIntValue("BicepsArm") + " %");
            bicepsProgress.setProgress(SharedPrefUtility.getInstance(this).getIntValue("BicepsArm"));
        } else if (BicepsActivity.resultWatchVideo >= 0) {
            bicepsProgressText.setText(BicepsActivity.resultWatchVideo + " %");
            bicepsProgress.setProgress(BicepsActivity.resultWatchVideo);

        } else {
            bicepsProgressText.setText("0%");
            bicepsProgress.setProgress(0);
        }


        if (SharedPrefUtility.getInstance(this).getIntValue("") > 0) {
            tricepsTextProgress.setText(SharedPrefUtility.getInstance(this).getIntValue("Triceps") + " %");
            tricepsProgress.setProgress(SharedPrefUtility.getInstance(this).getIntValue("Triceps"));
        } else if (TricepsActivity.resultWatchVideo >= 0) {
            tricepsTextProgress.setText(TricepsActivity.resultWatchVideo + " %");
            tricepsProgress.setProgress(TricepsActivity.resultWatchVideo);

        } else {
            tricepsTextProgress.setText("0%");
            tricepsProgress.setProgress(0);
        }


        if (SharedPrefUtility.getInstance(this).getIntValue("Forearms") > 0) {
            forearmTextProgress.setText(SharedPrefUtility.getInstance(this).getIntValue("Forearms") + " %");
            forearmsProgress.setProgress(SharedPrefUtility.getInstance(this).getIntValue("Forearms"));
        } else if (ForearmActivity.resultWatchVideo >= 0) {
            forearmTextProgress.setText(ForearmActivity.resultWatchVideo + " %");
            forearmsProgress.setProgress(ForearmActivity.resultWatchVideo);

        } else {
            forearmTextProgress.setText("0%");
            forearmsProgress.setProgress(0);
        }

        mAdView = findViewById(R.id.adView);
        String id = Settings.Secure.getString(getContentResolver(), Settings.Secure.ANDROID_ID);
        //MobileAds.initialize(this, getString(R.string.bannerappid));
        MobileAds.initialize(this, getString(R.string.bannerappid));
        mAdView = findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder()
                .addTestDevice(id)
                .build();
        adRequest.isTestDevice(this);
        boolean istestdeviice = adRequest.isTestDevice(this);
        mAdView.loadAd(adRequest);
        boolean shown = mAdView.isShown();

        biceps = findViewById(R.id.biceps);
        triceps = findViewById(R.id.triceps);
        forearms = findViewById(R.id.forearms);

        biceps.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PlayerMetaData playerMetaData = new PlayerMetaData(ArmsWorkOutActivity.this);
                playerMetaData.setServerId("rikshot");
                playerMetaData.commit();

                MediationMetaData ordinalMetaData = new MediationMetaData(ArmsWorkOutActivity.this);
                ordinalMetaData.setOrdinal(ordinal++);
                ordinalMetaData.commit();

                UnityAds.show(ArmsWorkOutActivity.this, interstitialPlacementId);
                Utility.launchActivity(ArmsWorkOutActivity.this, BicepsActivity.class, false, null);
            }
        });
        triceps.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PlayerMetaData playerMetaData = new PlayerMetaData(ArmsWorkOutActivity.this);
                playerMetaData.setServerId("rikshot");
                playerMetaData.commit();

                MediationMetaData ordinalMetaData = new MediationMetaData(ArmsWorkOutActivity.this);
                ordinalMetaData.setOrdinal(ordinal++);
                ordinalMetaData.commit();

                UnityAds.show(ArmsWorkOutActivity.this, interstitialPlacementId);
                Utility.launchActivity(ArmsWorkOutActivity.this, TricepsActivity.class, false, null);

            }
        });
        forearms.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PlayerMetaData playerMetaData = new PlayerMetaData(ArmsWorkOutActivity.this);
                playerMetaData.setServerId("rikshot");
                playerMetaData.commit();

                MediationMetaData ordinalMetaData = new MediationMetaData(ArmsWorkOutActivity.this);
                ordinalMetaData.setOrdinal(ordinal++);
                ordinalMetaData.commit();

                UnityAds.show(ArmsWorkOutActivity.this, interstitialPlacementId);
                Utility.launchActivity(ArmsWorkOutActivity.this, ForearmActivity.class, false, null);

            }
        });
        totalSumOfResults = getPercentageOfAllWorkout();

    }

    @Override
    protected void onResume() {
        if (SharedPrefUtility.getInstance(this).getIntValue("BicepsArm") > 0) {
            bicepsProgressText.setText(SharedPrefUtility.getInstance(this).getIntValue("BicepsArm") + " %");
            bicepsProgress.setProgress(SharedPrefUtility.getInstance(this).getIntValue("BicepsArm"));
        } else if (BicepsActivity.resultWatchVideo >= 0) {
            bicepsProgressText.setText(BicepsActivity.resultWatchVideo + " %");
            bicepsProgress.setProgress(BicepsActivity.resultWatchVideo);

        } else {
            bicepsProgressText.setText("0%");
            bicepsProgress.setProgress(0);
        }


        if (SharedPrefUtility.getInstance(this).getIntValue("") > 0) {
            tricepsTextProgress.setText(SharedPrefUtility.getInstance(this).getIntValue("Triceps") + " %");
            tricepsProgress.setProgress(SharedPrefUtility.getInstance(this).getIntValue("Triceps"));
        } else if (TricepsActivity.resultWatchVideo >= 0) {
            tricepsTextProgress.setText(TricepsActivity.resultWatchVideo + " %");
            tricepsProgress.setProgress(TricepsActivity.resultWatchVideo);

        } else {
            tricepsTextProgress.setText("0%");
            tricepsProgress.setProgress(0);
        }


        if (SharedPrefUtility.getInstance(this).getIntValue("Forearms") > 0) {
            forearmTextProgress.setText(SharedPrefUtility.getInstance(this).getIntValue("Forearms") + " %");
            forearmsProgress.setProgress(SharedPrefUtility.getInstance(this).getIntValue("Forearms"));
        } else if (ForearmActivity.resultWatchVideo >= 0) {
            forearmTextProgress.setText(ForearmActivity.resultWatchVideo + " %");
            forearmsProgress.setProgress(ForearmActivity.resultWatchVideo);

        } else {
            forearmTextProgress.setText("0%");
            forearmsProgress.setProgress(0);
        }

        super.onResume();
    }

    @Override
    public void onBackPressed() {
       // MainActivity.mainscreenloadtime++;

        super.onBackPressed();
    }

    @Override
    protected void onPause() {
        totalSumOfResults = getPercentageOfAllWorkout();

        super.onPause();
    }

    public int getPercentageOfAllWorkout() {

        int sumofAllResults = BicepsActivity.resultWatchVideo + TricepsActivity.resultWatchVideo + ForearmActivity.resultWatchVideo;

        int result = ((sumofAllResults * 100) / 300);
        SharedPrefUtility.getInstance(this).savePrefrences("ArmsWorkoutP", result);
        return result;

    }

    private void initializeInterstitialAd() {
        UnityAds.initialize(this, gameId, unityAdsListener, true);
        // store entered gameid in app settings
        SharedPreferences preferences = getSharedPreferences("Settings", MODE_PRIVATE);
        SharedPreferences.Editor preferencesEdit = preferences.edit();
        preferencesEdit.putString("gameId", gameId);
        preferencesEdit.commit();
        if (UnityAds.isInitialized()) {

            unityInterstitialAd();
        }
    }

    private void unityInterstitialAd() {
        UnityAds.setListener(unityAdsListener);
        UnityAds.setDebugMode(true);

        MediationMetaData mediationMetaData = new MediationMetaData(this);
        mediationMetaData.setName("mediationPartner");
        mediationMetaData.setVersion("v12345");
        mediationMetaData.commit();

        MetaData debugMetaData = new MetaData(this);
        debugMetaData.set("test.debugOverlayEnabled", true);
        debugMetaData.commit();
    }

    private class UnityAdsListener implements IUnityAdsListener {

        @Override
        public void onUnityAdsReady(final String zoneId) {
            /*TextView statusText = (TextView) findViewById(R.id.unityads_example_statustext);
            statusText.setText("");*/

            DeviceLog.debug("onUnityAdsReady: " + zoneId);
            Utilities.runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    // look for various default placement ids over time
                    switch (zoneId) {
                        case "video":
                        case "defaultZone":
                        case "defaultVideoAndPictureZone":
                            interstitialPlacementId = zoneId;
                            // enableButton((Button) findViewById(R.id.unityads_example_interstitial_button));
                            break;

                        case "rewardedVideo":
                        case "rewardedVideoZone":

                    }
                }
            });

            //  toast("Ready", zoneId);
        }

        @Override
        public void onUnityAdsStart(String zoneId) {
            DeviceLog.debug("onUnityAdsStart: " + zoneId);
            // toast("Start", zoneId);
        }

        @Override
        public void onUnityAdsFinish(String zoneId, UnityAds.FinishState result) {
            DeviceLog.debug("onUnityAdsFinish: " + zoneId + " - " + result);
            // toast("Finish", zoneId + " " + result);
        }

        @Override
        public void onUnityAdsError(UnityAds.UnityAdsError error, String message) {
            DeviceLog.debug("onUnityAdsError: " + error + " - " + message);
            //    toast("Error", error + " " + message);

           /* TextView statusText = (TextView) findViewById(R.id.unityads_example_statustext);
            statusText.setText(error + " - " + message);*/
        }

   /*     private void toast(String callback, String msg) {
            Toast.makeText(getApplicationContext(), callback + ": " + msg, Toast.LENGTH_SHORT).show();
        }*/
    }

}
