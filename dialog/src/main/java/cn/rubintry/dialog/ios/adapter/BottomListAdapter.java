package cn.rubintry.dialog.ios.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import cn.rubintry.dialog.R;

public class BottomListAdapter extends RecyclerView.Adapter<BottomListAdapter.BottomListViewHolder> {

    private List<String> dataList;
    private OnItemClickListener onItemClickListener;

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    public BottomListAdapter(List<String> dataList) {
        this.dataList = dataList;
    }

    public void setData(List<String> dataList) {
        this.dataList = dataList;
    }

    @NonNull
    @Override
    public BottomListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_ios_bottom_list_dialog , parent , false);
        BottomListViewHolder bottomListViewHolder = new BottomListViewHolder(view);
        return bottomListViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull BottomListViewHolder holder, final int position) {
        holder.tvIosBottomItem.setText(dataList.get(position));
        holder.tvIosBottomItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(onItemClickListener != null){
                    onItemClickListener.onItemClick(dataList.get(position));
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }

    class BottomListViewHolder extends RecyclerView.ViewHolder {
        TextView tvIosBottomItem;
        public BottomListViewHolder(@NonNull View itemView) {
            super(itemView);
            tvIosBottomItem = itemView.findViewById(R.id.tvIosBottomItem);
        }
    }

    public interface OnItemClickListener{

        /**
         * item点击回调
         * @param content
         */
        void onItemClick(String content);
    }
}
