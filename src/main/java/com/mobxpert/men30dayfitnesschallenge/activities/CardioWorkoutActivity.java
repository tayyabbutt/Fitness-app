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
import com.mobxpert.men30dayfitnesschallenge.activities.cardioWorkout.CardioActivity;
import com.mobxpert.men30dayfitnesschallenge.activities.cardioWorkout.Stretching;
import com.mobxpert.men30dayfitnesschallenge.activities.cardioWorkout.YogaActivity;
import com.mobxpert.men30dayfitnesschallenge.utils.SharedPrefUtility;
import com.mobxpert.men30dayfitnesschallenge.utils.Utility;
import com.unity3d.ads.IUnityAdsListener;
import com.unity3d.ads.UnityAds;
import com.unity3d.ads.metadata.MediationMetaData;
import com.unity3d.ads.metadata.MetaData;
import com.unity3d.ads.metadata.PlayerMetaData;
import com.unity3d.services.core.log.DeviceLog;
import com.unity3d.services.core.misc.Utilities;

public class CardioWorkoutActivity extends AppCompatActivity {
    CardView cardio, stretching, yoga;
    AdView mAdView;
    ProgressBar cardioProgress, stretchingProgress, yogaProgress;
    TextView cardioProgressText, stretchingTextProgress, yogaTextProgress;
    public static int totalSumOfResults = 0;

    final private String gameId = "3094662";

