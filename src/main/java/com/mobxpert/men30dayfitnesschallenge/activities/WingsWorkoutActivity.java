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
import com.mobxpert.men30dayfitnesschallenge.activities.wingWorkout.V_ShapeWingActivity;
import com.mobxpert.men30dayfitnesschallenge.activities.wingWorkout.WingsExtention;
import com.mobxpert.men30dayfitnesschallenge.utils.SharedPrefUtility;
import com.mobxpert.men30dayfitnesschallenge.utils.Utility;
import com.unity3d.ads.IUnityAdsListener;
import com.unity3d.ads.UnityAds;
import com.unity3d.ads.metadata.MediationMetaData;
import com.unity3d.ads.metadata.MetaData;
import com.unity3d.ads.metadata.PlayerMetaData;
import com.unity3d.services.core.log.DeviceLog;
import com.unity3d.services.core.misc.Utilities;

public class WingsWorkoutActivity extends AppCompatActivity {
    CardView vShape, wingsExtention;
    AdView mAdView;

    ProgressBar vShapeWingsProgress, wingsExtentionProgress;
    TextView vShapeWingsProgressText, wingsExtentionProgressText;
    public static int totalSumOfResults = 0;

    UnityAdsListener unityAdsListener;//= new UnityAdsListener();
    private static int ordinal = 1;
    private String interstitialPlacementId;
    final private String gameId = "3094662";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wings_workout);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        vShapeWingsProgress = findViewById(R.id.vShapeWingsProgress);
        wingsExtentionProgress = findViewById(R.id.wingsExtentionProgress);
        unityAdsListener = new UnityAdsListener();
        initializeInterstitialAd();
        vShapeWingsProgressText = findViewById(R.id.textVShapeWingsProgress);
        wingsExtentionProgressText = findViewById(R.id.textWingsExtentionProgress);

        vShapeWingsProgress.setMax(100);
        wingsExtentionProgress.setMax(100);

        if (SharedPrefUtility.getInstance(this).getIntValue("VShapeWings") > 0) {
            vShapeWingsProgressText.setText(SharedPrefUtility.getInstance(this).getIntValue("VShapeWings") + " %");
            vShapeWingsProgress.setProgress(SharedPrefUtility.getInstance(this).getIntValue("VShapeWings"));
        } else if (V_ShapeWingActivity.resultWatchVideo >= 0) {
            vShapeWingsProgressText.setText(V_ShapeWingActivity.resultWatchVideo + " %");
            vShapeWingsProgress.setProgress(V_ShapeWingActivity.resultWatchVideo);

        } else {
            vShapeWingsProgressText.setText("0%");
            vShapeWingsProgress.setProgress(0);
        }

        if (SharedPrefUtility.getInstance(this).getIntValue("WingsExtention") > 0) {
            wingsExtentionProgressText.setText(SharedPrefUtility.getInstance(this).getIntValue("WingsExtention") + " %");
            wingsExtentionProgress.setProgress(SharedPrefUtility.getInstance(this).getIntValue("WingsExtention"));
        } else if (WingsExtention.resultWatchVideo >= 0) {
            wingsExtentionProgressText.setText(WingsExtention.resultWatchVideo + " %");
            wingsExtentionProgress.setProgress(WingsExtention.resultWatchVideo);

        } else {
            wingsExtentionProgressText.setText("0%");
            wingsExtentionProgress.setProgress(0);
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
        vShape = findViewById(R.id.vShapeWings);
        wingsExtention = findViewById(R.id.wingsExtention);
        vShape.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PlayerMetaData playerMetaData = new PlayerMetaData(WingsWorkoutActivity.this);
                playerMetaData.setServerId("rikshot");
                playerMetaData.commit();

                MediationMetaData ordinalMetaData = new MediationMetaData(WingsWorkoutActivity.this);
                ordinalMetaData.setOrdinal(ordinal++);
                ordinalMetaData.commit();

                UnityAds.show(WingsWorkoutActivity.this, interstitialPlacementId);

                Utility.launchActivity(WingsWorkoutActivity.this, V_ShapeWingActivity.class, false, null);
            }
        });
        wingsExtention.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PlayerMetaData playerMetaData = new PlayerMetaData(WingsWorkoutActivity.this);
                playerMetaData.setServerId("rikshot");
                playerMetaData.commit();

                MediationMetaData ordinalMetaData = new MediationMetaData(WingsWorkoutActivity.this);
                ordinalMetaData.setOrdinal(ordinal++);
                ordinalMetaData.commit();

                UnityAds.show(WingsWorkoutActivity.this, interstitialPlacementId);


                Utility.launchActivity(WingsWorkoutActivity.this, WingsExtention.class, false, null);

            }
        });
        totalSumOfResults = getPercentageOfAllWorkout();


    }

    @Override
    public void onBackPressed() {
        //MainActivity.mainscreenloadtime++;
        super.onBackPressed();
    }

    @Override
    protected void onResume() {
        if (V_ShapeWingActivity.resultWatchVideo >= 0) {
            vShapeWingsProgressText.setText(V_ShapeWingActivity.resultWatchVideo + " %");
            vShapeWingsProgress.setProgress(V_ShapeWingActivity.resultWatchVideo);

        } else {
            vShapeWingsProgressText.setText("0%");
            vShapeWingsProgress.setProgress(0);
        }
        if (WingsExtention.resultWatchVideo >= 0) {
            wingsExtentionProgressText.setText(WingsExtention.resultWatchVideo + " %");
            wingsExtentionProgress.setProgress(WingsExtention.resultWatchVideo);

        } else {
            wingsExtentionProgressText.setText("0%");
            wingsExtentionProgress.setProgress(0);
        }
        super.onResume();
    }

    @Override
    protected void onPause() {
        totalSumOfResults = getPercentageOfAllWorkout();

        super.onPause();
    }

    public int getPercentageOfAllWorkout() {

        int sumofAllResults = V_ShapeWingActivity.resultWatchVideo + WingsExtention.resultWatchVideo;

        int result = ((sumofAllResults * 100) / 200);
        SharedPrefUtility.getInstance(this).savePrefrences("WingsWorkoutP", result);
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
