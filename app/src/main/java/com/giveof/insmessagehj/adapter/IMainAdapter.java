package com.giveof.insmessagehj.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.giveof.insmessagehj.R;
import com.giveof.insmessagehj.entity.Contract;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Time on 16/10/10.
 */
public class IMainAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private static final int IMAIN_ITEM = 0;

    private static final int MISS_ITEM = 1;


    private Context context;

    private List<Contract> mData;

    int type = 0;

    public IMainAdapter(Context context) {
        this.context = context;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        viewType = type;
        if (viewType == IMAIN_ITEM) {
            return new MyViewHolder(LayoutInflater.from(context).inflate(R.layout.list_item, parent, false));
        } else {
            return new MissediewHolder(LayoutInflater.from(context).inflate(R.layout.missed_calls_item, parent, false));
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof MyViewHolder){
            MyViewHolder myholder = (MyViewHolder) holder;
            myholder.contact.setText(mData.get(position).getName());
        }else {
            MissediewHolder missediewHolder = (MissediewHolder) holder;
        }

    }

    public void getData(List<Contract> mData) {
        this.mData = mData;
    }

    public void setItemViewType(int type){
        this.type = type;
    }

    @Override
    public int getItemCount() {
        return 50;
    }

    @Override
    public int getItemViewType(int position) {
        return super.getItemViewType(position);

    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        @Bind(R.id.contact)
        TextView contact;

        public MyViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);

        }
    }

    class MissediewHolder extends RecyclerView.ViewHolder {
        @Bind(R.id.contact)
        TextView contact;
        @Bind(R.id.time)
        TextView time;
        @Bind(R.id.linearlayout)
        LinearLayout linearlayout;

        public MissediewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);

        }
    }
}
