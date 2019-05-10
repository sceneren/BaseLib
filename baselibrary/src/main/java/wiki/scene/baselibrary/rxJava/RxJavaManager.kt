package wiki.scene.baselibrary.rxJava


import io.reactivex.Observable

import java.util.concurrent.TimeUnit

/**
 * @Author: AriesHoo on 2018/7/23 11:02
 * @E-Mail: AriesHoo@126.com
 * Function: RxJava使用管理类
 * Description:
 * 1、2018-7-23 11:10:35 去掉设置定时器返回值并标记为废弃使用另一个[.setTimer]
 */
class RxJavaManager private constructor() {

    interface TimerListener {
        /**
         * 倒计时结束回调
         */
        fun timeEnd()
    }

    /**
     * 创建Observable
     *
     * @param value
     * @param delay
     * @param unit
     * @param <T>
     * @return
    </T> */
    fun <T> getDelayObservable(value: T, delay: Long, unit: TimeUnit): Observable<T> {
        return Observable.just(value)
            .delay(delay, unit)
            .compose(FastTransformer.switchSchedulers())
    }

    /**
     * 创建 时延单位秒的Observable
     *
     * @param value
     * @param delay
     * @param <T>
     * @return
    </T> */
    fun <T> getDelayObservable(value: T, delay: Long): Observable<T> {
        return getDelayObservable(value, delay, TimeUnit.SECONDS)
    }

    /**
     * 设置时延为毫秒的定时器
     *
     * @param delayTime
     * @return
     */
    fun setTimer(delayTime: Long): Observable<Long> {
        return getDelayObservable(delayTime, delayTime, TimeUnit.MILLISECONDS)
    }

    companion object {

        @Volatile
        private var instance: RxJavaManager? = null

        fun getInstance(): RxJavaManager? {
            if (instance == null) {
                synchronized(RxJavaManager::class.java) {
                    if (instance == null) {
                        instance = RxJavaManager()
                    }
                }
            }
            return instance
        }
    }

}
