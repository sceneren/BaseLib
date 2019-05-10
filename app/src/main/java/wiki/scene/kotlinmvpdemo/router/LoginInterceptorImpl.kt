package wiki.scene.kotlinmvpdemo.router

import android.content.Context
import com.alibaba.android.arouter.facade.Postcard
import com.alibaba.android.arouter.facade.annotation.Interceptor
import com.alibaba.android.arouter.facade.callback.InterceptorCallback
import com.alibaba.android.arouter.facade.template.IInterceptor
import com.alibaba.android.arouter.launcher.ARouter
import com.blankj.utilcode.util.LogUtils
import com.blankj.utilcode.util.SPUtils
import wiki.scene.kotlinmvpdemo.config.ARouterConfig
import wiki.scene.kotlinmvpdemo.config.SpConfig

@Interceptor(name = "login", priority = 6)
class LoginInterceptorImpl : IInterceptor {
    override fun process(postcard: Postcard, callback: InterceptorCallback) {
        val isLogin = SPUtils.getInstance().getBoolean(SpConfig.IS_LOGIN, false)
        if (isLogin) { // 如果已经登录不拦截
            callback.onContinue(postcard)
        } else {  // 如果没有登录
            if (postcard.extra == 10086) {
                ARouter.getInstance()
                    .build(ARouterConfig.LOGIN)
                    .navigation()
                callback.onInterrupt(null)
            } else {
                callback.onContinue(postcard)
            }

        }

    }

    override fun init(context: Context) {
        LogUtils.v("路由登录拦截器初始化成功") //只会走一次
    }

}