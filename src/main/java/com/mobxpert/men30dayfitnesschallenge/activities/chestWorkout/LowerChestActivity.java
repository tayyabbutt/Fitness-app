package com.mobxpert.men30dayfitnesschallenge.activities.chestWorkout;

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
import com.mobxpert.men30dayfitnesschallenge.adapter.chestWorkout.LowerChestAdapter;
import com.mobxpert.men30dayfitnesschallenge.interfaces.OnMyWorkoutClickListner;
import com.mobxpert.men30dayfitnesschallenge.models.MyWorkout;
import com.mobxpert.men30dayfitnesschallenge.utils.MyDbPrefrences;
import com.mobxpert.men30dayfitnesschallenge.utils.SharedPrefUtility;
import com.mobxpert.men30dayfitnesschallenge.youtubePlayer.YouTubePlayerActivity;

import java.util.ArrayList;
import java.util.List;


public class LowerChestActivity extends AppCompatActivity {
    RecyclerView recycler_view;
    LowerChestAdapter adapter;
    List<MyWorkout> myWorkoutList = new ArrayList<>();
    int itemPosition;
    boolean isExerciseDone;
    AdView mAdView;
    public static int resultWatchVideo = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lower_chest);
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
        if (MyDbPrefrences.getChestWorkoutLowerChest(LowerChestActivity.this) != null) {
            myWorkoutList.clear();
            myWorkoutList = MyDbPrefrences.getChestWorkoutLowerChest(LowerChestActivity.this);
        } else {
            initializingList();
        }
        setAdapter();
        resultWatchVideo = getPercentageOfBicepsExcercise(myWorkoutList);

    }

    private void initializingList() {
        myWorkoutList.add(0, new MyWorkout(R.string.lower_chest_exercise_1, R.string.lowerChest, "SKPab2YC8BE",R.string.repAndSets));
        myWorkoutList.add(1, new MyWorkout(R.string.lower_chest_exercise_2, R.string.lowerChest, "0xRvl4Qv3ZY",R.string.repAndSets));
        myWorkoutList.add(2, new MyWorkout(R.string.lower_chest_exercise_3, R.string.lowerChest, "LfyQBUKR8SE",R.string.repAndSets));
        myWorkoutList.add(3, new MyWorkout(R.string.lower_chest_exercise_4, R.string.lowerChest, "ilcbmIoz9S4",R.string.repAndSets));
        myWorkoutList.add(4, new MyWorkout(R.string.lower_chest_exercise_5, R.string.lowerChest, "Qr7dstCeYtw",R.string.repAndSets));
        myWorkoutList.add(5, new MyWorkout(R.string.lower_chest_exercise_6, R.string.lowerChest, "Iwe6AmxVf7o",R.string.repAndSets));
        myWorkoutList.add(6, new MyWorkout(R.string.lower_chest_exercise_7, R.string.lowerChest, "dX_nSOOJIsE",R.string.repAndSets));
    }


    private void setAdapter() {
        recycler_view.setHasFixedSize(true);

        recycler_view.setLayoutManager(new LinearLayoutManager(this));
        adapter = new LowerChestAdapter(LowerChestActivity.this, myWorkoutList, itemPosition, isExerciseDone, new OnMyWorkoutClickListner() {
            @Override
            public void onListItemClicked(int position, MyWorkout myWorkout) {

                Bundle bundle = new Bundle();
                bundle.putSerializable("myWorkout", myWorkout);
                bundle.putInt("position", position);
                Intent intent = new Intent(LowerChestActivity.this, YouTubePlayerActivity.class);
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
    protected void onStop() {
        resultWatchVideo = getPercentageOfBicepsExcercise(myWorkoutList);

        super.onStop();
    }

    @Override
    protected void onResume() {
        resultWatchVideo = getPercentageOfBicepsExcercise(myWorkoutList);

        super.onResume();
    }

    @Override
    protected void onRestart() {
        resultWatchVideo = getPercentageOfBicepsExcercise(myWorkoutList);

        super.onRestart();
    }


    @Override
    public void onBackPressed() {
       // MainActivity.mainscreenloadtime++;
        resultWatchVideo = getPercentageOfBicepsExcercise(myWorkoutList);

        super.onBackPressed();
    }

    public int getPercentageOfBicepsExcercise(List<MyWorkout> workoutList) {

        List<MyWorkout> watchVideos = new ArrayList<>();

        watchVideos = getBicepsWatchVideos(workoutList);

        int result = ((watchVideos.size() * 100) / workoutList.size());
        SharedPrefUtility.getInstance(this).savePrefrences("LowerChest", result);
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
