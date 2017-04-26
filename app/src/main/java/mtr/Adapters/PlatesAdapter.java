package mtr.Adapters;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.mtr.R;

import java.util.ArrayList;
import java.util.List;

import mtr.Activity.DriverActivity;
import mtr.Activity.PlateActivity;
import mtr.Response.Plates.Payload;
import mtr.Viewholders.PlatesViewholder;

/**
 * Created by saurabh on 6/4/17.
 */

public class PlatesAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>  {
    private List<Payload> mItemList;
    Activity ctx;
    public PlatesAdapter(ArrayList<Payload> payloadArrayList, PlateActivity plateActivity) {

        mItemList = payloadArrayList;
        this.ctx = plateActivity;

    }
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        View view = LayoutInflater.from(context).inflate(R.layout.row_plate, parent, false);
        return PlatesViewholder.newInstance(view);
    }


    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int position) {
        final PlatesViewholder holder = (PlatesViewholder) viewHolder;

        Log.e("no--->",mItemList.get(position).getNoplate());

       holder.getMtvPlate_no().setText(mItemList.get(position).getNoplate());
        holder.getMtvMake().setText(mItemList.get(position).getMak());
        holder.getMtvModel().setText(mItemList.get(position).getModel());
//        holder.getMtvYear().setText(mItemList.get(position).getVyear());
//        holder.mtvYear.setText(mItemList.get(position).getVyear());
        final MediaPlayer mp = MediaPlayer.create(ctx, R.raw.buttonclick);
        holder.getMtvYear().setText(String.valueOf(mItemList.get(position).getVyear()));
        holder.mllPlate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mp.start();
                ctx.startActivity(new Intent(ctx,DriverActivity.class));
            }
        });


    }
    @Override
    public int getItemCount() {

        return mItemList == null ? 0 : mItemList.size();
    }

    public List<Payload> getmItemList() {
        return mItemList;
    }

    @Override
    public long getItemId(int position) {
        return super.getItemId(position);
    }
}
