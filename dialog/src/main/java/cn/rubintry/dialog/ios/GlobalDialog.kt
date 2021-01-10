package cn.rubintry.dialog.ios

import android.app.Activity
import android.app.ActivityManager
import android.app.ActivityOptions
import android.app.Application
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.core.app.ActivityCompat
import cn.rubintry.dialog.R
import cn.rubintry.dialog.ios.activity.SystemDialogActivity
import java.lang.ref.WeakReference


/**
 *
 *
 * @constructor
 * 全局对话框
 *
 * @param builder
 */
final class GlobalDialog(builder: Builder) {
    private val TAG = this.javaClass.simpleName

    //    private val activityList = mutableListOf<Activity>()
    private val activityMap = mutableMapOf<String, Activity>()
    private var layoutId: Int? = null

    private var contextReference: WeakReference<Context>? = null

    init {
        this.contextReference = builder.contextReference
        var application: Application? = null
        this.layoutId = builder.getLayoutId()
        if (contextReference?.get() is Application) {
            application = contextReference?.get() as Application
        } else if (contextReference?.get() is Activity) {
            application = (contextReference?.get() as Activity).application
        }

        application?.registerActivityLifecycleCallbacks(object : Application.ActivityLifecycleCallbacks {
            override fun onActivityCreated(activity: Activity, savedInstanceState: Bundle?) {
                activityMap[activity.javaClass.name] = activity
            }

            override fun onActivityStarted(activity: Activity) {
            }

            override fun onActivityResumed(activity: Activity) {
            }

            override fun onActivityPaused(activity: Activity) {
            }

            override fun onActivityStopped(activity: Activity) {
            }

            override fun onActivitySaveInstanceState(activity: Activity, outState: Bundle) {
            }

            override fun onActivityDestroyed(activity: Activity) {
                if (activityMap.contains(activity.javaClass.name)) {
                    activityMap.remove(activity.javaClass.name)
                }
            }

        })
    }


    fun show() {
        if(isShowing()){
            return
        }
        if (contextReference == null) {
            throw IllegalArgumentException("context is null!!!")
        }
        val intent = Intent(contextReference?.get(), SystemDialogActivity::class.java)
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        intent.putExtra("GLOBAL_DIALOG_LAYOUT_ID", layoutId)
        val options = ActivityOptions.makeCustomAnimation(contextReference?.get(), R.anim.dialog_enter, R.anim.dialog_exit)
        contextReference?.get()?.startActivity(intent, options.toBundle())

    }

    fun cancel() {
        if (isShowing()) {
            val activity = activityMap[SystemDialogActivity::class.java.name]
            activity?.finish()
        }
    }

    fun isShowing(): Boolean {
        val activity = activityMap[SystemDialogActivity::class.java.name]
        if(activity != null && !activity.isFinishing){
            return true
        }
        return false
    }


    class Builder(context: Context) {


        var contextReference: WeakReference<Context>? = null

        private var layoutId = 0

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

        fun create(): GlobalDialog {
            return GlobalDialog(this)
        }
    }
}