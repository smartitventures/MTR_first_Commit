package mtr.Viewholders;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.mtr.R;

/**
 * Created by saurabh on 18/4/17.
 */

public class ReservedJobsViewholder extends RecyclerView.ViewHolder {

    public TextView mtxtTripNumber,mtvCount,mtxtMemberName,mtxtSchedulDate,mtxtReservationpickup,mtxtReservationDropOff,mtxtHeaderDate;
    public ImageView mivPerson;
    public LinearLayout mrelativeListItems;
    Activity ctx;


    public ReservedJobsViewholder(final View parent, TextView txtTripNumber, TextView tvCount, TextView txtMemberName, TextView txtSchedulDate,TextView txtReservationpickup,TextView txtReservationDropOff,TextView txtHeaderDate,ImageView ivPerson,LinearLayout relativeListItems) {
        super(parent);
        mtxtTripNumber=txtTripNumber;
        mtvCount=tvCount;
        mtxtMemberName=txtMemberName;
        mtxtSchedulDate=txtSchedulDate;
        mtxtReservationpickup=txtReservationpickup;
        mtxtReservationDropOff=txtReservationDropOff;
        mtxtHeaderDate=txtHeaderDate;
        mrelativeListItems= relativeListItems;
        mivPerson=ivPerson;
    }

    public static ReservedJobsViewholder newInstance(View parent) {
        TextView txtTripNumber = (TextView) parent.findViewById(R.id.txt_tripNumber);
        TextView tvCount=(TextView) parent.findViewById(R.id.tv_count);
        TextView txtMemberName= (TextView) parent.findViewById(R.id.txtMemberName);
        TextView txtSchedulDate=(TextView)parent.findViewById(R.id.txtSchedulDate);
        TextView txtReservationpickup=(TextView)parent.findViewById(R.id.txtReservationPlaces);
        TextView txtReservationDropOff=(TextView)parent.findViewById(R.id.txtReservationDropOff);
        TextView txtHeaderDate=(TextView)parent.findViewById(R.id.txtHeaderDate);
        ImageView ivPerson=(ImageView) parent.findViewById(R.id.iv_person);
        LinearLayout relativeListItems=(LinearLayout) parent.findViewById(R.id.ll_reserved);
        return new ReservedJobsViewholder(parent, txtTripNumber,tvCount,txtMemberName,txtSchedulDate,txtReservationpickup,txtReservationDropOff,txtHeaderDate,ivPerson,relativeListItems);
    }

}
