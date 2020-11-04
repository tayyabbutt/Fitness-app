package com.mobxpert.men30dayfitnesschallenge.activities.monthlyPlan.Week3;

import android.content.Intent;
import android.os.Bundle;
import android.provider.Settings;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;
import com.mobxpert.men30dayfitnesschallenge.R;
import com.mobxpert.men30dayfitnesschallenge.activities.MainActivity;
import com.mobxpert.men30dayfitnesschallenge.activities.monthlyPlan.adapter.Week3.Week3Day5Adapter;
import com.mobxpert.men30dayfitnesschallenge.interfaces.OnMyWorkoutClickListner;
import com.mobxpert.men30dayfitnesschallenge.models.MyWorkout;
import com.mobxpert.men30dayfitnesschallenge.utils.MyDbPrefrences;
import com.mobxpert.men30dayfitnesschallenge.utils.SharedPrefUtility;
import com.mobxpert.men30dayfitnesschallenge.youtubePlayer.YouTubePlayerActivity;

import java.util.ArrayList;
import java.util.List;

public class Week3Day5 extends AppCompatActivity {
    RecyclerView recycler_view;
    Week3Day5Adapter adapter;
    List<MyWorkout> myWorkoutList = new ArrayList<>();
    int itemPosition;
    boolean isExerciseDone;
    AdView mAdView;
    public static int resultWatchVideo = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_arms_day);
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
        if (MyDbPrefrences.getWeek3Day5Workout(Week3Day5.this) != null) {
            myWorkoutList.clear();
            myWorkoutList = MyDbPrefrences.getWeek3Day5Workout(Week3Day5.this);
            resultWatchVideo = getPercentageOfWeeklyWorkout(myWorkoutList);
        } else {
            initializingList();
        }
        setAdapter();
    }

    private void initializingList() {
        myWorkoutList.add(0, new MyWorkout(R.string.back_shoulder_exercise_1, R.string.backShoulder, "amCU-ziHITM",R.string.repAndSets));
        myWorkoutList.add(1, new MyWorkout(R.string.back_shoulder_exercise_2, R.string.backShoulder, "H530fW3kWfk",R.string.repAndSets));
        myWorkoutList.add(2, new MyWorkout(R.string.back_shoulder_exercise_3, R.string.backShoulder, "gzDawZwDC6Y",R.string.repAndSets));
        myWorkoutList.add(3, new MyWorkout(R.string.back_shoulder_exercise_4, R.string.backShoulder, "sT2q7elpVFs",R.string.repAndSets));
        myWorkoutList.add(4, new MyWorkout(R.string.back_shoulder_exercise_5, R.string.backShoulder, "oBGeXxnigsQ",R.string.repAndSets));
        myWorkoutList.add(5, new MyWorkout(R.string.back_shoulder_exercise_6, R.string.backShoulder, "P1QA7egaN-w",R.string.repAndSets));
        myWorkoutList.add(6, new MyWorkout(R.string.back_shoulder_exercise_7, R.string.backShoulder, "K-ilBw_D1a4",R.string.repAndSets));
        myWorkoutList.add(7, new MyWorkout(R.string.front_shoulder_exercise_1, R.string.frontShoulder, "qEwKCR5JCog",R.string.repAndSets));
        myWorkoutList.add(8, new MyWorkout(R.string.front_shoulder_exercise_2, R.string.frontShoulder, "3VcKaXpzqRo",R.string.repAndSets));
        myWorkoutList.add(9, new MyWorkout(R.string.front_shoulder_exercise_3, R.string.frontShoulder, "yfNg5sFndbw",R.string.repAndSets));
        myWorkoutList.add(10, new MyWorkout(R.string.front_shoulder_exercise_4, R.string.frontShoulder, "ECWxumBMLVQ",R.string.repAndSets));
        myWorkoutList.add(11, new MyWorkout(R.string.front_shoulder_exercise_5, R.string.frontShoulder, "X60-yTMOJfw",R.string.repAndSets));
        myWorkoutList.add(12, new MyWorkout(R.string.front_shoulder_exercise_6, R.string.frontShoulder, "-t7fuZ0KhDA",R.string.repAndSets));
        myWorkoutList.add(13, new MyWorkout(R.string.traps_shoulder_exercise_1, R.string.traps, "cT5_GyOXIgE",R.string.repAndSets));
        myWorkoutList.add(14, new MyWorkout(R.string.traps_shoulder_exercise_2, R.string.traps, "P-k64JwMJSo",R.string.repAndSets));
        myWorkoutList.add(15, new MyWorkout(R.string.traps_shoulder_exercise_3, R.string.traps, "IhZLB48kluc",R.string.repAndSets));
        myWorkoutList.add(16, new MyWorkout(R.string.traps_shoulder_exercise_4, R.string.traps, "xDt6qbKgLkY",R.string.repAndSets));
        myWorkoutList.add(17, new MyWorkout(R.string.traps_shoulder_exercise_5, R.string.traps, "6_sx_2YpJkw",R.string.repAndSets));
        myWorkoutList.add(18, new MyWorkout(R.string.traps_shoulder_exercise_6, R.string.traps, "pqYr_lb04O4",R.string.repAndSets));
        myWorkoutList.add(19, new MyWorkout(R.string.traps_shoulder_exercise_7, R.string.traps, "GTOn4JiBWCQ",R.string.repAndSets));
        myWorkoutList.add(20, new MyWorkout(R.string.traps_shoulder_exercise_8, R.string.traps, "GaHtS9SUqh4",R.string.repAndSets));

    }

    private void setAdapter() {
        recycler_view.setHasFixedSize(true);

        recycler_view.setLayoutManager(new LinearLayoutManager(this));
        adapter = new Week3Day5Adapter(Week3Day5.this, myWorkoutList, itemPosition, isExerciseDone, new OnMyWorkoutClickListner() {
            @Override
            public void onListItemClicked(int position, MyWorkout myWorkout) {

                Bundle bundle = new Bundle();
                bundle.putSerializable("myWorkout", myWorkout);
                bundle.putInt("position", position);
                Intent intent = new Intent(Week3Day5.this, YouTubePlayerActivity.class);
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
        //MainActivity.mainscreenloadtime++;
        resultWatchVideo = getPercentageOfWeeklyWorkout(myWorkoutList);

        super.onBackPressed();
    }

    public int getPercentageOfWeeklyWorkout(List<MyWorkout> workoutList) {

        List<MyWorkout> watchVideos = new ArrayList<>();

        watchVideos = getBicepsWatchVideos(workoutList);

        int result = ((watchVideos.size() * 100) / workoutList.size());
        SharedPrefUtility.getInstance(this).savePrefrences("Week3Day5", result);
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
}
