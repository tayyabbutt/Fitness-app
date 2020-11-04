package com.mobxpert.men30dayfitnesschallenge.utils;


import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.mobxpert.men30dayfitnesschallenge.models.MyImage;
import com.mobxpert.men30dayfitnesschallenge.models.MyWorkout;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class MyDbPrefrences {

    private static final String ARM_WORKOUT_BICEPS = "com.arm.workout.biceps";
    private static final String ARM_WORKOUT_TRICEPS = "com.arm.workout.triceps";
    private static final String ARM_WORKOUT_FOREARM = "com.arm.workout.forearm";
    private static final String ABS_WORKOUT_UPPER_ABS = "com.abs.workout.upper.abs";
    private static final String ABS_WORKOUT_LOWER_ABS = "com.abs.workout.lower.abs";
    private static final String ABS_WORKOUT_SIDE_CUTTING = "com.abs.workout.side.cutting";
    private static final String BACK_WORKOUT_UPPER_BACK = "com.back.workout.upper.back";
    private static final String BACK_WORKOUT_LOWER_BACK = "com.back.workout.lower.back";
    private static final String BACK_WORKOUT_VSHAPE_BACK = "com.arm.workout.vshape.back";
    private static final String WING_WORKOUT_VSHAPE_BACK = "com.wing.workout.vshape.back";
    private static final String WING_WORKOUT_WINGS_EXTENTION = "com.wing.workout.wings.extention";
    private static final String CHEST_WORKOUT_UPPER_CHEST = "com.chest.workout.uper.chest";
    private static final String CHEST_WORKOUT_MIDDLE_CHEST = "com.chest.workout.middle.chest";
    private static final String CHEST_WORKOUT_LOWER_CHEST = "com.chest.workout.lower.chest";
    private static final String LEGS_WORKOUT_FRONT_THIGHS = "com.leg.workout.front.thighs";
    private static final String LEGS_WORKOUT_BACK_THIGHS = "com.arm.workout.back.thighs";
    private static final String LEGS_WORKOUT_CALFS = "com.arm.workout.calfs";
    private static final String LEGS_WORKOUT_HIPS = "com.arm.workout.hips";
    private static final String SHOULDER_WORKOUT_FRONT_SHOULDER = "com.shoulder.workout.front.shoulder";
    private static final String SHOULDER_WORKOUT_BACK_SHOULDER = "com.shoulder.workout.back.shoulder";
    private static final String SHOULDER_WORKOUT_TRAPS = "com.shoulder.workout.traps";
    private static final String CARDIO_WORKOUT_CARDIO = "com.cardio.workout.cardio";
    private static final String CARDIO_WORKOUT_STRETCHING = "com.cardio.workout.stretching";
    private static final String CARDIO_WORKOUT_YOGA = "com.cardio.workout.yoga";
    private static final String CAMERA_IMAGES = "com.camera";

    private static final String ABS_WORKOPUT_PERCENTAGE = "com.abs.percentage";
    private static final String ARM_WORKOPUT_PERCENTAGE = "com.arm.percentage";
    private static final String BACK_WORKOPUT_PERCENTAGE = "com.back.percentage";
    private static final String WINGS_WORKOPUT_PERCENTAGE = "com.wings.percentage";
    private static final String CHEST_WORKOPUT_PERCENTAGE = "com.chest.percentage";
    private static final String LEGS_WORKOPUT_PERCENTAGE = "com.legs.percentage";
    private static final String SHOULDER_WORKOPUT_PERCENTAGE = "com.shoulder.percentage";
    private static final String CARDIO_WORKOPUT_PERCENTAGE = "com.cardio.percentage";

    private static final String DAY1WEEK1 = "com.week1.day1";
    private static final String DAY2WEEK1 = "com.week1.day2";
    private static final String DAY3WEEK1 = "com.week1.day3";
    private static final String DAY4WEEK1 = "com.week1.day4";
    private static final String DAY5WEEK1 = "com.week1.day5";
    private static final String DAY6WEEK1 = "com.week1.day6";

    private static final String DAY1WEEK2 = "com.week2.day1";
    private static final String DAY2WEEK2 = "com.week2.day2";
    private static final String DAY3WEEK2 = "com.week2.day3";
    private static final String DAY4WEEK2 = "com.week2.day4";
    private static final String DAY5WEEK2 = "com.week2.day5";
    private static final String DAY6WEEK2 = "com.week2.day6";

    private static final String DAY1WEEK3 = "com.week3.day1";
    private static final String DAY2WEEK3 = "com.week3.day2";
    private static final String DAY3WEEK3 = "com.week3.day3";
    private static final String DAY4WEEK3 = "com.week3.day4";
    private static final String DAY5WEEK3 = "com.week3.day5";
    private static final String DAY6WEEK3 = "com.week3.day6";

    private static final String DAY1WEEK4 = "com.week4.day1";
    private static final String DAY2WEEK4 = "com.week4.day2";
    private static final String DAY3WEEK4 = "com.week4.day3";
    private static final String DAY4WEEK4 = "com.week4.day4";
    private static final String DAY5WEEK4 = "com.week4.day5";
    private static final String DAY6WEEK4 = "com.week4.day6";




    private static final String UPER_ABS_WORKOPUT_PERCENTAGE = "com.UPER.percentage";
    private static final String LOWER_ABS_WORKOPUT_PERCENTAGE = "com.LOWER.percentage";
    private static final String SIDE_CUTTING_ABS_WORKOPUT_PERCENTAGE = "com.SIDE.percentage";

    private static final String BICEPS_ARM_WORKOPUT_PERCENTAGE = "com.BICEPS.percentage";
    private static final String TRICEPS_ARM_WORKOPUT_PERCENTAGE = "com.TRICEPS.percentage";
    private static final String FOREARMS_ARM_WORKOPUT_PERCENTAGE = "com.FOREARMS.percentage";

    private static final String UPPER_BACKWORKOPUT_PERCENTAGE = "com.BACKu.percentage";
    private static final String LOWER_BACK_WORKOPUT_PERCENTAGE = "com.BACKl.percentage";
    private static final String VSHAPE_BACK_WORKOPUT_PERCENTAGE = "com.vSHAPEb.percentage";


    private static final String VSHAPE_WINGS_WORKOPUT_PERCENTAGE = "com.VSHAPE_WINGS.percentage";
    private static final String WINGS_EXTENTION_WINGS_WORKOPUT_PERCENTAGE = "com.WINGS_EXTENTION.percentage";


    private static final String UPPER_CHEST_CHEST_WORKOPUT_PERCENTAGE = "com.upper.chest.chest.percentage";
    private static final String LOWER_CHEST_CHEST_WORKOPUT_PERCENTAGE = "com.lower.chest.chest.percentage";
    private static final String MIDDLE_CHEST_CHEST_WORKOPUT_PERCENTAGE = "com.middle.chest.chest.percentage";

    private static final String FRONT_THIGHS_LEGS_WORKOPUT_PERCENTAGE = "com.front.thighs.leg.percentage";
    private static final String BACK_THIGHS_LEGS_WORKOPUT_PERCENTAGE = "com.back.thighs.leg.percentage";
    private static final String CALFS_THIGHS_LEGS_WORKOPUT_PERCENTAGE = "com.calfs.thighs.leg.percentage";
    private static final String HIPS_THIGHS_LEGS_WORKOPUT_PERCENTAGE = "com.hips.thighs.leg.percentage";

    private static final String FRONT_SHOULDER_SHOULDER_WORKOPUT_PERCENTAGE = "com.front.thighs.leg.percentage";
    private static final String BACK_SHOULDER_SHOULDER_PERCENTAGE = "com.back.thighs.le.percentage";
    private static final String TRAPS_SHOULDER_PERCENTAGE = "com.traps.thighs.leg.pntage";


    private static final String CARDIO_CARDIO_PERCENTAGE = "com.cardio.cardio.leg.pntage";
    private static final String YOGA_CARDIO_PERCENTAGE = "com.cardio.yoga.leg.pntage";
    private static final String STRETCHING_CARDIO_PERCENTAGE = "com.stretching.cardio.leg.pntage";


    public static void saveStretchingCardioWorkoutPercentage(Context context, int percentage) {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putInt(STRETCHING_CARDIO_PERCENTAGE, percentage);
        editor.apply();
    }

    public static int getStretchingCardioWorkoutPercentage(Context context) {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        int workout = preferences.getInt(STRETCHING_CARDIO_PERCENTAGE, 0);
        return workout;
    }

    public static void saveYogaCardioWorkoutPercentage(Context context, int percentage) {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putInt(YOGA_CARDIO_PERCENTAGE, percentage);
        editor.apply();
    }

    public static int getYogaCardioWorkoutPercentage(Context context) {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        int workout = preferences.getInt(YOGA_CARDIO_PERCENTAGE, 0);
        return workout;
    }

    public static void saveCardioCardioWorkoutPercentage(Context context, int percentage) {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putInt(CARDIO_CARDIO_PERCENTAGE, percentage);
        editor.apply();
    }

    public static int getCadioCardioWorkoutPercentage(Context context) {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        int workout = preferences.getInt(CARDIO_CARDIO_PERCENTAGE, 0);
        return workout;
    }

    public static void saveTrapsWorkoutPercentage(Context context, int percentage) {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putInt(TRAPS_SHOULDER_PERCENTAGE, percentage);
        editor.apply();
    }

    public static int getTrapsWorkoutPercentage(Context context) {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        int workout = preferences.getInt(TRAPS_SHOULDER_PERCENTAGE, 0);
        return workout;
    }


    public static void saveBackShoulderWorkoutPercentage(Context context, int percentage) {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putInt(BACK_SHOULDER_SHOULDER_PERCENTAGE, percentage);
        editor.apply();
    }

    public static int getBackShoulderWorkoutPercentage(Context context) {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        int workout = preferences.getInt(BACK_SHOULDER_SHOULDER_PERCENTAGE, 0);
        return workout;
    }

    public static void saveFrontShoulderWorkoutPercentage(Context context, int percentage) {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putInt(FRONT_SHOULDER_SHOULDER_WORKOPUT_PERCENTAGE, percentage);
        editor.apply();
    }

    public static int getFrontShoulderWorkoutPercentage(Context context) {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        int workout = preferences.getInt(FRONT_SHOULDER_SHOULDER_WORKOPUT_PERCENTAGE, 0);
        return workout;
    }

    public static void saveHipsWorkoutPercentage(Context context, int percentage) {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putInt(HIPS_THIGHS_LEGS_WORKOPUT_PERCENTAGE, percentage);
        editor.apply();
    }

    public static int getHipsWorkoutPercentage(Context context) {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        int workout = preferences.getInt(HIPS_THIGHS_LEGS_WORKOPUT_PERCENTAGE, 0);
        return workout;
    }

    public static void saveCalfsWorkoutPercentage(Context context, int percentage) {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putInt(CALFS_THIGHS_LEGS_WORKOPUT_PERCENTAGE, percentage);
        editor.apply();
    }

    public static int getCalfsWorkoutPercentage(Context context) {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        int workout = preferences.getInt(CALFS_THIGHS_LEGS_WORKOPUT_PERCENTAGE, 0);
        return workout;
    }

    public static void saveBackThiighWorkoutPercentage(Context context, int percentage) {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putInt(BACK_THIGHS_LEGS_WORKOPUT_PERCENTAGE, percentage);
        editor.apply();
    }

    public static int getBackThighWorkoutPercentage(Context context) {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        int workout = preferences.getInt(BACK_THIGHS_LEGS_WORKOPUT_PERCENTAGE, 0);
        return workout;
    }

    public static void saveFrontThiighWorkoutPercentage(Context context, int percentage) {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putInt(FRONT_THIGHS_LEGS_WORKOPUT_PERCENTAGE, percentage);
        editor.apply();
    }

    public static int getFrontThighWorkoutPercentage(Context context) {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        int workout = preferences.getInt(FRONT_THIGHS_LEGS_WORKOPUT_PERCENTAGE, 0);
        return workout;
    }

    public static void saveMiddleChestWorkoutPercentage(Context context, int percentage) {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putInt(MIDDLE_CHEST_CHEST_WORKOPUT_PERCENTAGE, percentage);
        editor.apply();
    }

    public static int getMiddleChestWorkoutPercentage(Context context) {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        int workout = preferences.getInt(MIDDLE_CHEST_CHEST_WORKOPUT_PERCENTAGE, 0);
        return workout;
    }

    public static void saveLowerChestWorkoutPercentage(Context context, int percentage) {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putInt(LOWER_CHEST_CHEST_WORKOPUT_PERCENTAGE, percentage);
        editor.apply();
    }

    public static int getLowerChestWorkoutPercentage(Context context) {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        int workout = preferences.getInt(LOWER_CHEST_CHEST_WORKOPUT_PERCENTAGE, 0);
        return workout;
    }


    public static void saveUpperChestWorkoutPercentage(Context context, int percentage) {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putInt(UPPER_CHEST_CHEST_WORKOPUT_PERCENTAGE, percentage);
        editor.apply();
    }

    public static int getUpperChestWorkoutPercentage(Context context) {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        int workout = preferences.getInt(UPPER_CHEST_CHEST_WORKOPUT_PERCENTAGE, 0);
        return workout;
    }

    public static void saveWingsExtentionWingsWorkoutPercentage(Context context, int percentage) {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putInt(WINGS_EXTENTION_WINGS_WORKOPUT_PERCENTAGE, percentage);
        editor.apply();
    }

    public static int getWingsExtentionWingsWorkoutPercentage(Context context) {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        int workout = preferences.getInt(WINGS_EXTENTION_WINGS_WORKOPUT_PERCENTAGE, 0);
        return workout;
    }

    public static void saveVShapeWingsWorkoutPercentage(Context context, int percentage) {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putInt(VSHAPE_WINGS_WORKOPUT_PERCENTAGE, percentage);
        editor.apply();
    }

    public static int getVShapeWingsWorkoutPercentage(Context context) {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        int workout = preferences.getInt(VSHAPE_WINGS_WORKOPUT_PERCENTAGE, 0);
        return workout;
    }

    public static void saveVShapeBackWorkoutPercentage(Context context, int percentage) {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putInt(VSHAPE_BACK_WORKOPUT_PERCENTAGE, percentage);
        editor.apply();
    }

    public static int getVShapeBackWorkoutPercentage(Context context) {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        int workout = preferences.getInt(VSHAPE_BACK_WORKOPUT_PERCENTAGE, 0);
        return workout;
    }

    public static void saveLowerBackWorkoutPercentage(Context context, int percentage) {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putInt(LOWER_BACK_WORKOPUT_PERCENTAGE, percentage);
        editor.apply();
    }

    public static int getLowerBackWorkoutPercentage(Context context) {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        int workout = preferences.getInt(LOWER_BACK_WORKOPUT_PERCENTAGE, 0);
        return workout;
    }


    public static void saveUpperBackWorkoutPercentage(Context context, int percentage) {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putInt(UPPER_BACKWORKOPUT_PERCENTAGE, percentage);
        editor.apply();
    }

    public static int getUpperBackWorkoutPercentage(Context context) {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        int workout = preferences.getInt(UPPER_BACKWORKOPUT_PERCENTAGE, 0);
        return workout;
    }

    public static void saveForearmsArmWorkoutPercentage(Context context, int percentage) {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putInt(FOREARMS_ARM_WORKOPUT_PERCENTAGE, percentage);
        editor.apply();
    }

    public static int getForearmsArmWorkoutPercentage(Context context) {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        int workout = preferences.getInt(FOREARMS_ARM_WORKOPUT_PERCENTAGE, 0);
        return workout;
    }

    public static void saveTricepsArmWorkoutPercentage(Context context, int percentage) {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putInt(TRICEPS_ARM_WORKOPUT_PERCENTAGE, percentage);
        editor.apply();
    }

    public static int getTricepsArmWorkoutPercentage(Context context) {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        int workout = preferences.getInt(TRICEPS_ARM_WORKOPUT_PERCENTAGE, 0);
        return workout;
    }

    public static void saveBicepsArmWorkoutPercentage(Context context, int percentage) {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putInt(BICEPS_ARM_WORKOPUT_PERCENTAGE, percentage);
        editor.apply();
    }

    public static int getBicepsArmWorkoutPercentage(Context context) {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        int workout = preferences.getInt(BICEPS_ARM_WORKOPUT_PERCENTAGE, 0);
        return workout;
    }

    public static void saveSideCuttingAbsWorkoutPercentage(Context context, int percentage) {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putInt(SIDE_CUTTING_ABS_WORKOPUT_PERCENTAGE, percentage);
        editor.apply();
    }

    public static int getSideCuttingAbsWorkoutPercentage(Context context) {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        int workout = preferences.getInt(SIDE_CUTTING_ABS_WORKOPUT_PERCENTAGE, 0);
        return workout;
    }

    public static void saveLowerAbsWorkoutPercentage(Context context, int percentage) {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putInt(LOWER_ABS_WORKOPUT_PERCENTAGE, percentage);
        editor.apply();
    }

    public static int getLowerAbsWorkoutPercentage(Context context) {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        int workout = preferences.getInt(LOWER_ABS_WORKOPUT_PERCENTAGE, 0);
        return workout;
    }

    public static void saveUperAbsWorkoutPercentage(Context context, int percentage) {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putInt(UPER_ABS_WORKOPUT_PERCENTAGE, percentage);
        editor.apply();
    }

    public static int getUperAbsWorkoutPercentage(Context context) {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        int workout = preferences.getInt(UPER_ABS_WORKOPUT_PERCENTAGE, 0);
        return workout;
    }


    /**
     * main screen prefrences
     *
     * @param context
     * @param armPercentage
     */


    public static void saveArmWorkoutPercentage(Context context, int armPercentage) {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putInt(ARM_WORKOPUT_PERCENTAGE, armPercentage);
        editor.commit();
        //editor.apply();
    }

    public static int getArmWorkoutPercentage(Context context) {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        int workout = preferences.getInt(ARM_WORKOPUT_PERCENTAGE, 0);

        return workout;
    }

    public static void saveCardioWorkoutPercentage(Context context, int armPercentage) {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putInt(CARDIO_WORKOPUT_PERCENTAGE, armPercentage);
        editor.commit();
        //editor.apply();
    }

    public static int getCardioWorkoutPercentage(Context context) {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        int workout = preferences.getInt(CARDIO_WORKOPUT_PERCENTAGE, 0);

        return workout;
    }


    public static void saveShoulderWorkoutPercentage(Context context, int armPercentage) {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putInt(SHOULDER_WORKOPUT_PERCENTAGE, armPercentage);
        editor.commit();
        //editor.apply();
    }

    public static int getShoulderWorkoutPercentage(Context context) {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        int workout = preferences.getInt(SHOULDER_WORKOPUT_PERCENTAGE, 0);

        return workout;
    }


    public static void saveLegsWorkoutPercentage(Context context, int armPercentage) {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putInt(LEGS_WORKOPUT_PERCENTAGE, armPercentage);
        editor.commit();
        //editor.apply();
    }

    public static int getLegsWorkoutPercentage(Context context) {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        int workout = preferences.getInt(LEGS_WORKOPUT_PERCENTAGE, 0);
        return workout;
    }

    public static void saveChestWorkoutPercentage(Context context, int armPercentage) {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putInt(CHEST_WORKOPUT_PERCENTAGE, armPercentage);
        editor.commit();
        //editor.apply();
    }

    public static int getChestWorkoutPercentage(Context context) {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        int workout = preferences.getInt(CHEST_WORKOPUT_PERCENTAGE, 0);

        return workout;
    }

    public static void saveBackWorkoutPercentage(Context context, int backPercentage) {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putInt(BACK_WORKOPUT_PERCENTAGE, backPercentage);
        editor.commit();
        //editor.apply();
    }

    public static int getBackWorkoutPercentage(Context context) {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        int workout = preferences.getInt(BACK_WORKOPUT_PERCENTAGE, 0);
        return workout;
    }

    public static void saveWingsWorkoutPercentage(Context context, int wingsPercentage) {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putInt(WINGS_WORKOPUT_PERCENTAGE, wingsPercentage);
        editor.commit();
        //editor.apply();
    }

    public static int getWingsWorkoutPercentage(Context context) {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        int workout = preferences.getInt(WINGS_WORKOPUT_PERCENTAGE, 0);
        return workout;
    }

    public static void saveAbsWorkoutPercentage(Context context, int absPercentage) {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putInt(ABS_WORKOPUT_PERCENTAGE, absPercentage);
        editor.commit();
        //editor.apply();
    }

    public static int getAbsWorkoutPercentage(Context context) {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        int workout = preferences.getInt(ABS_WORKOPUT_PERCENTAGE, 0);
        return workout;
    }


    /**
     * save workout prefrences
     *
     * @param context
     * @param saveImages
     */

    public static void saveCameraImage(Context context, List<MyImage> saveImages) {
        //saveImages.add(myImages);
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor prefsEditor = preferences.edit();
        Gson gson = new Gson();
        String json = gson.toJson(saveImages);
        prefsEditor.putString(CAMERA_IMAGES, json);
        prefsEditor.apply();
    }

    public static List<MyImage> getCameraImages(Context context) {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        Gson gson = new Gson();
        String json = preferences.getString(CAMERA_IMAGES, "");
        Type listType = new TypeToken<ArrayList<MyImage>>() {
        }.getType();

        List<MyImage> myImages = gson.fromJson(json, listType);
        return myImages;
    }

    public static void saveArmWorkoutBiceps(Context context, List<MyWorkout> myWorkoutList) {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor prefsEditor = preferences.edit();
        Gson gson = new Gson();
        String json = gson.toJson(myWorkoutList);
        prefsEditor.putString(ARM_WORKOUT_BICEPS, json);
        prefsEditor.apply();
    }

    public static void saveArmWorkoutTriceps(Context context, List<MyWorkout> myWorkoutList) {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor prefsEditor = preferences.edit();
        Gson gson = new Gson();
        String json = gson.toJson(myWorkoutList);
        prefsEditor.putString(ARM_WORKOUT_TRICEPS, json);
        prefsEditor.apply();
    }

    public static void saveArmWorkoutForearm(Context context, List<MyWorkout> myWorkoutList) {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor prefsEditor = preferences.edit();
        Gson gson = new Gson();
        String json = gson.toJson(myWorkoutList);
        prefsEditor.putString(ARM_WORKOUT_FOREARM, json);
        prefsEditor.apply();
    }

    public static void saveAbsWorkoutUpperAbs(Context context, List<MyWorkout> myWorkoutList) {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor prefsEditor = preferences.edit();
        Gson gson = new Gson();
        String json = gson.toJson(myWorkoutList);
        prefsEditor.putString(ABS_WORKOUT_UPPER_ABS, json);
        prefsEditor.apply();
    }

    public static void saveAbsWorkoutLowerAbs(Context context, List<MyWorkout> myWorkoutList) {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor prefsEditor = preferences.edit();
        Gson gson = new Gson();
        String json = gson.toJson(myWorkoutList);
        prefsEditor.putString(ABS_WORKOUT_LOWER_ABS, json);
        prefsEditor.apply();
    }

    public static void saveAbsWorkoutSideCuttingAbs(Context context, List<MyWorkout> myWorkoutList) {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor prefsEditor = preferences.edit();
        Gson gson = new Gson();
        String json = gson.toJson(myWorkoutList);
        prefsEditor.putString(ABS_WORKOUT_SIDE_CUTTING, json);
        prefsEditor.apply();
    }

    public static void saveBackWorkoutUperBack(Context context, List<MyWorkout> myWorkoutList) {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor prefsEditor = preferences.edit();
        Gson gson = new Gson();
        String json = gson.toJson(myWorkoutList);
        prefsEditor.putString(BACK_WORKOUT_UPPER_BACK, json);
        prefsEditor.apply();
    }

    public static void saveBackWorkoutLowerBack(Context context, List<MyWorkout> myWorkoutList) {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor prefsEditor = preferences.edit();
        Gson gson = new Gson();
        String json = gson.toJson(myWorkoutList);
        prefsEditor.putString(BACK_WORKOUT_LOWER_BACK, json);
        prefsEditor.apply();
    }

    public static void saveBackWorkoutVShapeBack(Context context, List<MyWorkout> myWorkoutList) {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor prefsEditor = preferences.edit();
        Gson gson = new Gson();
        String json = gson.toJson(myWorkoutList);
        prefsEditor.putString(BACK_WORKOUT_VSHAPE_BACK, json);
        prefsEditor.apply();
    }

    public static void saveWingsWorkoutWingsExtention(Context context, List<MyWorkout> myWorkoutList) {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor prefsEditor = preferences.edit();
        Gson gson = new Gson();
        String json = gson.toJson(myWorkoutList);
        prefsEditor.putString(WING_WORKOUT_WINGS_EXTENTION, json);
        prefsEditor.apply();
    }

    public static void saveWingsWorkoutVShape(Context context, List<MyWorkout> myWorkoutList) {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor prefsEditor = preferences.edit();
        Gson gson = new Gson();
        String json = gson.toJson(myWorkoutList);
        prefsEditor.putString(WING_WORKOUT_VSHAPE_BACK, json);
        prefsEditor.apply();
    }

    public static void saveChestWorkoutUperChest(Context context, List<MyWorkout> myWorkoutList) {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor prefsEditor = preferences.edit();
        Gson gson = new Gson();
        String json = gson.toJson(myWorkoutList);
        prefsEditor.putString(CHEST_WORKOUT_UPPER_CHEST, json);
        prefsEditor.apply();
    }

    public static void saveChestWorkoutMiddleChest(Context context, List<MyWorkout> myWorkoutList) {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor prefsEditor = preferences.edit();
        Gson gson = new Gson();
        String json = gson.toJson(myWorkoutList);
        prefsEditor.putString(CHEST_WORKOUT_MIDDLE_CHEST, json);
        prefsEditor.apply();
    }

    public static void saveChestWorkoutLowerChest(Context context, List<MyWorkout> myWorkoutList) {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor prefsEditor = preferences.edit();
        Gson gson = new Gson();
        String json = gson.toJson(myWorkoutList);
        prefsEditor.putString(CHEST_WORKOUT_LOWER_CHEST, json);
        prefsEditor.apply();
    }

    public static void saveLegsWorkoutFrontThighs(Context context, List<MyWorkout> myWorkoutList) {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor prefsEditor = preferences.edit();
        Gson gson = new Gson();
        String json = gson.toJson(myWorkoutList);
        prefsEditor.putString(LEGS_WORKOUT_FRONT_THIGHS, json);
        prefsEditor.apply();
    }

    public static void saveLegsWorkoutBackThighs(Context context, List<MyWorkout> myWorkoutList) {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor prefsEditor = preferences.edit();
        Gson gson = new Gson();
        String json = gson.toJson(myWorkoutList);
        prefsEditor.putString(LEGS_WORKOUT_BACK_THIGHS, json);
        prefsEditor.apply();
    }

    public static void saveLegsWorkoutCalfs(Context context, List<MyWorkout> myWorkoutList) {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor prefsEditor = preferences.edit();
        Gson gson = new Gson();
        String json = gson.toJson(myWorkoutList);
        prefsEditor.putString(LEGS_WORKOUT_CALFS, json);
        prefsEditor.apply();
    }

    public static void saveLegsWorkoutHips(Context context, List<MyWorkout> myWorkoutList) {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor prefsEditor = preferences.edit();
        Gson gson = new Gson();
        String json = gson.toJson(myWorkoutList);
        prefsEditor.putString(LEGS_WORKOUT_HIPS, json);
        prefsEditor.apply();
    }

    public static void saveShoulderWorkoutTraps(Context context, List<MyWorkout> myWorkoutList) {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor prefsEditor = preferences.edit();
        Gson gson = new Gson();
        String json = gson.toJson(myWorkoutList);
        prefsEditor.putString(SHOULDER_WORKOUT_TRAPS, json);
        prefsEditor.apply();
    }

    public static void saveShoulderWorkoutBackShoulder(Context context, List<MyWorkout> myWorkoutList) {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor prefsEditor = preferences.edit();
        Gson gson = new Gson();
        String json = gson.toJson(myWorkoutList);
        prefsEditor.putString(SHOULDER_WORKOUT_BACK_SHOULDER, json);
        prefsEditor.apply();
    }

    public static void saveShoulderWorkoutFrontShoulder(Context context, List<MyWorkout> myWorkoutList) {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor prefsEditor = preferences.edit();
        Gson gson = new Gson();
        String json = gson.toJson(myWorkoutList);
        prefsEditor.putString(SHOULDER_WORKOUT_FRONT_SHOULDER, json);
        prefsEditor.apply();
    }

    public static void saveCardioWorkoutCardio(Context context, List<MyWorkout> myWorkoutList) {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor prefsEditor = preferences.edit();
        Gson gson = new Gson();
        String json = gson.toJson(myWorkoutList);
        prefsEditor.putString(CARDIO_WORKOUT_CARDIO, json);
        prefsEditor.apply();
    }

    public static void saveCardioWorkoutStretching(Context context, List<MyWorkout> myWorkoutList) {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor prefsEditor = preferences.edit();
        Gson gson = new Gson();
        String json = gson.toJson(myWorkoutList);
        prefsEditor.putString(CARDIO_WORKOUT_STRETCHING, json);
        prefsEditor.apply();
    }

    public static void saveCardioWorkoutYoga(Context context, List<MyWorkout> myWorkoutList) {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor prefsEditor = preferences.edit();
        Gson gson = new Gson();
        String json = gson.toJson(myWorkoutList);
        prefsEditor.putString(CARDIO_WORKOUT_YOGA, json);
        prefsEditor.apply();
    }

    public static List<MyWorkout> getArmWorkoutBiceps(Context context) {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        Gson gson = new Gson();
        String json = preferences.getString(ARM_WORKOUT_BICEPS, "");
        Type listType = new TypeToken<ArrayList<MyWorkout>>() {
        }.getType();

        List<MyWorkout> myWorkouts = gson.fromJson(json, listType);
        return myWorkouts;
    }

    public static List<MyWorkout> getArmWorkoutTriceps(Context context) {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        Gson gson = new Gson();
        String json = preferences.getString(ARM_WORKOUT_TRICEPS, "");
        Type listType = new TypeToken<ArrayList<MyWorkout>>() {
        }.getType();

        List<MyWorkout> myWorkouts = gson.fromJson(json, listType);
        return myWorkouts;
    }

    public static List<MyWorkout> getArmWorkoutForearm(Context context) {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        Gson gson = new Gson();
        String json = preferences.getString(ARM_WORKOUT_FOREARM, "");
        Type listType = new TypeToken<ArrayList<MyWorkout>>() {
        }.getType();

        List<MyWorkout> myWorkouts = gson.fromJson(json, listType);
        return myWorkouts;
    }

    public static List<MyWorkout> getAbsWorkoutUpperAbs(Context context) {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        Gson gson = new Gson();
        String json = preferences.getString(ABS_WORKOUT_UPPER_ABS, "");
        Type listType = new TypeToken<ArrayList<MyWorkout>>() {
        }.getType();

        List<MyWorkout> myWorkouts = gson.fromJson(json, listType);
        return myWorkouts;
    }

    public static List<MyWorkout> getAbsWorkoutLowerAbs(Context context) {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        Gson gson = new Gson();
        String json = preferences.getString(ABS_WORKOUT_LOWER_ABS, "");
        Type listType = new TypeToken<ArrayList<MyWorkout>>() {
        }.getType();

        List<MyWorkout> myWorkouts = gson.fromJson(json, listType);
        return myWorkouts;
    }

    public static List<MyWorkout> getAbsWorkoutSideCuttingAbs(Context context) {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        Gson gson = new Gson();
        String json = preferences.getString(ABS_WORKOUT_SIDE_CUTTING, "");
        Type listType = new TypeToken<ArrayList<MyWorkout>>() {
        }.getType();

        List<MyWorkout> myWorkouts = gson.fromJson(json, listType);
        return myWorkouts;
    }

    public static List<MyWorkout> getBackWorkoutUperBack(Context context) {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        Gson gson = new Gson();
        String json = preferences.getString(BACK_WORKOUT_UPPER_BACK, "");
        Type listType = new TypeToken<ArrayList<MyWorkout>>() {
        }.getType();

        List<MyWorkout> myWorkouts = gson.fromJson(json, listType);
        return myWorkouts;
    }

    public static List<MyWorkout> getBackWorkoutLowerBack(Context context) {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        Gson gson = new Gson();
        String json = preferences.getString(BACK_WORKOUT_LOWER_BACK, "");
        Type listType = new TypeToken<ArrayList<MyWorkout>>() {
        }.getType();

        List<MyWorkout> myWorkouts = gson.fromJson(json, listType);
        return myWorkouts;
    }

    public static List<MyWorkout> getBackWorkoutVShapeBack(Context context) {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        Gson gson = new Gson();
        String json = preferences.getString(BACK_WORKOUT_VSHAPE_BACK, "");
        Type listType = new TypeToken<ArrayList<MyWorkout>>() {
        }.getType();

        List<MyWorkout> myWorkouts = gson.fromJson(json, listType);
        return myWorkouts;
    }

    public static List<MyWorkout> getWingsWorkoutVShapeWings(Context context) {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        Gson gson = new Gson();
        String json = preferences.getString(WING_WORKOUT_VSHAPE_BACK, "");
        Type listType = new TypeToken<ArrayList<MyWorkout>>() {
        }.getType();

        List<MyWorkout> myWorkouts = gson.fromJson(json, listType);
        return myWorkouts;
    }

    public static List<MyWorkout> getWingsWorkoutWingsExtention(Context context) {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        Gson gson = new Gson();
        String json = preferences.getString(WING_WORKOUT_WINGS_EXTENTION, "");
        Type listType = new TypeToken<ArrayList<MyWorkout>>() {
        }.getType();

        List<MyWorkout> myWorkouts = gson.fromJson(json, listType);
        return myWorkouts;
    }

    public static List<MyWorkout> getChestWorkoutUpperChest(Context context) {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        Gson gson = new Gson();
        String json = preferences.getString(CHEST_WORKOUT_UPPER_CHEST, "");
        Type listType = new TypeToken<ArrayList<MyWorkout>>() {
        }.getType();

        List<MyWorkout> myWorkouts = gson.fromJson(json, listType);
        return myWorkouts;
    }

    public static List<MyWorkout> getChestWorkoutMiddleChest(Context context) {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        Gson gson = new Gson();
        String json = preferences.getString(CHEST_WORKOUT_MIDDLE_CHEST, "");
        Type listType = new TypeToken<ArrayList<MyWorkout>>() {
        }.getType();

        List<MyWorkout> myWorkouts = gson.fromJson(json, listType);
        return myWorkouts;
    }

    public static List<MyWorkout> getChestWorkoutLowerChest(Context context) {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        Gson gson = new Gson();
        String json = preferences.getString(CHEST_WORKOUT_LOWER_CHEST, "");
        Type listType = new TypeToken<ArrayList<MyWorkout>>() {
        }.getType();

        List<MyWorkout> myWorkouts = gson.fromJson(json, listType);
        return myWorkouts;
    }

    public static List<MyWorkout> getLegsWorkoutFrontThighs(Context context) {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        Gson gson = new Gson();
        String json = preferences.getString(LEGS_WORKOUT_FRONT_THIGHS, "");
        Type listType = new TypeToken<ArrayList<MyWorkout>>() {
        }.getType();

        List<MyWorkout> myWorkouts = gson.fromJson(json, listType);
        return myWorkouts;
    }

    public static List<MyWorkout> getLegsWorkoutBackThighs(Context context) {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        Gson gson = new Gson();
        String json = preferences.getString(LEGS_WORKOUT_BACK_THIGHS, "");
        Type listType = new TypeToken<ArrayList<MyWorkout>>() {
        }.getType();

        List<MyWorkout> myWorkouts = gson.fromJson(json, listType);
        return myWorkouts;
    }

    public static List<MyWorkout> getLegsWorkoutCalfs(Context context) {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        Gson gson = new Gson();
        String json = preferences.getString(LEGS_WORKOUT_CALFS, "");
        Type listType = new TypeToken<ArrayList<MyWorkout>>() {
        }.getType();

        List<MyWorkout> myWorkouts = gson.fromJson(json, listType);
        return myWorkouts;
    }

    public static List<MyWorkout> getLegsWorkoutHips(Context context) {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        Gson gson = new Gson();
        String json = preferences.getString(LEGS_WORKOUT_HIPS, "");
        Type listType = new TypeToken<ArrayList<MyWorkout>>() {
        }.getType();

        List<MyWorkout> myWorkouts = gson.fromJson(json, listType);
        return myWorkouts;
    }

    public static List<MyWorkout> getShoulderWorkoutFrontShoulder(Context context) {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        Gson gson = new Gson();
        String json = preferences.getString(SHOULDER_WORKOUT_FRONT_SHOULDER, "");
        Type listType = new TypeToken<ArrayList<MyWorkout>>() {
        }.getType();

        List<MyWorkout> myWorkouts = gson.fromJson(json, listType);
        return myWorkouts;
    }

    public static List<MyWorkout> getShoulderWorkoutBackShoulder(Context context) {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        Gson gson = new Gson();
        String json = preferences.getString(SHOULDER_WORKOUT_BACK_SHOULDER, "");
        Type listType = new TypeToken<ArrayList<MyWorkout>>() {
        }.getType();

        List<MyWorkout> myWorkouts = gson.fromJson(json, listType);
        return myWorkouts;
    }

    public static List<MyWorkout> getShoulderWorkoutTraps(Context context) {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        Gson gson = new Gson();
        String json = preferences.getString(SHOULDER_WORKOUT_TRAPS, "");
        Type listType = new TypeToken<ArrayList<MyWorkout>>() {
        }.getType();

        List<MyWorkout> myWorkouts = gson.fromJson(json, listType);
        return myWorkouts;
    }

    public static List<MyWorkout> getCardioWorkoutCardio(Context context) {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        Gson gson = new Gson();
        String json = preferences.getString(CARDIO_WORKOUT_CARDIO, "");
        Type listType = new TypeToken<ArrayList<MyWorkout>>() {
        }.getType();

        List<MyWorkout> myWorkouts = gson.fromJson(json, listType);
        return myWorkouts;
    }

    public static List<MyWorkout> getCardioWorkoutStretching(Context context) {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        Gson gson = new Gson();
        String json = preferences.getString(CARDIO_WORKOUT_STRETCHING, "");
        Type listType = new TypeToken<ArrayList<MyWorkout>>() {
        }.getType();

        List<MyWorkout> myWorkouts = gson.fromJson(json, listType);
        return myWorkouts;
    }

    public static List<MyWorkout> getCardioWorkoutYoga(Context context) {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        Gson gson = new Gson();
        String json = preferences.getString(CARDIO_WORKOUT_YOGA, "");
        Type listType = new TypeToken<ArrayList<MyWorkout>>() {
        }.getType();

        List<MyWorkout> myWorkouts = gson.fromJson(json, listType);
        return myWorkouts;
    }


    public static void deleteWorkoutPrefrences(Context context) {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor prefsEditor = preferences.edit();
        prefsEditor.clear();
        prefsEditor.apply();
    }




    public static void saveWeek1Day1Workout(Context context, List<MyWorkout> myWorkoutList) {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor prefsEditor = preferences.edit();
        Gson gson = new Gson();
        String json = gson.toJson(myWorkoutList);
        prefsEditor.putString(DAY1WEEK1, json);
        prefsEditor.apply();
    }

    public static List<MyWorkout> getWeek1Day1Workout(Context context) {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        Gson gson = new Gson();
        String json = preferences.getString(DAY1WEEK1, "");
        Type listType = new TypeToken<ArrayList<MyWorkout>>() {
        }.getType();

        List<MyWorkout> myWorkouts = gson.fromJson(json, listType);
        return myWorkouts;
    }

    public static void saveWeek1Day2Workout(Context context, List<MyWorkout> myWorkoutList) {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor prefsEditor = preferences.edit();
        Gson gson = new Gson();
        String json = gson.toJson(myWorkoutList);
        prefsEditor.putString(DAY2WEEK1, json);
        prefsEditor.apply();
    }

    public static List<MyWorkout> getWeek1Day2Workout(Context context) {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        Gson gson = new Gson();
        String json = preferences.getString(DAY2WEEK1, "");
        Type listType = new TypeToken<ArrayList<MyWorkout>>() {
        }.getType();

        List<MyWorkout> myWorkouts = gson.fromJson(json, listType);
        return myWorkouts;
    }

    public static void saveWeek1Day3Workout(Context context, List<MyWorkout> myWorkoutList) {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor prefsEditor = preferences.edit();
        Gson gson = new Gson();
        String json = gson.toJson(myWorkoutList);
        prefsEditor.putString(DAY3WEEK1, json);
        prefsEditor.apply();
    }

    public static List<MyWorkout> getWeek1Day3Workout(Context context) {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        Gson gson = new Gson();
        String json = preferences.getString(DAY3WEEK1, "");
        Type listType = new TypeToken<ArrayList<MyWorkout>>() {
        }.getType();

        List<MyWorkout> myWorkouts = gson.fromJson(json, listType);
        return myWorkouts;
    }

    public static void saveWeek1Day4Workout(Context context, List<MyWorkout> myWorkoutList) {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor prefsEditor = preferences.edit();
        Gson gson = new Gson();
        String json = gson.toJson(myWorkoutList);
        prefsEditor.putString(DAY4WEEK1, json);
        prefsEditor.apply();
    }

    public static List<MyWorkout> getWeek1Day4Workout(Context context) {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        Gson gson = new Gson();
        String json = preferences.getString(DAY4WEEK1, "");
        Type listType = new TypeToken<ArrayList<MyWorkout>>() {
        }.getType();

        List<MyWorkout> myWorkouts = gson.fromJson(json, listType);
        return myWorkouts;
    }

    public static void saveWeek1Day5Workout(Context context, List<MyWorkout> myWorkoutList) {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor prefsEditor = preferences.edit();
        Gson gson = new Gson();
        String json = gson.toJson(myWorkoutList);
        prefsEditor.putString(DAY5WEEK1, json);
        prefsEditor.apply();
    }

    public static List<MyWorkout> getWeek1Day5Workout(Context context) {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        Gson gson = new Gson();
        String json = preferences.getString(DAY5WEEK1, "");
        Type listType = new TypeToken<ArrayList<MyWorkout>>() {
        }.getType();

        List<MyWorkout> myWorkouts = gson.fromJson(json, listType);
        return myWorkouts;
    }

    public static void saveWeek1Day6Workout(Context context, List<MyWorkout> myWorkoutList) {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor prefsEditor = preferences.edit();
        Gson gson = new Gson();
        String json = gson.toJson(myWorkoutList);
        prefsEditor.putString(DAY6WEEK1, json);
        prefsEditor.apply();
    }

    public static List<MyWorkout> getWeek1Day6Workout(Context context) {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        Gson gson = new Gson();
        String json = preferences.getString(DAY6WEEK1, "");
        Type listType = new TypeToken<ArrayList<MyWorkout>>() {
        }.getType();

        List<MyWorkout> myWorkouts = gson.fromJson(json, listType);
        return myWorkouts;
    }







    public static void saveWeek2Day1Workout(Context context, List<MyWorkout> myWorkoutList) {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor prefsEditor = preferences.edit();
        Gson gson = new Gson();
        String json = gson.toJson(myWorkoutList);
        prefsEditor.putString(DAY1WEEK2, json);
        prefsEditor.apply();
    }

    public static List<MyWorkout> getWeek2Day1Workout(Context context) {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        Gson gson = new Gson();
        String json = preferences.getString(DAY1WEEK2, "");
        Type listType = new TypeToken<ArrayList<MyWorkout>>() {
        }.getType();

        List<MyWorkout> myWorkouts = gson.fromJson(json, listType);
        return myWorkouts;
    }

    public static void saveWeek2Day2Workout(Context context, List<MyWorkout> myWorkoutList) {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor prefsEditor = preferences.edit();
        Gson gson = new Gson();
        String json = gson.toJson(myWorkoutList);
        prefsEditor.putString(DAY2WEEK2, json);
        prefsEditor.apply();
    }

    public static List<MyWorkout> getWeek2Day2Workout(Context context) {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        Gson gson = new Gson();
        String json = preferences.getString(DAY2WEEK2, "");
        Type listType = new TypeToken<ArrayList<MyWorkout>>() {
        }.getType();

        List<MyWorkout> myWorkouts = gson.fromJson(json, listType);
        return myWorkouts;
    }

    public static void saveWeek2Day3Workout(Context context, List<MyWorkout> myWorkoutList) {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor prefsEditor = preferences.edit();
        Gson gson = new Gson();
        String json = gson.toJson(myWorkoutList);
        prefsEditor.putString(DAY3WEEK2, json);
        prefsEditor.apply();
    }

    public static List<MyWorkout> getWeek2Day3Workout(Context context) {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        Gson gson = new Gson();
        String json = preferences.getString(DAY3WEEK2, "");
        Type listType = new TypeToken<ArrayList<MyWorkout>>() {
        }.getType();

        List<MyWorkout> myWorkouts = gson.fromJson(json, listType);
        return myWorkouts;
    }

    public static void saveWeek2Day4Workout(Context context, List<MyWorkout> myWorkoutList) {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor prefsEditor = preferences.edit();
        Gson gson = new Gson();
        String json = gson.toJson(myWorkoutList);
        prefsEditor.putString(DAY4WEEK2, json);
        prefsEditor.apply();
    }

    public static List<MyWorkout> getWeek2Day4Workout(Context context) {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        Gson gson = new Gson();
        String json = preferences.getString(DAY4WEEK2, "");
        Type listType = new TypeToken<ArrayList<MyWorkout>>() {
        }.getType();

        List<MyWorkout> myWorkouts = gson.fromJson(json, listType);
        return myWorkouts;
    }

    public static void saveWeek2Day5Workout(Context context, List<MyWorkout> myWorkoutList) {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor prefsEditor = preferences.edit();
        Gson gson = new Gson();
        String json = gson.toJson(myWorkoutList);
        prefsEditor.putString(DAY5WEEK2, json);
        prefsEditor.apply();
    }

    public static List<MyWorkout> getWeek2Day5Workout(Context context) {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        Gson gson = new Gson();
        String json = preferences.getString(DAY5WEEK2, "");
        Type listType = new TypeToken<ArrayList<MyWorkout>>() {
        }.getType();

        List<MyWorkout> myWorkouts = gson.fromJson(json, listType);
        return myWorkouts;
    }

    public static void saveWeek2Day6Workout(Context context, List<MyWorkout> myWorkoutList) {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor prefsEditor = preferences.edit();
        Gson gson = new Gson();
        String json = gson.toJson(myWorkoutList);
        prefsEditor.putString(DAY6WEEK2, json);
        prefsEditor.apply();
    }

    public static List<MyWorkout> getWeek2Day6Workout(Context context) {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        Gson gson = new Gson();
        String json = preferences.getString(DAY6WEEK2, "");
        Type listType = new TypeToken<ArrayList<MyWorkout>>() {
        }.getType();

        List<MyWorkout> myWorkouts = gson.fromJson(json, listType);
        return myWorkouts;
    }







    public static void saveWeek3Day1Workout(Context context, List<MyWorkout> myWorkoutList) {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor prefsEditor = preferences.edit();
        Gson gson = new Gson();
        String json = gson.toJson(myWorkoutList);
        prefsEditor.putString(DAY1WEEK3, json);
        prefsEditor.apply();
    }

    public static List<MyWorkout> getWeek3Day1Workout(Context context) {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        Gson gson = new Gson();
        String json = preferences.getString(DAY1WEEK3, "");
        Type listType = new TypeToken<ArrayList<MyWorkout>>() {
        }.getType();

        List<MyWorkout> myWorkouts = gson.fromJson(json, listType);
        return myWorkouts;
    }

    public static void saveWeek3Day2Workout(Context context, List<MyWorkout> myWorkoutList) {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor prefsEditor = preferences.edit();
        Gson gson = new Gson();
        String json = gson.toJson(myWorkoutList);
        prefsEditor.putString(DAY2WEEK3, json);
        prefsEditor.apply();
    }

    public static List<MyWorkout> getWeek3Day2Workout(Context context) {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        Gson gson = new Gson();
        String json = preferences.getString(DAY2WEEK3, "");
        Type listType = new TypeToken<ArrayList<MyWorkout>>() {
        }.getType();

        List<MyWorkout> myWorkouts = gson.fromJson(json, listType);
        return myWorkouts;
    }

    public static void saveWeek3Day3Workout(Context context, List<MyWorkout> myWorkoutList) {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor prefsEditor = preferences.edit();
        Gson gson = new Gson();
        String json = gson.toJson(myWorkoutList);
        prefsEditor.putString(DAY3WEEK3, json);
        prefsEditor.apply();
    }

    public static List<MyWorkout> getWeek3Day3Workout(Context context) {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        Gson gson = new Gson();
        String json = preferences.getString(DAY3WEEK3, "");
        Type listType = new TypeToken<ArrayList<MyWorkout>>() {
        }.getType();

        List<MyWorkout> myWorkouts = gson.fromJson(json, listType);
        return myWorkouts;
    }

    public static void saveWeek3Day4Workout(Context context, List<MyWorkout> myWorkoutList) {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor prefsEditor = preferences.edit();
        Gson gson = new Gson();
        String json = gson.toJson(myWorkoutList);
        prefsEditor.putString(DAY4WEEK3, json);
        prefsEditor.apply();
    }

    public static List<MyWorkout> getWeek3Day4Workout(Context context) {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        Gson gson = new Gson();
        String json = preferences.getString(DAY4WEEK3, "");
        Type listType = new TypeToken<ArrayList<MyWorkout>>() {
        }.getType();

        List<MyWorkout> myWorkouts = gson.fromJson(json, listType);
        return myWorkouts;
    }

    public static void saveWeek3Day5Workout(Context context, List<MyWorkout> myWorkoutList) {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor prefsEditor = preferences.edit();
        Gson gson = new Gson();
        String json = gson.toJson(myWorkoutList);
        prefsEditor.putString(DAY5WEEK3, json);
        prefsEditor.apply();
    }

    public static List<MyWorkout> getWeek3Day5Workout(Context context) {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        Gson gson = new Gson();
        String json = preferences.getString(DAY5WEEK3, "");
        Type listType = new TypeToken<ArrayList<MyWorkout>>() {
        }.getType();

        List<MyWorkout> myWorkouts = gson.fromJson(json, listType);
        return myWorkouts;
    }

    public static void saveWeek3Day6Workout(Context context, List<MyWorkout> myWorkoutList) {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor prefsEditor = preferences.edit();
        Gson gson = new Gson();
        String json = gson.toJson(myWorkoutList);
        prefsEditor.putString(DAY6WEEK3, json);
        prefsEditor.apply();
    }

    public static List<MyWorkout> getWeek3Day6Workout(Context context) {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        Gson gson = new Gson();
        String json = preferences.getString(DAY6WEEK3, "");
        Type listType = new TypeToken<ArrayList<MyWorkout>>() {
        }.getType();

        List<MyWorkout> myWorkouts = gson.fromJson(json, listType);
        return myWorkouts;
    }










    public static void saveWeek4Day1Workout(Context context, List<MyWorkout> myWorkoutList) {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor prefsEditor = preferences.edit();
        Gson gson = new Gson();
        String json = gson.toJson(myWorkoutList);
        prefsEditor.putString(DAY1WEEK4, json);
        prefsEditor.apply();
    }

    public static List<MyWorkout> getWeek4Day1Workout(Context context) {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        Gson gson = new Gson();
        String json = preferences.getString(DAY1WEEK4, "");
        Type listType = new TypeToken<ArrayList<MyWorkout>>() {
        }.getType();

        List<MyWorkout> myWorkouts = gson.fromJson(json, listType);
        return myWorkouts;
    }

    public static void saveWeek4Day2Workout(Context context, List<MyWorkout> myWorkoutList) {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor prefsEditor = preferences.edit();
        Gson gson = new Gson();
        String json = gson.toJson(myWorkoutList);
        prefsEditor.putString(DAY2WEEK4, json);
        prefsEditor.apply();
    }

    public static List<MyWorkout> getWeek4Day2Workout(Context context) {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        Gson gson = new Gson();
        String json = preferences.getString(DAY2WEEK4, "");
        Type listType = new TypeToken<ArrayList<MyWorkout>>() {
        }.getType();

        List<MyWorkout> myWorkouts = gson.fromJson(json, listType);
        return myWorkouts;
    }

    public static void saveWeek4Day3Workout(Context context, List<MyWorkout> myWorkoutList) {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor prefsEditor = preferences.edit();
        Gson gson = new Gson();
        String json = gson.toJson(myWorkoutList);
        prefsEditor.putString(DAY3WEEK4, json);
        prefsEditor.apply();
    }

    public static List<MyWorkout> getWeek4Day3Workout(Context context) {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        Gson gson = new Gson();
        String json = preferences.getString(DAY3WEEK4, "");
        Type listType = new TypeToken<ArrayList<MyWorkout>>() {
        }.getType();

        List<MyWorkout> myWorkouts = gson.fromJson(json, listType);
        return myWorkouts;
    }

    public static void saveWeek4Day4Workout(Context context, List<MyWorkout> myWorkoutList) {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor prefsEditor = preferences.edit();
        Gson gson = new Gson();
        String json = gson.toJson(myWorkoutList);
        prefsEditor.putString(DAY4WEEK4, json);
        prefsEditor.apply();
    }

    public static List<MyWorkout> getWeek4Day4Workout(Context context) {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        Gson gson = new Gson();
        String json = preferences.getString(DAY4WEEK4, "");
        Type listType = new TypeToken<ArrayList<MyWorkout>>() {
        }.getType();

        List<MyWorkout> myWorkouts = gson.fromJson(json, listType);
        return myWorkouts;
    }

    public static void saveWeek4Day5Workout(Context context, List<MyWorkout> myWorkoutList) {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor prefsEditor = preferences.edit();
        Gson gson = new Gson();
        String json = gson.toJson(myWorkoutList);
        prefsEditor.putString(DAY5WEEK4, json);
        prefsEditor.apply();
    }

    public static List<MyWorkout> getWeek4Day5Workout(Context context) {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        Gson gson = new Gson();
        String json = preferences.getString(DAY5WEEK4, "");
        Type listType = new TypeToken<ArrayList<MyWorkout>>() {
        }.getType();

        List<MyWorkout> myWorkouts = gson.fromJson(json, listType);
        return myWorkouts;
    }

    public static void saveWeek4Day6Workout(Context context, List<MyWorkout> myWorkoutList) {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor prefsEditor = preferences.edit();
        Gson gson = new Gson();
        String json = gson.toJson(myWorkoutList);
        prefsEditor.putString(DAY6WEEK4, json);
        prefsEditor.apply();
    }

    public static List<MyWorkout> getWeek4Day6Workout(Context context) {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        Gson gson = new Gson();
        String json = preferences.getString(DAY6WEEK4, "");
        Type listType = new TypeToken<ArrayList<MyWorkout>>() {
        }.getType();

        List<MyWorkout> myWorkouts = gson.fromJson(json, listType);
        return myWorkouts;
    }


}
