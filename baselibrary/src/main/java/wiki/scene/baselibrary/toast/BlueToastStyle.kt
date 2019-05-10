package wiki.scene.baselibrary.toast

import android.view.Gravity
import com.blankj.utilcode.util.SizeUtils
import com.hjq.toast.IToastStyle

class BlueToastStyle : IToastStyle {

    override fun getGravity(): Int {
        return Gravity.CENTER_HORIZONTAL or Gravity.BOTTOM
    }

    override fun getXOffset(): Int {
        return 0
    }

    override fun getYOffset(): Int {
        return SizeUtils.dp2px(40f)
    }

    override fun getZ(): Int {
        return SizeUtils.dp2px(5f)
    }

    override fun getCornerRadius(): Int {
        return 6
    }

    override fun getBackgroundColor(): Int {
        return -0xb86d09
    }

    override fun getTextColor(): Int {
        return -0x1
    }

    override fun getTextSize(): Float {
        return 14f
    }

    override fun getMaxLines(): Int {
        return 3
    }

    override fun getPaddingLeft(): Int {
        return 24
    }

    override fun getPaddingTop(): Int {
        return 10
    }

    override fun getPaddingRight(): Int {
        return paddingLeft
    }

    override fun getPaddingBottom(): Int {
        return paddingTop
    }
}