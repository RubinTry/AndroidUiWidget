package cn.rubintry.dialog.ios.activity

import android.app.Activity
import android.os.Bundle
import android.view.Gravity
import android.view.WindowManager
import cn.rubintry.dialog.R


class SystemDialogActivity : Activity() {
    protected var activityCloseEnterAnimation = 0

    protected var activityCloseExitAnimation = 0


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.clearFlags(WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE or WindowManager.LayoutParams.FLAG_ALT_FOCUSABLE_IM)
        // 然后弹出输入法
        window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN)
        window.decorView.setPadding(0, 0, 0, 0)
        window.setGravity(Gravity.TOP)

        var activityStyle = theme.obtainStyledAttributes(intArrayOf(android.R.attr.windowAnimationStyle))

        val windowAnimationStyleResId = activityStyle.getResourceId(0, 0)

        activityStyle.recycle()

        activityStyle = theme.obtainStyledAttributes(windowAnimationStyleResId, intArrayOf(android.R.attr.activityCloseEnterAnimation, android.R.attr.activityCloseExitAnimation))

        activityCloseEnterAnimation = activityStyle.getResourceId(0, 0)

        activityCloseExitAnimation = activityStyle.getResourceId(1, 0)

        activityStyle.recycle()

        val layoutId: Int? = intent.getIntExtra("GLOBAL_DIALOG_LAYOUT_ID", 0)
        if (layoutId == null) {
            setContentView(R.layout.activity_system_dialog)
        } else {
            if (layoutId == 0) {
                setContentView(R.layout.activity_system_dialog)
            } else {
                setContentView(layoutId)
            }
        }


    }


    override fun finish() {
        super.finish()
        overridePendingTransition(activityCloseEnterAnimation, activityCloseExitAnimation)
    }
}