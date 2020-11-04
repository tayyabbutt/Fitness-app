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
import com.mobxpert.men30dayfitnesschallenge.activities.legsWorkout.BackThigsActivity;
import com.mobxpert.men30dayfitnesschallenge.activities.legsWorkout.CalfsActivity;
import com.mobxpert.men30dayfitnesschallenge.activities.legsWorkout.FrontThigsActivity;
import com.mobxpert.men30dayfitnesschallenge.activities.legsWorkout.HipsActivity;
import com.mobxpert.men30dayfitnesschallenge.utils.SharedPrefUtility;
import com.mobxpert.men30dayfitnesschallenge.utils.Utility;
import com.unity3d.ads.IUnityAdsListener;
import com.unity3d.ads.UnityAds;
import com.unity3d.ads.metadata.MediationMetaData;
import com.unity3d.ads.metadata.MetaData;
import com.unity3d.ads.metadata.PlayerMetaData;
import com.unity3d.services.core.log.DeviceLog;
import com.unity3d.services.core.misc.Utilities;

public class LegsWorkoutActivity extends AppCompatActivity {
    CardView frontThighs, backThighs, calfs, hips;
    AdView mAdView;
    ProgressBar frontThighsProgress, backThighsProgress, calfsProgress, hipsProgress;
    TextView frontThighsProgressText, backThighsProgressText, calfsProgressText, hipsProgressText;
    public static int totalSumOfResults = 0;
    final private String gameId = "3094662";

