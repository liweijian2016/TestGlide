package cn.com.mint.testglide;

import android.app.Application;

import com.umeng.analytics.MobclickAgent;

/**
 * @File FileName
 * @Function Function
 * @Author lwj.
 * @Time 2017/8/8.
 * @Copyright 2017 Polaris.
 */

public class GlideApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        MobclickAgent.setScenarioType(this, MobclickAgent.EScenarioType.E_UM_NORMAL);

    }
}