    UnityAdsListener unityAdsListener;//= new UnityAdsListener();
    private static int ordinal = 1;
    private String interstitialPlacementId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cardio_workout);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        cardioProgress = (ProgressBar) findViewById(R.id.cardioProgress);
        stretchingProgress = findViewById(R.id.stretchingProgress);
        yogaProgress = findViewById(R.id.yogaProgress);
        unityAdsListener = new UnityAdsListener();
        initializeInterstitialAd();
        cardioProgressText = findViewById(R.id.textupeCardioProgress);
        stretchingTextProgress = findViewById(R.id.textStretchingProgress);
        yogaTextProgress = findViewById(R.id.textYogaProgress);
        cardioProgress.setMax(100);
        stretchingProgress.setMax(100);
        yogaProgress.setMax(100);

        if (SharedPrefUtility.getInstance(this).getIntValue("CardioActivity") > 0) {
            cardioProgressText.setText(SharedPrefUtility.getInstance(this).getIntValue("CardioActivity") + " %");
            cardioProgress.setProgress(SharedPrefUtility.getInstance(this).getIntValue("CardioActivity"));
        } else if (CardioActivity.resultWatchVideo >= 0) {
            cardioProgressText.setText(CardioActivity.resultWatchVideo + " %");
            cardioProgress.setProgress(CardioActivity.resultWatchVideo);

        } else {
            cardioProgressText.setText("0%");
            cardioProgress.setProgress(0);
        }

        if (SharedPrefUtility.getInstance(this).getIntValue("Stretching") > 0) {
            stretchingTextProgress.setText(SharedPrefUtility.getInstance(this).getIntValue("Stretching") + " %");
            stretchingProgress.setProgress(SharedPrefUtility.getInstance(this).getIntValue("Stretching"));
        } else if (Stretching.resultWatchVideo >= 0) {
            stretchingTextProgress.setText(Stretching.resultWatchVideo + " %");
            stretchingProgress.setProgress(Stretching.resultWatchVideo);
        } else {
            stretchingTextProgress.setText("0%");
            stretchingProgress.setProgress(0);
        }

        if (SharedPrefUtility.getInstance(this).getIntValue("Yoga") > 0) {
            yogaTextProgress.setText(SharedPrefUtility.getInstance(this).getIntValue("Yoga") + " %");
            yogaProgress.setProgress(SharedPrefUtility.getInstance(this).getIntValue("Yoga"));
        } else if (YogaActivity.resultWatchVideo >= 0) {
            yogaTextProgress.setText(YogaActivity.resultWatchVideo + " %");
            yogaProgress.setProgress(YogaActivity.resultWatchVideo);

        } else {
            yogaTextProgress.setText("0%");
            yogaProgress.setProgress(0);
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
        cardio = findViewById(R.id.cardio);
        stretching = findViewById(R.id.stretching);
        yoga = findViewById(R.id.yoga);

        cardio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PlayerMetaData playerMetaData = new PlayerMetaData(CardioWorkoutActivity.this);
                playerMetaData.setServerId("rikshot");
                playerMetaData.commit();

                MediationMetaData ordinalMetaData = new MediationMetaData(CardioWorkoutActivity.this);
                ordinalMetaData.setOrdinal(ordinal++);
                ordinalMetaData.commit();

                UnityAds.show(CardioWorkoutActivity.this, interstitialPlacementId);
                Utility.launchActivity(CardioWorkoutActivity.this, CardioActivity.class, false, null);
            }
        });
        stretching.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PlayerMetaData playerMetaData = new PlayerMetaData(CardioWorkoutActivity.this);
                playerMetaData.setServerId("rikshot");
                playerMetaData.commit();

                MediationMetaData ordinalMetaData = new MediationMetaData(CardioWorkoutActivity.this);
                ordinalMetaData.setOrdinal(ordinal++);
                ordinalMetaData.commit();

                UnityAds.show(CardioWorkoutActivity.this, interstitialPlacementId);
                Utility.launchActivity(CardioWorkoutActivity.this, Stretching.class, false, null);
            }
        });
        yoga.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PlayerMetaData playerMetaData = new PlayerMetaData(CardioWorkoutActivity.this);
                playerMetaData.setServerId("rikshot");
                playerMetaData.commit();

                MediationMetaData ordinalMetaData = new MediationMetaData(CardioWorkoutActivity.this);
                ordinalMetaData.setOrdinal(ordinal++);
                ordinalMetaData.commit();

                UnityAds.show(CardioWorkoutActivity.this, interstitialPlacementId);
                Utility.launchActivity(CardioWorkoutActivity.this, YogaActivity.class, false, null);

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
        if (SharedPrefUtility.getInstance(this).getIntValue("CardioActivity") > 0) {
            cardioProgressText.setText(SharedPrefUtility.getInstance(this).getIntValue("CardioActivity") + " %");
            cardioProgress.setProgress(SharedPrefUtility.getInstance(this).getIntValue("CardioActivity"));
        } else if (CardioActivity.resultWatchVideo >= 0) {
            cardioProgressText.setText(CardioActivity.resultWatchVideo + " %");
            cardioProgress.setProgress(CardioActivity.resultWatchVideo);

        } else {
            cardioProgressText.setText("0%");
            cardioProgress.setProgress(0);
        }

        if (SharedPrefUtility.getInstance(this).getIntValue("Stretching") > 0) {
            stretchingTextProgress.setText(SharedPrefUtility.getInstance(this).getIntValue("Stretching") + " %");
            stretchingProgress.setProgress(SharedPrefUtility.getInstance(this).getIntValue("Stretching"));
        } else if (Stretching.resultWatchVideo >= 0) {
            stretchingTextProgress.setText(Stretching.resultWatchVideo + " %");
            stretchingProgress.setProgress(Stretching.resultWatchVideo);
        } else {
            stretchingTextProgress.setText("0%");
            stretchingProgress.setProgress(0);
        }

        if (SharedPrefUtility.getInstance(this).getIntValue("Yoga") > 0) {
            yogaTextProgress.setText(SharedPrefUtility.getInstance(this).getIntValue("Yoga") + " %");
            yogaProgress.setProgress(SharedPrefUtility.getInstance(this).getIntValue("Yoga"));
        } else if (YogaActivity.resultWatchVideo >= 0) {
            yogaTextProgress.setText(YogaActivity.resultWatchVideo + " %");
            yogaProgress.setProgress(YogaActivity.resultWatchVideo);

        } else {
            yogaTextProgress.setText("0%");
            yogaProgress.setProgress(0);
        }
        super.onResume();
    }

    @Override
    protected void onPause() {
        totalSumOfResults = getPercentageOfAllWorkout();

        super.onPause();
    }

    public int getPercentageOfAllWorkout() {

        int sumofAllResults = CardioActivity.resultWatchVideo + Stretching.resultWatchVideo + YogaActivity.resultWatchVideo;

        int result = ((sumofAllResults * 100) / 300);
        SharedPrefUtility.getInstance(this).savePrefrences("CardioWorkoutP", result);
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
         //   toast("Start", zoneId);
        }

        @Override
        public void onUnityAdsFinish(String zoneId, UnityAds.FinishState result) {
            DeviceLog.debug("onUnityAdsFinish: " + zoneId + " - " + result);
           // toast("Finish", zoneId + " " + result);
        }

        @Override
        public void onUnityAdsError(UnityAds.UnityAdsError error, String message) {
            DeviceLog.debug("onUnityAdsError: " + error + " - " + message);
           // toast("Error", error + " " + message);

           /* TextView statusText = (TextView) findViewById(R.id.unityads_example_statustext);
            statusText.setText(error + " - " + message);*/
        }

   /*     private void toast(String callback, String msg) {
            Toast.makeText(getApplicationContext(), callback + ": " + msg, Toast.LENGTH_SHORT).show();
        }*/
    }
}
