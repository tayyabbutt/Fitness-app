package com.mobxpert.men30dayfitnesschallenge.activities;

import android.Manifest;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.provider.Settings;
import android.support.design.widget.NavigationView;
import android.support.v4.app.ActivityCompat;
import android.support.v4.view.GravityCompat;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.ads.AbstractAdListener;
import com.facebook.ads.Ad;
import com.facebook.ads.AdError;
import com.facebook.ads.AdIconView;
import com.facebook.ads.AdOptionsView;
import com.facebook.ads.AudienceNetworkAds;
import com.facebook.ads.MediaView;
import com.facebook.ads.NativeAd;
import com.facebook.ads.NativeAdLayout;
import com.facebook.ads.NativeAdListener;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;
import com.google.android.gms.ads.MobileAds;
import com.infideap.drawerbehavior.Advance3DDrawerLayout;
import com.mobxpert.men30dayfitnesschallenge.R;
import com.mobxpert.men30dayfitnesschallenge.activities.cardioWorkout.CardioActivity;
import com.mobxpert.men30dayfitnesschallenge.activities.monthlyPlan.SelectPlanActivity;
import com.mobxpert.men30dayfitnesschallenge.interfaces.OnNetworkDialogClickListener;
import com.mobxpert.men30dayfitnesschallenge.utils.MyDialog;
import com.mobxpert.men30dayfitnesschallenge.utils.SharedPrefUtility;
import com.mobxpert.men30dayfitnesschallenge.utils.Utility;
import com.unity3d.ads.IUnityAdsListener;
import com.unity3d.ads.UnityAds;
import com.unity3d.ads.metadata.MediationMetaData;
import com.unity3d.ads.metadata.MetaData;
import com.unity3d.ads.metadata.PlayerMetaData;
import com.unity3d.services.IUnityServicesListener;
import com.unity3d.services.UnityServices;
import com.unity3d.services.core.log.DeviceLog;
import com.unity3d.services.core.misc.Utilities;
import com.unity3d.services.monetization.placementcontent.ads.IShowAdListener;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity
        implements IUnityAdsListener, View.OnClickListener, IUnityServicesListener, IShowAdListener, NavigationView.OnNavigationItemSelectedListener {
    // public static int mainscreenloadtime = 0;
    private Advance3DDrawerLayout mDrawer;
    private NavigationView navigationView;
    int PERMISSION_ALL = 1;
    AdView mAdView;
    InterstitialAd mInterstitialAd;
    CardView armWorkout, absWorkout, backWorkout, wingsWorkout, chestWorkout, legsWorkout, shoulderWorkout, cardioWorkout;
    ProgressBar absWorkoutProgress, armsWorkoutProgress, backWorkoutProgress, wingsWorkoutProgress, chestWorkoutProgress, legsWorkoutProgress, shoulderWorkoutProgress, cardioWorkoutProgress;
    TextView absWorkoutProgressText, armsWorkoutProgressText, backWorkoutProgressText, wingsWorkoutProgressText, chestWorkoutProgressText, legsWorkoutProgressText, shoulderWorkoutProgressText, cardioWorkoutProgressText;
    ImageView bell;

    final private String gameId = "3094662";

    UnityAdsListener unityAdsListener;//= new UnityAdsListener();
    private static int ordinal = 1;
    private String interstitialPlacementId;


    com.facebook.ads.InterstitialAd fbInterstitialAd;
    private NativeAd nativeAd, nativeAd1;

    private NativeAdLayout nativeAdLayout;
    private LinearLayout adView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        AudienceNetworkAds.initialize(this);
        fbInterstitialAd = new com.facebook.ads.InterstitialAd(this, getString(R.string.debugFbPlacementIdInterstitial));
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        unityAdsListener = new UnityAdsListener();
        initializeInterstitialAd();
        navigationView = (NavigationView) findViewById(R.id.nav_view);
        mDrawer = (Advance3DDrawerLayout) findViewById(R.id.drawer_layout);
        // UnityMonetization.initialize(MainActivity.this, unityGameID, myListener, true);
        bell = findViewById(R.id.bell);
        Animation animShake = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.shakeanimation);
        bell.startAnimation(animShake);


        mAdView = findViewById(R.id.adView);
