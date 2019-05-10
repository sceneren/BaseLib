package wiki.scene.baselibrary.extend

import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner
import com.uber.autodispose.ObservableSubscribeProxy
import com.uber.autodispose.android.lifecycle.AndroidLifecycleScopeProvider
import com.uber.autodispose.autoDisposable
import io.reactivex.Observable
import wiki.scene.baselibrary.scheduler.SchedulerUtils

fun <T> Observable<T>.transform(owner: LifecycleOwner): ObservableSubscribeProxy<T> {
    return this.compose(SchedulerUtils.ioToMain())
        .autoDisposable(
            AndroidLifecycleScopeProvider
                .from(owner, Lifecycle.Event.ON_DESTROY)
        )
}