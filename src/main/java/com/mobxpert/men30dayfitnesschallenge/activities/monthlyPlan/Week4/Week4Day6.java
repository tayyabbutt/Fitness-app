package com.mobxpert.men30dayfitnesschallenge.activities.monthlyPlan.Week4;

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
import com.mobxpert.men30dayfitnesschallenge.activities.monthlyPlan.adapter.Week4.Week4Day6Adapter;
import com.mobxpert.men30dayfitnesschallenge.interfaces.OnMyWorkoutClickListner;
import com.mobxpert.men30dayfitnesschallenge.models.MyWorkout;
import com.mobxpert.men30dayfitnesschallenge.utils.MyDbPrefrences;
import com.mobxpert.men30dayfitnesschallenge.utils.SharedPrefUtility;
import com.mobxpert.men30dayfitnesschallenge.youtubePlayer.YouTubePlayerActivity;

import java.util.ArrayList;
import java.util.List;

public class Week4Day6 extends AppCompatActivity {
    RecyclerView recycler_view;
    Week4Day6Adapter adapter;
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
        if (MyDbPrefrences.getWeek4Day6Workout(Week4Day6.this) != null) {
            myWorkoutList.clear();
            myWorkoutList = MyDbPrefrences.getWeek4Day6Workout(Week4Day6.this);
            resultWatchVideo = getPercentageOfWeeklyWorkout(myWorkoutList);
        } else {
            initializingList();
        }
        setAdapter();
    }

    private void initializingList() {
        myWorkoutList.add(0, new MyWorkout(R.string.back_thigh_exercise_1, R.string.backThigs, "gOit1Rfov7I",R.string.repAndSets));
        myWorkoutList.add(1, new MyWorkout(R.string.back_thigh_exercise_2, R.string.backThigs, "e_YHV9psOiY",R.string.repAndSets));
        myWorkoutList.add(2, new MyWorkout(R.string.back_thigh_exercise_3, R.string.backThigs, "1Tq3QdYUuHs",R.string.repAndSets));
        myWorkoutList.add(3, new MyWorkout(R.string.back_thigh_exercise_4, R.string.backThigs, "IZxyjW7MPJQ",R.string.repAndSets));
        myWorkoutList.add(4, new MyWorkout(R.string.back_thigh_exercise_5, R.string.backThigs, "D7KaRcUTQeE",R.string.repAndSets));
        myWorkoutList.add(5, new MyWorkout(R.string.back_thigh_exercise_6, R.string.backThigs, "JCXUYuzwNrM",R.string.repAndSets));
        myWorkoutList.add(6, new MyWorkout(R.string.back_thigh_exercise_7, R.string.backThigs, "7NvOuty_Fnc",R.string.repAndSets));
        myWorkoutList.add(7, new MyWorkout(R.string.calfs_exercise_1, R.string.calfs, "2tvsvCRCQZY",R.string.repAndSets));
        myWorkoutList.add(8, new MyWorkout(R.string.calfs_exercise_2, R.string.calfs, "3UWi44yN-wM",R.string.repAndSets));
        myWorkoutList.add(9, new MyWorkout(R.string.calfs_exercise_3, R.string.calfs, "M4FojyRAcuE",R.string.repAndSets));
        myWorkoutList.add(10, new MyWorkout(R.string.front_thigh_exercise_1, R.string.frontThigs, "gOit1Rfov7I",R.string.repAndSets));
        myWorkoutList.add(11, new MyWorkout(R.string.front_thigh_exercise_2, R.string.frontThigs, "ODbkgFgYCgQ",R.string.repAndSets));
        myWorkoutList.add(12, new MyWorkout(R.string.front_thigh_exercise_3, R.string.frontThigs, "IZxyjW7MPJQ",R.string.repAndSets));
        myWorkoutList.add(13, new MyWorkout(R.string.front_thigh_exercise_4, R.string.frontThigs, "ndKAF-VUhzM",R.string.repAndSets));
        myWorkoutList.add(14, new MyWorkout(R.string.front_thigh_exercise_5, R.string.frontThigs, "1Tq3QdYUuHs",R.string.repAndSets));
        myWorkoutList.add(15, new MyWorkout(R.string.front_thigh_exercise_6, R.string.frontThigs, "0tn5K9NlCfo",R.string.repAndSets));
        myWorkoutList.add(16, new MyWorkout(R.string.hips_exercise_1, R.string.hips, "CBaDRYtLHtM",R.string.repAndSets));
        myWorkoutList.add(17, new MyWorkout(R.string.hips_exercise_2, R.string.hips, "SEdqd1n0cvg",R.string.repAndSets));
        myWorkoutList.add(18, new MyWorkout(R.string.hips_exercise_3, R.string.hips, "2C-uNgKwPLE",R.string.repAndSets));
        myWorkoutList.add(19, new MyWorkout(R.string.hips_exercise_4, R.string.hips, "YSxHifyI6s8",R.string.repAndSets));
        myWorkoutList.add(20, new MyWorkout(R.string.hips_exercise_5, R.string.hips, "29k1TypoU4w",R.string.repAndSets));

    }

    private void setAdapter() {
        recycler_view.setHasFixedSize(true);


        recycler_view.setLayoutManager(new LinearLayoutManager(this));
        adapter = new Week4Day6Adapter(Week4Day6.this, myWorkoutList, itemPosition, isExerciseDone, new OnMyWorkoutClickListner() {
            @Override
            public void onListItemClicked(int position, MyWorkout myWorkout) {

                Bundle bundle = new Bundle();
                bundle.putSerializable("myWorkout", myWorkout);
                bundle.putInt("position", position);
                Intent intent = new Intent(Week4Day6.this, YouTubePlayerActivity.class);
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

    public int getPercentageOfWeeklyWorkout(List<MyWorkout> workoutList) {

        List<MyWorkout> watchVideos = new ArrayList<>();

        watchVideos = getBicepsWatchVideos(workoutList);

        int result = ((watchVideos.size() * 100) / workoutList.size());
        SharedPrefUtility.getInstance(this).savePrefrences("Week4Day6", result);
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
