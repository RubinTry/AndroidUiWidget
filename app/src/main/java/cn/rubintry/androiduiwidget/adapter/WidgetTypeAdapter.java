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

/**
 * @author logcat
 * 控件类型列表适配器
 */
public class WidgetTypeAdapter extends RecyclerView.Adapter<WidgetTypeAdapter.TypeViewHolder> {

    private List<String> dataList;
    private OnItemClickListener onItemClickListener;

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    public WidgetTypeAdapter(List<String> dataList) {
        this.dataList = dataList;
    }

    public void setData(List<String> dataList) {
        this.dataList = dataList;
    }

    @NonNull
    @Override
    public TypeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_widget_type_list , parent , false);
        TypeViewHolder typeViewHolder = new TypeViewHolder(view);
        return typeViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull TypeViewHolder holder, int position) {
        holder.tvWidgetTypeData.setText(dataList.get(position));
        holder.tvWidgetTypeData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(onItemClickListener != null){
                    onItemClickListener.onItemClick(dataList.get(position) , position);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }

    public class TypeViewHolder extends RecyclerView.ViewHolder {
        TextView tvWidgetTypeData;
        public TypeViewHolder(@NonNull View itemView) {
            super(itemView);
            tvWidgetTypeData = itemView.findViewById(R.id.tvWidgetTypeData);
        }
    }

    public interface OnItemClickListener{
        void onItemClick(String data , int position);
    }
}
