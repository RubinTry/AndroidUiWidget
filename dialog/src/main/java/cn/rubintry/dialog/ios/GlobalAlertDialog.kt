package cn.rubintry.dialog.ios

import android.content.Context
import android.content.DialogInterface
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.os.Message
import android.provider.Settings
import android.util.Log
import android.view.*
import android.widget.Scroller
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.view.ContextThemeWrapper
import androidx.core.content.ContextCompat
import cn.rubintry.dialog.R
import java.lang.ref.WeakReference
import java.util.*
import kotlin.concurrent.schedule
import kotlin.concurrent.timerTask
import kotlin.math.abs

class GlobalAlertDialog(builder: Builder) : AlertDialog(builder.contextReference?.get()!!, false, DialogInterface.OnCancelListener { }) {

    private var layoutId: Int = 0
    private var contextReference: WeakReference<Context>? = null
    private val delayHandler = Handler(Looper.getMainLooper())
    private var onNotifyClickListener: OnNotifyClickListener? = null

    private var pressing = false
    private var timeout = false

    private var rootView: ViewGroup? = null

    init {
        this.contextReference = builder.contextReference
        this.layoutId = builder.getLayoutId()
        this.onNotifyClickListener = builder.getOnNotifyClickListener()
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            window?.setType(WindowManager.LayoutParams.TYPE_APPLICATION_OVERLAY)
        }

        setCanceledOnTouchOutside(false)

        if (layoutId == 0) {
            setContentView(R.layout.dialog_global_alert)
        } else {
            setContentView(layoutId)
        }

        rootView = findViewById<ViewGroup>(android.R.id.content)

        if (rootView?.childCount!! > 0) {
            rootView?.getChildAt(0)?.setOnClickListener {
                cancel()
                if (onNotifyClickListener != null) {
                    onNotifyClickListener?.onRootViewClick()
                }
            }
        }

    }


    override fun onKeyDown(keyCode: Int, event: KeyEvent): Boolean {
        when (keyCode) {
            KeyEvent.KEYCODE_BACK -> {
                cancel()
            }
        }
        return super.onKeyDown(keyCode, event)
    }

    override fun show() {
        super.show()
        timeout = false
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.M) {
            if (!Settings.canDrawOverlays(contextReference?.get())) {
                Toast.makeText(contextReference?.get(), R.string.permission_denied, Toast.LENGTH_SHORT).show()
                return
            }
        }

        window?.clearFlags(WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE or WindowManager.LayoutParams.FLAG_ALT_FOCUSABLE_IM)
        // 然后弹出输入法
        window?.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN)
        window?.decorView?.setPadding(0, 0, 0, 0)
        window?.setGravity(Gravity.TOP)
        window?.setBackgroundDrawable(ContextCompat.getDrawable(contextReference?.get()!!, R.drawable.notify_dialog_bg))
        window?.setWindowAnimations(R.style.DialogNotifyAnimations)
        val attributes = window?.attributes
        attributes?.dimAmount = 0.0f
        attributes?.width = WindowManager.LayoutParams.WRAP_CONTENT
        attributes?.height = WindowManager.LayoutParams.WRAP_CONTENT
        window?.attributes = attributes

        window?.setFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCH_MODAL or WindowManager.LayoutParams.FLAG_WATCH_OUTSIDE_TOUCH, WindowManager.LayoutParams.FLAG_NOT_TOUCH_MODAL or WindowManager.LayoutParams.FLAG_WATCH_OUTSIDE_TOUCH)
        window?.decorView?.setOnTouchListener { v, event -> true }


        delayHandler.postDelayed(Runnable {
            if (!pressing){
                cancel()
            }
            timeout = true
        }, 3000)
    }

    override fun dispatchTouchEvent(ev: MotionEvent): Boolean {
        when (ev.action) {
            MotionEvent.ACTION_DOWN -> {
                pressing = true
            }

            MotionEvent.ACTION_UP -> {
                pressing = false
                if(timeout){
                    cancel()
                }
            }
        }
        return super.dispatchTouchEvent(ev)
    }

    override fun cancel() {
        delayHandler.removeCallbacksAndMessages(null)
        super.cancel()
    }

    class Builder(context: Context) {
        var contextReference: WeakReference<Context>? = null
        private var layoutId = 0
        private var themeId = 0
        private var onNotifyClickListener: OnNotifyClickListener? = null


        init {
            contextReference = WeakReference(context)
        }

        fun setLayoutId(layoutId: Int): Builder {
            this.layoutId = layoutId
            return this
        }

        fun getLayoutId(): Int {
            return layoutId
        }

        fun setTheme(themeId: Int): Builder {
            this.themeId = themeId
            return this
        }

        fun getTheme(): Int {
            return themeId
        }

        fun setOnNotifyClickListener(onNotifyClickListener: OnNotifyClickListener): Builder {
            this.onNotifyClickListener = onNotifyClickListener
            return this
        }

        fun getOnNotifyClickListener(): OnNotifyClickListener? {
            return onNotifyClickListener
        }

        fun create(): GlobalAlertDialog {
            contextReference = if (themeId == 0) {
                WeakReference(ContextThemeWrapper(contextReference?.get(), R.style.GlobalAlertTheme))
            } else {
                WeakReference(ContextThemeWrapper(contextReference?.get(), themeId))
            }
            return GlobalAlertDialog(this)

        }
    }


    interface OnNotifyClickListener {
        fun onRootViewClick()
    }
}