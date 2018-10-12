package com.example.dev97.tuantran.prankcall;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import com.example.dev97.tuantran.prankcall.UI.FakeActivity.FakeRingingActivity;

public class FakeCallReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        String name = intent.getStringExtra("FAKE_NAME");
        String phoneNum = intent.getStringExtra("FAKE_PHONE");
        Intent intentObject = new Intent(context, FakeRingingActivity.class);
        intentObject.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intentObject.putExtra("myfakename",name);
        intentObject.putExtra("myfakenum",phoneNum);
        context.startActivity(intentObject);
    }
}
