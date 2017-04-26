package mtr.Viewholders;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.mtr.R;

/**
 * Created by saurabh on 15/4/17.
 */

public class RegularJobsViewholder extends RecyclerView.ViewHolder {
    public TextView mtvPickup,mtvPickupBetween,mtvDropoff;
    public ImageView mchildFlag,msmokeFlag,mpetFlag,mcatFlag,mdialysisFlag,mnumpeopleFlag;
    Activity ctx;
    public Button mbtnReject,mbtnAccept;

    public RegularJobsViewholder(final View parent, TextView tvPickup,TextView tvPickupBetween,TextView tvDropoff, ImageView childFlag, ImageView smokeFlag,ImageView petFlag,
                                 ImageView catFlag,ImageView dialysisFlag,ImageView numpeopleFlag,Button btnReject, Button btnAccept) {
        super(parent);
        mtvPickup = tvPickup;
        mtvPickupBetween=tvPickupBetween;
        mtvDropoff=tvDropoff;
        mchildFlag= childFlag;
        msmokeFlag= smokeFlag;
        mpetFlag= petFlag;
        mcatFlag= catFlag;
        mdialysisFlag= dialysisFlag;
        mnumpeopleFlag= numpeopleFlag;
        mbtnReject=btnReject;
        mbtnAccept=btnAccept;
    }

    public static RegularJobsViewholder newInstance(View parent) {
        TextView tvPickup = (TextView) parent.findViewById(R.id.tv_pickup);
        TextView tvPickupBetween=(TextView) parent.findViewById(R.id.tv_pickup_between);
        TextView tvDropoff= (TextView) parent.findViewById(R.id.tv_dropoff);
        ImageView childFlag=(ImageView) parent.findViewById(R.id.child_flag);
        ImageView smokeFlag=(ImageView) parent.findViewById(R.id.smoke_flag);
        ImageView petFlag=(ImageView) parent.findViewById(R.id.pet_flag);
        ImageView catFlag=(ImageView) parent.findViewById(R.id.cat_flag);
        ImageView dialysisFlag=(ImageView) parent.findViewById(R.id.dialysis_flag);
        ImageView numpeopleFlag=(ImageView) parent.findViewById(R.id.numpeople_flag);
        Button btnReject=(Button)parent.findViewById(R.id.btn_reject);
        Button btnAccept=(Button) parent.findViewById(R.id.btn_accept);
        return new RegularJobsViewholder(parent, tvPickup,tvPickupBetween,tvDropoff,childFlag,smokeFlag,petFlag,catFlag,dialysisFlag,numpeopleFlag,btnReject,btnAccept);
    }



}