//        mContentAdView1 = findViewById(R.id.contentAdView1);
        //    mContentAdView2 = findViewById(R.id.contentAdView2);

        //bottom adView

        String id = Settings.Secure.getString(getContentResolver(), Settings.Secure.ANDROID_ID);
        MobileAds.initialize(this, getString(R.string.bannerappid));
        AdRequest adRequest = new AdRequest.Builder()
                .addTestDevice(id)
                .build();
        adRequest.isTestDevice(this);
        boolean istestdeviice = adRequest.isTestDevice(this);
        mAdView.loadAd(adRequest);
        boolean shown = mAdView.isShown();
        //middle first adView

      /*  String id1 = Settings.Secure.getString(getContentResolver(), Settings.Secure.ANDROID_ID);
        MobileAds.initialize(this, getString(R.string.bannerappid));
        AdRequest adRequest1 = new AdRequest.Builder()
                .addTestDevice(id1)
                .build();
        adRequest1.isTestDevice(this);
        boolean istestdeviice1 = adRequest1.isTestDevice(this);
        mContentAdView1.loadAd(adRequest1);
        boolean shown1 = mContentAdView1.isShown();*/

        //middle second adView

     /*   String id2 = Settings.Secure.getString(getContentResolver(), Settings.Secure.ANDROID_ID);
        MobileAds.initialize(this, getString(R.string.bannerappid));
        AdRequest adRequest2 = new AdRequest.Builder()
                .addTestDevice(id1)
                .build();
        adRequest2.isTestDevice(this);
        boolean istestdeviice2 = adRequest2.isTestDevice(this);
        mContentAdView2.loadAd(adRequest2);
        boolean shown2 = mContentAdView2.isShown();*/


        //loadinterad();
        String[] PERMISSIONS = {
                Manifest.permission.WRITE_EXTERNAL_STORAGE,
                Manifest.permission.READ_EXTERNAL_STORAGE,
                Manifest.permission.CAMERA
        };
        if (!hasPermissions(this, PERMISSIONS)) {
            ActivityCompat.requestPermissions(this, PERMISSIONS, PERMISSION_ALL);
        }
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, mDrawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        mDrawer.addDrawerListener(toggle);
        toggle.syncState();
        navigationView.setNavigationItemSelectedListener(this);
        mDrawer.setViewScale(Gravity.START, 0.96f);
        mDrawer.setRadius(Gravity.START, 20);
        mDrawer.setViewElevation(Gravity.START, 8);
        mDrawer.setViewRotation(Gravity.START, 15);

        mInterstitialAd = new InterstitialAd(this);
        mInterstitialAd.setAdUnitId(getString(R.string.interstitial));
        AdRequest adInterstitialRequest = new AdRequest.Builder().build();
        mInterstitialAd.loadAd(adInterstitialRequest);


        init();
        //    loadNativeAd1();
        //    loadNativeAd2();

    }

    private void loadNativeAd1() {
        // Instantiate a NativeAd object.
        // NOTE: the placement ID will eventually identify this as your App, you can ignore it for
        // now, while you are testing and replace it later when you have signed up.
        // While you are using this temporary code you will only get test ads and if you release
        // your code like this to the Google Play your users will not receive ads (you will get a no fill error).
        nativeAd = new NativeAd(this, getString(R.string.debugFbPlacementIdNative));

        nativeAd.setAdListener(new NativeAdListener() {
            @Override
            public void onMediaDownloaded(Ad ad) {
                // Native ad finished downloading all assets
                // Log.e(TAG, "Native ad finished downloading all assets.");
            }

            @Override
            public void onError(Ad ad, AdError adError) {
                // Native ad failed to load
                Log.e("FB Native ad error", "Native ad failed to load: " + adError.getErrorMessage());
            }

            @Override
            public void onAdLoaded(Ad ad) {
                // Native ad is loaded and ready to be displayed
                if (nativeAd == null || nativeAd != ad) {
                    return;
                }
                // Inflate Native Ad into Container
                inflateAd(nativeAd);
                Log.d("FB Native ad load", "Native ad is loaded and ready to be displayed!");
            }

            @Override
            public void onAdClicked(Ad ad) {
                // Native ad clicked
                //  Log.d(TAG, "Native ad clicked!");
            }

            @Override
            public void onLoggingImpression(Ad ad) {
                // Native ad impression
                //  Log.d(TAG, "Native ad impression logged!");
            }
        });

        // Request an ad
        nativeAd.loadAd();
    }

    private void inflateAd(NativeAd nativeAd) {

        nativeAd.unregisterView();

        // Add the Ad view into the ad container.
        nativeAdLayout = findViewById(R.id.contentAdView1);
        LayoutInflater inflater = LayoutInflater.from(this);
        // Inflate the Ad view.  The layout referenced should be the one you created in the last step.
        adView = (LinearLayout) inflater.inflate(R.layout.native_ad_layout_1, nativeAdLayout, false);
        nativeAdLayout.addView(adView);

        // Add the AdOptionsView
        LinearLayout adChoicesContainer = findViewById(R.id.ad_choices_container);
        AdOptionsView adOptionsView = new AdOptionsView(this, nativeAd, nativeAdLayout);
        adChoicesContainer.removeAllViews();
        adChoicesContainer.addView(adOptionsView, 0);

        // Create native UI using the ad metadata.
        AdIconView nativeAdIcon = adView.findViewById(R.id.native_ad_icon);
        TextView nativeAdTitle = adView.findViewById(R.id.native_ad_title);
        MediaView nativeAdMedia = adView.findViewById(R.id.native_ad_media);
        TextView nativeAdSocialContext = adView.findViewById(R.id.native_ad_social_context);
        TextView nativeAdBody = adView.findViewById(R.id.native_ad_body);
        TextView sponsoredLabel = adView.findViewById(R.id.native_ad_sponsored_label);
        Button nativeAdCallToAction = adView.findViewById(R.id.native_ad_call_to_action);

        // Set the Text.
        nativeAdTitle.setText(nativeAd.getAdvertiserName());
        nativeAdBody.setText(nativeAd.getAdBodyText());
        nativeAdSocialContext.setText(nativeAd.getAdSocialContext());
        nativeAdCallToAction.setVisibility(nativeAd.hasCallToAction() ? View.VISIBLE : View.INVISIBLE);
        nativeAdCallToAction.setText(nativeAd.getAdCallToAction());
        sponsoredLabel.setText(nativeAd.getSponsoredTranslation());

        // Create a list of clickable views
        List<View> clickableViews = new ArrayList<>();
        clickableViews.add(nativeAdTitle);
        clickableViews.add(nativeAdCallToAction);

        // Register the Title and CTA button to listen for clicks.
        nativeAd.registerViewForInteraction(
                adView,
                nativeAdMedia,
                nativeAdIcon,
                clickableViews);
    }


    private void loadNativeAd2() {
        // Instantiate a NativeAd object.
        // NOTE: the placement ID will eventually identify this as your App, you can ignore it for
        // now, while you are testing and replace it later when you have signed up.
        // While you are using this temporary code you will only get test ads and if you release
        // your code like this to the Google Play your users will not receive ads (you will get a no fill error).
        nativeAd1 = new NativeAd(this, getString(R.string.debugFbPlacementIdNative));

        nativeAd1.setAdListener(new NativeAdListener() {
            @Override
            public void onMediaDownloaded(Ad ad) {
                // Native ad finished downloading all assets
                // Log.e(TAG, "Native ad finished downloading all assets.");
            }

            @Override
            public void onError(Ad ad, AdError adError) {
                // Native ad failed to load
                Log.e("FB Native ad error", "Native ad failed to load: " + adError.getErrorMessage());
            }

            @Override
            public void onAdLoaded(Ad ad) {
                // Native ad is loaded and ready to be displayed
                if (nativeAd1 == null || nativeAd1 != ad) {
                    return;
                }
                // Inflate Native Ad into Container
                inflateAd2(nativeAd1);
                Log.d("FB Native ad load", "Native ad is loaded and ready to be displayed!");
            }

            @Override
            public void onAdClicked(Ad ad) {
                // Native ad clicked
                //  Log.d(TAG, "Native ad clicked!");
            }

            @Override
            public void onLoggingImpression(Ad ad) {
                // Native ad impression
                //  Log.d(TAG, "Native ad impression logged!");
            }
        });

        // Request an ad
        nativeAd1.loadAd();
    }

    private void inflateAd2(NativeAd nativeAd) {

        nativeAd.unregisterView();

        // Add the Ad view into the ad container.
        nativeAdLayout = findViewById(R.id.contentAdView2);
        LayoutInflater inflater = LayoutInflater.from(this);
        // Inflate the Ad view.  The layout referenced should be the one you created in the last step.
        adView = (LinearLayout) inflater.inflate(R.layout.native_ad_layout_1, nativeAdLayout, false);
        nativeAdLayout.addView(adView);

        // Add the AdOptionsView
        LinearLayout adChoicesContainer = findViewById(R.id.ad_choices_container);
        AdOptionsView adOptionsView = new AdOptionsView(this, nativeAd, nativeAdLayout);
        adChoicesContainer.removeAllViews();
        adChoicesContainer.addView(adOptionsView, 0);

        // Create native UI using the ad metadata.
        AdIconView nativeAdIcon = adView.findViewById(R.id.native_ad_icon);
        TextView nativeAdTitle = adView.findViewById(R.id.native_ad_title);
        MediaView nativeAdMedia = adView.findViewById(R.id.native_ad_media);
        TextView nativeAdSocialContext = adView.findViewById(R.id.native_ad_social_context);
        TextView nativeAdBody = adView.findViewById(R.id.native_ad_body);
        TextView sponsoredLabel = adView.findViewById(R.id.native_ad_sponsored_label);
        Button nativeAdCallToAction = adView.findViewById(R.id.native_ad_call_to_action);

        // Set the Text.
        nativeAdTitle.setText(nativeAd.getAdvertiserName());
        nativeAdBody.setText(nativeAd.getAdBodyText());
        nativeAdSocialContext.setText(nativeAd.getAdSocialContext());
        nativeAdCallToAction.setVisibility(nativeAd.hasCallToAction() ? View.VISIBLE : View.INVISIBLE);
        nativeAdCallToAction.setText(nativeAd.getAdCallToAction());
        sponsoredLabel.setText(nativeAd.getSponsoredTranslation());

        // Create a list of clickable views
        List<View> clickableViews = new ArrayList<>();
        clickableViews.add(nativeAdTitle);
        clickableViews.add(nativeAdCallToAction);

        // Register the Title and CTA button to listen for clicks.
        nativeAd.registerViewForInteraction(
                adView,
                nativeAdMedia,
                nativeAdIcon,
                clickableViews);
    }

    private void init() {

        backWorkout = findViewById(R.id.backWorkout);
        wingsWorkout = findViewById(R.id.wingsWorkout);
        chestWorkout = findViewById(R.id.chestWorkout);
        legsWorkout = findViewById(R.id.legsWorkout);
        shoulderWorkout = findViewById(R.id.shoulderWorkout);
        cardioWorkout = findViewById(R.id.cardioWorkout);
        armWorkout = findViewById(R.id.armsWorkout);
        absWorkout = findViewById(R.id.absWorkout);


        absWorkoutProgress = findViewById(R.id.absWorkoutProgressBar);
        backWorkoutProgress = findViewById(R.id.backProgressBar);
        wingsWorkoutProgress = findViewById(R.id.wingsProgressBar);
        chestWorkoutProgress = findViewById(R.id.chestProgressBar);
        legsWorkoutProgress = findViewById(R.id.legsProgressBar);
        shoulderWorkoutProgress = findViewById(R.id.shoulderProgressBar);
        cardioWorkoutProgress = findViewById(R.id.cardioProgressBar);
        armsWorkoutProgress = findViewById(R.id.armProgressBar);

        absWorkoutProgressText = findViewById(R.id.absWorkoutTextProgressBar);
        backWorkoutProgressText = findViewById(R.id.backprogressText);
        wingsWorkoutProgressText = findViewById(R.id.wingsprogressText);
        chestWorkoutProgressText = findViewById(R.id.chestprogressText);
        legsWorkoutProgressText = findViewById(R.id.legsprogressText);
        shoulderWorkoutProgressText = findViewById(R.id.shoulderprogressText);
        cardioWorkoutProgressText = findViewById(R.id.cardioprogressText);
        armsWorkoutProgressText = findViewById(R.id.armProgressText);

        if (SharedPrefUtility.getInstance(this).getIntValue("AbsWorkoutP") > 0) {
            absWorkoutProgress.setProgress(SharedPrefUtility.getInstance(this).getIntValue("AbsWorkoutP"));
            absWorkoutProgressText.setText(SharedPrefUtility.getInstance(this).getIntValue("AbsWorkoutP") + " %");
        } else if (SharedPrefUtility.getInstance(this).getIntValue("AbsWorkoutP") >= 0) {
            absWorkoutProgress.setProgress(AbsWorkoutActivity.totalSumOfResults);
            absWorkoutProgressText.setText(AbsWorkoutActivity.totalSumOfResults + " %");
        } else {
            absWorkoutProgressText.setText("0 %");
            absWorkoutProgress.setProgress(0);
        }
        if (SharedPrefUtility.getInstance(this).getIntValue("BackWorkoutP") > 0) {

            backWorkoutProgress.setProgress(SharedPrefUtility.getInstance(this).getIntValue("BackWorkoutP"));
            backWorkoutProgressText.setText(SharedPrefUtility.getInstance(this).getIntValue("BackWorkoutP") + " %");
        } else if (SharedPrefUtility.getInstance(this).getIntValue("BackWorkoutP") >= 0) {
            backWorkoutProgress.setProgress(BackWorkoutActivity.totalSumOfResults);
            backWorkoutProgressText.setText(BackWorkoutActivity.totalSumOfResults + " %");
        } else {
            backWorkoutProgressText.setText("0 %");
            backWorkoutProgress.setProgress(0);
        }


        if (SharedPrefUtility.getInstance(this).getIntValue("WingsWorkoutP") > 0) {
            wingsWorkoutProgress.setProgress(SharedPrefUtility.getInstance(this).getIntValue("WingsWorkoutP"));
            wingsWorkoutProgressText.setText(SharedPrefUtility.getInstance(this).getIntValue("WingsWorkoutP") + " %");
        } else if (SharedPrefUtility.getInstance(this).getIntValue("WingsWorkoutP") >= 0) {
            wingsWorkoutProgress.setProgress(WingsWorkoutActivity.totalSumOfResults);
            wingsWorkoutProgressText.setText(WingsWorkoutActivity.totalSumOfResults + " %");
        } else {
            wingsWorkoutProgressText.setText("0 %");
            wingsWorkoutProgress.setProgress(0);
        }


        if (SharedPrefUtility.getInstance(this).getIntValue("ChestWorkoutP") > 0) {
            chestWorkoutProgress.setProgress(SharedPrefUtility.getInstance(this).getIntValue("ChestWorkoutP"));
            chestWorkoutProgressText.setText(SharedPrefUtility.getInstance(this).getIntValue("ChestWorkoutP") + " %");
        } else if (SharedPrefUtility.getInstance(this).getIntValue("ChestWorkoutP") >= 0) {
            chestWorkoutProgress.setProgress(ChestWorkoutActivity.totalSumOfResults);
            chestWorkoutProgressText.setText(ChestWorkoutActivity.totalSumOfResults + " %");
        } else {
            chestWorkoutProgressText.setText("0 %");
            chestWorkoutProgress.setProgress(0);
        }


        if (SharedPrefUtility.getInstance(this).getIntValue("LegsWorkoutP") > 0) {
            legsWorkoutProgressText.setText(SharedPrefUtility.getInstance(this).getIntValue("LegsWorkoutP") + " %");
            legsWorkoutProgress.setProgress(SharedPrefUtility.getInstance(this).getIntValue("LegsWorkoutP"));
        } else if (SharedPrefUtility.getInstance(this).getIntValue("LegsWorkoutP") >= 0) {
            legsWorkoutProgressText.setText(LegsWorkoutActivity.totalSumOfResults + " %");
            legsWorkoutProgress.setProgress(LegsWorkoutActivity.totalSumOfResults);
        } else {
            legsWorkoutProgressText.setText("0 %");
            legsWorkoutProgress.setProgress(0);
        }

        if (SharedPrefUtility.getInstance(this).getIntValue("ShoulderWorkoutP") > 0) {
            shoulderWorkoutProgressText.setText(SharedPrefUtility.getInstance(this).getIntValue("ShoulderWorkoutP") + " %");
            shoulderWorkoutProgress.setProgress(SharedPrefUtility.getInstance(this).getIntValue("ShoulderWorkoutP"));
        } else if (SharedPrefUtility.getInstance(this).getIntValue("ShoulderWorkoutP") >= 0) {
            shoulderWorkoutProgressText.setText(ShouldersWorkoutActivity.totalSumOfResults + " %");
            shoulderWorkoutProgress.setProgress(ShouldersWorkoutActivity.totalSumOfResults);
        } else {
            shoulderWorkoutProgressText.setText("0 %");
            shoulderWorkoutProgress.setProgress(0);
        }


        if (SharedPrefUtility.getInstance(this).getIntValue("CardioWorkoutP") > 0) {
            cardioWorkoutProgressText.setText(SharedPrefUtility.getInstance(this).getIntValue("CardioWorkoutP") + " %");
            cardioWorkoutProgress.setProgress(SharedPrefUtility.getInstance(this).getIntValue("CardioWorkoutP"));
        } else if (SharedPrefUtility.getInstance(this).getIntValue("CardioWorkoutP") >= 0) {
            cardioWorkoutProgressText.setText(CardioActivity.resultWatchVideo + " %");
            cardioWorkoutProgress.setProgress(CardioActivity.resultWatchVideo);
        } else {
            cardioWorkoutProgressText.setText("0 %");
            cardioWorkoutProgress.setProgress(0);
        }


        if (SharedPrefUtility.getInstance(this).getIntValue("ArmsWorkoutP") > 0) {
            armsWorkoutProgressText.setText(SharedPrefUtility.getInstance(this).getIntValue("ArmsWorkoutP") + " %");
            armsWorkoutProgress.setProgress(SharedPrefUtility.getInstance(this).getIntValue("ArmsWorkoutP"));
        } else if (SharedPrefUtility.getInstance(this).getIntValue("ArmsWorkoutP") >= 0) {
            armsWorkoutProgressText.setText(ArmsWorkOutActivity.totalSumOfResults + " %");
            armsWorkoutProgress.setProgress(ArmsWorkOutActivity.totalSumOfResults);
        } else {
            armsWorkoutProgressText.setText("0 %");
            armsWorkoutProgress.setProgress(0);
        }

        armWorkout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (mInterstitialAd.isLoaded() && mInterstitialAd != null) {
                    mInterstitialAd.show();
                    // mainscreenloadtime = 0;
                } else {
                    Utility.launchActivity(MainActivity.this, ArmsWorkOutActivity.class, false, null);
                }
                if (mInterstitialAd != null) {
                    mInterstitialAd.setAdListener(new AdListener() {
                        @Override
                        public void onAdClosed() {
                            Utility.launchActivity(MainActivity.this, ArmsWorkOutActivity.class, false, null);
                            AdRequest adInterstitialRequest = new AdRequest.Builder().build();
                            mInterstitialAd.loadAd(adInterstitialRequest);
                        }
                    });
                }


            }
        });
        absWorkout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                fbInterstitialAd.setAdListener(new AbstractAdListener() {
                    @Override
                    public void onError(Ad ad, AdError adError) {
                        super.onError(ad, adError);
                        Utility.launchActivity(MainActivity.this, AbsWorkoutActivity.class, false, null);
                    }

                    @Override
                    public void onAdLoaded(Ad ad) {
                        super.onAdLoaded(ad);
                        fbInterstitialAd.show();
                    }

                    @Override
                    public void onInterstitialDismissed(Ad ad) {
                        super.onInterstitialDismissed(ad);
                        Utility.launchActivity(MainActivity.this, AbsWorkoutActivity.class, false, null);

                    }
                });
                if (fbInterstitialAd.isAdLoaded()) {
                    fbInterstitialAd = new com.facebook.ads.InterstitialAd(MainActivity.this, getString(R.string.debugFbPlacementIdInterstitial));
                } else {
                    fbInterstitialAd.loadAd();
                }

                /*if (mInterstitialAd.isLoaded() && mInterstitialAd != null) {
                    mInterstitialAd.show();
                } else {
                    Utility.launchActivity(MainActivity.this, AbsWorkoutActivity.class, false, null);
                }
                if (mInterstitialAd != null) {
                    mInterstitialAd.setAdListener(new AdListener() {
                        @Override
                        public void onAdClosed() {
                            Utility.launchActivity(MainActivity.this, AbsWorkoutActivity.class, false, null);
                            AdRequest adInterstitialRequest = new AdRequest.Builder().build();
                            mInterstitialAd.loadAd(adInterstitialRequest);
                        }
                    });
                }*/
            }
        });
        backWorkout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              /*  PlayerMetaData playerMetaData = new PlayerMetaData(MainActivity.this);
                playerMetaData.setServerId("rikshot");
                playerMetaData.commit();
                MediationMetaData ordinalMetaData = new MediationMetaData(MainActivity.this);
                ordinalMetaData.setOrdinal(ordinal++);
                ordinalMetaData.commit();

                UnityAds.show(MainActivity.this, interstitialPlacementId);*/
               /* loadinterad();
                if (mInterstitialAd.isLoaded() && mInterstitialAd != null) {
                    mInterstitialAd.show();
                    Utility.launchActivity(MainActivity.this, BackWorkoutActivity.class, false, null);
                } else {
                    Utility.launchActivity(MainActivity.this, BackWorkoutActivity.class, false, null);
                }*/


                if (mInterstitialAd.isLoaded() && mInterstitialAd != null) {
                    mInterstitialAd.show();
                    // mainscreenloadtime = 0;
                } else {
                    Utility.launchActivity(MainActivity.this, BackWorkoutActivity.class, false, null);
                }
                if (mInterstitialAd != null) {
                    mInterstitialAd.setAdListener(new AdListener() {
                        @Override
                        public void onAdClosed() {
                            Utility.launchActivity(MainActivity.this, BackWorkoutActivity.class, false, null);
                            AdRequest adInterstitialRequest = new AdRequest.Builder().build();
                            mInterstitialAd.loadAd(adInterstitialRequest);
                        }
                    });
                }
                //  Utility.launchActivity(MainActivity.this, BackWorkoutActivity.class, false, null);
            }
        });
        wingsWorkout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               /* PlayerMetaData playerMetaData = new PlayerMetaData(MainActivity.this);
                playerMetaData.setServerId("rikshot");
                playerMetaData.commit();
                MediationMetaData ordinalMetaData = new MediationMetaData(MainActivity.this);
                ordinalMetaData.setOrdinal(ordinal++);
                ordinalMetaData.commit();

                UnityAds.show(MainActivity.this, interstitialPlacementId);*/
               /* loadinterad();
                if (mInterstitialAd.isLoaded() && mInterstitialAd != null) {
                    mInterstitialAd.show();
                    Utility.launchActivity(MainActivity.this, WingsWorkoutActivity.class, false, null);

                } else {
                    Utility.launchActivity(MainActivity.this, WingsWorkoutActivity.class, false, null);

                }*/

                if (mInterstitialAd.isLoaded() && mInterstitialAd != null) {
                    mInterstitialAd.show();
                    // mainscreenloadtime = 0;
                } else {
                    Utility.launchActivity(MainActivity.this, WingsWorkoutActivity.class, false, null);
                }
                if (mInterstitialAd != null) {
                    mInterstitialAd.setAdListener(new AdListener() {
                        @Override
                        public void onAdClosed() {
                            Utility.launchActivity(MainActivity.this, WingsWorkoutActivity.class, false, null);
                            AdRequest adInterstitialRequest = new AdRequest.Builder().build();
                            mInterstitialAd.loadAd(adInterstitialRequest);
                        }
                    });
                }
                /// Utility.launchActivity(MainActivity.this, WingsWorkoutActivity.class, false, null);
            }
        });
        chestWorkout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               /* PlayerMetaData playerMetaData = new PlayerMetaData(MainActivity.this);
                playerMetaData.setServerId("rikshot");
                playerMetaData.commit();
                MediationMetaData ordinalMetaData = new MediationMetaData(MainActivity.this);
                ordinalMetaData.setOrdinal(ordinal++);
                ordinalMetaData.commit();

                UnityAds.show(MainActivity.this, interstitialPlacementId);*/
               /* loadinterad();
                if (mInterstitialAd.isLoaded() && mInterstitialAd != null) {
                    mInterstitialAd.show();
                    Utility.launchActivity(MainActivity.this, ChestWorkoutActivity.class, false, null);

                } else {
                    Utility.launchActivity(MainActivity.this, ChestWorkoutActivity.class, false, null);

                }*/
                fbInterstitialAd.setAdListener(new AbstractAdListener() {
                    @Override
                    public void onError(Ad ad, AdError adError) {
                        super.onError(ad, adError);
                        Utility.launchActivity(MainActivity.this, ChestWorkoutActivity.class, false, null);
                    }

                    @Override
                    public void onAdLoaded(Ad ad) {
                        super.onAdLoaded(ad);
                        fbInterstitialAd.show();
                    }

                    @Override
                    public void onInterstitialDismissed(Ad ad) {
                        super.onInterstitialDismissed(ad);
                        Utility.launchActivity(MainActivity.this, ChestWorkoutActivity.class, false, null);

                    }
                });
                if (fbInterstitialAd.isAdLoaded()) {
                    fbInterstitialAd = new com.facebook.ads.InterstitialAd(MainActivity.this, getString(R.string.debugFbPlacementIdInterstitial));
                } else {
                    fbInterstitialAd.loadAd();
                }


               /* if (mInterstitialAd.isLoaded() && mInterstitialAd != null) {
                    mInterstitialAd.show();
                    // mainscreenloadtime = 0;
                } else {
                    Utility.launchActivity(MainActivity.this, ChestWorkoutActivity.class, false, null);
                }
                if (mInterstitialAd != null) {
                    mInterstitialAd.setAdListener(new AdListener() {
                        @Override
                        public void onAdClosed() {
                            Utility.launchActivity(MainActivity.this, ChestWorkoutActivity.class, false, null);

                            AdRequest adInterstitialRequest = new AdRequest.Builder().build();
                            mInterstitialAd.loadAd(adInterstitialRequest); }
                    });
                }*/

                //  Utility.launchActivity(MainActivity.this, ChestWorkoutActivity.class, false, null);
            }
        });
        legsWorkout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              /*  PlayerMetaData playerMetaData = new PlayerMetaData(MainActivity.this);
                playerMetaData.setServerId("rikshot");
                playerMetaData.commit();
                MediationMetaData ordinalMetaData = new MediationMetaData(MainActivity.this);
                ordinalMetaData.setOrdinal(ordinal++);
                ordinalMetaData.commit();

                UnityAds.show(MainActivity.this, interstitialPlacementId);*/
               /* loadinterad();
                if (mInterstitialAd.isLoaded() && mInterstitialAd != null) {
                    mInterstitialAd.show();
                    Utility.launchActivity(MainActivity.this, LegsWorkoutActivity.class, false, null);

                } else {
                    Utility.launchActivity(MainActivity.this, LegsWorkoutActivity.class, false, null);

                }
                //Utility.launchActivity(MainActivity.this, LegsWorkoutActivity.class, false, null);
            }*/


                fbInterstitialAd.setAdListener(new AbstractAdListener() {
                    @Override
                    public void onError(Ad ad, AdError adError) {
                        super.onError(ad, adError);
                        Utility.launchActivity(MainActivity.this, LegsWorkoutActivity.class, false, null);
                    }

                    @Override
                    public void onAdLoaded(Ad ad) {
                        super.onAdLoaded(ad);
                        fbInterstitialAd.show();
                    }

                    @Override
                    public void onInterstitialDismissed(Ad ad) {
                        super.onInterstitialDismissed(ad);
                        Utility.launchActivity(MainActivity.this, LegsWorkoutActivity.class, false, null);
                    }
                });
                if (fbInterstitialAd.isAdLoaded()) {
                    fbInterstitialAd = new com.facebook.ads.InterstitialAd(MainActivity.this, getString(R.string.debugFbPlacementIdInterstitial));
                } else {
                    fbInterstitialAd.loadAd();
                }

                /*if (mInterstitialAd.isLoaded() && mInterstitialAd != null) {
                    mInterstitialAd.show();
                    // mainscreenloadtime = 0;
                } else {
                    Utility.launchActivity(MainActivity.this, LegsWorkoutActivity.class, false, null);
                }
                if (mInterstitialAd != null) {
                    mInterstitialAd.setAdListener(new AdListener() {
                        @Override
                        public void onAdClosed() {
                            Utility.launchActivity(MainActivity.this, LegsWorkoutActivity.class, false, null);
                            AdRequest adInterstitialRequest = new AdRequest.Builder().build();
                            mInterstitialAd.loadAd(adInterstitialRequest);
                        }
                    });
                }*/
            }
        });
        shoulderWorkout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               /* PlayerMetaData playerMetaData = new PlayerMetaData(MainActivity.this);
                playerMetaData.setServerId("rikshot");
                playerMetaData.commit();
                MediationMetaData ordinalMetaData = new MediationMetaData(MainActivity.this);
                ordinalMetaData.setOrdinal(ordinal++);
                ordinalMetaData.commit();

                UnityAds.show(MainActivity.this, interstitialPlacementId);*/
               /* loadinterad();
                if (mInterstitialAd.isLoaded() && mInterstitialAd != null) {
                    mInterstitialAd.show();
                    Utility.launchActivity(MainActivity.this, ShouldersWorkoutActivity.class, false, null);

                } else {
                    Utility.launchActivity(MainActivity.this, ShouldersWorkoutActivity.class, false, null);

                }*/
                if (mInterstitialAd.isLoaded() && mInterstitialAd != null) {
                    mInterstitialAd.show();
                    // mainscreenloadtime = 0;
                } else {
                    Utility.launchActivity(MainActivity.this, ShouldersWorkoutActivity.class, false, null);
                }
                if (mInterstitialAd != null) {
                    mInterstitialAd.setAdListener(new AdListener() {
                        @Override
                        public void onAdClosed() {
                            Utility.launchActivity(MainActivity.this, ShouldersWorkoutActivity.class, false, null);
                            AdRequest adInterstitialRequest = new AdRequest.Builder().build();
                            mInterstitialAd.loadAd(adInterstitialRequest);
                        }
                    });
                }
                //Utility.launchActivity(MainActivity.this, ShouldersWorkoutActivity.class, false, null);
            }
        });
        cardioWorkout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /*PlayerMetaData playerMetaData = new PlayerMetaData(MainActivity.this);
                playerMetaData.setServerId("rikshot");
                playerMetaData.commit();
                MediationMetaData ordinalMetaData = new MediationMetaData(MainActivity.this);
                ordinalMetaData.setOrdinal(ordinal++);
                ordinalMetaData.commit();

                UnityAds.show(MainActivity.this, interstitialPlacementId);*/
               /* loadinterad();
                if (mInterstitialAd.isLoaded() && mInterstitialAd != null) {
                    mInterstitialAd.show();
                    Utility.launchActivity(MainActivity.this, CardioWorkoutActivity.class, false, null);
                } else {
                    Utility.launchActivity(MainActivity.this, CardioWorkoutActivity.class, false, null);


                }*/

                if (mInterstitialAd.isLoaded() && mInterstitialAd != null) {
                    mInterstitialAd.show();
                    // mainscreenloadtime = 0;
                } else {
                    Utility.launchActivity(MainActivity.this, CardioWorkoutActivity.class, false, null);
                }
                if (mInterstitialAd != null) {
                    mInterstitialAd.setAdListener(new AdListener() {
                        @Override
                        public void onAdClosed() {
                            Utility.launchActivity(MainActivity.this, CardioWorkoutActivity.class, false, null);
                            AdRequest adInterstitialRequest = new AdRequest.Builder().build();
                            mInterstitialAd.loadAd(adInterstitialRequest);
                        }
                    });
                }
                // Utility.launchActivity(MainActivity.this, CardioWorkoutActivity.class, false, null);
            }
        });


        bell.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                PlayerMetaData playerMetaData = new PlayerMetaData(MainActivity.this);
                playerMetaData.setServerId("rikshot");
                playerMetaData.commit();

                MediationMetaData ordinalMetaData = new MediationMetaData(MainActivity.this);
                ordinalMetaData.setOrdinal(ordinal++);
                ordinalMetaData.commit();

                UnityAds.show(MainActivity.this, interstitialPlacementId);

                Utility.launchActivity(MainActivity.this, SelectPlanActivity.class, false, null);
            }

        });

    }

    @Override
    protected void onResume() {
        if (SharedPrefUtility.getInstance(this).getIntValue("AbsWorkoutP") > 0) {
            absWorkoutProgress.setProgress(SharedPrefUtility.getInstance(this).getIntValue("AbsWorkoutP"));
            absWorkoutProgressText.setText(SharedPrefUtility.getInstance(this).getIntValue("AbsWorkoutP") + " %");
        } else if (SharedPrefUtility.getInstance(this).getIntValue("AbsWorkoutP") >= 0) {
            absWorkoutProgress.setProgress(AbsWorkoutActivity.totalSumOfResults);
            absWorkoutProgressText.setText(AbsWorkoutActivity.totalSumOfResults + " %");
        } else {
            absWorkoutProgressText.setText("0 %");
            absWorkoutProgress.setProgress(0);
        }
        if (SharedPrefUtility.getInstance(this).getIntValue("BackWorkoutP") > 0) {

            backWorkoutProgress.setProgress(SharedPrefUtility.getInstance(this).getIntValue("BackWorkoutP"));
            backWorkoutProgressText.setText(SharedPrefUtility.getInstance(this).getIntValue("BackWorkoutP") + " %");
        } else if (SharedPrefUtility.getInstance(this).getIntValue("BackWorkoutP") >= 0) {
            backWorkoutProgress.setProgress(BackWorkoutActivity.totalSumOfResults);
            backWorkoutProgressText.setText(BackWorkoutActivity.totalSumOfResults + " %");
        } else {
            backWorkoutProgressText.setText("0 %");
            backWorkoutProgress.setProgress(0);
        }


        if (SharedPrefUtility.getInstance(this).getIntValue("WingsWorkoutP") > 0) {
            wingsWorkoutProgress.setProgress(SharedPrefUtility.getInstance(this).getIntValue("WingsWorkoutP"));
            wingsWorkoutProgressText.setText(SharedPrefUtility.getInstance(this).getIntValue("WingsWorkoutP") + " %");
        } else if (SharedPrefUtility.getInstance(this).getIntValue("WingsWorkoutP") >= 0) {
            wingsWorkoutProgress.setProgress(WingsWorkoutActivity.totalSumOfResults);
            wingsWorkoutProgressText.setText(WingsWorkoutActivity.totalSumOfResults + " %");
        } else {
            wingsWorkoutProgressText.setText("0 %");
            wingsWorkoutProgress.setProgress(0);
        }


        if (SharedPrefUtility.getInstance(this).getIntValue("ChestWorkoutP") > 0) {
            chestWorkoutProgress.setProgress(SharedPrefUtility.getInstance(this).getIntValue("ChestWorkoutP"));
            chestWorkoutProgressText.setText(SharedPrefUtility.getInstance(this).getIntValue("ChestWorkoutP") + " %");
        } else if (SharedPrefUtility.getInstance(this).getIntValue("ChestWorkoutP") >= 0) {
            chestWorkoutProgress.setProgress(ChestWorkoutActivity.totalSumOfResults);
            chestWorkoutProgressText.setText(ChestWorkoutActivity.totalSumOfResults + " %");
        } else {
            chestWorkoutProgressText.setText("0 %");
            chestWorkoutProgress.setProgress(0);
        }


        if (SharedPrefUtility.getInstance(this).getIntValue("LegsWorkoutP") > 0) {
            legsWorkoutProgressText.setText(SharedPrefUtility.getInstance(this).getIntValue("LegsWorkoutP") + " %");
            legsWorkoutProgress.setProgress(SharedPrefUtility.getInstance(this).getIntValue("LegsWorkoutP"));
        } else if (SharedPrefUtility.getInstance(this).getIntValue("LegsWorkoutP") >= 0) {
            legsWorkoutProgressText.setText(LegsWorkoutActivity.totalSumOfResults + " %");
            legsWorkoutProgress.setProgress(LegsWorkoutActivity.totalSumOfResults);
        } else {
            legsWorkoutProgressText.setText("0 %");
            legsWorkoutProgress.setProgress(0);
        }

        if (SharedPrefUtility.getInstance(this).getIntValue("ShoulderWorkoutP") > 0) {
            shoulderWorkoutProgressText.setText(SharedPrefUtility.getInstance(this).getIntValue("ShoulderWorkoutP") + " %");
            shoulderWorkoutProgress.setProgress(SharedPrefUtility.getInstance(this).getIntValue("ShoulderWorkoutP"));
        } else if (SharedPrefUtility.getInstance(this).getIntValue("ShoulderWorkoutP") >= 0) {
            shoulderWorkoutProgressText.setText(ShouldersWorkoutActivity.totalSumOfResults + " %");
            shoulderWorkoutProgress.setProgress(ShouldersWorkoutActivity.totalSumOfResults);
        } else {
            shoulderWorkoutProgressText.setText("0 %");
            shoulderWorkoutProgress.setProgress(0);
        }


        if (SharedPrefUtility.getInstance(this).getIntValue("CardioWorkoutP") > 0) {
            cardioWorkoutProgressText.setText(SharedPrefUtility.getInstance(this).getIntValue("CardioWorkoutP") + " %");
            cardioWorkoutProgress.setProgress(SharedPrefUtility.getInstance(this).getIntValue("CardioWorkoutP"));
        } else if (SharedPrefUtility.getInstance(this).getIntValue("CardioWorkoutP") >= 0) {
            cardioWorkoutProgressText.setText(CardioActivity.resultWatchVideo + " %");
            cardioWorkoutProgress.setProgress(CardioActivity.resultWatchVideo);
        } else {
            cardioWorkoutProgressText.setText("0 %");
            cardioWorkoutProgress.setProgress(0);
        }


        if (SharedPrefUtility.getInstance(this).getIntValue("ArmsWorkoutP") > 0) {
            armsWorkoutProgressText.setText(SharedPrefUtility.getInstance(this).getIntValue("ArmsWorkoutP") + " %");
            armsWorkoutProgress.setProgress(SharedPrefUtility.getInstance(this).getIntValue("ArmsWorkoutP"));
        } else if (SharedPrefUtility.getInstance(this).getIntValue("ArmsWorkoutP") >= 0) {
            armsWorkoutProgressText.setText(ArmsWorkOutActivity.totalSumOfResults + " %");
            armsWorkoutProgress.setProgress(ArmsWorkOutActivity.totalSumOfResults);
        } else {
            armsWorkoutProgressText.setText("0 %");
            armsWorkoutProgress.setProgress(0);
        }

       /* if (mainscreenloadtime > 0) {
            boolean loading = mInterstitialAd.isLoading();
            boolean loaded = mInterstitialAd.isLoaded();
            if (mInterstitialAd.isLoaded()) {
                mInterstitialAd.show();
                mainscreenloadtime = 0;
            } else {
            }
        }*/
        super.onResume();
    }


    public void loadinterad() {
        MobileAds.initialize(this, getString(R.string.bannerappid));
        mInterstitialAd = new InterstitialAd(this);
        mInterstitialAd.setAdUnitId(getString(R.string.interstitial));
        mInterstitialAd.setAdListener(new AdListener() {
            @Override
            public void onAdClosed() {
                AddNewRequestAndLoad();
            }
        });
        AddNewRequestAndLoad();
    }

    public void AddNewRequestAndLoad() {
        String id = Settings.Secure.getString(getContentResolver(), Settings.Secure.ANDROID_ID);
        if (!mInterstitialAd.isLoading() && !mInterstitialAd.isLoaded()) {

            AdRequest adRequest = new AdRequest.Builder()
                    .addTestDevice(id)
                    .build();
            mInterstitialAd.loadAd(adRequest);
        }
    }

    public void launchmarket() {
        Uri uri = Uri.parse("market://details?id=" + getPackageName());
        Intent myAppLinkToMarket = new Intent(Intent.ACTION_VIEW, uri);
        try {
            startActivity(myAppLinkToMarket);
        } catch (ActivityNotFoundException e) {
            Toast.makeText(this, getString(R.string.marketerror), Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public void onBackPressed() {

        if (mDrawer.isDrawerOpen(GravityCompat.START)) {
            mDrawer.closeDrawer(GravityCompat.START);
        } else {
            MyDialog.showNativeAdDialogOnExit(MainActivity.this, new OnNetworkDialogClickListener() {
                @Override
                public void onExitButtonClick(boolean isShown) {

                    if (isShown) {
                        MainActivity.super.onBackPressed();
                        finish();
                    } else {
                        return;
                    }
                }

                @Override
                public void onRateUsButtonClick() {
                    fbInterstitialAd.setAdListener(new AbstractAdListener() {
                        @Override
                        public void onError(Ad ad, AdError adError) {
                            super.onError(ad, adError);
                            launchmarket();
                        }

                        @Override
                        public void onAdLoaded(Ad ad) {
                            super.onAdLoaded(ad);
                            fbInterstitialAd.show();
                        }

                        @Override
                        public void onInterstitialDismissed(Ad ad) {
                            super.onInterstitialDismissed(ad);
                            launchmarket();
                        }
                    });
                    if (fbInterstitialAd.isAdLoaded()) {
                        fbInterstitialAd = new com.facebook.ads.InterstitialAd(MainActivity.this, getString(R.string.debugFbPlacementIdInterstitial));
                    } else {
                        fbInterstitialAd.loadAd();
                    }

                }
            });
        }


    }


    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        selectDrawerItem(item);
        // mDrawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public void selectDrawerItem(MenuItem menuItem) {
        int id = menuItem.getItemId();
        if (id == R.id.navBmiCalculator) {
            PlayerMetaData playerMetaData = new PlayerMetaData(MainActivity.this);
            playerMetaData.setServerId("rikshot");
            playerMetaData.commit();

            MediationMetaData ordinalMetaData = new MediationMetaData(MainActivity.this);
            ordinalMetaData.setOrdinal(ordinal++);
            ordinalMetaData.commit();

            UnityAds.show(MainActivity.this, interstitialPlacementId);
            Utility.launchActivity(MainActivity.this, BMI_CalculatorActivity.class, false, null);
        } else if (id == R.id.navSelectPlan) {
            PlayerMetaData playerMetaData = new PlayerMetaData(MainActivity.this);
            playerMetaData.setServerId("rikshot");
            playerMetaData.commit();

            MediationMetaData ordinalMetaData = new MediationMetaData(MainActivity.this);
            ordinalMetaData.setOrdinal(ordinal++);
            ordinalMetaData.commit();

            UnityAds.show(MainActivity.this, interstitialPlacementId);
            Utility.launchActivity(MainActivity.this, SelectPlanActivity.class, false, null);
        } else if (id == R.id.nav_camera) {
            PlayerMetaData playerMetaData = new PlayerMetaData(MainActivity.this);
            playerMetaData.setServerId("rikshot");
            playerMetaData.commit();

            MediationMetaData ordinalMetaData = new MediationMetaData(MainActivity.this);
            ordinalMetaData.setOrdinal(ordinal++);
            ordinalMetaData.commit();

            UnityAds.show(MainActivity.this, interstitialPlacementId);
            Utility.launchActivity(MainActivity.this, CameraActivity.class, false, null);
        } else if (id == R.id.nav_rate_us) {
            fbInterstitialAd.setAdListener(new AbstractAdListener() {
                @Override
                public void onError(Ad ad, AdError adError) {
                    super.onError(ad, adError);
                    launchmarket();
                }

                @Override
                public void onAdLoaded(Ad ad) {
                    super.onAdLoaded(ad);
                    fbInterstitialAd.show();
                }

                @Override
                public void onInterstitialDismissed(Ad ad) {
                    super.onInterstitialDismissed(ad);
                    launchmarket();
                }
            });
            if (fbInterstitialAd.isAdLoaded()) {
                fbInterstitialAd = new com.facebook.ads.InterstitialAd(this, getString(R.string.debugFbPlacementIdInterstitial));
            } else {
                fbInterstitialAd.loadAd();
            }


        } else if (id == R.id.nav_review) {
            fbInterstitialAd.setAdListener(new AbstractAdListener() {
                @Override
                public void onError(Ad ad, AdError adError) {
                    super.onError(ad, adError);
                    launchmarket();
                }

                @Override
                public void onAdLoaded(Ad ad) {
                    super.onAdLoaded(ad);
                    fbInterstitialAd.show();
                }

                @Override
                public void onInterstitialDismissed(Ad ad) {
                    super.onInterstitialDismissed(ad);
                    launchmarket();
                }
            });
            if (fbInterstitialAd.isAdLoaded()) {
                fbInterstitialAd = new com.facebook.ads.InterstitialAd(this, getString(R.string.debugFbPlacementIdInterstitial));
            } else {
                fbInterstitialAd.loadAd();
            }

        } /*else if (id == R.id.nav_privacyPolicy) {
            PlayerMetaData playerMetaData = new PlayerMetaData(MainActivity.this);
            playerMetaData.setServerId("rikshot");
            playerMetaData.commit();

            MediationMetaData ordinalMetaData = new MediationMetaData(MainActivity.this);
            ordinalMetaData.setOrdinal(ordinal++);
            ordinalMetaData.commit();

            UnityAds.show(MainActivity.this, interstitialPlacementId);
            Utility.launchActivity(MainActivity.this, PrivacyPolicy.class, false, null);
        }*/
        menuItem.setChecked(true);
        mDrawer.closeDrawers();
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

    public static boolean hasPermissions(Context context, String... permissions) {
        if (context != null && permissions != null) {
            for (String permission : permissions) {
                if (ActivityCompat.checkSelfPermission(context, permission) != PackageManager.PERMISSION_GRANTED) {
                    return false;
                }
            }
        }
        return true;
    }


    @Override
    public void onUnityAdsReady(String placementId) {

    }

    @Override
    public void onUnityAdsStart(String placementId) {

    }

    @Override
    public void onUnityAdsFinish(String placementId, UnityAds.FinishState result) {

    }

    @Override
    public void onUnityAdsError(UnityAds.UnityAdsError error, String message) {

    }

    @Override
    public void onUnityServicesError(UnityServices.UnityServicesError error, String message) {

    }

    @Override
    public void onAdFinished(String placementId, UnityAds.FinishState withState) {

     /*   if (withState == UnityAds.FinishState.COMPLETED) {
            if (placementId.equals (rewardedPlacementId)) {
                // Reward the player here.
            }
        }*/
    }

    @Override
    public void onAdStarted(String placementId) {

    }

    @Override
    public void onClick(View view) {

    }



    /*private class UnityBannerListener implements IUnityBannerListener {

        @Override
        public void onUnityBannerLoaded (String placementId, View view) {
            bannerView = view;
            ((ViewGroup) findViewById (R.id.unityads_example_layout_root)).addView (view);
        }

        @Override
        public void onUnityBannerUnloaded (String placementId) {
            bannerView = null;
        }

        @Override
        public void onUnityBannerShow (String placementId) {
        }

        @Override
        public void onUnityBannerClick (String placementId) {
        }

        @Override
        public void onUnityBannerHide (String placementId) {
        }

        @Override
        public void onUnityBannerError (String message) {

        }
    }



    private class UnityMonetizationListener implements IUnityMonetizationListener {

        @Override
        public void onPlacementContentReady (String placementId, PlacementContent placementContent) {
        }

        @Override
        public void onPlacementContentStateChange (String placementId, PlacementContent placementContent, UnityMonetization.PlacementContentState previousState, UnityMonetization.PlacementContentState newState) {
        }

        @Override
        public void onUnityServicesError (UnityServices.UnityServicesError error, String message) {
        }
    }*/


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

            //    toast("Ready", zoneId);
        }

        @Override
        public void onUnityAdsStart(String zoneId) {
            DeviceLog.debug("onUnityAdsStart: " + zoneId);
            // toast("Start", zoneId);
        }

        @Override
        public void onUnityAdsFinish(String zoneId, UnityAds.FinishState result) {
            DeviceLog.debug("onUnityAdsFinish: " + zoneId + " - " + result);
            //   toast("Finish", zoneId + " " + result);
        }

        @Override
        public void onUnityAdsError(UnityAds.UnityAdsError error, String message) {
            DeviceLog.debug("onUnityAdsError: " + error + " - " + message);
        }
    }

}
