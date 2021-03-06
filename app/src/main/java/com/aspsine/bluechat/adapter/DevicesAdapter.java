package com.aspsine.bluechat.adapter;

import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.aspsine.bluechat.R;
import com.aspsine.bluechat.listener.OnItemClickListener;
import com.aspsine.bluechat.listener.OnItemLongClickListener;
import com.aspsine.bluechat.model.Device;

import java.util.List;

/**
 * Created by littlexi on 2015/1/30.
 */
public class DevicesAdapter extends RecyclerView.Adapter<DevicesAdapter.ViewHolder> {

    private List<Device> mDevices;

    private OnItemClickListener mOnItemClickListener;

    private OnItemLongClickListener mOnItemLongClickListener;

    public DevicesAdapter(List<Device> devices) {

        mDevices = devices;
    }

    public void setOnItemClickListener(final OnItemClickListener listener) {

        mOnItemClickListener = listener;
    }

    public void setOnItemLongClickListener(final OnItemLongClickListener listener) {
        mOnItemLongClickListener = listener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {

        View itemView = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_device, viewGroup, false);
        return new ViewHolder(itemView);
    }


    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int i) {

        Device device = mDevices.get(i);
        if (!TextUtils.isEmpty(device.name)) viewHolder.tvName.setText(device.name);
    }

    @Override
    public int getItemCount() {

        return mDevices == null ? 0 : mDevices.size();
    }

    public void remove(int position) {
        mDevices.remove(position);
        notifyItemRemoved(position);
    }

    public void add(Device device, int position) {
        mDevices.add(device);
        notifyItemInserted(position);
    }

    public class ViewHolder extends RecyclerView.ViewHolder
            implements View.OnClickListener, View.OnLongClickListener {
        public ImageView ivAvatar;
        public TextView tvName;

        public ViewHolder(View itemView) {
            super(itemView);
            tvName = (TextView) itemView.findViewById(R.id.tvName);
            ivAvatar = (ImageView) itemView.findViewById(R.id.ivAvatar);
            itemView.setOnClickListener(this);
            ivAvatar.setOnClickListener(this);
            itemView.setOnLongClickListener(this);
        }

        @Override
        public void onClick(View v) {
            if (mOnItemClickListener == null) return;
            mOnItemClickListener.onItemClick(getPosition(), v);
        }

        @Override
        public boolean onLongClick(View v) {
            if (mOnItemLongClickListener == null) return false;
            mOnItemLongClickListener.onItemLongClick(getPosition(), v);
            return true;
        }
    }


}
