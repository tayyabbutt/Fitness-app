package com.mobxpert.men30dayfitnesschallenge.activities.monthlyPlan.Week1;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.provider.Settings;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Toast;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;
import com.mobxpert.men30dayfitnesschallenge.R;
import com.mobxpert.men30dayfitnesschallenge.activities.monthlyPlan.adapter.Week1.AbsDayAdapter;
import com.mobxpert.men30dayfitnesschallenge.interfaces.OnMyWorkoutClickListner;
import com.mobxpert.men30dayfitnesschallenge.models.MyWorkout;
import com.mobxpert.men30dayfitnesschallenge.utils.MyDbPrefrences;
import com.mobxpert.men30dayfitnesschallenge.utils.SharedPrefUtility;
import com.mobxpert.men30dayfitnesschallenge.youtubePlayer.YouTubePlayerActivity;
import com.unity3d.ads.IUnityAdsListener;
import com.unity3d.ads.UnityAds;
import com.unity3d.ads.metadata.MediationMetaData;
import com.unity3d.ads.metadata.MetaData;
import com.unity3d.ads.metadata.PlayerMetaData;
import com.unity3d.services.core.log.DeviceLog;
import com.unity3d.services.core.misc.Utilities;

import java.util.ArrayList;
import java.util.List;

public class AbsDay extends AppCompatActivity {
    final private String gameId = "3094662";

    RecyclerView recycler_view;
    AbsDayAdapter adapter;
    List<MyWorkout> myWorkoutList = new ArrayList<>();
    int itemPosition;
    boolean isExerciseDone;
    AdView mAdView;
    private String interstitialPlacementId;

