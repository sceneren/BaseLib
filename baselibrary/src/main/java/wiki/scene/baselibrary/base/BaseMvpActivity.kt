package wiki.scene.baselibrary.base


/**
 *
 * @Description:    mvpActivity的基类
 * @Author:         scene
 * @CreateDate:     2019-06-03 17:58
 * @UpdateUser:     更新者：
 * @UpdateDate:     2019-06-03 17:58
 * @UpdateRemark:   更新说明：
 * @Version:        版本号：
 */
abstract class BaseMvpActivity<v : IBaseView, P : BasePresenter<v>> : BaseActivity(), IBaseView {
    /**
     * 多种状态的 View 的切换
     */
    protected var mPresenter: P? = null

    abstract fun initPresenter()

    override fun beforeInitView() {
        super.beforeInitView()
        initPresenter()
    }

    override fun onDestroy() {
        super.onDestroy()
        mPresenter?.detachView()
    }

    override fun showLoading() {
    }

    override fun dismissLoading() {
    }
}


