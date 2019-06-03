package wiki.scene.baselibrary.base


/**
 *
 * @Description:    BasePresenter
 * @Author:         scene
 * @CreateDate:     2019-06-03 17:59
 * @UpdateUser:     更新者：
 * @UpdateDate:     2019-06-03 17:59
 * @UpdateRemark:   更新说明：
 * @Version:        版本号：
 */
interface IPresenter<in V : IBaseView> {

    fun attachView(mRootView: V)

    fun detachView()

}
