package com.example.dev97.tuantran.prankcall.UI.Dialog;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.ContactsContract;
import android.provider.MediaStore;
import android.support.v4.app.DialogFragment;
import android.util.Log;
import android.widget.Toast;

import com.example.dev97.tuantran.prankcall.Models.Events.EventDefaultContact;
import com.example.dev97.tuantran.prankcall.UI.FragmentMakeCall.ActivityDetailMakeCall;

import org.greenrobot.eventbus.EventBus;

import java.io.File;

public class DialogPerson extends DialogFragment {
    final int PICK_CONTACT = 1212;
    final int RESULT_LOAD_IMAGE = 1213;
    final int TAKE_PICTURE = 1214;
    private Uri uriContact;
    private String contactID;     // contacts unique ID
    private String contactNameTuan = "";
    private String contactNumber = "";
    private Uri imageUri;
    File file;
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        final String[] Colors ={"Default Contact","Contacts"};
        AlertDialog.Builder dialogList = new AlertDialog.Builder(getActivity());
        //Set title
        dialogList.setTitle("Set contact");
        //Truyền danh sách colors vào, cài đặt sự kiện khi click vào Item
        dialogList.setItems(Colors, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int item) {
                switch (item){
                    case 0:
                        EventBus.getDefault().post(new EventDefaultContact("Tuan Tran","01639488996"));
                        break;
                    case 1:
                        Intent contactPickerIntent = new Intent(Intent.ACTION_PICK,
                                ContactsContract.CommonDataKinds.Phone.CONTENT_URI);
                        startActivityForResult(contactPickerIntent, 1);
                        dismiss();
                        break;
                }
            }
        });
        return  dialogList.create();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {

        Log.d("abc",data+"");
        super.onActivityResult(requestCode, resultCode, data);
    }

    private void contactPicked(Intent data) {
        Cursor cursor = null;
        try {
            String phoneNo = null ;
            String name = null;
            // getData() method will have the Content Uri of the selected contact
            Uri uri = data.getData();
            //Query the content uri
            cursor = getActivity().getContentResolver().query(uri, null, null, null, null);
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
            Log.d("121212", "onActivityResult: "+name);
            contactNumber = phoneNo;
            contactNameTuan = name;
            //Log.d("1212", "contactPicked: "+phoneNo +"   "+name);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    private void retrieveContactName() {

        String contactName = null;

        // querying contact data store
        Cursor cursor = getActivity().getContentResolver().query(uriContact, null, null, null, null);

        if (cursor.moveToFirst()) {

            // DISPLAY_NAME = The display name for the contact.
            // HAS_PHONE_NUMBER =   An indicator of whether this contact has at least one phone number.

            contactName = cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME));
        }

        cursor.close();
        contactNameTuan = contactName;
        //Log.d(, "Contact Name: " + contactName);

    }
}


