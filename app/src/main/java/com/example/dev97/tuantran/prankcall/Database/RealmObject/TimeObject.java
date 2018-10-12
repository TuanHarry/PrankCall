package com.example.dev97.tuantran.prankcall.Database.RealmObject;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class TimeObject extends RealmObject {
    @PrimaryKey
    private String id;
    private String phoneNumber;
    private String name;
    private long time;
    private long timeisSelect;

    public long getTimeisSelect() {
        return timeisSelect;
    }

    public void setTimeisSelect(long timeisSelect) {
        this.timeisSelect = timeisSelect;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }
}
