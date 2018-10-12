package com.example.dev97.tuantran.prankcall.UI.FakeActivity;

import android.content.Intent;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.dev97.tuantran.prankcall.Adapter.AdapterDetailCall;
import com.example.dev97.tuantran.prankcall.Models.ModelDetailCall;
import com.example.dev97.tuantran.prankcall.R;

import java.util.ArrayList;
import java.util.List;

public class DetailFakeRingingActivity extends AppCompatActivity {

    protected TextView tvCountTime;
    protected ImageButton btnReject;
    protected TextView tvNumber;
    protected TextView tvName;

    protected RecyclerView mRecyclerView;
    private AdapterDetailCall mAdapter;

    private List<ModelDetailCall> listData = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_fake_ringing);
        initView();
        initListData();
        initAdapter();
    }

    private void initView(){
        tvCountTime = findViewById(R.id.tv_count_time);
        mRecyclerView = findViewById(R.id.rv_detail_call);
        btnReject = findViewById(R.id.button_reject);
        tvNumber = findViewById(R.id.tv_number_called);
        tvName = findViewById(R.id.tv_name_called);
    }

    private void initListData(){
        listData.add(new ModelDetailCall("Thêm cuộc gọi",R.drawable.ic_baseline_add));
        listData.add(new ModelDetailCall("Tăng âm lượng",R.drawable.ic_call_volumn_up));
        listData.add(new ModelDetailCall("Bluetooth",R.drawable.ic_baseline_bluetooth));
        listData.add(new ModelDetailCall("Loa",R.drawable.ic_outline_volume_up));
        listData.add(new ModelDetailCall("Tắt âm",R.drawable.ic_outline_mic_off));
        listData.add(new ModelDetailCall("Bàn phím",R.drawable.ic_outline_dialpad));
    }

    private void initAdapter(){
        mAdapter = new AdapterDetailCall(listData);
        // set a GridLayoutManager with default vertical orientation and 2 number of columns
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getApplicationContext(),3);
        mRecyclerView.setLayoutManager(gridLayoutManager); // set LayoutManager to RecyclerView
        mRecyclerView.setAdapter(mAdapter);


        btnReject.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentReject = new Intent(Intent.ACTION_MAIN);
                intentReject.addCategory(Intent.CATEGORY_HOME);
                intentReject.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                finish();
                startActivity(intentReject);

            }
        });
        tvName.setText(getName());
        tvNumber.setText(getNumberPhone());
        Timetext();
    }
    // func get number phone by intent
    private String getNumberPhone(){
        String number = null;
        Bundle bundle = getIntent().getExtras();
        if(bundle != null){
            number = bundle.getString("phoneNumber");
        }
        return  number;
    }
    private String getName(){
        String number = null;
        Bundle bundle = getIntent().getExtras();
        if(bundle != null){
            number = bundle.getString("nameCalled");
        }
        return  number;
    }

    public void Timetext(){
        //time
        final int[] a = {0};

        new CountDownTimer(5999000, 1000){

            @Override
            public void onTick(long l) {
                a[0] = a[0] +1;
                int m = a[0]/60;
                int s = a[0] %60;
                if(m<10 && s<10){
                    tvCountTime.setText("0"+String.valueOf(m)+":"+"0"+String.valueOf(s));
                }else if(m<10&& s>=10){
                    tvCountTime.setText("0"+String.valueOf(m)+":"+String.valueOf(s));
                }else if(m>=10 && s<10 ){
                    tvCountTime.setText(String.valueOf(m)+":"+"0"+String.valueOf(s));
                }else {
                    tvCountTime.setText(String.valueOf(m)+":"+String.valueOf(s));
                }

            }

            @Override
            public void onFinish() {
                android.os.Process.killProcess(android.os.Process.myPid());
                System.exit(1);
            }
        }.start();
    }
}
