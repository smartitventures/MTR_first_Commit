package mtr.Activity;

import android.app.Activity;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.mtr.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import mtr.Adapters.PlatesAdapter;
import mtr.Response.Plates.Payload;
import mtr.Response.Plates.ResponsePlates;
import mtr.Response.Response;
import mtr.Response.deviceidValidation.ResponseValidateID;
import mtr.Utils.CommonUtil;
import mtr.Utils.ConnectionDetector;
import mtr.Utils.RestClient;
import okhttp3.internal.Util;
import retrofit2.Call;
import retrofit2.Callback;

/**
 * Created by saurabh on 6/4/17.
 */




public class PlateActivity extends Activity {



    private RecyclerView rvPlates;
    List<Payload> payloadList;
    private ArrayList<Payload> payloadArrayList;
    PlatesAdapter platesAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_plate);
          rvPlates=(RecyclerView) findViewById(R.id.rv_plates);

//        ConnectionDetector connectionDetector= new ConnectionDetector(PlateActivity.this);
//        if(connectionDetector.isConnectingToInternet()){
            getPlates();
//        }
//        else {
//            Dialog dialog= new Dialog(PlateActivity.this);
//            dialog.setTitle("No Internet");
//            dialog.show();
//        }
    }

    private void getPlates() {
        final ProgressDialog progressDialog = ProgressDialog.show(PlateActivity.this, "", "loading...");
        CommonUtil util = new CommonUtil(PlateActivity.this);
        RestClient.GitApiInterface service= RestClient.getClient();
        HashMap<String, String> data = new HashMap<String, String>();
        data.put("deviceid", util.getRegistrationId());
        data.put("taxi_driver_info_id",CommonUtil.getDriverId(PlateActivity.this));
        data.put("domainid",CommonUtil.getDomainId(PlateActivity.this));
        service.getPlates(data).enqueue(new Callback<ResponsePlates>() {
            @Override
            public void onResponse(Call<ResponsePlates> call, retrofit2.Response<ResponsePlates> response) {

                if (response.body().getSuccess().equalsIgnoreCase("true")) {

                    payloadList = response.body().getPayload();

                    Log.e("res-->",payloadList+"");
                    payloadArrayList = new ArrayList<Payload>();
                    for (Payload obj : payloadList) {
                        //Log.e("Email->", obj.getFirstName());
                        payloadArrayList.add(obj);
                        platesAdapter = new PlatesAdapter(payloadArrayList,PlateActivity.this);
                        LinearLayoutManager llm = new LinearLayoutManager(PlateActivity.this);
                        llm.setOrientation(LinearLayoutManager.VERTICAL);
                        rvPlates.setLayoutManager(llm);
                        rvPlates.setAdapter(platesAdapter);
                        platesAdapter.notifyDataSetChanged();
                    }


                }
                progressDialog.dismiss();
            }
            @Override
            public void onFailure(Call<ResponsePlates> call, Throwable t) {
                Log.e("fail-->",t.getMessage());
                progressDialog.dismiss();
            }
        });


    }

}
