package cn.rubintry.dialog.expand

import android.content.res.Resources
import android.view.View
import android.widget.LinearLayout

object ExpandFunction {

    fun View.setMarginTop(dpValue : Int) {
        val marginLayout = LinearLayout.LayoutParams(this.layoutParams)
        marginLayout.topMargin = dp2px(dpValue.toFloat()).toInt()
        this.layoutParams = marginLayout
    }


    private fun dp2px(dpValue: Float): Float {
        val scale = Resources.getSystem().displayMetrics.density
        return dpValue * scale + 0.5f
    }
}