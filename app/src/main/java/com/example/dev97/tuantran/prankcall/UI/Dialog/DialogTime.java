package com.example.dev97.tuantran.prankcall.UI.Dialog;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.widget.Toast;

import com.example.dev97.tuantran.prankcall.Models.Events.EventSendTime;
import com.example.dev97.tuantran.prankcall.UI.FragmentMakeCall.ActivityDetailMakeCall;

import org.greenrobot.eventbus.EventBus;


public class DialogTime extends DialogFragment {
    private String[] item = {
            "10 seconds later",
            "30 seconds later",
            "1 minutes later",
            "5 minutes later",
            "15 minutes later",
            "30 minutes later"
    };
    private String selectedItem = "";

    @SuppressLint("ResourceType")
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        final AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("SET TIME");
        selectedItem = item[0];
        builder.setSingleChoiceItems(item, 0, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                selectedItem = item[which]; // truyền giá trị vào selectedItem
            }
        });

        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
//                Toast toast = Toast.makeText(getActivity(), "Đã Chọn : " + selectedItem, Toast.LENGTH_SHORT);
//                toast.show();
//                Intent intent = new Intent(getActivity(), ActivityDetailMakeCall.class);
//                Bundle bundle = new Bundle();
//                bundle.putLong("TIME_SELECT",convertTime(selectedItem));
//                intent.putExtras(bundle);
//                startActivity(intent);
                EventBus.getDefault().post(new EventSendTime(convertTime(selectedItem)));
            }
        });

        return builder.create();

    }

    private long convertTime(String abc){
        if(abc.equals("10 seconds later")){
            return 10;
        }else if(abc.equals("30 seconds later")){
            return 30;
        }else if(abc.equals("1 minutes later")){
            return 60;
        }else if(abc.equals("5 minutes later")){
            return 300;
        }else if(abc.equals("15 minutes later")){
            return 900;
        }else if(abc.equals("30 minutes later")){
            return 1800;
        }
        return 0;
    }
}
