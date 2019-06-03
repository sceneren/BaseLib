package wiki.scene.baselibrary.base

import android.content.Context
import android.os.Bundle
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import com.alibaba.android.arouter.launcher.ARouter
import com.aries.ui.view.title.TitleBarView
import com.blankj.rxbus.RxBus
import com.qmuiteam.qmui.arch.QMUIActivity
import com.qmuiteam.qmui.util.QMUIDisplayHelper
import com.qmuiteam.qmui.util.QMUIStatusBarHelper
import wiki.scene.baselibrary.R
import wiki.scene.baselibrary.view.MultipleStatusView


/**
 *
 * @Description:    activity基类
 * @Author:         scene
 * @CreateDate:     2019-06-03 17:58
 * @UpdateUser:     更新者：
 * @UpdateDate:     2019-06-03 17:58
 * @UpdateRemark:   更新说明：
 * @Version:        版本号：
 */
abstract class BaseActivity : QMUIActivity() {
    /**
     * 多种状态的 View 的切换
     */
    open var mLayoutStatusView: MultipleStatusView? = null
    protected var mTitleBar: TitleBarView? = null
    protected val mContext by lazy { this@BaseActivity }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (needInjectARouter()) {
            ARouter.getInstance().inject(this)
        }
        loadIntentData()
        beforeSetContentView()
        setContentView(layoutId())
        initData()
        beforeInitView()
        initView()
        initToolBar()
        loadData()
        initRetryListener()
        initListener()
        bindRxBusEvent()
    }

    /**
     * @description 加载intent传递的数据
     * @date: 2019-06-03 17:46
     * @author: scene
     */
    open fun loadIntentData() {

    }

    /**
     * @description 是否需要获取ARouter传递的数据
     * @date: 2019-06-03 17:46
     * @author: scene
     */
    open fun needInjectARouter(): Boolean {
        return false
    }

    /**
     * @description 在设置layout之前执行
     * @date: 2019-06-03 17:46
     * @author: scene
     */
    open fun beforeSetContentView() {
        QMUIStatusBarHelper.translucent(this)
        //true-状态栏文字黑色false-文字白色
        QMUIStatusBarHelper.setStatusBarDarkMode(this)
    }

    /**
     * @description 在初始化view之前执行
     * @date: 2019-06-03 17:47
     * @author: scene
     */
    open fun beforeInitView() {

    }

    /**
     * @description 初始化监听器
     * @date: 2019-06-03 17:47
     * @author: scene
     */
    open fun initListener() {

    }

    /**
     * @description 设置重试监听器-默认会执行loadData方法
     * @date: 2019-06-03 17:47
     * @author: scene
     */
    private fun initRetryListener() {
        mLayoutStatusView?.setOnClickListener(mRetryClickListener)
    }

    /**
     * @description 重试监听
     * @date: 2019-06-03 17:48
     * @author: scene
     */
    open val mRetryClickListener: View.OnClickListener = View.OnClickListener {
        loadData()
    }

    /**
     * @description 基于qmui的返回键的边距
     *建议根据需求在base类里面实现
     * @date: 2019-06-03 17:49
     * @author: scene
     */
    override fun backViewInitOffset(): Int {
        return QMUIDisplayHelper.dp2px(this, 100)
    }

    /**
     *  加载布局
     */
    abstract fun layoutId(): Int

    /**
     * 初始化数据
     */
    open fun initData() {}

    /**
     * @description 初始化toolbar默认实现的左侧按钮是点击返回
     * @date: 2019-06-03 17:49
     * @author: scene
     */
    open fun initToolBar() {
        mTitleBar = findViewById(R.id.titleBarView)
        mTitleBar?.setOnLeftTextClickListener {
            onBackPressed()
        }
    }

    /**
     * 初始化 View
     */
    abstract fun initView()

    /**
     * 开始请求
     */
    open fun loadData() {}


    /**
     * 绑定RxBus的事件统一位置方便寻找
     */
    open fun bindRxBusEvent() {

    }


    /**
     * 打开软键盘
     */
    fun openKeyBord(mEditText: EditText, mContext: Context) {
        val imm = mContext.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.showSoftInput(mEditText, InputMethodManager.RESULT_SHOWN)
        imm.toggleSoftInput(InputMethodManager.SHOW_FORCED, InputMethodManager.HIDE_IMPLICIT_ONLY)
    }

    /**
     * 关闭软键盘
     */
    fun closeKeyBord(mEditText: EditText, mContext: Context) {
        val imm = mContext.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(mEditText.windowToken, 0)
    }

    override fun onDestroy() {
        super.onDestroy()
        RxBus.getDefault().unregister(this)
    }

}


