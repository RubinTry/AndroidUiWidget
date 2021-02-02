package cn.rubintry.dialog.ios.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import java.lang.ref.WeakReference;
import java.util.List;

import cn.rubintry.dialog.R;

/**
 * @author logcat
 */
public class BottomListAdapter extends RecyclerView.Adapter<BottomListAdapter.BottomListViewHolder> {

    private List<String> dataList;
    private OnItemClickListener onItemClickListener;
    private Context context;

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    public BottomListAdapter(Context context , List<String> dataList) {
        this.dataList = dataList;
        this.context = context.getApplicationContext();
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
//        if(position == 0){
//            holder.vBaseLine.setVisibility(View.VISIBLE);
//            holder.tvIosBottomItem.setBackground(ContextCompat.getDrawable(context , R.drawable.bg_ripple_ios_bottom_list_item_top));
//        }else if(position == dataList.size() - 1){
//            holder.vBaseLine.setVisibility(View.GONE);
//            holder.tvIosBottomItem.setBackground(ContextCompat.getDrawable(context , R.drawable.bg_ripple_ios_bottom_list_item_bottom));
//        }else{
//            holder.vBaseLine.setVisibility(View.VISIBLE);
//            holder.tvIosBottomItem.setBackground(ContextCompat.getDrawable(context , R.drawable.bg_ripple_ios_bottom_list_item));
//        }
        holder.tvIosBottomItem.setBackground(ContextCompat.getDrawable(context , R.drawable.bg_ripple_ios_bottom_list_item));
        holder.tvIosBottomItem.setText(dataList.get(position));
        holder.tvIosBottomItem.setOnClickListener(new View.OnClickListener() {
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

    class BottomListViewHolder extends RecyclerView.ViewHolder {
        TextView tvIosBottomItem;
//        View vBaseLine;
        public BottomListViewHolder(@NonNull View itemView) {
            super(itemView);
            tvIosBottomItem = itemView.findViewById(R.id.tvIosBottomItem);
//            vBaseLine = itemView.findViewById(R.id.vBaseLine);
        }
    }

    public interface OnItemClickListener{

        /**
         * item点击回调
         * @param content  文本内容
         * @param position  点击位置
         */
        void onItemClick(String content , int position);
    }
}