    public static int resultWatchVideo = 0;
    UnityAdsListener unityAdsListener;//= new UnityAdsListener();
    private static int ordinal = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_abs_day);

        unityAdsListener = new UnityAdsListener();
        initializeInterstitialAd();
       // unityInterstitialAd();
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
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

        recycler_view = findViewById(R.id.recycler_view);
        if (MyDbPrefrences.getWeek1Day4Workout(AbsDay.this) != null) {
            myWorkoutList.clear();
            myWorkoutList = MyDbPrefrences.getWeek1Day4Workout(AbsDay.this);
            resultWatchVideo = getPercentageOfWeeklyWorkout(myWorkoutList);
        } else {
            initializingList();
        }
        setAdapter();
    }

    private void initializingList() {
        myWorkoutList.add(0, new MyWorkout(R.string.lower_ab_exercise_1, R.string.lowerabs, "G0ysNevIv0w", R.string.repAndSets));
        myWorkoutList.add(1, new MyWorkout(R.string.lower_ab_exercise_2, R.string.lowerabs, "rJPsWH3ibVU", R.string.repAndSets));
        myWorkoutList.add(2, new MyWorkout(R.string.lower_ab_exercise_3, R.string.lowerabs, "6w30ZwGS7uI", R.string.repAndSets));
        myWorkoutList.add(3, new MyWorkout(R.string.lower_ab_exercise_4, R.string.lowerabs, "10sH4Ls-Pts", R.string.repAndSets));
        myWorkoutList.add(4, new MyWorkout(R.string.lower_ab_exercise_5, R.string.lowerabs, "NVkOuImkXlM", R.string.repAndSets));
        myWorkoutList.add(5, new MyWorkout(R.string.lower_ab_exercise_6, R.string.lowerabs, "gbEkJzaeFRk", R.string.repAndSets));
        myWorkoutList.add(6, new MyWorkout(R.string.lower_ab_exercise_7, R.string.lowerabs, "pSHjTRCQxIw", R.string.repAndSets));
        myWorkoutList.add(7, new MyWorkout(R.string.siedecutting_ab_exercise_1, R.string.sidecuting, "cDIYH5rH0qU", R.string.repAndSets));
        myWorkoutList.add(8, new MyWorkout(R.string.siedecutting_ab_exercise_2, R.string.sidecuting, "z1Kw1hfUq6U", R.string.repAndSets));
        myWorkoutList.add(9, new MyWorkout(R.string.siedecutting_ab_exercise_3, R.string.sidecuting, "wkD8rjkodUI", R.string.repAndSets));
        myWorkoutList.add(10, new MyWorkout(R.string.siedecutting_ab_exercise_4, R.string.sidecuting, "eazQpjRjy2U", R.string.repAndSets));
        myWorkoutList.add(11, new MyWorkout(R.string.siedecutting_ab_exercise_5, R.string.sidecuting, "hugRaDLJSi0", R.string.repAndSets));
        myWorkoutList.add(12, new MyWorkout(R.string.siedecutting_ab_exercise_6, R.string.sidecuting, "NKTzIM00gbU", R.string.repAndSets));
        myWorkoutList.add(13, new MyWorkout(R.string.upper_ab_exercise_1, R.string.uperabs, "YSQ6w0YynpI", R.string.repAndSets));
        myWorkoutList.add(14, new MyWorkout(R.string.upper_ab_exercise_2, R.string.uperabs, "NJQROeaBiVE", R.string.repAndSets));
        myWorkoutList.add(15, new MyWorkout(R.string.upper_ab_exercise_3, R.string.uperabs, "tVYg6qAntf4", R.string.repAndSets));
        myWorkoutList.add(16, new MyWorkout(R.string.upper_ab_exercise_4, R.string.uperabs, "De3Gl-nC7IQ", R.string.repAndSets));
        myWorkoutList.add(17, new MyWorkout(R.string.upper_ab_exercise_5, R.string.uperabs, "10sH4Ls-Pts", R.string.repAndSets));
        myWorkoutList.add(18, new MyWorkout(R.string.upper_ab_exercise_6, R.string.uperabs, "xqTh6NqbAtM", R.string.repAndSets));
        myWorkoutList.add(19, new MyWorkout(R.string.upper_ab_exercise_7, R.string.uperabs, "eazQpjRjy2U", R.string.repAndSets));
        myWorkoutList.add(20, new MyWorkout(R.string.upper_ab_exercise_8, R.string.uperabs, "pSHjTRCQxIw", R.string.repAndSets));

    }

    private void setAdapter() {
        recycler_view.setHasFixedSize(true);

        recycler_view.setLayoutManager(new LinearLayoutManager(this));
        adapter = new AbsDayAdapter(AbsDay.this, myWorkoutList, itemPosition, isExerciseDone, new OnMyWorkoutClickListner() {
            @Override
            public void onListItemClicked(int position, MyWorkout myWorkout) {

                Bundle bundle = new Bundle();
                bundle.putSerializable("myWorkout", myWorkout);
                bundle.putInt("position", position);
                Intent intent = new Intent(AbsDay.this, YouTubePlayerActivity.class);
                intent.putExtras(bundle);
                startActivityForResult(intent, 1);
            }

        });
        recycler_view.setAdapter(adapter);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == RESULT_OK) {
            isExerciseDone = data.getBooleanExtra("isDone", false);
            itemPosition = data.getIntExtra("itemPosition", -1);
            setAdapter();
        }

    }


    @Override
    protected void onResume() {
        resultWatchVideo = getPercentageOfWeeklyWorkout(myWorkoutList);

        super.onResume();
    }

    @Override
    protected void onRestart() {
        resultWatchVideo = getPercentageOfWeeklyWorkout(myWorkoutList);

        super.onRestart();
    }


    @Override
    public void onBackPressed() {
        // MainActivity.mainscreenloadtime++;
        resultWatchVideo = getPercentageOfWeeklyWorkout(myWorkoutList);


        super.onBackPressed();
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

        PlayerMetaData playerMetaData = new PlayerMetaData(this);
        playerMetaData.setServerId("rikshot");
        playerMetaData.commit();

        MediationMetaData ordinalMetaData = new MediationMetaData(this);
        ordinalMetaData.setOrdinal(ordinal++);
        ordinalMetaData.commit();

        UnityAds.show(this, interstitialPlacementId);
    }

    public int getPercentageOfWeeklyWorkout(List<MyWorkout> workoutList) {

        List<MyWorkout> watchVideos = new ArrayList<>();

        watchVideos = getBicepsWatchVideos(workoutList);

        int result = ((watchVideos.size() * 100) / workoutList.size());
        SharedPrefUtility.getInstance(this).savePrefrences("Week1Day4", result);
        return result;

    }


    private List<MyWorkout> getBicepsWatchVideos(List<MyWorkout> myWorkouts) {

        List<MyWorkout> myWorkoutList = new ArrayList<>();
        for (MyWorkout myWorkout : myWorkouts) {

            if (myWorkout.isExerciseDone() == true) {
                myWorkoutList.add(myWorkout);
            }
        }
        return myWorkoutList;
    }

/* LISTENER */

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

        toast("Ready", zoneId);
    }

    @Override
    public void onUnityAdsStart(String zoneId) {
        DeviceLog.debug("onUnityAdsStart: " + zoneId);
        toast("Start", zoneId);
    }

    @Override
    public void onUnityAdsFinish(String zoneId, UnityAds.FinishState result) {
        DeviceLog.debug("onUnityAdsFinish: " + zoneId + " - " + result);
        toast("Finish", zoneId + " " + result);
    }

    @Override
    public void onUnityAdsError(UnityAds.UnityAdsError error, String message) {
        DeviceLog.debug("onUnityAdsError: " + error + " - " + message);
        toast("Error", error + " " + message);

           /* TextView statusText = (TextView) findViewById(R.id.unityads_example_statustext);
            statusText.setText(error + " - " + message);*/
    }

    private void toast(String callback, String msg) {
        Toast.makeText(getApplicationContext(), callback + ": " + msg, Toast.LENGTH_SHORT).show();
    }
}

}
