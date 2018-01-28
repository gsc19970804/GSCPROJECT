package com.example.lenovo.taobaodemo.greendao;

import android.content.Context;

import com.example.lenovo.taobaodemo.database.DaoMaster;
import com.example.lenovo.taobaodemo.database.DaoSession;

/**
 * author:Created by WangZhiQiang on 2017/12/31.
 */

public class GreenDaoUtil {

    private static DaoSession daoSession;

    public static void initGrennDao(Context context) {
        daoSession = DaoMaster.newDevSession(context, "GSC.db");
    }

    public static DaoSession getDaoSession() {

        return daoSession;
    }

}
