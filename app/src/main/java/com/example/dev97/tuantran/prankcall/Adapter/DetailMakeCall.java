package com.example.dev97.tuantran.prankcall.Adapter;

import android.support.annotation.Nullable;
import android.view.Display;
import android.widget.ImageView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.dev97.tuantran.prankcall.ImageHelper;
import com.example.dev97.tuantran.prankcall.Models.ModelDetailMakeCall;
import com.example.dev97.tuantran.prankcall.R;

import java.util.ArrayList;
import java.util.List;

public class DetailMakeCall extends BaseQuickAdapter<ModelDetailMakeCall,BaseViewHolder>{
    List<ModelDetailMakeCall> list = new ArrayList<>();
    public DetailMakeCall(@Nullable List<ModelDetailMakeCall> data) {
        super(R.layout.item_make_call,data);
        list = data;
    }

    @Override
    protected void convert(BaseViewHolder helper, ModelDetailMakeCall item) {
        ImageView icon = helper.getView(R.id.icon_1);
        ImageHelper.load(mContext,icon,item.getImage());
        helper.setText(R.id.tv_title_item,item.getTitle());
        helper.setText(R.id.tv_detail_item,item.getSub());
    }
}
