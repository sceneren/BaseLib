package wiki.scene.kotlinmvpdemo.mvp.contract

import wiki.scene.baselibrary.base.IBaseView
import wiki.scene.baselibrary.base.IPresenter

class SplashContract {
    interface View : IBaseView {
        fun toMain()
    }

    interface Presenter : IPresenter<View>{
        fun getData()
    }
}