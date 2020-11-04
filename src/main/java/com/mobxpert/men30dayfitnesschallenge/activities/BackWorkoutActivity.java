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
import com.mobxpert.men30dayfitnesschallenge.activities.backWorkout.LowerBackActivity;
import com.mobxpert.men30dayfitnesschallenge.activities.backWorkout.UpperBackActivity;
import com.mobxpert.men30dayfitnesschallenge.activities.backWorkout.V_shapeBackActivity;
import com.mobxpert.men30dayfitnesschallenge.utils.SharedPrefUtility;
import com.mobxpert.men30dayfitnesschallenge.utils.Utility;
import com.unity3d.ads.IUnityAdsListener;
import com.unity3d.ads.UnityAds;
import com.unity3d.ads.metadata.MediationMetaData;
import com.unity3d.ads.metadata.MetaData;
import com.unity3d.ads.metadata.PlayerMetaData;
import com.unity3d.services.core.log.DeviceLog;
import com.unity3d.services.core.misc.Utilities;

public class BackWorkoutActivity extends AppCompatActivity {
    CardView uperBack, lowerBack, vshapeBack;
    AdView mAdView;
    ProgressBar lowerBackProgress, uperBackProgress, vShapeBackProgress;
    TextView lowerBackProgressText, uperBackTextProgress, vShapeBackTextProgress;
    public static int totalSumOfResults = 0;
    final private String gameId = "3094662";

