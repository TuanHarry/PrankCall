package com.example.dev97.tuantran.prankcall.Adapter;

import android.support.annotation.Nullable;
import android.widget.ImageView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.dev97.tuantran.prankcall.ImageHelper;
import com.example.dev97.tuantran.prankcall.Models.ModelDetailCall;
import com.example.dev97.tuantran.prankcall.R;

import java.util.ArrayList;
import java.util.List;

public class AdapterDetailCall extends BaseQuickAdapter<ModelDetailCall,BaseViewHolder> {
    List<ModelDetailCall> list = new ArrayList<>();
    public AdapterDetailCall(@Nullable List<ModelDetailCall> data) {
        super(R.layout.item_grid_single,data);
        list = data;
    }

    @Override
    protected void convert(BaseViewHolder helper, ModelDetailCall item) {
        helper.setText(R.id.grid_text,item.getText());
        ImageView icon = helper.getView(R.id.grid_image);
        ImageHelper.load(mContext,icon,item.getImage());
    }
}
