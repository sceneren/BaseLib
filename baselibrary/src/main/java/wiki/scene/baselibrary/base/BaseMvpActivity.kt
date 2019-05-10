package wiki.scene.baselibrary.base


/**
 * @author xuhao
 * created: 2017/10/25
 * desc:BaseActivity基类
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


