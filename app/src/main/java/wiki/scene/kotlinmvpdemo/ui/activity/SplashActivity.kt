package wiki.scene.kotlinmvpdemo.ui.activity

import androidx.lifecycle.LifecycleOwner
import com.alibaba.android.arouter.launcher.ARouter
import com.blankj.utilcode.util.BarUtils
import com.qmuiteam.qmui.util.QMUIDisplayHelper
import wiki.scene.baselibrary.base.BaseMvpActivity
import wiki.scene.kotlinmvpdemo.R
import wiki.scene.kotlinmvpdemo.config.ARouterConfig
import wiki.scene.kotlinmvpdemo.mvp.contract.SplashContract
import wiki.scene.kotlinmvpdemo.mvp.presenter.SplashPresenter

class SplashActivity : BaseMvpActivity<SplashContract.View, SplashPresenter>(), SplashContract.View {
    override fun getLifeCycle(): LifecycleOwner {
        return this
    }

    override fun initPresenter() {
        mPresenter = SplashPresenter(this)
        mPresenter?.attachView(this)
    }

    override fun layoutId(): Int {
        return R.layout.activity_splash
    }

    override fun beforeSetContentView() {
        super.beforeSetContentView()
        QMUIDisplayHelper.setFullScreen(this)
        BarUtils.setNavBarVisibility(mContext, false)
    }

    override fun initView() {
        window.setBackgroundDrawable(null)
        mPresenter?.getData()
    }

    override fun dismissLoading() {
    }

    override fun showLoading() {
    }

    override fun toMain() {
        ARouter.getInstance()
            .build(ARouterConfig.MAIN)
            .withString("title", "测试")
            .navigation()
        finish()
    }


}
