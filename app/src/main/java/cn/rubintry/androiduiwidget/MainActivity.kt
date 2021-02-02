package cn.rubintry.androiduiwidget;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.core.widget.NestedScrollView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import cn.rubintry.androiduiwidget.adapter.TestListAdapter;
import cn.rubintry.androiduiwidget.adapter.WidgetTypeAdapter;
import cn.rubintry.androiduiwidget.base.BaseActivity;
import cn.rubintry.androiduiwidget.model.TestDataModel;
import cn.rubintry.androiduiwidget.view.circle.CircleImageViewActivity;
import cn.rubintry.androiduiwidget.view.dialog.DialogActivity;
import cn.rubintry.androiduiwidget.view.elastic.ElasticActivity;
import cn.rubintry.dialog.base.IDialog;
import cn.rubintry.dialog.ios.IOSBottomListDialog;
import cn.rubintry.dialog.ios.IOSMessageDialog;
import cn.rubintry.widget.elastic.ElasticView;
import cn.rubintry.widget.elastic.OnReadyPullListener;
import cn.rubintry.widget.elastic.ScreenUtils;

/**
 * @author logcat
 */
public class MainActivity extends BaseActivity {

    @BindView(R.id.rvWidgetTypeList)
    RecyclerView rvWidgetTypeList;
    private WidgetTypeAdapter widgetTypeAdapter;
    private List<String> widgetTypeList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initRecycler();
    }

    @Override
    protected int setLayout() {
        return R.layout.activity_main;
    }

    private void initRecycler() {
        widgetTypeList = new ArrayList<>();
        widgetTypeList.add("Dialog");
        widgetTypeList.add("ElasticView");
        widgetTypeList.add("CircleImageView");
        rvWidgetTypeList.setLayoutManager(new LinearLayoutManager(this));
        rvWidgetTypeList.setNestedScrollingEnabled(false);
        widgetTypeAdapter = new WidgetTypeAdapter(widgetTypeList);
        rvWidgetTypeList.setAdapter(widgetTypeAdapter);
        widgetTypeAdapter.setOnItemClickListener(listener);
    }

    private WidgetTypeAdapter.OnItemClickListener listener = new WidgetTypeAdapter.OnItemClickListener() {
        @Override
        public void onItemClick(String data , int position) {
            switch (position){
                case 0:
                    //Dialog
                    startActivity(new Intent(MainActivity.this , DialogActivity.class));
                    break;
                case 1:
                    //ElasticView
                    startActivity(new Intent(MainActivity.this , ElasticActivity.class));
                    break;
                case 2:
                    //CircleImageView
                    startActivity(new Intent(MainActivity.this , CircleImageViewActivity.class));
                    break;
                    default:
                        break;
            }
        }
    };

}
