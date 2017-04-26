package mtr.Adapters;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.mtr.R;

import java.util.ArrayList;
import java.util.List;

import mtr.Activity.ReservedJobDetails;
import mtr.Fragment.ReservedJob;
import mtr.Response.regularjobs.Payload;
import mtr.Viewholders.RegularJobsViewholder;
import mtr.Viewholders.ReservedJobsViewholder;

/**
 * Created by saurabh on 18/4/17.
 */

public class ReservedJobsAdapter extends RecyclerView.Adapter{
    private List<mtr.Response.reservedjobs.Payload> mItemList;
    Activity ctx;
    public ReservedJobsAdapter(ArrayList<mtr.Response.reservedjobs.Payload> payloadArrayList, Activity context) {

        mItemList = payloadArrayList;
        this.ctx = context;

    }
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        View view = LayoutInflater.from(context).inflate(R.layout.row_reservedjobs, parent, false);
        return ReservedJobsViewholder.newInstance(view);
    }


    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int position) {
        final ReservedJobsViewholder holder = (ReservedJobsViewholder) viewHolder;



        if(mItemList.get(position).getNoofpassanger().equalsIgnoreCase("1")){
            holder.mivPerson.setVisibility(View.VISIBLE);
            holder.mivPerson.setBackgroundResource(R.drawable.oneperson);
        }
        else if(mItemList.get(position).getNoofpassanger().equalsIgnoreCase("2")){
            holder.mivPerson.setVisibility(View.VISIBLE);
            holder.mivPerson.setBackgroundResource(R.drawable.twoperson);
        }
        else if(mItemList.get(position).getNoofpassanger().equalsIgnoreCase("3")){
            holder.mivPerson.setVisibility(View.VISIBLE);
            holder.mivPerson.setBackgroundResource(R.drawable.threepeoples);
        }

        else if(mItemList.get(position).getNoofpassanger().equalsIgnoreCase("4")){
            holder.mivPerson.setVisibility(View.VISIBLE);
            holder.mivPerson.setBackgroundResource(R.drawable.fourpeoples);
        }

        else if(mItemList.get(position).getNoofpassanger().equalsIgnoreCase("5")){
            holder.mivPerson.setVisibility(View.VISIBLE);
            holder.mivPerson.setBackgroundResource(R.drawable.fivepeoples);
        }

        else if(mItemList.get(position).getNoofpassanger().equalsIgnoreCase("6")){
            holder.mivPerson.setVisibility(View.VISIBLE);
            holder.mivPerson.setBackgroundResource(R.drawable.sixpeoples);
        }
        else {
            holder.mivPerson.setVisibility(View.GONE);
        }

        holder.mtxtHeaderDate.setText(mItemList.get(position).getReservationtime());
        holder.mtxtMemberName.setText(mItemList.get(position).getName());
        holder.mtxtReservationpickup.setText(mItemList.get(position).getPickupaddress());
        holder.mtxtReservationDropOff.setText(mItemList.get(position).getDropAddress());
        holder.mtxtSchedulDate.setText(mItemList.get(position).getRsvrtime());
        holder.mtxtTripNumber.setText(String.valueOf(mItemList.get(position).getTripno()));
        holder.mrelativeListItems.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                ctx.startActivity(new Intent(ctx,ReservedJobDetails.class));

            }
        });







    }
    @Override
    public int getItemCount() {

        return mItemList == null ? 0 : mItemList.size();
    }

    public List<mtr.Response.reservedjobs.Payload> getmItemList() {
        return mItemList;
    }

    @Override
    public long getItemId(int position) {
        return super.getItemId(position);
    }
}
