package com.mobxpert.men30dayfitnesschallenge.activities.monthlyPlan;

import android.graphics.Color;
import android.os.Bundle;
import android.provider.Settings;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;
import com.mobxpert.men30dayfitnesschallenge.R;
import com.mobxpert.men30dayfitnesschallenge.activities.MainActivity;
import com.mobxpert.men30dayfitnesschallenge.activities.monthlyPlan.Week1.AbsDay;
import com.mobxpert.men30dayfitnesschallenge.activities.monthlyPlan.Week1.ArmsDay;
import com.mobxpert.men30dayfitnesschallenge.activities.monthlyPlan.Week1.ChestDay;
import com.mobxpert.men30dayfitnesschallenge.activities.monthlyPlan.Week1.LegsDay;
import com.mobxpert.men30dayfitnesschallenge.activities.monthlyPlan.Week1.ShoulderDay;
import com.mobxpert.men30dayfitnesschallenge.activities.monthlyPlan.Week1.WingsDay;
import com.mobxpert.men30dayfitnesschallenge.activities.monthlyPlan.Week2.AbsDayWeek2Day4;
import com.mobxpert.men30dayfitnesschallenge.activities.monthlyPlan.Week2.ChestDayWeek2Day1;
import com.mobxpert.men30dayfitnesschallenge.activities.monthlyPlan.Week2.Week2Day2;
import com.mobxpert.men30dayfitnesschallenge.activities.monthlyPlan.Week2.Week2Day3;
import com.mobxpert.men30dayfitnesschallenge.activities.monthlyPlan.Week2.Week2Day5;
import com.mobxpert.men30dayfitnesschallenge.activities.monthlyPlan.Week2.Week2Day6;
import com.mobxpert.men30dayfitnesschallenge.activities.monthlyPlan.Week3.Week3Day1;
import com.mobxpert.men30dayfitnesschallenge.activities.monthlyPlan.Week3.Week3Day2;
import com.mobxpert.men30dayfitnesschallenge.activities.monthlyPlan.Week3.Week3Day3;
import com.mobxpert.men30dayfitnesschallenge.activities.monthlyPlan.Week3.Week3Day4;
import com.mobxpert.men30dayfitnesschallenge.activities.monthlyPlan.Week3.Week3Day5;
import com.mobxpert.men30dayfitnesschallenge.activities.monthlyPlan.Week3.Week3Day6;
import com.mobxpert.men30dayfitnesschallenge.activities.monthlyPlan.Week4.Week4Day1;
import com.mobxpert.men30dayfitnesschallenge.activities.monthlyPlan.Week4.Week4Day2;
import com.mobxpert.men30dayfitnesschallenge.activities.monthlyPlan.Week4.Week4Day3;
import com.mobxpert.men30dayfitnesschallenge.activities.monthlyPlan.Week4.Week4Day4;
import com.mobxpert.men30dayfitnesschallenge.activities.monthlyPlan.Week4.Week4Day5;
import com.mobxpert.men30dayfitnesschallenge.activities.monthlyPlan.Week4.Week4Day6;
import com.mobxpert.men30dayfitnesschallenge.utils.SharedPrefUtility;
import com.mobxpert.men30dayfitnesschallenge.utils.Utility;

public class SelectPlanActivity extends AppCompatActivity {
    AdView mAdView;
    TextView week1Day1, week1Day2, week1Day3, week1Day4, week1Day5, week1Day6, week1Day7,
            week2Day1, week2Day2, week2Day3, week2Day4, week2Day5, week2Day6, week2Day7,
            week3Day1, week3Day2, week3Day3, week3Day4, week3Day5, week3Day6, week3Day7,
            week4Day1, week4Day2, week4Day3, week4Day4, week4Day5, week4Day6, week4Day7;

