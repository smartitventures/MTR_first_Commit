package mtr.Activity;

import android.Manifest;
import android.app.Activity;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.Toast;
import android.widget.VideoView;

import com.mtr.R;

import java.util.HashMap;

import mtr.Response.deviceidValidation.ResponseValidateID;
import mtr.Response.validateaccesscode.ResponseCode;
import mtr.Utils.CommonUtil;
import mtr.Utils.RestClient;
import mtr.gcm.GCMClientManager;
import okhttp3.internal.Util;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static java.security.AccessController.getContext;

public class MainActivity extends Activity implements ActivityCompat.OnRequestPermissionsResultCallback {

    VideoView video;
    private RelativeLayout rlLogin;
    private GCMClientManager pushClientManager;
    Dialog dialog;
    private EditText etCode;
    private  Button btnCode;
    String[] PERMISSIONS = {Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION,Manifest.permission.READ_CONTACTS, Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.CAMERA, Manifest.permission.USE_SIP,Manifest.permission.READ_EXTERNAL_STORAGE};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);
        initial();
        if(!hasPermissions(this, PERMISSIONS)){
            ActivityCompat.requestPermissions(this, PERMISSIONS, 1);

        }
        else {
            TelephonyManager tm = (TelephonyManager) getSystemService(Context.TELEPHONY_SERVICE);
            String imei = tm.getDeviceId();
            Log.e("android id-->",imei+"");
            CommonUtil util = new CommonUtil(MainActivity.this);
            util.storeRegistrationId(imei);
        }

        playvideo();

    }
    public static boolean hasPermissions(Context context, String... permissions) {
        if (android.os.Build.VERSION.SDK_INT >= 23 && context != null && permissions != null) {
            for (String permission : permissions) {
                if (ActivityCompat.checkSelfPermission(context, permission) != PackageManager.PERMISSION_GRANTED) {
                    return false;
                }
            }
        }
        return true;
    }
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {

        switch (requestCode) {
            case 1:
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    TelephonyManager tm = (TelephonyManager) getSystemService(Context.TELEPHONY_SERVICE);
                    String imei = tm.getDeviceId();
                    Log.e("android id-->",imei+"");
                    CommonUtil util = new CommonUtil(MainActivity.this);
                    util.storeRegistrationId(imei);

                    // permission was granted, yay! Do the
                    // contacts-related task you need to do.

                }
                else {
                    Toast.makeText(MainActivity.this,"Permission Not Granted",Toast.LENGTH_SHORT).show();
                }
                return;


        }
    }

    private void validateDeviceId() {

        final ProgressDialog progressDialog = ProgressDialog.show(MainActivity.this, "", "loading...");
        CommonUtil util = new CommonUtil(MainActivity.this);
        RestClient.GitApiInterface service= RestClient.getClient();
        HashMap<String, String> data = new HashMap<String, String>();
        data.put("deviceid", util.getRegistrationId());
        service.validateid(data).enqueue(new Callback<ResponseValidateID>() {
            @Override
            public void onResponse(Call<ResponseValidateID> call, Response<ResponseValidateID> response) {
                Log.e("res-->",response.body().getSuccess()+"");

                if(response.body().getSuccess().equalsIgnoreCase("true")){
                    dialog = new Dialog(MainActivity.this, R.style.AllDialog);
                    dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                    WindowManager.LayoutParams layoutParams = dialog.getWindow().getAttributes();
                    layoutParams.dimAmount = 0.6f;
                    dialog.getWindow().addFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
                    dialog.setContentView(R.layout.dialog_accesscode);
                     etCode=(EditText)dialog.findViewById(R.id.et_code);
                    btnCode=(Button) dialog.findViewById(R.id.btn_code);
                    dialog.show();
                    dialog.setCanceledOnTouchOutside(false);


                    btnCode.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {

                            if(etCode.getText().toString().length()==6){
                                sendAccessCode();
                            }
                            else {
                                Toast.makeText(MainActivity.this,"Please Enter a Valid Code",Toast.LENGTH_SHORT).show();
                            }

                        }
                    });
                }

                else {


                    CommonUtil.saveDriverId(MainActivity.this,String.valueOf(response.body().getPayload().getTaxiDriverInfoId()));
                    CommonUtil.saveUsername(MainActivity.this,response.body().getPayload().getDriverName()+" "+response.body().getPayload().getDriverLname());
                    CommonUtil.saveBase(MainActivity.this,response.body().getPayload().getDomainname());
                    CommonUtil.saveDomainId(MainActivity.this,String.valueOf(response.body().getPayload().getDomainid()));
                    startActivity(new Intent(MainActivity.this, LoginActivity.class));
                    overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);

                }

                Log.e("response-->",response.body().getSuccess());
                progressDialog.dismiss();
            }

            @Override
            public void onFailure(Call<ResponseValidateID> call, Throwable t) {
                progressDialog.dismiss();

            }
        });
    }

    private void sendAccessCode() {

        final ProgressDialog progressDialog = ProgressDialog.show(MainActivity.this, "", "loading...");
        CommonUtil util = new CommonUtil(MainActivity.this);
        RestClient.GitApiInterface service= RestClient.getClient();
        HashMap<String, String> data = new HashMap<String, String>();
        data.put("deviceid", util.getRegistrationId());
        data.put("code",etCode.getText().toString().trim());
        service.sendaccesscode(data).enqueue(new Callback<ResponseCode>() {
            @Override
            public void onResponse(Call<ResponseCode> call, Response<ResponseCode> response) {
                dialog.dismiss();

                 if(response.body().getSuccess().equalsIgnoreCase("true")){
                     startActivity(new Intent(MainActivity.this,LoginActivity.class));
                     CommonUtil.saveUsername(MainActivity.this,response.body().getPayload().getDriverName()+" "+response.body().getPayload().getDriverLname());
                     CommonUtil.saveBase(MainActivity.this,response.body().getPayload().getDomainname());
                     CommonUtil.saveDomainId(MainActivity.this,response.body().getPayload().getDomainid());
                     CommonUtil.saveDriverId(MainActivity.this,response.body().getPayload().getDomainid());

                 }

                progressDialog.dismiss();
            }

            @Override
            public void onFailure(Call<ResponseCode> call, Throwable t) {

                progressDialog.dismiss();
            }
        });
    }

    private void initial() {
        final MediaPlayer mp = MediaPlayer.create(this, R.raw.buttonclick);
        rlLogin=(RelativeLayout) findViewById(R.id.rl_login);
        rlLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                mp.start();
                if(CommonUtil.getUserName(MainActivity.this).equalsIgnoreCase("")){
                    validateDeviceId();
                }
                else {
                    startActivity(new Intent(MainActivity.this, LoginActivity.class));
                    overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
                }
            }
        });

    }

    @Override
    protected void onResume() {
        super.onResume();
        playvideo();
    }

    private void playvideo() {

        video=(VideoView) findViewById(R.id.video);
        Uri uri = Uri.parse("android.resource://"+getPackageName()+"/"+R.raw.mtr_animation);
        video.setVideoURI(uri);
        video.start();
        video.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mp) {
                mp.setLooping(true);

            }
        });

    }
}
