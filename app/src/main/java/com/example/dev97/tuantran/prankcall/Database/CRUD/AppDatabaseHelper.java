package com.example.dev97.tuantran.prankcall.Database.CRUD;

import android.content.Context;
import android.content.Intent;

import com.example.dev97.tuantran.prankcall.Database.RealmObject.TimeObject;

import java.util.List;

import io.realm.Realm;
import io.realm.RealmConfiguration;

public class AppDatabaseHelper implements DatabaseHelper{
    private Realm mRealm;

    public AppDatabaseHelper(Context context){
        Realm.init(context);
        RealmConfiguration config = new RealmConfiguration
                .Builder()
                .schemaVersion(1)
                .build();
        mRealm = Realm.getInstance(config);
    }

    @Override
    public void saveCall(final TimeObject object) {
        mRealm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                realm.insertOrUpdate(object);
            }
        });
    }

    @Override
    public List<TimeObject> getTimeObject() {
        return mRealm.where(TimeObject.class).findAll();
    }

    @Override
    public TimeObject getObject(String id) {
        return mRealm.where(TimeObject.class).equalTo("id",id).findFirst();
    }

    @Override
    public long getTimeByID(String id) {
        TimeObject a = mRealm.where(TimeObject.class).equalTo("id",id).findFirst();
        return a.getTimeisSelect();
    }


}
