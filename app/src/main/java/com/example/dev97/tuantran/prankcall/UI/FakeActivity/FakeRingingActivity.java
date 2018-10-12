package com.example.dev97.tuantran.prankcall.UI.FakeActivity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.media.RingtoneManager;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.dev97.tuantran.prankcall.R;

public class FakeRingingActivity extends AppCompatActivity {

    protected TextView tvName;
    protected TextView tvNumberPhone;
    protected ImageButton btnAnswer;
    protected ImageButton btnReject;
    private String callName;
    private String callNum;
    private MediaPlayer mediaPlayer;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fake_ringing);
        initView();

        if(getContactName() == null){
            tvNumberPhone.setVisibility(View.GONE);
            tvName.setText(getContactNumberPhone());
        }else {
            tvName.setText(getContactName());
            tvNumberPhone.setText(getContactNumberPhone());
        }


        Uri notification = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_RINGTONE);
        mediaPlayer = MediaPlayer.create(getApplicationContext(), notification);
        mediaPlayer.start();
        wakeup();

        btnAnswer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mediaPlayer.stop();
                Intent intentDetail = new Intent(getApplicationContext(),DetailFakeRingingActivity.class);
                Bundle bundle = new Bundle();
                bundle.putString("nameCalled",getContactName());
                bundle.putString("phoneNumber",getContactNumberPhone());
                intentDetail.putExtras(bundle);
                startActivity(intentDetail);
            }
        });

        btnReject.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mediaPlayer.stop();
                Intent intentReject = new Intent(Intent.ACTION_MAIN);
                intentReject.addCategory(Intent.CATEGORY_HOME);
                intentReject.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intentReject);
                android.os.Process.killProcess(android.os.Process.myPid());

            }
        });
    }

    private void initView(){
        tvName = findViewById(R.id.tv_name);
        tvNumberPhone = findViewById(R.id.tv_number);
        btnAnswer = findViewById(R.id.btn_resolve);
        btnReject = findViewById(R.id.btn_reject);
    }

    // get Name
    private String getContactName(){
        String contact = null;
        Intent myIntent = getIntent();
        Bundle bundle = myIntent.getExtras();
        if(bundle != null){
            contact = bundle.getString("myfakename");
        }
        return  contact;
    }

    private String getContactNumberPhone(){
        String contact = null;
        Intent myIntent = getIntent();
        Bundle bundle = myIntent.getExtras();
        if(bundle != null){
            contact = bundle.getString("myfakenum");
        }
        return  contact;
    }

    public void wakeup(){
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_SHOW_WHEN_LOCKED);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_TURN_SCREEN_ON);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_DISMISS_KEYGUARD);
    }
}
