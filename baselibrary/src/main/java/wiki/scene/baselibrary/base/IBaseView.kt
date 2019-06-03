package wiki.scene.baselibrary.base

import androidx.lifecycle.LifecycleOwner

/**
 *
 * @Description:    BaseView
 * @Author:         scene
 * @CreateDate:     2019-06-03 17:59
 * @UpdateUser:     更新者：
 * @UpdateDate:     2019-06-03 17:59
 * @UpdateRemark:   更新说明：
 * @Version:        版本号：
 */
interface IBaseView {
    fun getLifeCycle(): LifecycleOwner

    fun showLoading()

    fun dismissLoading()

}
