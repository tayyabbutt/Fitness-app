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

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;
import com.mobxpert.men30dayfitnesschallenge.R;
import com.mobxpert.men30dayfitnesschallenge.activities.absWorkout.LowerAbsActivity;
import com.mobxpert.men30dayfitnesschallenge.activities.absWorkout.SideCuttingActivity;
import com.mobxpert.men30dayfitnesschallenge.activities.absWorkout.UperAbsActivity;
import com.mobxpert.men30dayfitnesschallenge.utils.SharedPrefUtility;
import com.mobxpert.men30dayfitnesschallenge.utils.Utility;
import com.unity3d.ads.IUnityAdsListener;
import com.unity3d.ads.UnityAds;
import com.unity3d.ads.metadata.MediationMetaData;
import com.unity3d.ads.metadata.MetaData;
import com.unity3d.ads.metadata.PlayerMetaData;
import com.unity3d.services.core.log.DeviceLog;
import com.unity3d.services.core.misc.Utilities;

public class AbsWorkoutActivity extends AppCompatActivity {
    CardView uperAbs, lowerabs, sideCutting;
    AdView mAdView;
    ProgressBar lowerAbsProgress, uperAbsProgress, sideCuttingProgress;
    TextView lowerAbsProgressText, upperAbsTextProgress, sideCuttingTextProgress;

    public static int totalSumOfResults = 0;
    final private String gameId = "3094662";

