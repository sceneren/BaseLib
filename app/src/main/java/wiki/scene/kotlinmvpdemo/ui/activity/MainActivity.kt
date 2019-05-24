package wiki.scene.kotlinmvpdemo.ui.activity

import com.alibaba.android.arouter.facade.annotation.Autowired
import com.alibaba.android.arouter.facade.annotation.Route
import com.alibaba.android.arouter.launcher.ARouter
import com.blankj.rxbus.RxBus
import com.blankj.utilcode.util.SPUtils
import com.jakewharton.rxbinding3.view.clicks
import kotlinx.android.synthetic.main.activity_main.*
import wiki.scene.baselibrary.base.BaseActivity
import wiki.scene.baselibrary.extend.transform
import wiki.scene.kotlinmvpdemo.R
import wiki.scene.kotlinmvpdemo.config.ARouterConfig
import wiki.scene.kotlinmvpdemo.config.SpConfig
import java.util.concurrent.TimeUnit


@Route(path = ARouterConfig.MAIN)
class MainActivity : BaseActivity() {
    @Autowired
    @JvmField
    var title: String? = null

    private var isLogin: Boolean = false

    override fun layoutId(): Int {
        return R.layout.activity_main
    }

    override fun initToolBar() {
        super.initToolBar()
        mTitleBar?.setTitleMainText(title)
    }

    override fun beforeSetContentView() {
        super.beforeSetContentView()
        isLogin = SPUtils.getInstance().getBoolean(SpConfig.IS_LOGIN, false)
    }

    override fun initView() {
        login.text = if (isLogin) "退出登陆" else "登陆"

        login.clicks()
            .throttleFirst(1, TimeUnit.SECONDS)
            .transform(this)
            .subscribe {
                if (isLogin) {
                    isLogin = false
                    SPUtils.getInstance().put(SpConfig.IS_LOGIN, isLogin)
                    login.text = "登陆"
                } else {
                    ARouter.getInstance()
                        .build(ARouterConfig.LOGIN)
                        .navigation(mContext, 1001)
                }
            }

        order.clicks()
            .throttleFirst(1, TimeUnit.SECONDS)
            .transform(this)
            .subscribe({
                ARouter.getInstance()
                    .build(ARouterConfig.ORDER)
                    .navigation()
            }, {
                it.printStackTrace()
            })

    }

    override fun needInjectARouter(): Boolean {
        return true
    }


    override fun bindRxBusEvent() {
        super.bindRxBusEvent()
        RxBus.getDefault().subscribe(this, object : RxBus.Callback<String>() {
            override fun onEvent(t: String?) {
                isLogin = true
                login.text = "退出登陆"
            }
        })
    }

}
