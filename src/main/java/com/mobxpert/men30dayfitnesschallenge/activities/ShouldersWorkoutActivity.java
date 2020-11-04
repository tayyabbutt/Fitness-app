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
import com.mobxpert.men30dayfitnesschallenge.activities.shoulderWorkout.BackShoulderActivity;
import com.mobxpert.men30dayfitnesschallenge.activities.shoulderWorkout.FrontShoulderActivity;
import com.mobxpert.men30dayfitnesschallenge.activities.shoulderWorkout.TrapsActivity;
import com.mobxpert.men30dayfitnesschallenge.utils.SharedPrefUtility;
import com.mobxpert.men30dayfitnesschallenge.utils.Utility;
import com.unity3d.ads.IUnityAdsListener;
import com.unity3d.ads.UnityAds;
import com.unity3d.ads.metadata.MediationMetaData;
import com.unity3d.ads.metadata.MetaData;
import com.unity3d.ads.metadata.PlayerMetaData;
import com.unity3d.services.core.log.DeviceLog;
import com.unity3d.services.core.misc.Utilities;

public class ShouldersWorkoutActivity extends AppCompatActivity {
    CardView frontShoulder, backShoulder, traps;
    AdView mAdView;
    ProgressBar frontShoulderProgress, backShoulderProgress, trapsProgres;
    TextView frontSHoulderProgressText, backShoulderProgressText, trapsProgressText;
    public static int totalSumOfResults = 0;

