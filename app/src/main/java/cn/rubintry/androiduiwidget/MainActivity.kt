package cn.rubintry.androiduiwidget

import android.content.Intent
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import cn.rubintry.androiduiwidget.adapter.WidgetTypeAdapter
import cn.rubintry.androiduiwidget.base.BaseActivity
import cn.rubintry.androiduiwidget.view.circle.CircleImageViewActivity
import cn.rubintry.androiduiwidget.view.dialog.DialogActivity
import cn.rubintry.androiduiwidget.view.elastic.ElasticActivity
import java.util.*

/**
 * @author logcat
 */
class MainActivity : BaseActivity() {
    private var widgetTypeAdapter: WidgetTypeAdapter? = null
    private var widgetTypeList: MutableList<String> = mutableListOf()
    private var rvWidgetTypeList: RecyclerView ?= null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        rvWidgetTypeList = findViewById(R.id.rvWidgetTypeList)
        initRecyclerView()
    }

    override fun setLayout(): Int {
        return R.layout.activity_main
    }

    private fun initRecyclerView() {
        widgetTypeList.clear()
        widgetTypeList = ArrayList()
        widgetTypeList.add("Dialog")
        widgetTypeList.add("ElasticView")
        widgetTypeList.add("CircleImageView")
        rvWidgetTypeList?.layoutManager = LinearLayoutManager(this)
        rvWidgetTypeList?.isNestedScrollingEnabled = false
        widgetTypeAdapter = WidgetTypeAdapter(widgetTypeList)
        rvWidgetTypeList?.adapter = widgetTypeAdapter
        widgetTypeAdapter?.setOnItemClickListener(listener)
    }

    private val listener = WidgetTypeAdapter.OnItemClickListener { data, position ->
        when (position) {
            0 ->                     //Dialog
                startActivity(Intent(this@MainActivity, DialogActivity::class.java))
            1 ->                     //ElasticView
                startActivity(Intent(this@MainActivity, ElasticActivity::class.java))
            2 ->                     //CircleImageView
                startActivity(Intent(this@MainActivity, CircleImageViewActivity::class.java))
            else -> {
            }
        }
    }
}