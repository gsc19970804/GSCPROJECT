package app;

import android.app.Application;

import com.example.lenovo.taobaodemo.greendao.GreenDaoUtil;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.uuzuche.lib_zxing.activity.ZXingLibrary;

/**
 * Created by Administrator on 2017/10/11 0011.
 */

public class MyApp extends Application {

    private static MyApp mInstance;


    @Override
    public void onCreate() {
        super.onCreate();
        mInstance = this;
        ImageLoaderConfiguration loaderConfiguration = new ImageLoaderConfiguration.Builder(this).build();
        ImageLoader.getInstance().init(loaderConfiguration);
        //初始化SQLite
        GreenDaoUtil.initGrennDao(this);
        //初始化二维码工具类
        ZXingLibrary.initDisplayOpinion(this);
    }

    public static MyApp getInstance() {
        return mInstance;
    }

    ;

}