    UnityAdsListener unityAdsListener;//= new UnityAdsListener();
    private static int ordinal = 1;
    private String interstitialPlacementId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_legs_workout);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        frontThighsProgress = (ProgressBar) findViewById(R.id.frontThighsProgress);
        backThighsProgress = findViewById(R.id.backThighsProgress);
        calfsProgress = findViewById(R.id.calfsProgress);
        hipsProgress = findViewById(R.id.hipsProgress);
        unityAdsListener = new UnityAdsListener();
        initializeInterstitialAd();

        frontThighsProgressText = findViewById(R.id.textFrontThighsProgress);
        backThighsProgressText = findViewById(R.id.textBackThighsProgress);
        calfsProgressText = findViewById(R.id.textCalfsProgress);
        hipsProgressText = findViewById(R.id.textHipsProgress);
        frontThighsProgress.setMax(100);
        backThighsProgress.setMax(100);
        calfsProgress.setMax(100);
        hipsProgress.setMax(100);

        if (SharedPrefUtility.getInstance(this).getIntValue("FrontThigh") > 0) {
            frontThighsProgressText.setText(SharedPrefUtility.getInstance(this).getIntValue("FrontThigh") + " %");
            frontThighsProgress.setProgress(SharedPrefUtility.getInstance(this).getIntValue("FrontThigh"));
        } else if (FrontThigsActivity.resultWatchVideo >= 0) {
            frontThighsProgressText.setText(FrontThigsActivity.resultWatchVideo + " %");
            frontThighsProgress.setProgress(FrontThigsActivity.resultWatchVideo);

        } else {
            frontThighsProgressText.setText("0%");
            frontThighsProgress.setProgress(0);
        }


        if (SharedPrefUtility.getInstance(this).getIntValue("BackThigh") > 0) {
            backThighsProgressText.setText(SharedPrefUtility.getInstance(this).getIntValue("BackThigh") + " %");
            backThighsProgress.setProgress(SharedPrefUtility.getInstance(this).getIntValue("BackThigh"));
        } else if (BackThigsActivity.resultWatchVideo >= 0) {
            backThighsProgressText.setText(BackThigsActivity.resultWatchVideo + " %");
            backThighsProgress.setProgress(BackThigsActivity.resultWatchVideo);

        } else {
            backThighsProgressText.setText("0%");
            backThighsProgress.setProgress(0);
        }


        if (SharedPrefUtility.getInstance(this).getIntValue("Calfs") > 0) {
            calfsProgressText.setText(SharedPrefUtility.getInstance(this).getIntValue("Calfs") + " %");
            calfsProgress.setProgress(SharedPrefUtility.getInstance(this).getIntValue("Calfs"));
        } else if (CalfsActivity.resultWatchVideo >= 0) {
            calfsProgressText.setText(CalfsActivity.resultWatchVideo + " %");
            calfsProgress.setProgress(CalfsActivity.resultWatchVideo);

        } else {
            calfsProgressText.setText("0%");
            calfsProgress.setProgress(0);
        }


        if (SharedPrefUtility.getInstance(this).getIntValue("Hips") > 0) {
            hipsProgressText.setText(SharedPrefUtility.getInstance(this).getIntValue("Hips") + " %");
            hipsProgress.setProgress(SharedPrefUtility.getInstance(this).getIntValue("Hips"));
        } else if (HipsActivity.resultWatchVideo >= 0) {
            hipsProgressText.setText(HipsActivity.resultWatchVideo + " %");
            hipsProgress.setProgress(HipsActivity.resultWatchVideo);

        } else {
            hipsProgressText.setText("0%");
            hipsProgress.setProgress(0);
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
        frontThighs = findViewById(R.id.frontThighs);
        backThighs = findViewById(R.id.backThighs);
        calfs = findViewById(R.id.calfs);
        hips = findViewById(R.id.hips);

        frontThighs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PlayerMetaData playerMetaData = new PlayerMetaData(LegsWorkoutActivity.this);
                playerMetaData.setServerId("rikshot");
                playerMetaData.commit();

                MediationMetaData ordinalMetaData = new MediationMetaData(LegsWorkoutActivity.this);
                ordinalMetaData.setOrdinal(ordinal++);
                ordinalMetaData.commit();

                UnityAds.show(LegsWorkoutActivity.this, interstitialPlacementId);
                Utility.launchActivity(LegsWorkoutActivity.this, FrontThigsActivity.class, false, null);
            }
        });
        backThighs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PlayerMetaData playerMetaData = new PlayerMetaData(LegsWorkoutActivity.this);
                playerMetaData.setServerId("rikshot");
                playerMetaData.commit();

                MediationMetaData ordinalMetaData = new MediationMetaData(LegsWorkoutActivity.this);
                ordinalMetaData.setOrdinal(ordinal++);
                ordinalMetaData.commit();

                UnityAds.show(LegsWorkoutActivity.this, interstitialPlacementId);
                Utility.launchActivity(LegsWorkoutActivity.this, BackThigsActivity.class, false, null);
            }
        });
        calfs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PlayerMetaData playerMetaData = new PlayerMetaData(LegsWorkoutActivity.this);
                playerMetaData.setServerId("rikshot");
                playerMetaData.commit();

                MediationMetaData ordinalMetaData = new MediationMetaData(LegsWorkoutActivity.this);
                ordinalMetaData.setOrdinal(ordinal++);
                ordinalMetaData.commit();

                UnityAds.show(LegsWorkoutActivity.this, interstitialPlacementId);

                Utility.launchActivity(LegsWorkoutActivity.this, CalfsActivity.class, false, null);

            }
        });
        hips.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PlayerMetaData playerMetaData = new PlayerMetaData(LegsWorkoutActivity.this);
                playerMetaData.setServerId("rikshot");
                playerMetaData.commit();

                MediationMetaData ordinalMetaData = new MediationMetaData(LegsWorkoutActivity.this);
                ordinalMetaData.setOrdinal(ordinal++);
                ordinalMetaData.commit();

                UnityAds.show(LegsWorkoutActivity.this, interstitialPlacementId);
                Utility.launchActivity(LegsWorkoutActivity.this, HipsActivity.class, false, null);

            }
        });
        totalSumOfResults = getPercentageOfAllWorkout();


    }

    @Override
    public void onBackPressed() {
       // MainActivity.mainscreenloadtime++;
        totalSumOfResults = getPercentageOfAllWorkout();

        if (SharedPrefUtility.getInstance(this).getIntValue("FrontThigh") > 0) {
            frontThighsProgressText.setText(SharedPrefUtility.getInstance(this).getIntValue("FrontThigh") + " %");
            frontThighsProgress.setProgress(SharedPrefUtility.getInstance(this).getIntValue("FrontThigh"));
        } else if (FrontThigsActivity.resultWatchVideo >= 0) {
            frontThighsProgressText.setText(FrontThigsActivity.resultWatchVideo + " %");
            frontThighsProgress.setProgress(FrontThigsActivity.resultWatchVideo);

        } else {
            frontThighsProgressText.setText("0%");
            frontThighsProgress.setProgress(0);
        }


        if (SharedPrefUtility.getInstance(this).getIntValue("BackThigh") > 0) {
            backThighsProgressText.setText(SharedPrefUtility.getInstance(this).getIntValue("BackThigh") + " %");
            backThighsProgress.setProgress(SharedPrefUtility.getInstance(this).getIntValue("BackThigh"));
        } else if (BackThigsActivity.resultWatchVideo >= 0) {
            backThighsProgressText.setText(BackThigsActivity.resultWatchVideo + " %");
            backThighsProgress.setProgress(BackThigsActivity.resultWatchVideo);

        } else {
            backThighsProgressText.setText("0%");
            backThighsProgress.setProgress(0);
        }


        if (SharedPrefUtility.getInstance(this).getIntValue("Calfs") > 0) {
            calfsProgressText.setText(SharedPrefUtility.getInstance(this).getIntValue("Calfs") + " %");
            calfsProgress.setProgress(SharedPrefUtility.getInstance(this).getIntValue("Calfs"));
        } else if (CalfsActivity.resultWatchVideo >= 0) {
            calfsProgressText.setText(CalfsActivity.resultWatchVideo + " %");
            calfsProgress.setProgress(CalfsActivity.resultWatchVideo);

        } else {
            calfsProgressText.setText("0%");
            calfsProgress.setProgress(0);
        }


        if (SharedPrefUtility.getInstance(this).getIntValue("Hips") > 0) {
            hipsProgressText.setText(SharedPrefUtility.getInstance(this).getIntValue("Hips") + " %");
            hipsProgress.setProgress(SharedPrefUtility.getInstance(this).getIntValue("Hips"));
        } else if (HipsActivity.resultWatchVideo >= 0) {
            hipsProgressText.setText(HipsActivity.resultWatchVideo + " %");
            hipsProgress.setProgress(HipsActivity.resultWatchVideo);

        } else {
            hipsProgressText.setText("0%");
            hipsProgress.setProgress(0);
        }

        super.onBackPressed();
    }

    @Override
    protected void onResume() {
        totalSumOfResults = getPercentageOfAllWorkout();

        if (FrontThigsActivity.resultWatchVideo >= 0) {
            frontThighsProgressText.setText(FrontThigsActivity.resultWatchVideo + " %");
            frontThighsProgress.setProgress(FrontThigsActivity.resultWatchVideo);

        } else {
            frontThighsProgressText.setText("0%");
            frontThighsProgress.setProgress(0);
        }
        if (BackThigsActivity.resultWatchVideo >= 0) {
            backThighsProgressText.setText(BackThigsActivity.resultWatchVideo + " %");
            backThighsProgress.setProgress(BackThigsActivity.resultWatchVideo);

        } else {
            backThighsProgressText.setText("0%");
            backThighsProgress.setProgress(0);
        }
        if (CalfsActivity.resultWatchVideo >= 0) {
            calfsProgressText.setText(CalfsActivity.resultWatchVideo + " %");
            calfsProgress.setProgress(CalfsActivity.resultWatchVideo);

        } else {
            calfsProgressText.setText("0%");
            calfsProgress.setProgress(0);
        }
        if (HipsActivity.resultWatchVideo >= 0) {
            hipsProgressText.setText(HipsActivity.resultWatchVideo + " %");
            hipsProgress.setProgress(HipsActivity.resultWatchVideo);

        } else {
            hipsProgressText.setText("0%");
            hipsProgress.setProgress(0);
        }
        super.onResume();
    }

    @Override
    protected void onRestart() {
        totalSumOfResults = getPercentageOfAllWorkout();

        if (FrontThigsActivity.resultWatchVideo >= 0) {
            frontThighsProgressText.setText(FrontThigsActivity.resultWatchVideo + " %");
            frontThighsProgress.setProgress(FrontThigsActivity.resultWatchVideo);

        } else {
            frontThighsProgressText.setText("0%");
            frontThighsProgress.setProgress(0);
        }
        if (BackThigsActivity.resultWatchVideo >= 0) {
            backThighsProgressText.setText(BackThigsActivity.resultWatchVideo + " %");
            backThighsProgress.setProgress(BackThigsActivity.resultWatchVideo);

        } else {
            backThighsProgressText.setText("0%");
            backThighsProgress.setProgress(0);
        }
        if (CalfsActivity.resultWatchVideo >= 0) {
            calfsProgressText.setText(CalfsActivity.resultWatchVideo + " %");
            calfsProgress.setProgress(CalfsActivity.resultWatchVideo);

        } else {
            calfsProgressText.setText("0%");
            calfsProgress.setProgress(0);
        }
        if (HipsActivity.resultWatchVideo >= 0) {
            hipsProgressText.setText(HipsActivity.resultWatchVideo + " %");
            hipsProgress.setProgress(HipsActivity.resultWatchVideo);

        } else {
            hipsProgressText.setText("0%");
            hipsProgress.setProgress(0);
        }

        super.onRestart();
    }

    @Override
    protected void onPause() {
        totalSumOfResults = getPercentageOfAllWorkout();

        super.onPause();
    }

    public int getPercentageOfAllWorkout() {

        int sumofAllResults = FrontThigsActivity.resultWatchVideo + BackThigsActivity.resultWatchVideo + CalfsActivity.resultWatchVideo + HipsActivity.resultWatchVideo;

        int result = ((sumofAllResults * 100) / 400);
        SharedPrefUtility.getInstance(this).savePrefrences("LegsWorkoutP", result);
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
            //toast("Start", zoneId);
        }

        @Override
        public void onUnityAdsFinish(String zoneId, UnityAds.FinishState result) {
            DeviceLog.debug("onUnityAdsFinish: " + zoneId + " - " + result);
         //   toast("Finish", zoneId + " " + result);
        }

        @Override
        public void onUnityAdsError(UnityAds.UnityAdsError error, String message) {
            DeviceLog.debug("onUnityAdsError: " + error + " - " + message);
          //  toast("Error", error + " " + message);

           /* TextView statusText = (TextView) findViewById(R.id.unityads_example_statustext);
            statusText.setText(error + " - " + message);*/
        }

       /* private void toast(String callback, String msg) {
            Toast.makeText(getApplicationContext(), callback + ": " + msg, Toast.LENGTH_SHORT).show();
        }*/
    }

}
