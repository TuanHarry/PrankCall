package com.example.dev97.tuantran.prankcall.UI.FragmentMakeCall;

import android.Manifest;
import android.app.Activity;
import android.app.AlarmManager;
import android.app.Dialog;
import android.app.PendingIntent;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.provider.ContactsContract;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.example.dev97.tuantran.prankcall.Adapter.DetailMakeCall;
import com.example.dev97.tuantran.prankcall.Database.CRUD.AppDatabaseHelper;
import com.example.dev97.tuantran.prankcall.Database.RealmObject.TimeObject;
import com.example.dev97.tuantran.prankcall.FakeCallReceiver;
import com.example.dev97.tuantran.prankcall.Models.Events.EventDefaultContact;
import com.example.dev97.tuantran.prankcall.Models.Events.EventSendTime;
import com.example.dev97.tuantran.prankcall.Models.ModelDetailMakeCall;
import com.example.dev97.tuantran.prankcall.Models.ModelTime;
import com.example.dev97.tuantran.prankcall.R;
import com.example.dev97.tuantran.prankcall.UI.Dialog.DialogPerson;
import com.example.dev97.tuantran.prankcall.UI.Dialog.DialogTime;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

public class ActivityDetailMakeCall extends AppCompatActivity {
    private static final String TAG = "12221";
    private RecyclerView mRecyclerView, mRecyclerViewTime;
    private List<ModelDetailMakeCall> dataList = new ArrayList<>();
    private List<ModelTime> listTime = new ArrayList<>();
    private DetailMakeCall mAdapter;
    private EditText edtName;
    private EditText edtPhoneNumber;
    private ImageButton imageButton;
    private Dialog dialog;
    private Button btnSave;
    private AppDatabaseHelper databaseHelper;
    private Long time;
    private CountDownTimer timer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_make_call);
        initView();
        initAdapter();
        initData();

        // init object

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final TimeObject object = new TimeObject();
                object.setId(System.currentTimeMillis() + "");
                object.setName(edtName.getText().toString());
                object.setPhoneNumber(edtPhoneNumber.getText().toString());
                object.setTime(System.currentTimeMillis());
                if(time == null){
                    time = Long.valueOf(10);
                    object.setTimeisSelect(time*1000);
                }else {
                    object.setTimeisSelect(time*1000);
                }


                if (edtPhoneNumber.getText().length() == 0) {
                    Toast.makeText(ActivityDetailMakeCall.this, "Số điện thoại không được để trống!", Toast.LENGTH_SHORT).show();
                    return;
                } else {
                    databaseHelper.saveCall(object);
                }

                final TimeObject timeObject = databaseHelper.getObject(object.getId());

                timer = new CountDownTimer(timeObject.getTimeisSelect(),1000) {
                    @Override
                    public void onTick(long millisUntilFinished) {

                    }

                    @Override
                    public void onFinish() {
                        setUpAlarm(timeObject.getTimeisSelect(),timeObject.getName(),timeObject.getPhoneNumber());
                    }
                }.start();

                //exit home
                Intent homeIntent = new Intent(Intent.ACTION_MAIN);
                homeIntent.addCategory( Intent.CATEGORY_HOME );
                homeIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(homeIntent);

            }
        });


    }

    private void initView() {
        mRecyclerView = findViewById(R.id.rv_make_call);
        edtName = findViewById(R.id.edt_name);
        edtPhoneNumber = findViewById(R.id.edt_number);
        imageButton = findViewById(R.id.add_user);
        btnSave = findViewById(R.id.btn_save);
    }

    private void initData() {
        databaseHelper = new AppDatabaseHelper(this);
        dataList.add(new ModelDetailMakeCall(R.drawable.ic_baseline_access_alarm, "Select time", (time == null)? "0 minute" :time +" minute"));
    }

    private void initAdapter() {
        mAdapter = new DetailMakeCall(dataList);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mRecyclerView.setAdapter(mAdapter);

        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogPerson dialogPerson = new DialogPerson();
                dialogPerson.show(getSupportFragmentManager(),"");
              //  Log.d(TAG, "onClick: "+getSupportFragmentManager());
            }
        });

        mAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                switch (position) {
                    case 0:
                        hideKeyboard(ActivityDetailMakeCall.this);
                        DialogTime dialogSetTime = new DialogTime();
                        dialogSetTime.show(getFragmentManager(), "1");
                        break;
                    case 1:
                        Toast.makeText(ActivityDetailMakeCall.this, "" + position, Toast.LENGTH_SHORT).show();
                        break;
                }
            }
        });

    }

    private void setUpAlarm(long time , String name, String phoneNumber){
        AlarmManager alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
        Intent intent =new Intent(this, FakeCallReceiver.class);
        intent.putExtra("FAKE_NAME",name);
        intent.putExtra("FAKE_PHONE",phoneNumber);
        PendingIntent fakePendingIntent = PendingIntent.getBroadcast(this,0,intent,PendingIntent.FLAG_CANCEL_CURRENT);
        alarmManager.set(AlarmManager.RTC_WAKEUP,time,fakePendingIntent);
        Intent intents = new Intent(this,ActivityDetailMakeCall.class);
        startActivity(intents);
    }


    public static void hideKeyboard(Activity activity) {
        InputMethodManager imm = (InputMethodManager) activity.getSystemService(Activity.INPUT_METHOD_SERVICE);
        //Find the currently focused view, so we can grab the correct window token from it.
        View view = activity.getCurrentFocus();
        //If no view currently has focus, create a new one, just so we can grab a window token from it
        if (view == null) {
            view = new View(activity);
        }
        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessageEvent(EventDefaultContact event) {
        edtPhoneNumber.setText(event.getPhone());
        edtName.setText(event.getName());
    };

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessageEvent(EventSendTime event) {
        time = event.getTime();
        dataList.clear();
        dataList.add(new ModelDetailMakeCall(R.drawable.ic_baseline_access_alarm, "Select time", (time >=60)? time/60+" minute" :time +" seconds"));
        mAdapter.notifyDataSetChanged();
    };



    @Override
    protected void onStart() {
        super.onStart();
        if (!EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().register(this);
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        EventBus.getDefault().unregister(this);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

            Log.d("MainActivity",requestCode+"");
            contactPicked(data);
    }
    private void contactPicked(Intent data) {
        Cursor cursor = null;
        try {
            String phoneNo = null ;
            String name = null;
            // getData() method will have the Content Uri of the selected contact
            Uri uri = data.getData();
            //Query the content uri
            cursor = getContentResolver().query(uri, null, null, null, null);
            cursor.moveToFirst();
            // column index of the phone number
            int  phoneIndex =cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER);
            // column index of the contact name
            int  nameIndex =cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME);
            phoneNo = cursor.getString(phoneIndex);
            name = cursor.getString(nameIndex);
            // Set the value to the textviews
//                textView1.setText(name);
//                textView2.setText(phoneNo);

            edtPhoneNumber.setText(phoneNo);
            edtName.setText(name);
            //Log.d("1212", "contactPicked: "+phoneNo +"   "+name);
        } catch (Exception e) {
            e.printStackTrace();
        }}
}
