package wiki.scene.kotlinmvpdemo.ui.activity

import com.alibaba.android.arouter.facade.annotation.Route
import com.blankj.rxbus.RxBus
import com.blankj.utilcode.util.SPUtils
import kotlinx.android.synthetic.main.activity_login.*
import wiki.scene.baselibrary.base.BaseActivity
import wiki.scene.kotlinmvpdemo.R
import wiki.scene.kotlinmvpdemo.config.ARouterConfig
import wiki.scene.kotlinmvpdemo.config.SpConfig
import wiki.scene.kotlinmvpdemo.event.StringEvent

@Route(path = ARouterConfig.LOGIN)
class LoginActivity : BaseActivity() {
    override fun layoutId(): Int {
        return R.layout.activity_login
    }

    override fun initView() {
        login.setOnClickListener {
            SPUtils.getInstance().put(SpConfig.IS_LOGIN, true)
            RxBus.getDefault().post(StringEvent.EVENT_LOGIN_SUCCESS)
            finish()
        }
    }

}