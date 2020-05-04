package cn.rubintry.androiduiwidget.view.elastic;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.widget.NestedScrollView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import cn.rubintry.androiduiwidget.R;
import cn.rubintry.androiduiwidget.adapter.ElasticDataAdapter;
import cn.rubintry.androiduiwidget.base.BaseActivity;
import cn.rubintry.widget.elastic.ElasticView;
import cn.rubintry.widget.elastic.OnReadyPullListener;

/**
 * @author logcat
 * 弹性View页面
 */
public class ElasticActivity extends BaseActivity {


    @BindView(R.id.nslContent)
    NestedScrollView nslContent;
    @BindView(R.id.imgHeader)
    ImageView imgHeader;
    @BindView(R.id.esvContainer)
    ElasticView esvContainer;
    @BindView(R.id.rvElasticData)
    RecyclerView rvElasticData;
    private ElasticDataAdapter elasticDataAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        esvContainer.setHeader(imgHeader)
        .setOnReadyPullListener(new OnReadyPullListener() {
            @Override
            public boolean isReady() {
                return nslContent.getScrollY() == 0;
            }
        });

        initRecycler();
    }

    private void initRecycler() {
        List<String> elasticData = new ArrayList<>();
        rvElasticData.setLayoutManager(new LinearLayoutManager(this));
        rvElasticData.setNestedScrollingEnabled(false);
        for (int i = 0; i < 30; i++) {
            elasticData.add("data" + i);
        }
        elasticDataAdapter = new ElasticDataAdapter(elasticData);
        rvElasticData.setAdapter(elasticDataAdapter);
    }

    @Override
    protected int setLayout() {
        return R.layout.activity_elastic_activity;
    }
}
