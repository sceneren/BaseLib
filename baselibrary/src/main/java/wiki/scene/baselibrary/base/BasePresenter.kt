package wiki.scene.baselibrary.base

import android.content.Context
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable


/**
 *
 * @Description:    basePresenter
 * @Author:         scene
 * @CreateDate:     2019-06-03 17:58
 * @UpdateUser:     更新者：
 * @UpdateDate:     2019-06-03 17:58
 * @UpdateRemark:   更新说明：
 * @Version:        版本号：
 */
open class BasePresenter<T : IBaseView>(context: Context) : IPresenter<T> {

    var mRootView: T? = null
        private set

    private var compositeDisposable = CompositeDisposable()


    override fun attachView(mRootView: T) {
        this.mRootView = mRootView
    }

    override fun detachView() {
        mRootView = null

        //保证activity结束时取消所有正在执行的订阅
        if (!compositeDisposable.isDisposed) {
            compositeDisposable.clear()
        }

    }

    private val isViewAttached: Boolean
        get() = mRootView != null

    fun checkViewAttached() {
        if (!isViewAttached) throw MvpViewNotAttachedException()
    }

    fun addSubscription(disposable: Disposable) {
        compositeDisposable.add(disposable)
    }

    private class MvpViewNotAttachedException internal constructor() :
        RuntimeException("Please call IPresenter.attachView(IBaseView) before" + " requesting data to the IPresenter")


}