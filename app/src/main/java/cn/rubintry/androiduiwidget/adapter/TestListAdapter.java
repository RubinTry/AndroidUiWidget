package cn.rubintry.androiduiwidget.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import cn.rubintry.androiduiwidget.R;
import cn.rubintry.androiduiwidget.model.TestDataModel;

/**
 * @author logcat
 */
public class TestListAdapter extends RecyclerView.Adapter<TestListAdapter.TestViewHolder> {

    private List<TestDataModel> dataList;
    private OnItemClickListener onItemClickListener;

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    public TestListAdapter(List<TestDataModel> dataList) {
        this.dataList = dataList;
    }

    public void setDataList(List<TestDataModel> dataList) {
        this.dataList = dataList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public TestViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_test , parent , false);
        TestViewHolder testViewHolder = new TestViewHolder(view);
        return testViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull TestViewHolder holder, int position) {
        TestDataModel item = dataList.get(position);
        holder.tvTestData.setText(item.getData());
        holder.llContainer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(onItemClickListener != null){
                    onItemClickListener.onItemClick();
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }

    class TestViewHolder extends RecyclerView.ViewHolder {
        private TextView tvTestData;
        private LinearLayout llContainer;
        public TestViewHolder(@NonNull View itemView) {
            super(itemView);
            tvTestData = itemView.findViewById(R.id.tvTestData);
            llContainer = itemView.findViewById(R.id.llContainer);
        }
    }


    public interface OnItemClickListener{
        void onItemClick();
    }
}
