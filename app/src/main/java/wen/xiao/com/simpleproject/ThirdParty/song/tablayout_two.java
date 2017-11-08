package wen.xiao.com.simpleproject.ThirdParty.song;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import wen.xiao.com.simpleproject.Base_z.BaseFragment_lanjiazai;
import wen.xiao.com.simpleproject.Base_z.LazyFragment;
import wen.xiao.com.simpleproject.R;
import wen.xiao.com.simpleproject.View_z.simpleview.StatusViewLayout;

/**
 * Created by xiaowen on 2017/5/28.
 */

public class tablayout_two extends BaseFragment_lanjiazai {
    private StatusViewLayout layout_one;
    private View view;
    private TextView tv_one;
    @Override
    protected View initViews(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view=inflater.inflate(R.layout.layout_tablayout_two,container,false);
        layout_one= (StatusViewLayout) view.findViewById(R.id.layout_one);
        tv_one= (TextView) view.findViewById(R.id.tv_one);
        return view;
    }

    @Override
    protected void initData() {
        tv_one.setText("two 加载完毕");
        layout_one.showContent();
    }

    @Override
    protected void showY() {

    }

    @Override
    protected void showX() {

    }

    @Override
    protected void onInvisible() {

    }





}
