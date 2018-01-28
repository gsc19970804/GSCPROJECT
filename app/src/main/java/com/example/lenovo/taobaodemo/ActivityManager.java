package com.example.lenovo.taobaodemo;

import android.app.Activity;

import java.util.ArrayList;
import java.util.List;

/**
 * author:Created by WangZhiQiang on 2018/1/9.
 */

public class ActivityManager {

    private static List<Activity> list = new ArrayList<>();

    public static void stActivty(Activity activity) {
        list.add(activity);
    }

    public static void finishs() {
        for (int i = 0; i < list.size(); i++) {
            list.get(i).finish();
        }
    }

}
