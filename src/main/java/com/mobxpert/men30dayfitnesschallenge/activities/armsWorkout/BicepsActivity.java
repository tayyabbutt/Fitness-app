package com.mobxpert.men30dayfitnesschallenge.activities.armsWorkout;

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
import com.mobxpert.men30dayfitnesschallenge.adapter.armsWorkout.ArmWorkoutBicepsAdapter;
import com.mobxpert.men30dayfitnesschallenge.interfaces.OnMyWorkoutClickListner;
import com.mobxpert.men30dayfitnesschallenge.models.MyWorkout;
import com.mobxpert.men30dayfitnesschallenge.utils.MyDbPrefrences;
import com.mobxpert.men30dayfitnesschallenge.utils.SharedPrefUtility;
import com.mobxpert.men30dayfitnesschallenge.youtubePlayer.YouTubePlayerActivity;

import java.util.ArrayList;
import java.util.List;


public class BicepsActivity extends AppCompatActivity {
    RecyclerView recycler_view;
    ArmWorkoutBicepsAdapter armWorkoutBicepsAdapter;
    List<MyWorkout> bicepList = new ArrayList<>();
    boolean isExerciseDone = false;
    int itemPosition;
    AdView mAdView;
    public static int resultWatchVideo = 0;

    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_biceps);
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
        if (MyDbPrefrences.getArmWorkoutBiceps(BicepsActivity.this) != null) {
            bicepList.clear();
            bicepList = MyDbPrefrences.getArmWorkoutBiceps(BicepsActivity.this);
        } else {
            initializingList();
        }
        setAdapterAfterGettingResultFromPlayer();


        resultWatchVideo = getPercentageOfBicepsExcercise(bicepList);


    }


    private void initializingList() {
        bicepList.add(0, new MyWorkout(R.string.biceps_arm_exercise_1, R.string.biceps, "LY1V6UbRHFM",R.string.repAndSets));
        bicepList.add(1, new MyWorkout(R.string.biceps_arm_exercise_2, R.string.biceps, "BsULGO70tcU",R.string.repAndSets));
        bicepList.add(2, new MyWorkout(R.string.biceps_arm_exercise_3, R.string.biceps, "_2caXWsxlNk",R.string.repAndSets));
        bicepList.add(3, new MyWorkout(R.string.biceps_arm_exercise_4, R.string.biceps, "85kXYq7Ssh4",R.string.repAndSets));
        bicepList.add(4, new MyWorkout(R.string.biceps_arm_exercise_5, R.string.biceps, "TwD-YGVP4BkHow",R.string.repAndSets));
        bicepList.add(5, new MyWorkout(R.string.biceps_arm_exercise_6, R.string.biceps, "kwG2ipFRgfo",R.string.repAndSets));
    }

    private void setAdapterAfterGettingResultFromPlayer() {
        recycler_view.setHasFixedSize(true);

        recycler_view.setLayoutManager(new LinearLayoutManager(this));

        armWorkoutBicepsAdapter = new ArmWorkoutBicepsAdapter(BicepsActivity.this, bicepList, itemPosition, isExerciseDone, new OnMyWorkoutClickListner() {
            @Override
            public void onListItemClicked(int position, MyWorkout myWorkout) {
                Bundle bundle = new Bundle();
                bundle.putSerializable("myWorkout", myWorkout);
                bundle.putInt("position", position);
                Intent intent = new Intent(BicepsActivity.this, YouTubePlayerActivity.class);
                intent.putExtras(bundle);

                startActivityForResult(intent, 1);

            }

        });

        recycler_view.setAdapter(armWorkoutBicepsAdapter);
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        if (resultCode == RESULT_OK) {

            isExerciseDone = data.getBooleanExtra("isDone", false);
            itemPosition = data.getIntExtra("itemPosition", -1);
            setAdapterAfterGettingResultFromPlayer();


            // tvResult.setText(data.getIntExtra("result", -1) + "");
        }

    }

    @Override
    protected void onStop() {
        resultWatchVideo = getPercentageOfBicepsExcercise(bicepList);

        super.onStop();
    }

    @Override
    protected void onResume() {
        resultWatchVideo = getPercentageOfBicepsExcercise(bicepList);

        super.onResume();
    }

    @Override
    protected void onRestart() {
        resultWatchVideo = getPercentageOfBicepsExcercise(bicepList);
        super.onRestart();
    }


    @Override
    public void onBackPressed() {
       // MainActivity.mainscreenloadtime++;
        resultWatchVideo = getPercentageOfBicepsExcercise(bicepList);

        super.onBackPressed();
    }

    public int getPercentageOfBicepsExcercise(List<MyWorkout> workoutList) {

        List<MyWorkout> watchVideos = new ArrayList<>();

        watchVideos = getBicepsWatchVideos(workoutList);

        int result = ((watchVideos.size() * 100) / workoutList.size());
        SharedPrefUtility.getInstance(this).savePrefrences("BicepsArm", result);
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
