package wiki.scene.baselibrary.base

import androidx.lifecycle.LifecycleOwner

/**
 * @author Jake.Ho
 * created: 2017/10/25
 * desc:
 */
interface IBaseView {
    fun getLifeCycle(): LifecycleOwner

    fun showLoading()

    fun dismissLoading()

}
