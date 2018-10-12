package com.example.dev97.tuantran.prankcall.Adapter;

import android.support.annotation.Nullable;
import android.widget.ImageView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.dev97.tuantran.prankcall.ImageHelper;
import com.example.dev97.tuantran.prankcall.Models.ModelMain;
import com.example.dev97.tuantran.prankcall.R;

import java.util.ArrayList;
import java.util.List;


public class AdapterMain extends BaseQuickAdapter<ModelMain,BaseViewHolder> {
    List<ModelMain> list = new ArrayList<>();
    public AdapterMain(@Nullable List<ModelMain> data) {
        super(R.layout.item_option_choose,data);
        list = data;
    }

    @Override
    protected void convert(BaseViewHolder helper, ModelMain item) {
        ImageView icon = helper.getView(R.id.img_header);
        ImageHelper.load(mContext,icon,item.getImage());
        helper.setText(R.id.tv_header,item.getContent());
    }
}
