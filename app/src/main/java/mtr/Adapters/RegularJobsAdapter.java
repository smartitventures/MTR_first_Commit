package mtr.Adapters;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
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
import mtr.Viewholders.RegularJobsViewholder;

/**
 * Created by saurabh on 15/4/17.
 */

public class RegularJobsAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private List<mtr.Response.regularjobs.Payload> mItemList;
    Activity ctx;
    public RegularJobsAdapter(ArrayList<mtr.Response.regularjobs.Payload> payloadArrayList, Activity context) {

        mItemList = payloadArrayList;
        this.ctx = context;

    }
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        View view = LayoutInflater.from(context).inflate(R.layout.row_regularjobs, parent, false);
        return RegularJobsViewholder.newInstance(view);
    }


    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int position) {
        final RegularJobsViewholder holder = (RegularJobsViewholder) viewHolder;


        if(mItemList.get(position).getAllowCats().equals(0)){
            holder.mcatFlag.setVisibility(View.GONE);
        }
        else{
            holder.mcatFlag.setVisibility(View.VISIBLE);
        }
         if(mItemList.get(position).getAllowChilds().equals(0)){
            holder.mchildFlag.setVisibility(View.GONE);
        }
        else {
             holder.mchildFlag.setVisibility(View.VISIBLE);
         }
         if(mItemList.get(position).getAllowPets().equals(0)){
            holder.mpetFlag.setVisibility(View.GONE);
        }
        else {
             holder.mpetFlag.setVisibility(View.VISIBLE);
         }
        if(mItemList.get(position).getDialysis().equals(0)){
            holder.mdialysisFlag.setVisibility(View.GONE);
        }
        else {
            holder.mdialysisFlag.setVisibility(View.VISIBLE);
        }
        if(mItemList.get(position).getSmoke().equals(0)){
            holder.msmokeFlag.setVisibility(View.GONE);
        }
        else {
            holder.msmokeFlag.setVisibility(View.VISIBLE);
        }

         if(mItemList.get(position).getNoofpassanger().equalsIgnoreCase("1")){
             holder.mnumpeopleFlag.setVisibility(View.VISIBLE);
           holder.mnumpeopleFlag.setBackgroundResource(R.drawable.oneperson);
       }
        else if(mItemList.get(position).getNoofpassanger().equalsIgnoreCase("2")){
             holder.mnumpeopleFlag.setVisibility(View.VISIBLE);
           holder.mnumpeopleFlag.setBackgroundResource(R.drawable.twoperson);
       }
       else if(mItemList.get(position).getNoofpassanger().equalsIgnoreCase("3")){
             holder.mnumpeopleFlag.setVisibility(View.VISIBLE);
           holder.mnumpeopleFlag.setBackgroundResource(R.drawable.threepeoples);
       }

       else if(mItemList.get(position).getNoofpassanger().equalsIgnoreCase("4")){
             holder.mnumpeopleFlag.setVisibility(View.VISIBLE);
           holder.mnumpeopleFlag.setBackgroundResource(R.drawable.fourpeoples);
       }

       else if(mItemList.get(position).getNoofpassanger().equalsIgnoreCase("5")){
             holder.mnumpeopleFlag.setVisibility(View.VISIBLE);
           holder.mnumpeopleFlag.setBackgroundResource(R.drawable.fivepeoples);
       }

       else if(mItemList.get(position).getNoofpassanger().equalsIgnoreCase("6")){
             holder.mnumpeopleFlag.setVisibility(View.VISIBLE);
           holder.mnumpeopleFlag.setBackgroundResource(R.drawable.sixpeoples);
       }
        else {
           holder.mnumpeopleFlag.setVisibility(View.GONE);
       }

        holder.mtvPickup.setText(mItemList.get(position).getPickupaddress()+"("+mItemList.get(position).getTime()+")");
        holder.mtvDropoff.setText(mItemList.get(position).getDropAddress());
        holder.mtvPickupBetween.setText(mItemList.get(position).getPickupbetween());






    }
    @Override
    public int getItemCount() {

        return mItemList == null ? 0 : mItemList.size();
    }

    public List<mtr.Response.regularjobs.Payload> getmItemList() {
        return mItemList;
    }

    @Override
    public long getItemId(int position) {
        return super.getItemId(position);
    }
}