    UnityAdsListener unityAdsListener;//= new UnityAdsListener();
    private static int ordinal = 1;
    private String interstitialPlacementId;
    final private String gameId = "3094662";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shoulders_workout);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        unityAdsListener = new UnityAdsListener();
        initializeInterstitialAd();
        frontShoulderProgress = findViewById(R.id.frontShoulderProgress);
        backShoulderProgress = findViewById(R.id.backShoulderProgress);
        trapsProgres = findViewById(R.id.trapsProgress);
        frontSHoulderProgressText = findViewById(R.id.textFrontShoulderProgress);
        backShoulderProgressText = findViewById(R.id.textBackShoulderProgress);
        trapsProgressText = findViewById(R.id.textTrapsProgress);

        frontShoulderProgress.setMax(100);
        backShoulderProgress.setMax(100);
        trapsProgres.setMax(100);
        if (SharedPrefUtility.getInstance(this).getIntValue("FrontShoulder") > 0) {
            frontSHoulderProgressText.setText(SharedPrefUtility.getInstance(this).getIntValue("FrontShoulder") + " %");
            frontShoulderProgress.setProgress(SharedPrefUtility.getInstance(this).getIntValue("FrontShoulder"));
        } else if (FrontShoulderActivity.resultWatchVideo >= 0) {
            frontSHoulderProgressText.setText(FrontShoulderActivity.resultWatchVideo + " %");
            frontShoulderProgress.setProgress(FrontShoulderActivity.resultWatchVideo);

        } else {
            frontSHoulderProgressText.setText("0%");
            frontShoulderProgress.setProgress(0);
        }

        if (SharedPrefUtility.getInstance(this).getIntValue("BackShoulder") > 0) {
            backShoulderProgressText.setText(SharedPrefUtility.getInstance(this).getIntValue("BackShoulder") + " %");
            backShoulderProgress.setProgress(SharedPrefUtility.getInstance(this).getIntValue("BackShoulder"));
        } else if (BackShoulderActivity.resultWatchVideo >= 0) {
            backShoulderProgressText.setText(BackShoulderActivity.resultWatchVideo + " %");
            backShoulderProgress.setProgress(BackShoulderActivity.resultWatchVideo);

        } else {
            backShoulderProgressText.setText("0%");
            backShoulderProgress.setProgress(0);
        }

        if (SharedPrefUtility.getInstance(this).getIntValue("Traps") > 0) {
            trapsProgressText.setText(SharedPrefUtility.getInstance(this).getIntValue("Traps") + " %");
            trapsProgres.setProgress(SharedPrefUtility.getInstance(this).getIntValue("Traps"));
        } else if (TrapsActivity.resultWatchVideo >= 0) {
            trapsProgressText.setText(TrapsActivity.resultWatchVideo + " %");
            trapsProgres.setProgress(TrapsActivity.resultWatchVideo);

        } else {
            trapsProgressText.setText("0%");
            trapsProgres.setProgress(0);
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
        frontShoulder = findViewById(R.id.frontShoulder);
        backShoulder = findViewById(R.id.backShoulder);
        traps = findViewById(R.id.traps);

        frontShoulder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PlayerMetaData playerMetaData = new PlayerMetaData(ShouldersWorkoutActivity.this);
                playerMetaData.setServerId("rikshot");
                playerMetaData.commit();

                MediationMetaData ordinalMetaData = new MediationMetaData(ShouldersWorkoutActivity.this);
                ordinalMetaData.setOrdinal(ordinal++);
                ordinalMetaData.commit();

                UnityAds.show(ShouldersWorkoutActivity.this, interstitialPlacementId);

                Utility.launchActivity(ShouldersWorkoutActivity.this, FrontShoulderActivity.class, false, null);
            }
        });
        backShoulder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PlayerMetaData playerMetaData = new PlayerMetaData(ShouldersWorkoutActivity.this);
                playerMetaData.setServerId("rikshot");
                playerMetaData.commit();

                MediationMetaData ordinalMetaData = new MediationMetaData(ShouldersWorkoutActivity.this);
                ordinalMetaData.setOrdinal(ordinal++);
                ordinalMetaData.commit();

                UnityAds.show(ShouldersWorkoutActivity.this, interstitialPlacementId);
                Utility.launchActivity(ShouldersWorkoutActivity.this, BackShoulderActivity.class, false, null);
            }
        });
        traps.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PlayerMetaData playerMetaData = new PlayerMetaData(ShouldersWorkoutActivity.this);
                playerMetaData.setServerId("rikshot");
                playerMetaData.commit();

                MediationMetaData ordinalMetaData = new MediationMetaData(ShouldersWorkoutActivity.this);
                ordinalMetaData.setOrdinal(ordinal++);
                ordinalMetaData.commit();

                UnityAds.show(ShouldersWorkoutActivity.this, interstitialPlacementId);
                Utility.launchActivity(ShouldersWorkoutActivity.this, TrapsActivity.class, false, null);

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

        if (SharedPrefUtility.getInstance(this).getIntValue("FrontShoulder") > 0) {
            frontSHoulderProgressText.setText(SharedPrefUtility.getInstance(this).getIntValue("FrontShoulder") + " %");
            frontShoulderProgress.setProgress(SharedPrefUtility.getInstance(this).getIntValue("FrontShoulder"));
        } else if (FrontShoulderActivity.resultWatchVideo >= 0) {
            frontSHoulderProgressText.setText(FrontShoulderActivity.resultWatchVideo + " %");
            frontShoulderProgress.setProgress(FrontShoulderActivity.resultWatchVideo);

        } else {
            frontSHoulderProgressText.setText("0%");
            frontShoulderProgress.setProgress(0);
        }

        if (SharedPrefUtility.getInstance(this).getIntValue("BackShoulder") > 0) {
            backShoulderProgressText.setText(SharedPrefUtility.getInstance(this).getIntValue("BackShoulder") + " %");
            backShoulderProgress.setProgress(SharedPrefUtility.getInstance(this).getIntValue("BackShoulder"));
        } else if (BackShoulderActivity.resultWatchVideo >= 0) {
            backShoulderProgressText.setText(BackShoulderActivity.resultWatchVideo + " %");
            backShoulderProgress.setProgress(BackShoulderActivity.resultWatchVideo);

        } else {
            backShoulderProgressText.setText("0%");
            backShoulderProgress.setProgress(0);
        }

        if (SharedPrefUtility.getInstance(this).getIntValue("Traps") > 0) {
            trapsProgressText.setText(SharedPrefUtility.getInstance(this).getIntValue("Traps") + " %");
            trapsProgres.setProgress(SharedPrefUtility.getInstance(this).getIntValue("Traps"));
        } else if (TrapsActivity.resultWatchVideo >= 0) {
            trapsProgressText.setText(TrapsActivity.resultWatchVideo + " %");
            trapsProgres.setProgress(TrapsActivity.resultWatchVideo);

        } else {
            trapsProgressText.setText("0%");
            trapsProgres.setProgress(0);
        }
        super.onResume();
    }

    @Override
    protected void onPause() {
        totalSumOfResults = getPercentageOfAllWorkout();

        super.onPause();

    }

    public int getPercentageOfAllWorkout() {

        int sumofAllResults = FrontShoulderActivity.resultWatchVideo + BackShoulderActivity.resultWatchVideo + TrapsActivity.resultWatchVideo;

        int result = ((sumofAllResults * 100) / 300);
        SharedPrefUtility.getInstance(this).savePrefrences("ShoulderWorkoutP", result);
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
           // toast("Finish", zoneId + " " + result);
        }

        @Override
        public void onUnityAdsError(UnityAds.UnityAdsError error, String message) {
            DeviceLog.debug("onUnityAdsError: " + error + " - " + message);
        //    toast("Error", error + " " + message);

           /* TextView statusText = (TextView) findViewById(R.id.unityads_example_statustext);
            statusText.setText(error + " - " + message);*/
        }

       /* private void toast(String callback, String msg) {
            Toast.makeText(getApplicationContext(), callback + ": " + msg, Toast.LENGTH_SHORT).show();
        }*/
    }
}
