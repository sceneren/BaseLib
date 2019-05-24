package wiki.scene.kotlinmvpdemo

import com.orhanobut.logger.Logger
import io.reactivex.plugins.RxJavaPlugins
import wiki.scene.baselibrary.BaseApp

class App : BaseApp() {
    override fun onCreate() {
        super.onCreate()
        RxJavaPlugins.setErrorHandler {
            Logger.d("onRxJavaErrorHandler ---->: $it")
        }
    }
}