package mtr.Activity;

import android.app.Activity;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;

import com.mtr.R;

import mtr.Utils.CommonUtil;

/**
 * Created by saurabh on 5/4/17.
 */

public class LoginActivity extends Activity {

    private Button btnLogin;
    private TextView tvUsername;
    private TextView tvBase;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_login);
        tvUsername=(TextView) findViewById(R.id.tv_username);
        tvBase=(TextView) findViewById(R.id.tv_base);
        btnLogin=(Button) findViewById(R.id.btn_login);
        tvUsername.setText(CommonUtil.getUserName(LoginActivity.this));
        tvBase.setText(CommonUtil.getBase(LoginActivity.this));
        final MediaPlayer mp = MediaPlayer.create(this, R.raw.buttonclick);
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mp.start();
                startActivity(new Intent(LoginActivity.this,PlateActivity.class));
                overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);

            }
        });


    }


    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
//        dialog.dismiss();
    }
    //
//    @Override
//    protected void onResume() {
//        super.onResume();
//        validateDeviceId();
//    }
}
