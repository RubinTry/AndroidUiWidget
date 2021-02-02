package cn.rubintry.dialog.ios

import android.content.Context
import android.content.DialogInterface
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import cn.rubintry.dialog.R
import cn.rubintry.dialog.base.BaseBottomDialog
import cn.rubintry.dialog.base.BaseClickListener
import cn.rubintry.dialog.base.IBottomDialogBuilder
import cn.rubintry.dialog.base.IDialogBuilder
import cn.rubintry.dialog.ios.adapter.BottomListAdapter
import kotlinx.android.synthetic.main.ios_bottom_list_dialog.*
import java.lang.ref.WeakReference

/**
 * @author logcat
 * 底部弹出式列表弹窗(IOS风格)
 */
class IOSBottomListDialog(builder: Builder) : BaseBottomDialog(builder.contextWeakReference.get()!!, builder.getCancelable(), builder.getCancelListener()) {
    private var onItemClickListener: OnItemClickListener? = null
    private var drawable: Drawable? = null
    private var messageList: List<String>? = null
    private var itemTextSize: Int? = null
    private var itemTextColor: Int? = null
    private var bottomListAdapter: BottomListAdapter? = null
    private var tvCancel: TextView? = null

    init {
        messageList = builder.getList()
        itemTextColor = builder.itemTextColor
        itemTextSize = builder.itemTextSize
        width = builder.width
        height = builder.height
        drawable = builder.drawable
        onItemClickListener = builder.getOnItemClickListener()
        mContext = builder.contextWeakReference.get()
    }

    override fun setDrawable(): Drawable? {
        return drawable
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        tvCancel = findViewById(R.id.tvCancel)
        bottomListAdapter = BottomListAdapter(context, messageList)
        rvBottomList.layoutManager = LinearLayoutManager(context)
        rvBottomList?.isNestedScrollingEnabled = false
        rvBottomList?.adapter = bottomListAdapter
        bottomListAdapter?.setOnItemClickListener { content, position ->
            if (onItemClickListener != null) {
                onItemClickListener?.onItemClick(content, position)
            }
            cancel()
        }
        tvCancel?.setOnClickListener { cancel() }
    }

    override fun setContent(): Int {
        return R.layout.ios_bottom_list_dialog
    }

    override fun show() {
        super.show()
        window?.setWindowAnimations(R.style.ios_bottom_list_animation)
    }

    class Builder(context: Context) : IBottomDialogBuilder {
        val contextWeakReference: WeakReference<Context> = WeakReference(context)
        private var list: List<String>? = null
        var itemTextSize: Int? = null
        var itemTextColor: Int? = null
        private var cancelable = false
        private var cancelListener: DialogInterface.OnCancelListener? = null
        var drawable: Drawable? = null
        var width = 0
        var height = 0
        private var onItemClickListener: OnItemClickListener? = null
        fun setList(list: List<String>?): Builder {
            this.list = list
            return this
        }

        fun setItemTextSize(itemTextSize: Int?): Builder {
            this.itemTextSize = itemTextSize
            return this
        }

        fun setItemTextColor(itemTextColor: Int?): Builder {
            this.itemTextColor = itemTextColor
            return this
        }

        override fun setSize(width: Int, height: Int): Builder {
            this.width = width
            this.height = height
            return this
        }

        override fun setDrawable(drawable: Drawable): Builder {
            this.drawable = drawable
            return this
        }

        override fun setTitle(title: String): IDialogBuilder {
            return this
        }

        override fun setMessage(message: String): Builder {
            return this
        }

        override fun setMessageTextSize(spValue: Int): Builder {
            return this
        }

        override fun setTextColor(textColor: Int): Builder {
            return this
        }

        override fun setCancelable(cancelable: Boolean): Builder {
            this.cancelable = cancelable
            return this
        }

        override fun setCancelListener(cancelListener: DialogInterface.OnCancelListener?): Builder {
            this.cancelListener = cancelListener
            return this
        }

        override fun setOnButtonClickListener(listener: BaseClickListener): Builder {
            return this
        }

        override fun setOnItemClickListener(listener: BaseClickListener): Builder {
            onItemClickListener = listener as OnItemClickListener
            return this
        }

        fun getCancelListener() : DialogInterface.OnCancelListener? {
            return cancelListener
        }

        fun getCancelable(): Boolean{
            return cancelable
        }

        fun getList(): List<String>?{
            return list
        }

        fun getOnItemClickListener() : OnItemClickListener?{
            return onItemClickListener
        }

        override fun create(): IOSBottomListDialog {
            require(!(list == null || list?.size == 0)) { "Please add some items!!!" }
            return IOSBottomListDialog(this)
        }

    }

    interface OnItemClickListener : BaseClickListener {
        override fun onCancel()
        fun onItemClick(content: String?, position: Int)
    }
}