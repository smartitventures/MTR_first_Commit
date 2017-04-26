package mtr.Fragment;

import android.Manifest;
import android.app.Fragment;
import android.app.ProgressDialog;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.mtr.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import mtr.Activity.PlateActivity;
import mtr.Adapters.RegularJobsAdapter;
import mtr.Response.regularjobs.Payload;
import mtr.Response.regularjobs.ResponseRegularJobs;
import mtr.Utils.CommonUtil;
import mtr.Utils.RestClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by saurabh on 15/4/17.
 */

public class JobRequest extends android.support.v4.app.Fragment implements GoogleApiClient.ConnectionCallbacks,
        GoogleApiClient.OnConnectionFailedListener, LocationListener {
    public final static String ITEMS_COUNT_KEY = "PartOneFragment$ItemsCount";
    private RecyclerView rvRegularJobs;
    List<Payload> jobList;
    private ArrayList<Payload> mPayloads;
    Location mLastLocation;
    private GoogleApiClient mGoogleApiClient;
    private LocationRequest mLocationRequest;
    double lat, lon;
    RegularJobsAdapter regularJobsAdapter;

    public static JobRequest createInstance(int itemsCount) {
        JobRequest partOneFragment = new JobRequest();
        Bundle bundle = new Bundle();
        bundle.putInt(ITEMS_COUNT_KEY, itemsCount);
        partOneFragment.setArguments(bundle);
        return partOneFragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        ViewGroup viewGroup = (ViewGroup) inflater.inflate(
                R.layout.fragment_regularjobs, container, false);
        rvRegularJobs = (RecyclerView) viewGroup.findViewById(R.id.rv_regular_jobs);

        return viewGroup;

    }

    @Override
    public void onResume() {
        super.onResume();
        buildGoogleApiClient();
        mGoogleApiClient.connect();
        getRegularJobs();

        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        rvRegularJobs.setLayoutManager(layoutManager);


    }

    synchronized void buildGoogleApiClient() {

        mGoogleApiClient = new GoogleApiClient.Builder(getActivity())
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .addApi(LocationServices.API)
                .build();

    }

    private void getRegularJobs() {

        final ProgressDialog progressDialog = ProgressDialog.show(getActivity(), "", "loading...");
        CommonUtil util = new CommonUtil(getActivity());
        RestClient.GitApiInterface service = RestClient.getClient();
        HashMap<String, String> data = new HashMap<String, String>();
        //"pick_up_longitude": "-74.1744624", "pick_up_latitude": "40.6895314"
        data.put("pick_up_longitude", "-74.1744624");
        data.put("pick_up_latitude","40.6895314");
        data.put("domainid", "25");
        data.put("distanceLimit", "500");
        data.put("deviceid", util.getRegistrationId());
        service.getjobs(data).enqueue(new Callback<ResponseRegularJobs>() {
            @Override
            public void onResponse(Call<ResponseRegularJobs> call, Response<ResponseRegularJobs> response) {

                Log.e("responseJobs-->", response.body().getSuccess());

                jobList = response.body().getPayload();
                mPayloads = new ArrayList<Payload>();
                for (Payload obj : jobList) {
                    mPayloads.add(obj);
                     regularJobsAdapter= new RegularJobsAdapter(mPayloads, getActivity());
                    rvRegularJobs.setAdapter(regularJobsAdapter);
                    regularJobsAdapter.notifyDataSetChanged();
                }
                progressDialog.dismiss();
            }
            @Override
            public void onFailure(Call<ResponseRegularJobs> call, Throwable t) {
                progressDialog.dismiss();
            }
        });


    }


    @Override
    public void onConnected(@Nullable Bundle bundle) {
        mLocationRequest = LocationRequest.create();
        mLocationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
        mLocationRequest.setInterval(100); // Update location every second

        try {
            if (ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                // TODO: Consider calling
                //    ActivityCompat#requestPermissions
                // here to request the missing permissions, and then overriding
                //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                //                                          int[] grantResults)
                // to handle the case where the user grants the permission. See the documentation
                // for ActivityCompat#requestPermissions for more details.
                return;
            }
            LocationServices.FusedLocationApi.requestLocationUpdates(mGoogleApiClient, mLocationRequest, this);
        } catch (Exception e) {
            e.printStackTrace();
        }


        mLastLocation = LocationServices.FusedLocationApi.getLastLocation(
                mGoogleApiClient);
        if (mLastLocation != null) {
            lat = mLastLocation.getLatitude();
            lon = mLastLocation.getLongitude();

        }
    }

    @Override
    public void onConnectionSuspended(int i) {

    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }

    @Override
    public void onLocationChanged(Location location) {
        lat = location.getLatitude();
        lon = location.getLongitude();
        Log.e("lat-->",lat+"");
        Log.e("long-->",lon+"");
    }
}
