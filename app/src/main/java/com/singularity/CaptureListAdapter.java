package com.singularity;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by siddharthamaji on 07/04/18.
 */

 public class CaptureListAdapter extends RecyclerView.Adapter<CaptureListAdapter.ViewHolder> {
    private Context context;
    private int layout;
    private ArrayList<CaptureDataItem> captureDataItems;
    private int lastPosition = -1;

    public CaptureListAdapter(Context context, int layout, ArrayList<CaptureDataItem> captureDataItems) {
        this.context = context;
        this.layout = layout;
        this.captureDataItems = captureDataItems;
    }

    // Allows to remember the last item shown on screen
    @Override
    public CaptureListAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(layout, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(CaptureListAdapter.ViewHolder holder, int position) {

        holder.tvCaptureId.setText("CAPTU#"+captureDataItems.get(position).getCaptureDataId());
        setAnimation(holder.itemView, position);
    }

    @Override
    public int getItemCount() {
        return captureDataItems.size();
    }

     class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvCaptureId;
         public ViewHolder(View itemView) {
             super(itemView);
             tvCaptureId = itemView.findViewById(R.id.tvCaptureId);
         }
     }

    private void setAnimation(View viewToAnimate, int position)
    {
        // If the bound view wasn't previously displayed on screen, it's animated
        if (position > lastPosition)
        {
            Animation animation = AnimationUtils.loadAnimation(context, R.anim.slide_left);
            viewToAnimate.startAnimation(animation);
            lastPosition = position;
        }
    }
}
