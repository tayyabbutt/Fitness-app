package com.mobxpert.men30dayfitnesschallenge.activities;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.provider.Settings;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;
import com.mobxpert.men30dayfitnesschallenge.R;
import com.mobxpert.men30dayfitnesschallenge.activities.chestWorkout.LowerChestActivity;
import com.mobxpert.men30dayfitnesschallenge.activities.chestWorkout.MiddleChestActivity;
import com.mobxpert.men30dayfitnesschallenge.activities.chestWorkout.UpperChestActivity;
import com.mobxpert.men30dayfitnesschallenge.utils.SharedPrefUtility;
import com.mobxpert.men30dayfitnesschallenge.utils.Utility;
import com.unity3d.ads.IUnityAdsListener;
import com.unity3d.ads.UnityAds;
import com.unity3d.ads.metadata.MediationMetaData;
import com.unity3d.ads.metadata.MetaData;
import com.unity3d.ads.metadata.PlayerMetaData;
import com.unity3d.services.core.log.DeviceLog;
import com.unity3d.services.core.misc.Utilities;

public class ChestWorkoutActivity extends AppCompatActivity {
    CardView uperchest, middlechest, lowerchest;
    AdView mAdView;
    ProgressBar lowerChestProgress, uperChestProgress, middleChestProgress;
    TextView lowerChestProgressText, uperChestTextProgress, middleChestTextProgress;
    public static int totalSumOfResults = 0;

    final private String gameId = "3094662";

