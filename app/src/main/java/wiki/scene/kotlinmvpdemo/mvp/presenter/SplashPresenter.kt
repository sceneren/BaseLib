package wiki.scene.kotlinmvpdemo.mvp.presenter

import android.content.Context
import com.blankj.utilcode.util.LogUtils
import com.hjq.toast.ToastUtils
import io.reactivex.Observable
import wiki.scene.baselibrary.base.BasePresenter
import wiki.scene.kotlinmvpdemo.mvp.contract.SplashContract
import java.util.concurrent.TimeUnit

class SplashPresenter(context: Context) : BasePresenter<SplashContract.View>(context = context),
    SplashContract.Presenter {

    override fun getData() {
        checkViewAttached()
        addSubscription(Observable.timer(2, TimeUnit.SECONDS)
            .doOnDispose {
                LogUtils.e("")
            }
            .subscribe {
                mRootView?.apply {
                    toMain()
                }
            })
    }
}