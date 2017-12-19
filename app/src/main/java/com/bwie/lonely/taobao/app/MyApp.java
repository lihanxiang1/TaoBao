package com.bwie.lonely.taobao.app;

import android.app.Application;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;

/**
 * Created by Lonely on 2017/12/7.
 */

public class MyApp extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        Fresco.initialize(this);


        ImageLoaderConfiguration configuration = ImageLoaderConfiguration.createDefault(this);
        ImageLoader.getInstance().init(configuration);
    }
}
