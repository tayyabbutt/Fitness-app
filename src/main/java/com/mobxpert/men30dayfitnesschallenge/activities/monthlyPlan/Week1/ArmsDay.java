package com.mobxpert.men30dayfitnesschallenge.activities.monthlyPlan.Week1;

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
import com.mobxpert.men30dayfitnesschallenge.activities.monthlyPlan.adapter.Week1.ArmsDayAdapter;
import com.mobxpert.men30dayfitnesschallenge.interfaces.OnMyWorkoutClickListner;
import com.mobxpert.men30dayfitnesschallenge.models.MyWorkout;
import com.mobxpert.men30dayfitnesschallenge.utils.MyDbPrefrences;
import com.mobxpert.men30dayfitnesschallenge.utils.SharedPrefUtility;
import com.mobxpert.men30dayfitnesschallenge.youtubePlayer.YouTubePlayerActivity;

import java.util.ArrayList;
import java.util.List;

public class ArmsDay extends AppCompatActivity {
    RecyclerView recycler_view;
    ArmsDayAdapter adapter;
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
        if (MyDbPrefrences.getWeek1Day3Workout(ArmsDay.this) != null) {
            myWorkoutList.clear();
            myWorkoutList = MyDbPrefrences.getWeek1Day3Workout(ArmsDay.this);
            resultWatchVideo = getPercentageOfWeeklyWorkout(myWorkoutList);
        } else {
            initializingList();
        }
        setAdapter();
    }

    private void initializingList() {
        myWorkoutList.add(0, new MyWorkout(R.string.biceps_arm_exercise_1, R.string.biceps, "LY1V6UbRHFM",R.string.repAndSets));
        myWorkoutList.add(1, new MyWorkout(R.string.biceps_arm_exercise_2, R.string.biceps, "BsULGO70tcU",R.string.repAndSets));
        myWorkoutList.add(2, new MyWorkout(R.string.biceps_arm_exercise_3, R.string.biceps, "_2caXWsxlNk",R.string.repAndSets));
        myWorkoutList.add(3, new MyWorkout(R.string.biceps_arm_exercise_4, R.string.biceps, "85kXYq7Ssh4",R.string.repAndSets));
        myWorkoutList.add(4, new MyWorkout(R.string.biceps_arm_exercise_5, R.string.biceps, "TwD-YGVP4BkHow",R.string.repAndSets));
        myWorkoutList.add(5, new MyWorkout(R.string.biceps_arm_exercise_6, R.string.biceps, "kwG2ipFRgfo",R.string.repAndSets));
        myWorkoutList.add(6, new MyWorkout(R.string.forearm_arm_exercise_1, R.string.forearm, "d2SJcvPVWn4",R.string.repAndSets));
        myWorkoutList.add(7, new MyWorkout(R.string.forearm_arm_exercise_2, R.string.forearm, "AIr3zMu5ApA",R.string.repAndSets));
        myWorkoutList.add(8, new MyWorkout(R.string.forearm_arm_exercise_3, R.string.forearm, "eXs25meoZr0",R.string.repAndSets));
        myWorkoutList.add(9, new MyWorkout(R.string.forearm_arm_exercise_4, R.string.forearm, "nRgxYX2Ve9w",R.string.repAndSets));
        myWorkoutList.add(10, new MyWorkout(R.string.forearm_arm_exercise_5, R.string.forearm, "ywvAN3q3soM",R.string.repAndSets));
        myWorkoutList.add(11, new MyWorkout(R.string.forearm_arm_exercise_6, R.string.forearm, "LMdNTHH6G8I",R.string.repAndSets));
        myWorkoutList.add(12, new MyWorkout(R.string.forearm_arm_exercise_7, R.string.forearm, "6l1FyKL_dmg",R.string.repAndSets));
        myWorkoutList.add(13, new MyWorkout(R.string.forearm_arm_exercise_8, R.string.forearm, "Tgi5SNDbBZQ",R.string.repAndSets));
        myWorkoutList.add(14, new MyWorkout(R.string.forearm_arm_exercise_9, R.string.forearm, "1lAW1OrJ7UU",R.string.repAndSets));
        myWorkoutList.add(15, new MyWorkout(R.string.triceps_arm_exercise_1, R.string.triceps, "xiMeMPZQzGQ",R.string.repAndSets));
        myWorkoutList.add(16, new MyWorkout(R.string.triceps_arm_exercise_2, R.string.triceps, "2-LAMcpzODU",R.string.repAndSets));
        myWorkoutList.add(17, new MyWorkout(R.string.triceps_arm_exercise_3, R.string.triceps, "nEF0bv2FW94",R.string.repAndSets));
        myWorkoutList.add(18, new MyWorkout(R.string.triceps_arm_exercise_4, R.string.triceps, "nEF0bv2FW94",R.string.repAndSets));
        myWorkoutList.add(19, new MyWorkout(R.string.triceps_arm_exercise_5, R.string.triceps, "d_KZxkY_0cM",R.string.repAndSets));
        myWorkoutList.add(20, new MyWorkout(R.string.triceps_arm_exercise_6, R.string.triceps, "F3OK3KA2NZM",R.string.repAndSets));
        myWorkoutList.add(21, new MyWorkout(R.string.triceps_arm_exercise_7, R.string.triceps, "T1L4smOP0L8",R.string.repAndSets));

    }

    private void setAdapter() {
        recycler_view.setHasFixedSize(true);
        recycler_view.setLayoutManager(new LinearLayoutManager(this));
        adapter = new ArmsDayAdapter(ArmsDay.this, myWorkoutList, itemPosition, isExerciseDone, new OnMyWorkoutClickListner() {
            @Override
            public void onListItemClicked(int position, MyWorkout myWorkout) {

                Bundle bundle = new Bundle();
                bundle.putSerializable("myWorkout", myWorkout);
                bundle.putInt("position", position);
                Intent intent = new Intent(ArmsDay.this, YouTubePlayerActivity.class);
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
        SharedPrefUtility.getInstance(this).savePrefrences("Week1Day3", result);
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
