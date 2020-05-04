package cn.rubintry.androiduiwidget.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import cn.rubintry.androiduiwidget.R;

public class ElasticDataAdapter extends RecyclerView.Adapter<ElasticDataAdapter.ElasticViewHolder> {

    private List<String> dataList;

    public ElasticDataAdapter(List<String> dataList) {
        this.dataList = dataList;
    }


    public void setData(List<String> dataList) {
        this.dataList = dataList;
    }

    @NonNull
    @Override
    public ElasticViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_widget_elastic_data , parent , false);
        ElasticViewHolder elasticViewHolder = new ElasticViewHolder(view);
        return elasticViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ElasticViewHolder holder, int position) {
        holder.tvWidgetElasticData.setText(dataList.get(position));
    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }

    public class ElasticViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.tvWidgetElasticData)
        TextView tvWidgetElasticData;
        public ElasticViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this , itemView);
        }
    }
}
