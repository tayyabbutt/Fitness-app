package com.mobxpert.men30dayfitnesschallenge.adapter.armsWorkout;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.mobxpert.men30dayfitnesschallenge.R;
import com.mobxpert.men30dayfitnesschallenge.interfaces.OnMyWorkoutClickListner;
import com.mobxpert.men30dayfitnesschallenge.models.MyWorkout;
import com.mobxpert.men30dayfitnesschallenge.utils.MyDbPrefrences;

import java.util.List;

public class ArmWorkoutTricepsAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    List<MyWorkout> myWorkoutList;
    OnMyWorkoutClickListner bicepsItemClickListner;
    int itemPosition;
    boolean isExerciseDone;
    Context context;

    public ArmWorkoutTricepsAdapter(Context context, List<MyWorkout> myWorkoutList, int itemPosition, boolean isExerciseDone, OnMyWorkoutClickListner onMyWorkoutClickListner) {
        this.context = context;
        this.myWorkoutList = myWorkoutList;
        this.itemPosition = itemPosition;
        this.isExerciseDone = isExerciseDone;
        this.bicepsItemClickListner = onMyWorkoutClickListner;
    }


    private class MyViewHolder extends RecyclerView.ViewHolder {
        TextView bicepsName,completetext;
        LinearLayout parenLayout;

        public MyViewHolder(View itemView) {
            super(itemView);
            bicepsName = itemView.findViewById(R.id.bicepsName);
            completetext = itemView.findViewById(R.id.doneText);
            parenLayout = itemView.findViewById(R.id.parentLayout);
        }
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view;
        view = LayoutInflater.from(parent.getContext()).inflate(R.layout.arm_workout_item_layout, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        MyViewHolder bodyViewHolder = (MyViewHolder) holder;
        bindBodyComponent(bodyViewHolder, position);
    }

    private void bindBodyComponent(MyViewHolder holder, final int position) {

        holder.bicepsName.setText(myWorkoutList.get(position).getName());
        holder.parenLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                bicepsItemClickListner.onListItemClicked(position, myWorkoutList.get(position));
            }
        });

        if (myWorkoutList.get(position).isExerciseDone() == true) {
            holder.completetext.setVisibility(View.VISIBLE);
        }

        if (isExerciseDone == true && itemPosition == position) {
            myWorkoutList.get(position).setExerciseDone(true);
            MyDbPrefrences.saveArmWorkoutTriceps(context, myWorkoutList);
            holder.completetext.setVisibility(View.VISIBLE);
        } else {
            holder.bicepsName.setText(myWorkoutList.get(position).getName());
        }
    }

    @Override
    public int getItemCount() {
        return myWorkoutList.size();
    }


}