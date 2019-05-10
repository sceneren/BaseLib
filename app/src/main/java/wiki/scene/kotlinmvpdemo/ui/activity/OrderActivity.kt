package wiki.scene.kotlinmvpdemo.ui.activity

import com.alibaba.android.arouter.facade.annotation.Route
import wiki.scene.baselibrary.base.BaseActivity
import wiki.scene.kotlinmvpdemo.R
import wiki.scene.kotlinmvpdemo.config.ARouterConfig

@Route(path = ARouterConfig.ORDER, extras = 10086)
class OrderActivity : BaseActivity() {
    override fun layoutId(): Int {
        return R.layout.activity_order
    }

    override fun initView() {

    }


}