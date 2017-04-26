package mtr.Activity;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.app.PendingIntent;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.sip.SipAudioCall;
import android.net.sip.SipException;
import android.net.sip.SipManager;
import android.net.sip.SipProfile;
import android.net.sip.SipRegistrationListener;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.mtr.R;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import mtr.Fragment.DriverLocation;
import mtr.Fragment.JobRequest;
import mtr.Fragment.ReservedJob;
import mtr.sip.IncomingCallReceiver;

/**
 * Created by saurabh on 6/4/17.
 */

public class DriverActivity extends BaseActivity{
    public static int currentPage = 0;
    LinearLayout llJobs;
    LinearLayout llLocation;
    LinearLayout llReserved;
    private ImageView ivJob,ivLocation,ivReserved;
    private TextView tvJob,tvLocation,tvReserved;
    boolean doubleBackToExitPressedOnce = false;
    public SipManager manager = null;
    public SipProfile me = null;
    private static final int UPDATE_SETTINGS_DIALOG = 3;
    public IncomingCallReceiver callReceiver;
    public SipAudioCall call;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_driver);
        IntentFilter filter = new IntentFilter();
        filter.addAction("android.SipDemo.INCOMING_CALL");
        callReceiver = new IncomingCallReceiver();
        this.registerReceiver(callReceiver, filter);

        llJobs=(LinearLayout) findViewById(R.id.ll_jobs);
        llLocation=(LinearLayout)findViewById(R.id.ll_location);
        llReserved=(LinearLayout) findViewById(R.id.ll_reserved);
        ivJob=(ImageView) findViewById(R.id.iv_job);
        ivLocation=(ImageView) findViewById(R.id.iv_location);
        ivReserved=(ImageView) findViewById(R.id.iv_reserved);
        tvJob=(TextView) findViewById(R.id.tv_job);
        tvLocation=(TextView) findViewById(R.id.tv_location);
        tvReserved=(TextView) findViewById(R.id.tv_reserved);
        initViewPagerAndTabs();
        initializeManager();

    }
    private void initializeManager() {

        if(manager == null) {
            manager = SipManager.newInstance(this);
        }

        initializeLocalProfile();
    }

    private void initializeLocalProfile() {
        if (manager == null) {
            return;
        }

        if (me != null) {
            closeLocalProfile();
        }

        try {
            SipProfile.Builder builder = new SipProfile.Builder("010030", "sip.mytaxiride.com");
            builder.setPassword("pbx010030");
            me = builder.build();

            Intent i = new Intent();
            i.setAction("android.SipDemo.INCOMING_CALL");
            PendingIntent pi = PendingIntent.getBroadcast(this, 0, i, Intent.FILL_IN_DATA);
            manager.open(me, pi, null);


            // This listener must be added AFTER manager.open is called,
            // Otherwise the methods aren't guaranteed to fire.

            manager.setRegistrationListener(me.getUriString(), new SipRegistrationListener() {
                public void onRegistering(String localProfileUri) {
                    updateStatus("Registering with SIP Server...");
                }

                public void onRegistrationDone(String localProfileUri, long expiryTime) {
                    updateStatus("Ready");
                }

                public void onRegistrationFailed(String localProfileUri, int errorCode,
                                                 String errorMessage) {
                    updateStatus("Registration failed.  Please check settings.");
                }
            });
        } catch (ParseException pe) {
            updateStatus("Connection Error.");
        } catch (SipException se) {
            updateStatus("Connection error.");
        }

    }

    public void closeLocalProfile() {
        if (manager == null) {
            return;
        }
        try {
            if (me != null) {
                manager.close(me.getUriString());
            }
        } catch (Exception ee) {
            Log.d("Exception", "Failed to close local profile.", ee);
        }
    }

    @Override
    protected void onStop()
    {
        unregisterReceiver(callReceiver);
        super.onStop();
    }
    public void updateStatus(final String status) {
        // Be a good citizen.  Make sure UI changes fire on the UI thread.
        this.runOnUiThread(new Runnable() {
            public void run() {
               Toast.makeText(DriverActivity.this,status,Toast.LENGTH_SHORT).show();
            }
        });
    }
    public void updateStatus(SipAudioCall call) {
        String useName = call.getPeerProfile().getDisplayName();
        if(useName == null) {
            useName = call.getPeerProfile().getUserName();
        }
        updateStatus(useName + "@" + call.getPeerProfile().getSipDomain());
    }
    @Override
    public void onBackPressed() {
        if (doubleBackToExitPressedOnce) {

            Intent intent = new Intent(Intent.ACTION_MAIN);
            intent.addCategory(Intent.CATEGORY_HOME);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);//***Change Here***
            startActivity(intent);
            finish();
            System.exit(0);
            return;
        }

        this.doubleBackToExitPressedOnce = true;
        Toast.makeText(this, "Please press again to exit.", Toast.LENGTH_SHORT).show();

        new Handler().postDelayed(new Runnable() {

            @Override
            public void run() {
                doubleBackToExitPressedOnce=false;
            }
        }, 2000);

    }

    private void initViewPagerAndTabs() {

        final ViewPager viewPager = (ViewPager) findViewById(R.id.viewPager);
        viewPager.setOnPageChangeListener(new PageListener());
        PagerAdapter pagerAdapter = new PagerAdapter(getSupportFragmentManager());

        pagerAdapter.addFragment(JobRequest.createInstance(0), getString(R.string.tab_1));
        pagerAdapter.addFragment(DriverLocation.createInstance(0), getString(R.string.tab_2));
        pagerAdapter.addFragment(ReservedJob.createInstance(0), getString(R.string.tab_3));

        viewPager.setAdapter(pagerAdapter);

        llJobs.setOnClickListener(new View.OnClickListener() {
            @TargetApi(Build.VERSION_CODES.M)
            @Override
            public void onClick(View v) {
                ivJob.setBackgroundResource(R.drawable.jobrequestblue);
                ivLocation.setBackgroundResource(R.drawable.driverlocationwhite);
                ivReserved.setBackgroundResource(R.drawable.reservewhite);
                tvJob.setTextColor(getColor(R.color.colorblue));
                tvLocation.setTextColor(getColor(R.color.colorwhite));
                tvReserved.setTextColor(getColor(R.color.colorwhite));
                viewPager.setCurrentItem(0);

            }
        });
        llLocation.setOnClickListener(new View.OnClickListener() {
            @TargetApi(Build.VERSION_CODES.M)
            @Override
            public void onClick(View v) {
                ivJob.setBackgroundResource(R.drawable.jobrequestwhite);
                ivLocation.setBackgroundResource(R.drawable.locationblue);
                ivReserved.setBackgroundResource(R.drawable.reservewhite);
                tvJob.setTextColor(getColor(R.color.colorwhite));
                tvLocation.setTextColor(getColor(R.color.colorblue));
                tvReserved.setTextColor(getColor(R.color.colorwhite));
                viewPager.setCurrentItem(1);
            }
        });
        llReserved.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ivJob.setBackgroundResource(R.drawable.jobrequestwhite);
                ivLocation.setBackgroundResource(R.drawable.driverlocationwhite);
                ivReserved.setBackgroundResource(R.drawable.reserveblue);
                tvJob.setTextColor(getColor(R.color.colorwhite));
                tvLocation.setTextColor(getColor(R.color.colorwhite));
                tvReserved.setTextColor(getColor(R.color.colorblue));
                viewPager.setCurrentItem(2);

            }
        });


    }
    static class PagerAdapter extends FragmentPagerAdapter {

        private final List<Fragment> fragmentList = new ArrayList<>();
        private final List<String> fragmentTitleList = new ArrayList<>();

        public PagerAdapter(android.support.v4.app.FragmentManager fragmentManager) {
            super(fragmentManager);
        }

        public void addFragment(Fragment fragment, String title) {
            fragmentList.add(fragment);
            fragmentTitleList.add(title);
        }

        @Override
        public Fragment getItem(int position) {
            return fragmentList.get(position);
        }

        @Override
        public int getCount() {
            return fragmentList.size();
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return fragmentTitleList.get(position);
        }
    }
    private class PageListener extends ViewPager.SimpleOnPageChangeListener {

        public void onPageSelected(int position) {
            Log.i("page selected ", "," + position);
            currentPage = position;
            if (position == 0) {


            }

        }
    }
}
