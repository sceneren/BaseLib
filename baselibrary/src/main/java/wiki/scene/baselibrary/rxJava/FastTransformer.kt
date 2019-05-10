package wiki.scene.baselibrary.rxJava


import io.reactivex.Observable
import io.reactivex.ObservableSource
import io.reactivex.ObservableTransformer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

/**
 * @Author: AriesHoo on 2018/7/23 14:24
 * @E-Mail: AriesHoo@126.com
 * Function: 控制操作线程的辅助类
 * Description:
 */
object FastTransformer {

    /**
     * 线程切换
     *
     * @param <T>
     * @return
    </T> */
    fun <T> switchSchedulers(): ObservableTransformer<T, T> {
        return ObservableTransformer { upstream -> switchSchedulers(upstream) }
    }

    /**
     * 线程切换
     *
     * @param observable
     * @param <T>
     * @return
    </T> */
    fun <T> switchSchedulers(observable: Observable<T>): Observable<T> {
        return observable.subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }

}
