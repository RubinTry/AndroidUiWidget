package cn.rubintry.androiduiwidget;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.widget.NestedScrollView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import cn.rubintry.androiduiwidget.adapter.TestListAdapter;
import cn.rubintry.androiduiwidget.model.TestDataModel;
import cn.rubintry.dialog.base.IDialog;
import cn.rubintry.dialog.ios.IOSMessageDialog;
import cn.rubintry.widget.elastic.ElasticView;
import cn.rubintry.widget.elastic.OnReadyPullListener;

/**
 * @author logcat
 */
public class MainActivity extends AppCompatActivity {

    private ElasticView esvContainer;
    private ImageView imgWorkerLine;
    private RecyclerView rvTest;
    private List<TestDataModel> dataList;
    private TestListAdapter testListAdapter;
    private NestedScrollView nslContent;
    private IDialog dialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        imgWorkerLine = findViewById(R.id.imgWorkerLine);
        esvContainer = findViewById(R.id.esvContainer);
        rvTest = findViewById(R.id.rvTest);
        nslContent = findViewById(R.id.nslContent);
        initRecyclerView();
        initDialog();
        esvContainer.setHeader(imgWorkerLine)
        .setOnReadyPullListener(new OnReadyPullListener() {
            @Override
            public boolean isReady() {
                return nslContent.getScrollY() == 0;
            }
        });
    }

    private void initDialog() {
        dialog = new IOSMessageDialog.Builder(this)
                .setCancelable(true)
                .setCancelListener(null)
                .setSize(732 , 316)
                .create();

        dialog.show();
    }

    private void initRecyclerView() {
        dataList = new ArrayList<>();
        rvTest.setLayoutManager(new LinearLayoutManager(this));
        rvTest.setNestedScrollingEnabled(false);
        testListAdapter = new TestListAdapter(dataList);
        rvTest.setAdapter(testListAdapter);
        testListAdapter.setOnItemClickListener(new TestListAdapter.OnItemClickListener() {
            @Override
            public void onItemClick() {
                Toast.makeText(MainActivity.this, "点击了", Toast.LENGTH_SHORT).show();
            }
        });
        for (int i = 0; i < 20; i++) {
            dataList.add(new TestDataModel(i + ""));
        }

        testListAdapter.setDataList(dataList);
    }
}