    View week1View1, week1View2, week1View3, week1View4, week1View5, week1View6,
            week2View1, week2View2, week2View3, week2View4, week2View5, week2View6,
            week3View1, week3View2, week3View3, week3View4, week3View5, week3View6,
            week4View1, week4View2, week4View3, week4View4, week4View5, week4View6;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_plan);
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

        init();


    }

    private void init() {
        week1Day1 = findViewById(R.id.week1Text1);
        week1Day2 = findViewById(R.id.week1Text2);
        week1Day3 = findViewById(R.id.week1Text3);
        week1Day4 = findViewById(R.id.week1Text4);
        week1Day5 = findViewById(R.id.week1Text5);
        week1Day6 = findViewById(R.id.week1Text6);
        week1Day7 = findViewById(R.id.week1Text7);

        week1View1 = findViewById(R.id.week1View1);
        week1View2 = findViewById(R.id.week1View2);
        week1View3 = findViewById(R.id.week1View3);
        week1View4 = findViewById(R.id.week1View4);
        week1View5 = findViewById(R.id.week1View5);
        week1View6 = findViewById(R.id.week1View6);

        week2Day1 = findViewById(R.id.week2Text1);
        week2Day2 = findViewById(R.id.week2Text2);
        week2Day3 = findViewById(R.id.week2Text3);
        week2Day4 = findViewById(R.id.week2Text4);
        week2Day5 = findViewById(R.id.week2Text5);
        week2Day6 = findViewById(R.id.week2Text6);
        week2Day7 = findViewById(R.id.week2Text7);

        week2View1 = findViewById(R.id.week2View1);
        week2View2 = findViewById(R.id.week2View2);
        week2View3 = findViewById(R.id.week2View3);
        week2View4 = findViewById(R.id.week2View4);
        week2View5 = findViewById(R.id.week2View5);
        week2View6 = findViewById(R.id.week2View6);

        week3Day1 = findViewById(R.id.week3Text1);
        week3Day2 = findViewById(R.id.week3Text2);
        week3Day3 = findViewById(R.id.week3Text3);
        week3Day4 = findViewById(R.id.week3Text4);
        week3Day5 = findViewById(R.id.week3Text5);
        week3Day6 = findViewById(R.id.week3Text6);
        week3Day7 = findViewById(R.id.week3Text7);

        week3View1 = findViewById(R.id.week3View1);
        week3View2 = findViewById(R.id.week3View2);
        week3View3 = findViewById(R.id.week3View3);
        week3View4 = findViewById(R.id.week3View4);
        week3View5 = findViewById(R.id.week3View5);
        week3View6 = findViewById(R.id.week3View6);

        week4Day1 = findViewById(R.id.week4Text1);
        week4Day2 = findViewById(R.id.week4Text2);
        week4Day3 = findViewById(R.id.week4Text3);
        week4Day4 = findViewById(R.id.week4Text4);
        week4Day5 = findViewById(R.id.week4Text5);
        week4Day6 = findViewById(R.id.week4Text6);
        week4Day7 = findViewById(R.id.week4Text7);

        week4View1 = findViewById(R.id.week4View1);
        week4View2 = findViewById(R.id.week4View2);
        week4View3 = findViewById(R.id.week4View3);
        week4View4 = findViewById(R.id.week4View4);
        week4View5 = findViewById(R.id.week4View5);
        week4View6 = findViewById(R.id.week4View6);

        week1Day1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Utility.launchActivity(SelectPlanActivity.this, ChestDay.class, false, null);
            }
        });
        week1Day2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Utility.launchActivity(SelectPlanActivity.this, WingsDay.class, false, null);
            }
        });
        week1Day3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Utility.launchActivity(SelectPlanActivity.this, ArmsDay.class, false, null);
            }
        });
        week1Day4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Utility.launchActivity(SelectPlanActivity.this, AbsDay.class, false, null);
            }
        });
        week1Day5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Utility.launchActivity(SelectPlanActivity.this, ShoulderDay.class, false, null);
            }
        });
        week1Day6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Utility.launchActivity(SelectPlanActivity.this, LegsDay.class, false, null);
            }
        });


        week2Day1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Utility.launchActivity(SelectPlanActivity.this, ChestDayWeek2Day1.class, false, null);
            }
        });
        week2Day2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Utility.launchActivity(SelectPlanActivity.this, Week2Day2.class, false, null);
            }
        });
        week2Day3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Utility.launchActivity(SelectPlanActivity.this, Week2Day3.class, false, null);
            }
        });
        week2Day4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Utility.launchActivity(SelectPlanActivity.this, AbsDayWeek2Day4.class, false, null);
            }
        });
        week2Day5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Utility.launchActivity(SelectPlanActivity.this, Week2Day5.class, false, null);
            }
        });
        week2Day6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Utility.launchActivity(SelectPlanActivity.this, Week2Day6.class, false, null);
            }
        });


        week3Day1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Utility.launchActivity(SelectPlanActivity.this, Week3Day1.class, false, null);
            }
        });
        week3Day2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Utility.launchActivity(SelectPlanActivity.this, Week3Day2.class, false, null);
            }
        });
        week3Day3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Utility.launchActivity(SelectPlanActivity.this, Week3Day3.class, false, null);
            }
        });
        week3Day4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Utility.launchActivity(SelectPlanActivity.this, Week3Day4.class, false, null);
            }
        });
        week3Day5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Utility.launchActivity(SelectPlanActivity.this, Week3Day5.class, false, null);
            }
        });
        week3Day6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Utility.launchActivity(SelectPlanActivity.this, Week3Day6.class, false, null);
            }
        });


        week4Day1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Utility.launchActivity(SelectPlanActivity.this, Week4Day1.class, false, null);
            }
        });
        week4Day2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Utility.launchActivity(SelectPlanActivity.this, Week4Day2.class, false, null);
            }
        });
        week4Day3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Utility.launchActivity(SelectPlanActivity.this, Week4Day3.class, false, null);
            }
        });
        week4Day4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Utility.launchActivity(SelectPlanActivity.this, Week4Day4.class, false, null);
            }
        });
        week4Day5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Utility.launchActivity(SelectPlanActivity.this, Week4Day5.class, false, null);
            }
        });
        week4Day6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Utility.launchActivity(SelectPlanActivity.this, Week4Day6.class, false, null);
            }
        });

        week1Day7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(SelectPlanActivity.this, "Rest Day", Toast.LENGTH_SHORT).show();
            }
        });
        week2Day7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(SelectPlanActivity.this, "Rest Day", Toast.LENGTH_SHORT).show();
            }
        });
        week3Day7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(SelectPlanActivity.this, "Rest Day", Toast.LENGTH_SHORT).show();
            }
        });
        week4Day7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(SelectPlanActivity.this, "Rest Day", Toast.LENGTH_SHORT).show();
            }
        });

        if (SharedPrefUtility.getInstance(this).getIntValue("Week1Day1") >= 100) {
            week1View1.setBackgroundColor(Color.parseColor("#FF0000"));
            week1Day1.setBackgroundResource(R.drawable.red_dot_drawable);
        }
        if (SharedPrefUtility.getInstance(this).getIntValue("Week1Day2") >= 100) {
            week1View2.setBackgroundColor(Color.parseColor("#FF0000"));
            week1Day2.setBackgroundResource(R.drawable.red_dot_drawable);
        }
        if (SharedPrefUtility.getInstance(this).getIntValue("Week1Day3") >= 100) {
            week1View3.setBackgroundColor(Color.parseColor("#FF0000"));
            week1Day3.setBackgroundResource(R.drawable.red_dot_drawable);
        }
        if (SharedPrefUtility.getInstance(this).getIntValue("Week1Day4") >= 100) {
            week1View4.setBackgroundColor(Color.parseColor("#FF0000"));
            week1Day4.setBackgroundResource(R.drawable.red_dot_drawable);
        }
        if (SharedPrefUtility.getInstance(this).getIntValue("Week1Day5") >= 100) {
            week1View5.setBackgroundColor(Color.parseColor("#FF0000"));
            week1Day5.setBackgroundResource(R.drawable.red_dot_drawable);
        }
        if (SharedPrefUtility.getInstance(this).getIntValue("Week1Day6") >= 100) {
            week1View6.setBackgroundColor(Color.parseColor("#FF0000"));
            week1Day6.setBackgroundResource(R.drawable.red_dot_drawable);
        }


        if (SharedPrefUtility.getInstance(this).getIntValue("Week2Day1") >= 100) {
            week2View1.setBackgroundColor(Color.parseColor("#FF0000"));
            week2Day1.setBackgroundResource(R.drawable.red_dot_drawable);
        }
        if (SharedPrefUtility.getInstance(this).getIntValue("Week2Day2") >= 100) {
            week2View2.setBackgroundColor(Color.parseColor("#FF0000"));
            week2Day2.setBackgroundResource(R.drawable.red_dot_drawable);
        }
        if (SharedPrefUtility.getInstance(this).getIntValue("Week2Day3") >= 100) {
            week2View3.setBackgroundColor(Color.parseColor("#FF0000"));
            week2Day3.setBackgroundResource(R.drawable.red_dot_drawable);
        }
        if (SharedPrefUtility.getInstance(this).getIntValue("Week2Day4") >= 100) {
            week2View4.setBackgroundColor(Color.parseColor("#FF0000"));
            week2Day4.setBackgroundResource(R.drawable.red_dot_drawable);
        }
        if (SharedPrefUtility.getInstance(this).getIntValue("Week2Day5") >= 100) {
            week2View5.setBackgroundColor(Color.parseColor("#FF0000"));
            week2Day5.setBackgroundResource(R.drawable.red_dot_drawable);
        }
        if (SharedPrefUtility.getInstance(this).getIntValue("Week2Day6") >= 100) {
            week2View6.setBackgroundColor(Color.parseColor("#FF0000"));
            week2Day6.setBackgroundResource(R.drawable.red_dot_drawable);
        }


        if (SharedPrefUtility.getInstance(this).getIntValue("Week3Day1") >= 100) {
            week3View1.setBackgroundColor(Color.parseColor("#FF0000"));
            week3Day1.setBackgroundResource(R.drawable.red_dot_drawable);
        }
        if (SharedPrefUtility.getInstance(this).getIntValue("Week3Day2") >= 100) {
            week3View2.setBackgroundColor(Color.parseColor("#FF0000"));
            week3Day2.setBackgroundResource(R.drawable.red_dot_drawable);
        }
        if (SharedPrefUtility.getInstance(this).getIntValue("Week3Day3") >= 100) {
            week3View3.setBackgroundColor(Color.parseColor("#FF0000"));
            week3Day3.setBackgroundResource(R.drawable.red_dot_drawable);
        }
        if (SharedPrefUtility.getInstance(this).getIntValue("Week3Day4") >= 100) {
            week3View4.setBackgroundColor(Color.parseColor("#FF0000"));
            week3Day4.setBackgroundResource(R.drawable.red_dot_drawable);
        }
        if (SharedPrefUtility.getInstance(this).getIntValue("Week3Day5") >= 100) {
            week3View5.setBackgroundColor(Color.parseColor("#FF0000"));
            week3Day5.setBackgroundResource(R.drawable.red_dot_drawable);
        }
        if (SharedPrefUtility.getInstance(this).getIntValue("Week3Day6") >= 100) {
            week3View6.setBackgroundColor(Color.parseColor("#FF0000"));
            week3Day6.setBackgroundResource(R.drawable.red_dot_drawable);
        }


        if (SharedPrefUtility.getInstance(this).getIntValue("Week4Day1") >= 100) {
            week4View1.setBackgroundColor(Color.parseColor("#FF0000"));
            week4Day1.setBackgroundResource(R.drawable.red_dot_drawable);
        }
        if (SharedPrefUtility.getInstance(this).getIntValue("Week4Day2") >= 100) {
            week4View2.setBackgroundColor(Color.parseColor("#FF0000"));
            week4Day2.setBackgroundResource(R.drawable.red_dot_drawable);
        }
        if (SharedPrefUtility.getInstance(this).getIntValue("Week4Day3") >= 100) {
            week4View3.setBackgroundColor(Color.parseColor("#FF0000"));
            week4Day3.setBackgroundResource(R.drawable.red_dot_drawable);
        }
        if (SharedPrefUtility.getInstance(this).getIntValue("Week4Day4") >= 100) {
            week4View4.setBackgroundColor(Color.parseColor("#FF0000"));
            week4Day4.setBackgroundResource(R.drawable.red_dot_drawable);
        }
        if (SharedPrefUtility.getInstance(this).getIntValue("Week4Day5") >= 100) {
            week4View5.setBackgroundColor(Color.parseColor("#FF0000"));
            week4Day5.setBackgroundResource(R.drawable.red_dot_drawable);
        }
        if (SharedPrefUtility.getInstance(this).getIntValue("Week4Day6") >= 100) {
            week4View6.setBackgroundColor(Color.parseColor("#FF0000"));
            week4Day6.setBackgroundResource(R.drawable.red_dot_drawable);
        }


    }

    @Override
    protected void onResume() {
        if (SharedPrefUtility.getInstance(this).getIntValue("Week1Day1") >= 100) {
            week1View1.setBackgroundColor(Color.parseColor("#FF0000"));
            week1Day1.setBackgroundResource(R.drawable.red_dot_drawable);
        }
        if (SharedPrefUtility.getInstance(this).getIntValue("Week1Day2") >= 100) {
            week1View2.setBackgroundColor(Color.parseColor("#FF0000"));
            week1Day2.setBackgroundResource(R.drawable.red_dot_drawable);
        }
        if (SharedPrefUtility.getInstance(this).getIntValue("Week1Day3") >= 100) {
            week1View3.setBackgroundColor(Color.parseColor("#FF0000"));
            week1Day3.setBackgroundResource(R.drawable.red_dot_drawable);
        }
        if (SharedPrefUtility.getInstance(this).getIntValue("Week1Day4") >= 100) {
            week1View4.setBackgroundColor(Color.parseColor("#FF0000"));
            week1Day4.setBackgroundResource(R.drawable.red_dot_drawable);
        }
        if (SharedPrefUtility.getInstance(this).getIntValue("Week1Day5") >= 100) {
            week1View5.setBackgroundColor(Color.parseColor("#FF0000"));
            week1Day5.setBackgroundResource(R.drawable.red_dot_drawable);
        }
        if (SharedPrefUtility.getInstance(this).getIntValue("Week1Day6") >= 100) {
            week1View6.setBackgroundColor(Color.parseColor("#FF0000"));
            week1Day6.setBackgroundResource(R.drawable.red_dot_drawable);
        }


        if (SharedPrefUtility.getInstance(this).getIntValue("Week2Day1") >= 100) {
            week2View1.setBackgroundColor(Color.parseColor("#FF0000"));
            week2Day1.setBackgroundResource(R.drawable.red_dot_drawable);
        }
        if (SharedPrefUtility.getInstance(this).getIntValue("Week2Day2") >= 100) {
            week2View2.setBackgroundColor(Color.parseColor("#FF0000"));
            week2Day2.setBackgroundResource(R.drawable.red_dot_drawable);
        }
        if (SharedPrefUtility.getInstance(this).getIntValue("Week2Day3") >= 100) {
            week2View3.setBackgroundColor(Color.parseColor("#FF0000"));
            week2Day3.setBackgroundResource(R.drawable.red_dot_drawable);
        }
        if (SharedPrefUtility.getInstance(this).getIntValue("Week2Day4") >= 100) {
            week2View4.setBackgroundColor(Color.parseColor("#FF0000"));
            week2Day4.setBackgroundResource(R.drawable.red_dot_drawable);
        }
        if (SharedPrefUtility.getInstance(this).getIntValue("Week2Day5") >= 100) {
            week2View5.setBackgroundColor(Color.parseColor("#FF0000"));
            week2Day5.setBackgroundResource(R.drawable.red_dot_drawable);
        }
        if (SharedPrefUtility.getInstance(this).getIntValue("Week2Day6") >= 100) {
            week2View6.setBackgroundColor(Color.parseColor("#FF0000"));
            week2Day6.setBackgroundResource(R.drawable.red_dot_drawable);
        }


        if (SharedPrefUtility.getInstance(this).getIntValue("Week3Day1") >= 100) {
            week3View1.setBackgroundColor(Color.parseColor("#FF0000"));
            week3Day1.setBackgroundResource(R.drawable.red_dot_drawable);
        }
        if (SharedPrefUtility.getInstance(this).getIntValue("Week3Day2") >= 100) {
            week3View2.setBackgroundColor(Color.parseColor("#FF0000"));
            week3Day2.setBackgroundResource(R.drawable.red_dot_drawable);
        }
        if (SharedPrefUtility.getInstance(this).getIntValue("Week3Day3") >= 100) {
            week3View3.setBackgroundColor(Color.parseColor("#FF0000"));
            week3Day3.setBackgroundResource(R.drawable.red_dot_drawable);
        }
        if (SharedPrefUtility.getInstance(this).getIntValue("Week3Day4") >= 100) {
            week3View4.setBackgroundColor(Color.parseColor("#FF0000"));
            week3Day4.setBackgroundResource(R.drawable.red_dot_drawable);
        }
        if (SharedPrefUtility.getInstance(this).getIntValue("Week3Day5") >= 100) {
            week3View5.setBackgroundColor(Color.parseColor("#FF0000"));
            week3Day5.setBackgroundResource(R.drawable.red_dot_drawable);
        }
        if (SharedPrefUtility.getInstance(this).getIntValue("Week3Day6") >= 100) {
            week3View6.setBackgroundColor(Color.parseColor("#FF0000"));
            week3Day6.setBackgroundResource(R.drawable.red_dot_drawable);
        }


        if (SharedPrefUtility.getInstance(this).getIntValue("Week4Day1") >= 100) {
            week4View1.setBackgroundColor(Color.parseColor("#FF0000"));
            week4Day1.setBackgroundResource(R.drawable.red_dot_drawable);
        }
        if (SharedPrefUtility.getInstance(this).getIntValue("Week4Day2") >= 100) {
            week4View2.setBackgroundColor(Color.parseColor("#FF0000"));
            week4Day2.setBackgroundResource(R.drawable.red_dot_drawable);
        }
        if (SharedPrefUtility.getInstance(this).getIntValue("Week4Day3") >= 100) {
            week4View3.setBackgroundColor(Color.parseColor("#FF0000"));
            week4Day3.setBackgroundResource(R.drawable.red_dot_drawable);
        }
        if (SharedPrefUtility.getInstance(this).getIntValue("Week4Day4") >= 100) {
            week4View4.setBackgroundColor(Color.parseColor("#FF0000"));
            week4Day4.setBackgroundResource(R.drawable.red_dot_drawable);
        }
        if (SharedPrefUtility.getInstance(this).getIntValue("Week4Day5") >= 100) {
            week4View5.setBackgroundColor(Color.parseColor("#FF0000"));
            week4Day5.setBackgroundResource(R.drawable.red_dot_drawable);
        }
        if (SharedPrefUtility.getInstance(this).getIntValue("Week4Day6") >= 100) {
            week4View6.setBackgroundColor(Color.parseColor("#FF0000"));
            week4Day6.setBackgroundResource(R.drawable.red_dot_drawable);
        }
        super.onResume();
    }

    @Override
    protected void onRestart() {
        if (SharedPrefUtility.getInstance(this).getIntValue("Week1Day1") >= 100) {
            week1View1.setBackgroundColor(Color.parseColor("#FF0000"));
            week1Day1.setBackgroundResource(R.drawable.red_dot_drawable);
        }
        if (SharedPrefUtility.getInstance(this).getIntValue("Week1Day2") >= 100) {
            week1View2.setBackgroundColor(Color.parseColor("#FF0000"));
            week1Day2.setBackgroundResource(R.drawable.red_dot_drawable);
        }
        if (SharedPrefUtility.getInstance(this).getIntValue("Week1Day3") >= 100) {
            week1View3.setBackgroundColor(Color.parseColor("#FF0000"));
            week1Day3.setBackgroundResource(R.drawable.red_dot_drawable);
        }
        if (SharedPrefUtility.getInstance(this).getIntValue("Week1Day4") >= 100) {
            week1View4.setBackgroundColor(Color.parseColor("#FF0000"));
            week1Day4.setBackgroundResource(R.drawable.red_dot_drawable);
        }
        if (SharedPrefUtility.getInstance(this).getIntValue("Week1Day5") >= 100) {
            week1View5.setBackgroundColor(Color.parseColor("#FF0000"));
            week1Day5.setBackgroundResource(R.drawable.red_dot_drawable);
        }
        if (SharedPrefUtility.getInstance(this).getIntValue("Week1Day6") >= 100) {
            week1View6.setBackgroundColor(Color.parseColor("#FF0000"));
            week1Day6.setBackgroundResource(R.drawable.red_dot_drawable);
        }


        if (SharedPrefUtility.getInstance(this).getIntValue("Week2Day1") >= 100) {
            week2View1.setBackgroundColor(Color.parseColor("#FF0000"));
            week2Day1.setBackgroundResource(R.drawable.red_dot_drawable);
        }
        if (SharedPrefUtility.getInstance(this).getIntValue("Week2Day2") >= 100) {
            week2View2.setBackgroundColor(Color.parseColor("#FF0000"));
            week2Day2.setBackgroundResource(R.drawable.red_dot_drawable);
        }
        if (SharedPrefUtility.getInstance(this).getIntValue("Week2Day3") >= 100) {
            week2View3.setBackgroundColor(Color.parseColor("#FF0000"));
            week2Day3.setBackgroundResource(R.drawable.red_dot_drawable);
        }
        if (SharedPrefUtility.getInstance(this).getIntValue("Week2Day4") >= 100) {
            week2View4.setBackgroundColor(Color.parseColor("#FF0000"));
            week2Day4.setBackgroundResource(R.drawable.red_dot_drawable);
        }
        if (SharedPrefUtility.getInstance(this).getIntValue("Week2Day5") >= 100) {
            week2View5.setBackgroundColor(Color.parseColor("#FF0000"));
            week2Day5.setBackgroundResource(R.drawable.red_dot_drawable);
        }
        if (SharedPrefUtility.getInstance(this).getIntValue("Week2Day6") >= 100) {
            week2View6.setBackgroundColor(Color.parseColor("#FF0000"));
            week2Day6.setBackgroundResource(R.drawable.red_dot_drawable);
        }


        if (SharedPrefUtility.getInstance(this).getIntValue("Week3Day1") >= 100) {
            week3View1.setBackgroundColor(Color.parseColor("#FF0000"));
            week3Day1.setBackgroundResource(R.drawable.red_dot_drawable);
        }
        if (SharedPrefUtility.getInstance(this).getIntValue("Week3Day2") >= 100) {
            week3View2.setBackgroundColor(Color.parseColor("#FF0000"));
            week3Day2.setBackgroundResource(R.drawable.red_dot_drawable);
        }
        if (SharedPrefUtility.getInstance(this).getIntValue("Week3Day3") >= 100) {
            week3View3.setBackgroundColor(Color.parseColor("#FF0000"));
            week3Day3.setBackgroundResource(R.drawable.red_dot_drawable);
        }
        if (SharedPrefUtility.getInstance(this).getIntValue("Week3Day4") >= 100) {
            week3View4.setBackgroundColor(Color.parseColor("#FF0000"));
            week3Day4.setBackgroundResource(R.drawable.red_dot_drawable);
        }
        if (SharedPrefUtility.getInstance(this).getIntValue("Week3Day5") >= 100) {
            week3View5.setBackgroundColor(Color.parseColor("#FF0000"));
            week3Day5.setBackgroundResource(R.drawable.red_dot_drawable);
        }
        if (SharedPrefUtility.getInstance(this).getIntValue("Week3Day6") >= 100) {
            week3View6.setBackgroundColor(Color.parseColor("#FF0000"));
            week3Day6.setBackgroundResource(R.drawable.red_dot_drawable);
        }


        if (SharedPrefUtility.getInstance(this).getIntValue("Week4Day1") >= 100) {
            week4View1.setBackgroundColor(Color.parseColor("#FF0000"));
            week4Day1.setBackgroundResource(R.drawable.red_dot_drawable);
        }
        if (SharedPrefUtility.getInstance(this).getIntValue("Week4Day2") >= 100) {
            week4View2.setBackgroundColor(Color.parseColor("#FF0000"));
            week4Day2.setBackgroundResource(R.drawable.red_dot_drawable);
        }
        if (SharedPrefUtility.getInstance(this).getIntValue("Week4Day3") >= 100) {
            week4View3.setBackgroundColor(Color.parseColor("#FF0000"));
            week4Day3.setBackgroundResource(R.drawable.red_dot_drawable);
        }
        if (SharedPrefUtility.getInstance(this).getIntValue("Week4Day4") >= 100) {
            week4View4.setBackgroundColor(Color.parseColor("#FF0000"));
            week4Day4.setBackgroundResource(R.drawable.red_dot_drawable);
        }
        if (SharedPrefUtility.getInstance(this).getIntValue("Week4Day5") >= 100) {
            week4View5.setBackgroundColor(Color.parseColor("#FF0000"));
            week4Day5.setBackgroundResource(R.drawable.red_dot_drawable);
        }
        if (SharedPrefUtility.getInstance(this).getIntValue("Week4Day6") >= 100) {
            week4View6.setBackgroundColor(Color.parseColor("#FF0000"));
            week4Day6.setBackgroundResource(R.drawable.red_dot_drawable);
        }
        super.onRestart();
    }

    @Override
    public void onBackPressed() {
       // MainActivity.mainscreenloadtime++;
        super.onBackPressed();
    }
}
