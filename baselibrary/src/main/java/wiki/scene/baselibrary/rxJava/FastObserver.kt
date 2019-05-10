package wiki.scene.baselibrary.rxJava


import io.reactivex.annotations.NonNull
import io.reactivex.functions.Consumer
import io.reactivex.observers.DefaultObserver

/**
 * @Author: AriesHoo on 2018/7/23 14:21
 * @E-Mail: AriesHoo@126.com
 * Function: Retrofit快速观察者-观察者基类用于错误全局设置
 * Description:
 * 1、2017-11-16 11:35:12 AriesHoo增加返回错误码全局控制
 * 2、2018-6-20 15:15:45 重构
 * 3、2018-7-9 14:27:03 删除IHttpRequestControl判断避免http错误时无法全局控制BUG
 */
abstract class FastObserver<T> : DefaultObserver<T>(), Consumer<T> {

    override fun onComplete() {

    }

    override fun onError(e: Throwable) {
        onFail(e)
    }

    override fun onNext(entity: T) {
        onSuccess(entity)
    }

    /**
     * 获取成功后数据展示
     *
     * @param entity
     */
    abstract fun onSuccess(@NonNull entity: T)

    open fun onFail(@NonNull e: Throwable) {}
}
