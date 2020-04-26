package com.yxb.my_mvp_rxjava_retrofit;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.yxb.my_mvp_rxjava_retrofit.bean.TestBean;
import com.yxb.my_mvp_rxjava_retrofit.retrofit.Api;

import java.util.List;
import java.util.logging.Logger;

/**
 * Created by yuxibing on 2018/10/11.
 * 描述：
 */

public class TestAdapter extends RecyclerView.Adapter<TestAdapter.viewHolder> {

    private List<TestBean.StoriesBean> list;
    private Context context;
    private onItemClickListeter mListener;

    public interface onItemClickListeter {
        void onClick();
    }

    public void setOnItemClickListeter(onItemClickListeter listeter) {
        this.mListener = listeter;
    }

    public TestAdapter(List<TestBean.StoriesBean> list) {
        this.list = list;
    }

    @Override
    public viewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        context = parent.getContext();
        return new viewHolder(LayoutInflater.from(context).inflate(R.layout.item_test, parent, false));
    }

    @Override
    public void onBindViewHolder(viewHolder holder, int position) {
        Log.e("onBindViewHolder--->", position + "");
        Glide.with(context).load(list.get(position).getImages().get(0)).into(holder.imageView);
        holder.textView.setText(list.get(position).getTitle());
        holder.textView1.setText("2017-9-7 12:00");
    }

    @Override
    public void onBindViewHolder(@NonNull viewHolder holder, int position, @NonNull List<Object> payloads) {
        Log.e("payloads--->", position+"");
        if (payloads.isEmpty()) {
            onBindViewHolder(holder, position);
        } else {
            Log.e("payloads--->", position+"--->"+payloads.size()+"--->"+payloads.get(0).toString());
            for (Object p : payloads) {
                int payload = (int) p;
                if (payload == 3) {
                    holder.textView1.setText("我变了");

                }
            }
        }
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class viewHolder extends RecyclerView.ViewHolder {

        private ImageView imageView;
        private TextView textView, textView1;

        public viewHolder(View itemView) {
            super(itemView);
            imageView = (ImageView) itemView.findViewById(R.id.image);
            textView = (TextView) itemView.findViewById(R.id.text);
            textView1 = (TextView) itemView.findViewById(R.id.text1);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (mListener != null) {
                        mListener.onClick();
                    }
                }
            });
        }
    }
}

