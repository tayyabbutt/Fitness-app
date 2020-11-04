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
import com.mobxpert.men30dayfitnesschallenge.activities.monthlyPlan.adapter.Week3.Week3Day1Adapter;
import com.mobxpert.men30dayfitnesschallenge.interfaces.OnMyWorkoutClickListner;
import com.mobxpert.men30dayfitnesschallenge.models.MyWorkout;
import com.mobxpert.men30dayfitnesschallenge.utils.MyDbPrefrences;
import com.mobxpert.men30dayfitnesschallenge.utils.SharedPrefUtility;
import com.mobxpert.men30dayfitnesschallenge.youtubePlayer.YouTubePlayerActivity;

import java.util.ArrayList;
import java.util.List;


public class Week3Day1 extends AppCompatActivity {
    RecyclerView recycler_view;
    Week3Day1Adapter adapter;
    List<MyWorkout> myWorkoutList = new ArrayList<>();
    int itemPosition;
    boolean isExerciseDone;
    AdView mAdView;

    public static int resultWatchVideo = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chest_day);
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
        if (MyDbPrefrences.getWeek3Day1Workout(Week3Day1.this) != null) {
            myWorkoutList.clear();
            myWorkoutList = MyDbPrefrences.getWeek3Day1Workout(Week3Day1.this);
            resultWatchVideo = getPercentageOfWeeklyWorkout(myWorkoutList);
        } else {
            initializingList();
        }
        setAdapter();
    }

    private void initializingList() {
        myWorkoutList.add(0, new MyWorkout(R.string.lower_chest_exercise_1, R.string.lowerChest, "SKPab2YC8BE",R.string.repAndSets));
        myWorkoutList.add(1, new MyWorkout(R.string.lower_chest_exercise_2, R.string.lowerChest, "0xRvl4Qv3ZY",R.string.repAndSets));
        myWorkoutList.add(2, new MyWorkout(R.string.lower_chest_exercise_3, R.string.lowerChest, "LfyQBUKR8SE",R.string.repAndSets));
        myWorkoutList.add(3, new MyWorkout(R.string.lower_chest_exercise_4, R.string.lowerChest, "ilcbmIoz9S4",R.string.repAndSets));
        myWorkoutList.add(4, new MyWorkout(R.string.lower_chest_exercise_5, R.string.lowerChest, "Qr7dstCeYtw",R.string.repAndSets));
        myWorkoutList.add(5, new MyWorkout(R.string.lower_chest_exercise_6, R.string.lowerChest, "Iwe6AmxVf7o",R.string.repAndSets));
        myWorkoutList.add(6, new MyWorkout(R.string.lower_chest_exercise_7, R.string.lowerChest, "dX_nSOOJIsE",R.string.repAndSets));
        myWorkoutList.add(7, new MyWorkout(R.string.middle_chest_exercise_1,R.string.middleChest, "Qr7dstCeYtw",R.string.repAndSets));
        myWorkoutList.add(8, new MyWorkout(R.string.middle_chest_exercise_1, R.string.middleChest, "GtHNC-5GtR0",R.string.repAndSets));
        myWorkoutList.add(9, new MyWorkout(R.string.middle_chest_exercise_1, R.string.middleChest, "nEF0bv2FW94",R.string.repAndSets));
        myWorkoutList.add(10, new MyWorkout(R.string.middle_chest_exercise_1, R.string.middleChest, "eozdVDA78K0",R.string.repAndSets));
        myWorkoutList.add(11, new MyWorkout(R.string.middle_chest_exercise_1, R.string.middleChest, "WCAIi9xvNR8",R.string.repAndSets));
        myWorkoutList.add(12, new MyWorkout(R.string.middle_chest_exercise_1, R.string.middleChest, "tpLnfSQJ0gg",R.string.repAndSets));
        myWorkoutList.add(13, new MyWorkout(R.string.upper_chest_exercise_1, R.string.upperchest, "Eh00_rniF8E", R.string.repAndSets));
        myWorkoutList.add(14, new MyWorkout(R.string.upper_chest_exercise_2, R.string.upperchest, "DbFgADa2PL8", R.string.repAndSets));
        myWorkoutList.add(15, new MyWorkout(R.string.upper_chest_exercise_3, R.string.upperchest, "DbFgADa2PL8", R.string.repAndSets));
        myWorkoutList.add(16, new MyWorkout(R.string.upper_chest_exercise_4, R.string.upperchest, "VmB1G1K7v94", R.string.repAndSets));
        myWorkoutList.add(17, new MyWorkout(R.string.upper_chest_exercise_5, R.string.upperchest, "rT7DgCr-3pg", R.string.repAndSets));
        myWorkoutList.add(18, new MyWorkout(R.string.upper_chest_exercise_6, R.string.upperchest, "xUm0BiZCWlQ", R.string.repAndSets));
        myWorkoutList.add(19, new MyWorkout(R.string.upper_chest_exercise_7, R.string.upperchest, "Iwe6AmxVf7o", R.string.repAndSets));
        myWorkoutList.add(20, new MyWorkout(R.string.upper_chest_exercise_8, R.string.upperchest, "tpLnfSQJ0gg", R.string.repAndSets));
    }

    private void setAdapter() {
        recycler_view.setHasFixedSize(true);

        recycler_view.setLayoutManager(new LinearLayoutManager(this));
        adapter = new Week3Day1Adapter(Week3Day1.this, myWorkoutList, itemPosition, isExerciseDone, new OnMyWorkoutClickListner() {
            @Override
            public void onListItemClicked(int position, MyWorkout myWorkout) {

                Bundle bundle = new Bundle();
                bundle.putSerializable("myWorkout", myWorkout);
                bundle.putInt("position", position);
                Intent intent = new Intent(Week3Day1.this, YouTubePlayerActivity.class);
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


    /* @Override
     protected void onStop() {
         resultWatchVideo = getPercentageOfWeeklyWorkout(myWorkoutList);

         super.onStop();
     }
 */
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

        watchVideos = getWeeklyWorkout(workoutList);

        int result = (((watchVideos.size() + 1) * 100) / workoutList.size());
        SharedPrefUtility.getInstance(this).savePrefrences("Week3Day1", result);
        return result;

    }


    private List<MyWorkout> getWeeklyWorkout(List<MyWorkout> myWorkouts) {

        List<MyWorkout> myWorkoutList = new ArrayList<>();
        for (MyWorkout myWorkout : myWorkouts) {

            if (myWorkout.isExerciseDone() == true) {
                myWorkoutList.add(myWorkout);
            }
        }
        return myWorkoutList;
    }
}
