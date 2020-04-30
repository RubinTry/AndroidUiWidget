package cn.rubintry.androiduiwidget;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.core.widget.NestedScrollView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
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

import cn.rubintry.androiduiwidget.adapter.TestListAdapter;
import cn.rubintry.androiduiwidget.model.TestDataModel;
import cn.rubintry.dialog.base.IDialog;
import cn.rubintry.dialog.ios.IOSBottomListDialog;
import cn.rubintry.dialog.ios.IOSMessageDialog;
import cn.rubintry.widget.elastic.ElasticView;
import cn.rubintry.widget.elastic.OnReadyPullListener;
import cn.rubintry.widget.elastic.ScreenUtils;

/**
 * @author logcat
 */
public class MainActivity extends AppCompatActivity {

//    private ElasticView esvContainer;
//    private ImageView imgWorkerLine;
//    private RecyclerView rvTest;
//    private List<TestDataModel> dataList;
//    private TestListAdapter testListAdapter;
//    private NestedScrollView nslContent;
    private IDialog dialog;
    private IDialog bottomDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        imgWorkerLine = findViewById(R.id.imgWorkerLine);
//        esvContainer = findViewById(R.id.esvContainer);
//        rvTest = findViewById(R.id.rvTest);
//        nslContent = findViewById(R.id.nslContent);
//        initRecyclerView();
        initDialog();
//        esvContainer.setHeader(imgWorkerLine)
//        .setOnReadyPullListener(new OnReadyPullListener() {
//            @Override
//            public boolean isReady() {
//                return nslContent.getScrollY() == 0;
//            }
//        });
    }

    private void initDialog() {
        dialog = new IOSMessageDialog.Builder(this)
                .setCancelable(true)
                .setMessage("这是信息")
                .setTextColor(ContextCompat.getColor(this , R.color.txtColor))
                .setOnButtonClickListener(new IOSMessageDialog.OnButtonClickListener() {
                    @Override
                    public void onConfirm() {
                        Toast.makeText(MainActivity.this, "点击确定", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onCancel() {
                        Toast.makeText(MainActivity.this, "点击取消", Toast.LENGTH_SHORT).show();
                    }
                })
                .create();


        List<String> list = new ArrayList<>();
        list.add("男");
        list.add("女");
        list.add("不显示");
        bottomDialog = new IOSBottomListDialog.Builder(this)
                .setCancelable(true)
                .setList(list)
                .setCancelListener(null)
                .setOnItemClickListener(new IOSBottomListDialog.OnItemClickListener() {
                    @Override
                    public void onCancel() {

                    }

                    @Override
                    public void onItemClick(String content , int position) {
                        Toast.makeText(MainActivity.this, content, Toast.LENGTH_SHORT).show();
                    }

                }).create();

    }


    public void tips(View view) {
        dialog.show();
    }

    public void bottomList(View view) {
        bottomDialog.show();
    }
}