    UnityAdsListener unityAdsListener;//= new UnityAdsListener();
    private static int ordinal = 1;
    private String interstitialPlacementId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_back_workout);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        lowerBackProgress = (ProgressBar) findViewById(R.id.lowerBackProgress);
        uperBackProgress = findViewById(R.id.uperBackProgress);
        unityAdsListener = new UnityAdsListener();
        initializeInterstitialAd();

        vShapeBackProgress = findViewById(R.id.vshapeBackProgress);
        lowerBackProgressText = findViewById(R.id.textLowerBackProgress);
        uperBackTextProgress = findViewById(R.id.textuperBackProgress);
        vShapeBackTextProgress = findViewById(R.id.textvshapeBackProgress);
        lowerBackProgress.setMax(100);
        uperBackProgress.setMax(100);
        vShapeBackProgress.setMax(100);


        if (SharedPrefUtility.getInstance(this).getIntValue("LowerBack") > 0) {
            lowerBackProgressText.setText(SharedPrefUtility.getInstance(this).getIntValue("LowerBack") + " %");
            lowerBackProgress.setProgress(SharedPrefUtility.getInstance(this).getIntValue("LowerBack"));
        } else if (LowerBackActivity.resultWatchVideo >= 0) {
            lowerBackProgressText.setText(LowerBackActivity.resultWatchVideo + " %");
            lowerBackProgress.setProgress(LowerBackActivity.resultWatchVideo);

        } else {
            lowerBackProgressText.setText("0%");
            lowerBackProgress.setProgress(0);
        }


        if (SharedPrefUtility.getInstance(this).getIntValue("UpperBack") > 0) {
            uperBackTextProgress.setText(SharedPrefUtility.getInstance(this).getIntValue("UpperBack") + " %");
            uperBackProgress.setProgress(SharedPrefUtility.getInstance(this).getIntValue("UpperBack"));
        } else if (UpperBackActivity.resultWatchVideo >= 0) {
            uperBackTextProgress.setText(UpperBackActivity.resultWatchVideo + " %");
            uperBackProgress.setProgress(UpperBackActivity.resultWatchVideo);

        } else {
            uperBackTextProgress.setText("0%");
            uperBackProgress.setProgress(0);
        }


        if (SharedPrefUtility.getInstance(this).getIntValue("VShapeBack") > 0) {
            vShapeBackTextProgress.setText(SharedPrefUtility.getInstance(this).getIntValue("VShapeBack") + " %");
            vShapeBackProgress.setProgress(SharedPrefUtility.getInstance(this).getIntValue("VShapeBack"));
        } else if (V_shapeBackActivity.resultWatchVideo >= 0) {
            vShapeBackTextProgress.setText(V_shapeBackActivity.resultWatchVideo + " %");
            vShapeBackProgress.setProgress(V_shapeBackActivity.resultWatchVideo);

        } else {
            vShapeBackTextProgress.setText("0%");
            vShapeBackProgress.setProgress(0);
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

        uperBack = findViewById(R.id.uperBack);
        lowerBack = findViewById(R.id.lowerBack);
        vshapeBack = findViewById(R.id.vshapeBack);


        uperBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PlayerMetaData playerMetaData = new PlayerMetaData(BackWorkoutActivity.this);
                playerMetaData.setServerId("rikshot");
                playerMetaData.commit();

                MediationMetaData ordinalMetaData = new MediationMetaData(BackWorkoutActivity.this);
                ordinalMetaData.setOrdinal(ordinal++);
                ordinalMetaData.commit();

                UnityAds.show(BackWorkoutActivity.this, interstitialPlacementId);
                Utility.launchActivity(BackWorkoutActivity.this, UpperBackActivity.class, false, null);
            }
        });
        lowerBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PlayerMetaData playerMetaData = new PlayerMetaData(BackWorkoutActivity.this);
                playerMetaData.setServerId("rikshot");
                playerMetaData.commit();

                MediationMetaData ordinalMetaData = new MediationMetaData(BackWorkoutActivity.this);
                ordinalMetaData.setOrdinal(ordinal++);
                ordinalMetaData.commit();

                UnityAds.show(BackWorkoutActivity.this, interstitialPlacementId);
                Utility.launchActivity(BackWorkoutActivity.this, LowerBackActivity.class, false, null);

            }
        });
        vshapeBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PlayerMetaData playerMetaData = new PlayerMetaData(BackWorkoutActivity.this);
                playerMetaData.setServerId("rikshot");
                playerMetaData.commit();

                MediationMetaData ordinalMetaData = new MediationMetaData(BackWorkoutActivity.this);
                ordinalMetaData.setOrdinal(ordinal++);
                ordinalMetaData.commit();

                UnityAds.show(BackWorkoutActivity.this, interstitialPlacementId);
                Utility.launchActivity(BackWorkoutActivity.this, V_shapeBackActivity.class, false, null);

            }
        });
        totalSumOfResults = getPercentageOfAllWorkout();

    }

    @Override
    public void onBackPressed() {
      //  MainActivity.mainscreenloadtime++;
        super.onBackPressed();
    }

    @Override
    protected void onResume() {
        if (SharedPrefUtility.getInstance(this).getIntValue("LowerBack") > 0) {
            lowerBackProgressText.setText(SharedPrefUtility.getInstance(this).getIntValue("LowerBack") + " %");
            lowerBackProgress.setProgress(SharedPrefUtility.getInstance(this).getIntValue("LowerBack"));
        } else if (LowerBackActivity.resultWatchVideo >= 0) {
            lowerBackProgressText.setText(LowerBackActivity.resultWatchVideo + " %");
            lowerBackProgress.setProgress(LowerBackActivity.resultWatchVideo);

        } else {
            lowerBackProgressText.setText("0%");
            lowerBackProgress.setProgress(0);
        }

        if (SharedPrefUtility.getInstance(this).getIntValue("UpperBack") > 0) {
            uperBackTextProgress.setText(SharedPrefUtility.getInstance(this).getIntValue("UpperBack") + " %");
            uperBackProgress.setProgress(SharedPrefUtility.getInstance(this).getIntValue("UpperBack"));
        } else if (UpperBackActivity.resultWatchVideo >= 0) {
            uperBackTextProgress.setText(UpperBackActivity.resultWatchVideo + " %");
            uperBackProgress.setProgress(UpperBackActivity.resultWatchVideo);

        } else {
            uperBackTextProgress.setText("0%");
            uperBackProgress.setProgress(0);
        }

        if (SharedPrefUtility.getInstance(this).getIntValue("VShapeBack") > 0) {
            vShapeBackTextProgress.setText(SharedPrefUtility.getInstance(this).getIntValue("VShapeBack") + " %");
            vShapeBackProgress.setProgress(SharedPrefUtility.getInstance(this).getIntValue("VShapeBack"));
        } else if (V_shapeBackActivity.resultWatchVideo >= 0) {
            vShapeBackTextProgress.setText(V_shapeBackActivity.resultWatchVideo + " %");
            vShapeBackProgress.setProgress(V_shapeBackActivity.resultWatchVideo);

        } else {
            vShapeBackTextProgress.setText("0%");
            vShapeBackProgress.setProgress(0);
        }
        super.onResume();
    }

    @Override
    protected void onPause() {
        totalSumOfResults = getPercentageOfAllWorkout();

        super.onPause();
    }

    public int getPercentageOfAllWorkout() {

        int sumofAllResults = LowerBackActivity.resultWatchVideo + UpperBackActivity.resultWatchVideo + V_shapeBackActivity.resultWatchVideo;

        int result = ((sumofAllResults * 100) / 300);
        SharedPrefUtility.getInstance(this).savePrefrences("BackWorkoutP", result);
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

            //toast("Ready", zoneId);
        }

        @Override
        public void onUnityAdsStart(String zoneId) {
            DeviceLog.debug("onUnityAdsStart: " + zoneId);
          //  toast("Start", zoneId);
        }

        @Override
        public void onUnityAdsFinish(String zoneId, UnityAds.FinishState result) {
            DeviceLog.debug("onUnityAdsFinish: " + zoneId + " - " + result);
          //  toast("Finish", zoneId + " " + result);
        }

        @Override
        public void onUnityAdsError(UnityAds.UnityAdsError error, String message) {
            DeviceLog.debug("onUnityAdsError: " + error + " - " + message);
        //    toast("Error", error + " " + message);

           /* TextView statusText = (TextView) findViewById(R.id.unityads_example_statustext);
            statusText.setText(error + " - " + message);*/
        }
/*
        private void toast(String callback, String msg) {
            Toast.makeText(getApplicationContext(), callback + ": " + msg, Toast.LENGTH_SHORT).show();
        }*/
    }
}
