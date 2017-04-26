package mtr.Fragment;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.mtr.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import mtr.Adapters.RegularJobsAdapter;
import mtr.Adapters.ReservedJobsAdapter;
import mtr.Response.regularjobs.Payload;
import mtr.Response.reservedjobs.ResponseReservedJobs;
import mtr.Utils.CommonUtil;
import mtr.Utils.RestClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by saurabh on 18/4/17.
 */

public class ReservedJob extends Fragment {

    private RecyclerView rvReservedJobs;
    List<mtr.Response.reservedjobs.Payload> reservedList;
    private ArrayList<mtr.Response.reservedjobs.Payload> mPayloads;
    public final static String ITEMS_COUNT_KEY = "PartThreeFragment$ItemsCount";
    private ReservedJobsAdapter mReservedJobsAdapter;
    public static ReservedJob  createInstance(int itemsCount) {
        ReservedJob partThreeFragment = new ReservedJob();
        Bundle bundle = new Bundle();
        bundle.putInt(ITEMS_COUNT_KEY, itemsCount);
        partThreeFragment.setArguments(bundle);
        return partThreeFragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        ViewGroup viewGroup = (ViewGroup) inflater.inflate(
                R.layout.fragment_reservedjobs, container, false);
//        RelativeLayout rv = (RelativeLayout) viewGroup.findViewById(R.id.rv);
        rvReservedJobs=(RecyclerView)viewGroup.findViewById(R.id.rv_reserved_jobs);
        return viewGroup;
    }

    @Override
    public void onResume() {
        super.onResume();
        getreservedjobs();
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        rvReservedJobs.setLayoutManager(layoutManager);
    }

    private void getreservedjobs() {

        final ProgressDialog progressDialog = ProgressDialog.show(getActivity(), "", "loading...");
        CommonUtil util = new CommonUtil(getActivity());
        RestClient.GitApiInterface service = RestClient.getClient();
        HashMap<String, String> data = new HashMap<String, String>();
        data.put("taxi_driver_info_id","113");
        data.put("domainid","25");
        service.getReservedJobs(data).enqueue(new Callback<ResponseReservedJobs>() {
            @Override
            public void onResponse(Call<ResponseReservedJobs> call, Response<ResponseReservedJobs> response) {

                progressDialog.dismiss();

                if(response.body().getSuccess().equalsIgnoreCase("true")){

                    reservedList = response.body().getPayload();
                    mPayloads = new ArrayList<mtr.Response.reservedjobs.Payload>();
                    for (mtr.Response.reservedjobs.Payload obj : reservedList) {
                        mPayloads.add(obj);
                        mReservedJobsAdapter= new ReservedJobsAdapter(mPayloads, getActivity());
                        rvReservedJobs.setAdapter(mReservedJobsAdapter);
                        mReservedJobsAdapter.notifyDataSetChanged();
                    }


                }
            }

            @Override
            public void onFailure(Call<ResponseReservedJobs> call, Throwable t) {
                progressDialog.dismiss();

            }
        });


    }
}
