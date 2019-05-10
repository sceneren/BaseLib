package wiki.scene.baselibrary

import android.app.Activity
import android.app.Application
import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.multidex.MultiDex
import com.alibaba.android.arouter.launcher.ARouter
import com.hjq.toast.ToastUtils
import com.orhanobut.logger.AndroidLogAdapter
import com.orhanobut.logger.Logger
import com.orhanobut.logger.PrettyFormatStrategy
import com.qmuiteam.qmui.arch.QMUISwipeBackActivityManager
import wiki.scene.baselibrary.toast.BlueToastStyle
import kotlin.properties.Delegates


/**
 * Created by xuhao on 2017/11/16.
 *
 */

open class BaseApp : Application() {

    override fun attachBaseContext(base: Context?) {
        super.attachBaseContext(base)
        MultiDex.install(this)
    }

    companion object {

        private val TAG = "BaseApp"

        var context: Context by Delegates.notNull()
            private set

    }

    override fun onCreate() {
        super.onCreate()
        context = applicationContext
        initConfig()
        registerActivityLifecycleCallbacks(mActivityLifecycleCallbacks)
        QMUISwipeBackActivityManager.init(this@BaseApp)
        ToastUtils.init(this@BaseApp)
        initToastStyle()
        initARouter()
    }


    private fun initARouter() {
        if (BuildConfig.DEBUG) {
            ARouter.openLog()
            ARouter.openDebug()
        }
        ARouter.init(this)
    }

    open fun initToastStyle() {
        ToastUtils.initStyle(BlueToastStyle())
    }

    /**
     * 初始化配置
     */
    private fun initConfig() {

        val formatStrategy = PrettyFormatStrategy.newBuilder()
            .showThreadInfo(false)  // 隐藏线程信息 默认：显示
            .methodCount(0)         // 决定打印多少行（每一行代表一个方法）默认：2
            .methodOffset(7)        // (Optional) Hides internal method calls up to offset. Default 5
            .tag("hao_zz")   // (Optional) Global tag for every log. Default PRETTY_LOGGER
            .build()
        Logger.addLogAdapter(object : AndroidLogAdapter(formatStrategy) {
            override fun isLoggable(priority: Int, tag: String?): Boolean {
                return BuildConfig.DEBUG
            }
        })
    }


    private val mActivityLifecycleCallbacks = object : Application.ActivityLifecycleCallbacks {
        override fun onActivityCreated(activity: Activity, savedInstanceState: Bundle?) {
            Log.d(TAG, "onCreated: " + activity.componentName.className)
        }

        override fun onActivityStarted(activity: Activity) {
            Log.d(TAG, "onStart: " + activity.componentName.className)
        }

        override fun onActivityResumed(activity: Activity) {

        }

        override fun onActivityPaused(activity: Activity) {

        }

        override fun onActivityStopped(activity: Activity) {

        }

        override fun onActivitySaveInstanceState(activity: Activity, outState: Bundle) {

        }

        override fun onActivityDestroyed(activity: Activity) {
            Log.d(TAG, "onDestroy: " + activity.componentName.className)
        }
    }


}
