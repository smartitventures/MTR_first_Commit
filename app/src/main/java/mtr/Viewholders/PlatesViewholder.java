package mtr.Viewholders;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.mtr.R;

/**
 * Created by saurabh on 6/4/17.
 */

public class PlatesViewholder extends RecyclerView.ViewHolder  {
     public TextView mtvPlate_no,mtvMake,mtvYear,mtvModel;
    Activity ctx;
    public LinearLayout mllPlate;

    public PlatesViewholder(final View parent, TextView tvPlate_no, TextView tvMake, TextView tvYear, TextView tvModel, LinearLayout llPlate) {
        super(parent);
        mtvPlate_no = tvPlate_no;
        mtvMake=tvMake;
        mtvYear=tvYear;
        mtvModel= tvModel;
        this.mllPlate = llPlate;
    }

    public static PlatesViewholder newInstance(View parent) {
        TextView tvPlate_no = (TextView) parent.findViewById(R.id.tv_plate_no);
        TextView tvMake=(TextView) parent.findViewById(R.id.tv_make);
        TextView tvYear= (TextView) parent.findViewById(R.id.tv_year);
        TextView tvModel=(TextView)parent.findViewById(R.id.tv_model);
        LinearLayout llPlate = (LinearLayout) parent.findViewById(R.id.ll_plate);
        return new PlatesViewholder(parent, tvPlate_no,tvMake,tvYear,tvModel,llPlate);
    }


    public TextView getMtvPlate_no() {
        return mtvPlate_no;
    }

    public void setMtvPlate_no(TextView mtvPlate_no) {
        this.mtvPlate_no = mtvPlate_no;
    }

    public TextView getMtvMake() {
        return mtvMake;
    }

    public void setMtvMake(TextView mtvMake) {
        this.mtvMake = mtvMake;
    }

    public TextView getMtvYear() {
        return mtvYear;
    }

    public void setMtvYear(TextView mtvYear) {
        this.mtvYear = mtvYear;
    }

    public TextView getMtvModel() {
        return mtvModel;
    }

    public void setMtvModel(TextView mtvModel) {
        this.mtvModel = mtvModel;
    }

    public LinearLayout getMllPlate() {
        return mllPlate;
    }

    public void setMllPlate(LinearLayout mllPlate) {
        this.mllPlate = mllPlate;
    }
}
