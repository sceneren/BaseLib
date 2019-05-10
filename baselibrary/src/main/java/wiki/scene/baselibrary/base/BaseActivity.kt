package wiki.scene.baselibrary.base

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import android.widget.Toast
import com.alibaba.android.arouter.launcher.ARouter
import com.aries.ui.view.title.TitleBarView
import com.blankj.rxbus.RxBus
import com.qmuiteam.qmui.arch.QMUIActivity
import com.qmuiteam.qmui.util.QMUIDisplayHelper
import com.qmuiteam.qmui.util.QMUIStatusBarHelper
import io.reactivex.annotations.NonNull
import pub.devrel.easypermissions.AppSettingsDialog
import pub.devrel.easypermissions.EasyPermissions
import wiki.scene.baselibrary.R
import wiki.scene.baselibrary.view.MultipleStatusView


/**
 * @author xuhao
 * created: 2017/10/25
 * desc:BaseActivity基类
 */
abstract class BaseActivity : QMUIActivity(),
    EasyPermissions.PermissionCallbacks {
    /**
     * 多种状态的 View 的切换
     */
    protected var mLayoutStatusView: MultipleStatusView? = null
    protected var mTitleBar: TitleBarView? = null
    protected val mContext by lazy { this@BaseActivity }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        ARouter.getInstance().inject(this);
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

    open fun beforeSetContentView() {
        QMUIStatusBarHelper.translucent(this)
        //true-状态栏文字黑色false-文字白色
        QMUIStatusBarHelper.setStatusBarDarkMode(this)
    }

    open fun beforeInitView() {

    }

    open fun initListener() {

    }

    private fun initRetryListener() {
        mLayoutStatusView?.setOnClickListener(mRetryClickListener)
    }

    open val mRetryClickListener: View.OnClickListener = View.OnClickListener {
        loadData()
    }

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

    open fun bindRxBusEvent(){

    }


    /**
     * 打卡软键盘
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

    /**
     * 重写要申请权限的Activity或者Fragment的onRequestPermissionsResult()方法，
     * 在里面调用EasyPermissions.onRequestPermissionsResult()，实现回调。
     *
     * @param requestCode  权限请求的识别码
     * @param permissions  申请的权限
     * @param grantResults 授权结果
     */
    override fun onRequestPermissionsResult(requestCode: Int, @NonNull permissions: Array<String>, @NonNull grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        EasyPermissions.onRequestPermissionsResult(requestCode, permissions, grantResults, this)
    }

    /**
     * 当权限被成功申请的时候执行回调
     *
     * @param requestCode 权限请求的识别码
     * @param perms       申请的权限的名字
     */
    override fun onPermissionsGranted(requestCode: Int, perms: List<String>) {
        Log.i("EasyPermissions", "获取成功的权限$perms")
    }

    /**
     * 当权限申请失败的时候执行的回调
     *
     * @param requestCode 权限请求的识别码
     * @param perms       申请的权限的名字
     */
    override fun onPermissionsDenied(requestCode: Int, perms: List<String>) {
        //处理权限名字字符串
        val sb = StringBuffer()
        for (str in perms) {
            sb.append(str)
            sb.append("\n")
        }
        sb.replace(sb.length - 2, sb.length, "")
        //用户点击拒绝并不在询问时候调用
        if (EasyPermissions.somePermissionPermanentlyDenied(this, perms)) {
            Toast.makeText(this, "已拒绝权限" + sb + "并不再询问", Toast.LENGTH_SHORT).show()
            AppSettingsDialog.Builder(this)
                .setRationale("此功能需要" + sb + "权限，否则无法正常使用，是否打开设置")
                .setPositiveButton("好")
                .setNegativeButton("不行")
                .build()
                .show()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        RxBus.getDefault().unregister(this);
    }

}