    UnityAdsListener unityAdsListener;//= new UnityAdsListener();
    private static int ordinal = 1;
    private String interstitialPlacementId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_abs_workout);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        unityAdsListener = new UnityAdsListener();
        initializeInterstitialAd();
        lowerAbsProgress = (ProgressBar) findViewById(R.id.lowerabsProgress);
        uperAbsProgress = findViewById(R.id.uperAbsProgress);
        sideCuttingProgress = findViewById(R.id.sideCuttingProgress);
        upperAbsTextProgress = findViewById(R.id.textuperAbsProgress);
        sideCuttingTextProgress = findViewById(R.id.textSideCuttingProgress);
        lowerAbsProgressText = findViewById(R.id.textLowerabsProgress);

        lowerAbsProgress.setMax(100);
        uperAbsProgress.setMax(100);
        sideCuttingProgress.setMax(100);

        if (SharedPrefUtility.getInstance(this).getIntValue("LowerAbs") > 0) {
            lowerAbsProgressText.setText(SharedPrefUtility.getInstance(this).getIntValue("LowerAbs") + " %");
            lowerAbsProgress.setProgress(SharedPrefUtility.getInstance(this).getIntValue("LowerAbs"));
        } else if (LowerAbsActivity.resultWatchVideo >= 0) {
            lowerAbsProgressText.setText(LowerAbsActivity.resultWatchVideo + " %");
            lowerAbsProgress.setProgress(LowerAbsActivity.resultWatchVideo);

        } else {
            lowerAbsProgressText.setText("0%");
            lowerAbsProgress.setProgress(0);
        }


        if (SharedPrefUtility.getInstance(this).getIntValue("Percentage") > 0) {
            upperAbsTextProgress.setText(SharedPrefUtility.getInstance(this).getIntValue("Percentage") + " %");
            uperAbsProgress.setProgress(SharedPrefUtility.getInstance(this).getIntValue("Percentage"));
        } else if (UperAbsActivity.resultWatchVideo >= 0) {
            upperAbsTextProgress.setText(UperAbsActivity.resultWatchVideo + " %");
            uperAbsProgress.setProgress(UperAbsActivity.resultWatchVideo);

        } else {
            upperAbsTextProgress.setText("0%");
            uperAbsProgress.setProgress(0);
        }


        if (SharedPrefUtility.getInstance(this).getIntValue("SideCuttingAbs") > 0) {
            sideCuttingTextProgress.setText(SharedPrefUtility.getInstance(this).getIntValue("SideCuttingAbs") + " %");
            sideCuttingProgress.setProgress(SharedPrefUtility.getInstance(this).getIntValue("SideCuttingAbs"));
        } else if (SideCuttingActivity.resultWatchVideo >= 0) {
            sideCuttingTextProgress.setText(SideCuttingActivity.resultWatchVideo + " %");
            sideCuttingProgress.setProgress(SideCuttingActivity.resultWatchVideo);

        } else {
            sideCuttingTextProgress.setText("0%");
            sideCuttingProgress.setProgress(0);
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
        uperAbs = findViewById(R.id.uperAbs);
        lowerabs = findViewById(R.id.lowerabs);
        sideCutting = findViewById(R.id.sideCutting);

        uperAbs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PlayerMetaData playerMetaData = new PlayerMetaData(AbsWorkoutActivity.this);
                playerMetaData.setServerId("rikshot");
                playerMetaData.commit();

                MediationMetaData ordinalMetaData = new MediationMetaData(AbsWorkoutActivity.this);
                ordinalMetaData.setOrdinal(ordinal++);
                ordinalMetaData.commit();

                UnityAds.show(AbsWorkoutActivity.this, interstitialPlacementId);
                Utility.launchActivity(AbsWorkoutActivity.this, UperAbsActivity.class, false, null);
            }
        });
        lowerabs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PlayerMetaData playerMetaData = new PlayerMetaData(AbsWorkoutActivity.this);
                playerMetaData.setServerId("rikshot");
                playerMetaData.commit();

                MediationMetaData ordinalMetaData = new MediationMetaData(AbsWorkoutActivity.this);
                ordinalMetaData.setOrdinal(ordinal++);
                ordinalMetaData.commit();

                UnityAds.show(AbsWorkoutActivity.this, interstitialPlacementId);
                Utility.launchActivity(AbsWorkoutActivity.this, LowerAbsActivity.class, false, null);

            }
        });
        sideCutting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PlayerMetaData playerMetaData = new PlayerMetaData(AbsWorkoutActivity.this);
                playerMetaData.setServerId("rikshot");
                playerMetaData.commit();

                MediationMetaData ordinalMetaData = new MediationMetaData(AbsWorkoutActivity.this);
                ordinalMetaData.setOrdinal(ordinal++);
                ordinalMetaData.commit();

                UnityAds.show(AbsWorkoutActivity.this, interstitialPlacementId);
                Utility.launchActivity(AbsWorkoutActivity.this, SideCuttingActivity.class, false, null);

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
        if (SharedPrefUtility.getInstance(this).getIntValue("LowerAbs") > 0) {
            lowerAbsProgressText.setText(SharedPrefUtility.getInstance(this).getIntValue("LowerAbs") + " %");
            lowerAbsProgress.setProgress(SharedPrefUtility.getInstance(this).getIntValue("LowerAbs"));
        } else if (LowerAbsActivity.resultWatchVideo >= 0) {
            lowerAbsProgressText.setText(LowerAbsActivity.resultWatchVideo + " %");
            lowerAbsProgress.setProgress(LowerAbsActivity.resultWatchVideo);

        } else {
            lowerAbsProgressText.setText("0%");
            lowerAbsProgress.setProgress(0);
        }

        if (SharedPrefUtility.getInstance(this).getIntValue("Percentage") > 0) {
            upperAbsTextProgress.setText(SharedPrefUtility.getInstance(this).getIntValue("Percentage") + " %");
            uperAbsProgress.setProgress(SharedPrefUtility.getInstance(this).getIntValue("Percentage"));
        } else if (UperAbsActivity.resultWatchVideo >= 0) {
            upperAbsTextProgress.setText(UperAbsActivity.resultWatchVideo + " %");
            uperAbsProgress.setProgress(UperAbsActivity.resultWatchVideo);

        } else {
            upperAbsTextProgress.setText("0%");
            uperAbsProgress.setProgress(0);
        }


        if (SharedPrefUtility.getInstance(this).getIntValue("SideCuttingAbs") > 0) {
            sideCuttingTextProgress.setText(SharedPrefUtility.getInstance(this).getIntValue("SideCuttingAbs") + " %");
            sideCuttingProgress.setProgress(SharedPrefUtility.getInstance(this).getIntValue("SideCuttingAbs"));
        } else if (SideCuttingActivity.resultWatchVideo >= 0) {
            sideCuttingTextProgress.setText(SideCuttingActivity.resultWatchVideo + " %");
            sideCuttingProgress.setProgress(SideCuttingActivity.resultWatchVideo);

        } else {
            sideCuttingTextProgress.setText("0%");
            sideCuttingProgress.setProgress(0);
        }
        super.onResume();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    protected void onRestart() {
        super.onRestart();
    }


    @Override
    protected void onPause() {
        totalSumOfResults = getPercentageOfAllWorkout();

        super.onPause();
    }

    public int getPercentageOfAllWorkout() {

        int sumofAllResults = LowerAbsActivity.resultWatchVideo + UperAbsActivity.resultWatchVideo + SideCuttingActivity.resultWatchVideo;

        int result = ((sumofAllResults * 100) / 300);
        SharedPrefUtility.getInstance(this).savePrefrences("AbsWorkoutP", result);
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
            //  toast("Finish", zoneId + " " + result);
        }

        @Override
        public void onUnityAdsError(UnityAds.UnityAdsError error, String message) {
            DeviceLog.debug("onUnityAdsError: " + error + " - " + message);
            //   toast("Error", error + " " + message);

           /* TextView statusText = (TextView) findViewById(R.id.unityads_example_statustext);
            statusText.setText(error + " - " + message);*/
        }

       /* private void toast(String callback, String msg) {
            Toast.makeText(getApplicationContext(), callback + ": " + msg, Toast.LENGTH_SHORT).show();
        }*/
    }


}
