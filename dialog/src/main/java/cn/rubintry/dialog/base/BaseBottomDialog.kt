package cn.rubintry.dialog.base

import android.content.Context
import android.content.DialogInterface
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.view.Gravity
import android.view.WindowManager

/**
 * @author logcat
 * 构建一个显示在屏幕底部的dialog
 */
open class BaseBottomDialog : BaseDialog {
    protected var mContext: Context? = null
    protected var width = 0
    protected var height = 0

    protected constructor(context: Context) : super(context) {}
    protected constructor(context: Context, themeResId: Int) : super(context, themeResId) {
        this.mContext = context.applicationContext
        width = 0
        height = 0
    }

    protected constructor(context: Context, cancelable: Boolean, cancelListener: DialogInterface.OnCancelListener?) : super(context, cancelable, cancelListener) {}

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(setContent())
        setSize(width, height)
    }

    protected open fun setContent(): Int {
        return 0
    }

    override fun show() {
        super.show()
        window?.clearFlags(WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE or WindowManager.LayoutParams.FLAG_ALT_FOCUSABLE_IM)
        // 然后弹出输入法
        window?.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN)
        window?.decorView?.setPadding(0, 0, 0, 0)
        window?.setGravity(Gravity.BOTTOM)
        //调整窗体大小
        val params = window?.attributes
        params?.width = width
        params?.height = height
        var drawable = setDrawable()
        if (drawable == null) {
            drawable = ColorDrawable(Color.TRANSPARENT)
        }
        window?.setBackgroundDrawable(drawable)
        window?.attributes = params
    }

    override fun setSize(width: Int, height: Int) {
        if (width == 0) {
            this.width = DEFAULT_WIDTH
        }
        if (height == 0) {
            this.height = DEFAULT_HEIGHT
        }
    }

    override fun setDrawable(): Drawable? {
        return null
    }

    companion object {
        private const val DEFAULT_WIDTH = WindowManager.LayoutParams.WRAP_CONTENT
        private const val DEFAULT_HEIGHT = WindowManager.LayoutParams.WRAP_CONTENT
    }
}