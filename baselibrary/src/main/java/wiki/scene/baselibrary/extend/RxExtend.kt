package wiki.scene.baselibrary.extend

import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner
import com.uber.autodispose.ObservableSubscribeProxy
import com.uber.autodispose.android.lifecycle.AndroidLifecycleScopeProvider
import com.uber.autodispose.autoDisposable
import io.reactivex.Observable

fun <T> Observable<T>.transform(owner: LifecycleOwner): ObservableSubscribeProxy<T> {
    return autoDisposable(
        AndroidLifecycleScopeProvider
            .from(owner, Lifecycle.Event.ON_DESTROY)
    )
}