    UnityAdsListener unityAdsListener;//= new UnityAdsListener();
    private static int ordinal = 1;
    private String interstitialPlacementId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chest_workout);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        lowerChestProgress = (ProgressBar) findViewById(R.id.lowerchestProgress);
        uperChestProgress = findViewById(R.id.uperchestProgress);
        unityAdsListener = new UnityAdsListener();
        initializeInterstitialAd();
        middleChestProgress = findViewById(R.id.middlechestProgress);
        lowerChestProgressText = findViewById(R.id.textLowerChestProgress);
        uperChestTextProgress = findViewById(R.id.textUperChestProgress);
        middleChestTextProgress = findViewById(R.id.textMiddleChestProgress);
        lowerChestProgress.setMax(100);
        uperChestProgress.setMax(100);
        middleChestProgress.setMax(100);


        if (SharedPrefUtility.getInstance(this).getIntValue("LowerChest") > 0) {
            lowerChestProgressText.setText(SharedPrefUtility.getInstance(this).getIntValue("LowerChest") + " %");
            lowerChestProgress.setProgress(SharedPrefUtility.getInstance(this).getIntValue("LowerChest"));
        } else if (LowerChestActivity.resultWatchVideo >= 0) {
            lowerChestProgressText.setText(LowerChestActivity.resultWatchVideo + " %");
            lowerChestProgress.setProgress(LowerChestActivity.resultWatchVideo);

        } else {
            lowerChestProgressText.setText("0%");
            lowerChestProgress.setProgress(0);
        }

        if (SharedPrefUtility.getInstance(this).getIntValue("UpperChest") > 0) {
            uperChestTextProgress.setText(SharedPrefUtility.getInstance(this).getIntValue("UpperChest") + " %");
            uperChestProgress.setProgress(SharedPrefUtility.getInstance(this).getIntValue("UpperChest"));
        } else if (UpperChestActivity.resultWatchVideo >= 0) {
            uperChestTextProgress.setText(UpperChestActivity.resultWatchVideo + " %");
            uperChestProgress.setProgress(UpperChestActivity.resultWatchVideo);

        } else {
            uperChestTextProgress.setText("0%");
            uperChestProgress.setProgress(0);
        }

        if (SharedPrefUtility.getInstance(this).getIntValue("MiddleChest") > 0) {
            middleChestTextProgress.setText(SharedPrefUtility.getInstance(this).getIntValue("MiddleChest") + " %");
            middleChestProgress.setProgress(SharedPrefUtility.getInstance(this).getIntValue("MiddleChest"));
        } else if (MiddleChestActivity.resultWatchVideo >= 0) {
            middleChestTextProgress.setText(MiddleChestActivity.resultWatchVideo + " %");
            middleChestProgress.setProgress(MiddleChestActivity.resultWatchVideo);

        } else {
            middleChestTextProgress.setText("0%");
            middleChestProgress.setProgress(0);
        }


        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
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
        uperchest = findViewById(R.id.uperchest);
        middlechest = findViewById(R.id.middlechest);
        lowerchest = findViewById(R.id.lowerchest);

        uperchest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PlayerMetaData playerMetaData = new PlayerMetaData(ChestWorkoutActivity.this);
                playerMetaData.setServerId("rikshot");
                playerMetaData.commit();

                MediationMetaData ordinalMetaData = new MediationMetaData(ChestWorkoutActivity.this);
                ordinalMetaData.setOrdinal(ordinal++);
                ordinalMetaData.commit();

                UnityAds.show(ChestWorkoutActivity.this, interstitialPlacementId);
                Utility.launchActivity(ChestWorkoutActivity.this, UpperChestActivity.class, false, null);
            }
        });
        middlechest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PlayerMetaData playerMetaData = new PlayerMetaData(ChestWorkoutActivity.this);
                playerMetaData.setServerId("rikshot");
                playerMetaData.commit();

                MediationMetaData ordinalMetaData = new MediationMetaData(ChestWorkoutActivity.this);
                ordinalMetaData.setOrdinal(ordinal++);
                ordinalMetaData.commit();

                UnityAds.show(ChestWorkoutActivity.this, interstitialPlacementId);
                Utility.launchActivity(ChestWorkoutActivity.this, MiddleChestActivity.class, false, null);

            }
        });
        lowerchest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PlayerMetaData playerMetaData = new PlayerMetaData(ChestWorkoutActivity.this);
                playerMetaData.setServerId("rikshot");
                playerMetaData.commit();

                MediationMetaData ordinalMetaData = new MediationMetaData(ChestWorkoutActivity.this);
                ordinalMetaData.setOrdinal(ordinal++);
                ordinalMetaData.commit();

                UnityAds.show(ChestWorkoutActivity.this, interstitialPlacementId);
                Utility.launchActivity(ChestWorkoutActivity.this, LowerChestActivity.class, false, null);

            }
        });
        totalSumOfResults = getPercentageOfAllWorkout();

    }

    @Override
    public void onBackPressed() {
       // MainActivity.mainscreenloadtime++;
        super.onBackPressed();
    }

    @Override
    protected void onResume() {
        if (LowerChestActivity.resultWatchVideo >= 0) {
            lowerChestProgressText.setText(LowerChestActivity.resultWatchVideo + " %");
            lowerChestProgress.setProgress(LowerChestActivity.resultWatchVideo);

        } else {
            lowerChestProgressText.setText("0%");
            lowerChestProgress.setProgress(0);
        }
        if (UpperChestActivity.resultWatchVideo >= 0) {
            uperChestTextProgress.setText(UpperChestActivity.resultWatchVideo + " %");
            uperChestProgress.setProgress(UpperChestActivity.resultWatchVideo);

        } else {
            uperChestTextProgress.setText("0%");
            uperChestProgress.setProgress(0);
        }
        if (MiddleChestActivity.resultWatchVideo >= 0) {
            middleChestTextProgress.setText(MiddleChestActivity.resultWatchVideo + " %");
            middleChestProgress.setProgress(MiddleChestActivity.resultWatchVideo);

        } else {
            middleChestTextProgress.setText("0%");
            middleChestProgress.setProgress(0);
        }
        super.onResume();
    }

    @Override
    protected void onPause() {
        totalSumOfResults = getPercentageOfAllWorkout();

        super.onPause();
    }

    public int getPercentageOfAllWorkout() {

        int sumofAllResults = LowerChestActivity.resultWatchVideo + UpperChestActivity.resultWatchVideo + MiddleChestActivity.resultWatchVideo;

        int result = ((sumofAllResults * 100) / 300);
        SharedPrefUtility.getInstance(this).savePrefrences("ChestWorkoutP", result);
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

           // toast("Ready", zoneId);
        }

        @Override
        public void onUnityAdsStart(String zoneId) {
            DeviceLog.debug("onUnityAdsStart: " + zoneId);
          //  toast("Start", zoneId);
        }

        @Override
        public void onUnityAdsFinish(String zoneId, UnityAds.FinishState result) {
            DeviceLog.debug("onUnityAdsFinish: " + zoneId + " - " + result);
        //    toast("Finish", zoneId + " " + result);
        }

        @Override
        public void onUnityAdsError(UnityAds.UnityAdsError error, String message) {
            DeviceLog.debug("onUnityAdsError: " + error + " - " + message);
         //   toast("Error", error + " " + message);

           /* TextView statusText = (TextView) findViewById(R.id.unityads_example_statustext);
            statusText.setText(error + " - " + message);*/
        }

     /*   private void toast(String callback, String msg) {
            Toast.makeText(getApplicationContext(), callback + ": " + msg, Toast.LENGTH_SHORT).show();
        }*/
    }
}
