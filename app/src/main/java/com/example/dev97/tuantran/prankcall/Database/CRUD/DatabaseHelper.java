package com.example.dev97.tuantran.prankcall.Database.CRUD;

import com.example.dev97.tuantran.prankcall.Database.RealmObject.TimeObject;

import java.util.List;

public interface DatabaseHelper {
    void saveCall(TimeObject object);
    List<TimeObject> getTimeObject();
    TimeObject getObject(String id);
    long getTimeByID(String id);
}
