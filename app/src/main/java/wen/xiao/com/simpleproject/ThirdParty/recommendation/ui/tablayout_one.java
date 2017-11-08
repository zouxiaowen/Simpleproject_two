package wen.xiao.com.simpleproject.ThirdParty.recommendation.ui;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bigkoo.convenientbanner.ConvenientBanner;
import com.bigkoo.convenientbanner.holder.Holder;
import com.youth.banner.Banner;
import com.yyydjk.library.BannerLayout;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

import wen.xiao.com.simpleproject.Base_z.BaseFragment_lanjiazai;
import wen.xiao.com.simpleproject.Common.entity.meizitu;
import wen.xiao.com.simpleproject.R;
import wen.xiao.com.simpleproject.ThirdParty.recommendation.presenter.Contract.RecommendContract;
import wen.xiao.com.simpleproject.ThirdParty.recommendation.presenter.RecommendPresenter;
import wen.xiao.com.simpleproject.Utils_z.GlideImageLoaders;
import wen.xiao.com.simpleproject.View_z.simpleview.StatusViewLayout;
import wen.xiao.com.simpleproject.entity.MessageEvent;
import wen.xiao.com.simpleproject.imageloader.GlideImageLoader;

/**
 * Created by xiaowen on 2017/5/28.
 */

public class tablayout_one extends BaseFragment_lanjiazai implements SwipeRefreshLayout.OnRefreshListener ,RecommendContract.View{
    private StatusViewLayout layout_one;
    private View view;
    //BannerLayout bannerLayout;
    Banner banner;
    private SwipeRefreshLayout mSwipeRefreshLayout;
    private RecommendPresenter presenter;
    private boolean asd;

    @Override
    protected View initViews(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.layout_tablayout_one, container, false);
        layout_one = (StatusViewLayout) view.findViewById(R.id.layout_one);
        banner = (Banner) view.findViewById(R.id.banner);
        mSwipeRefreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.swipeLayout);
        return view;
    }

    @Override
    protected void initData() {
        presenter=new RecommendPresenter(this);
        presenter.Banner();
        mSwipeRefreshLayout.setOnRefreshListener(this);
        mSwipeRefreshLayout.setColorSchemeColors(Color.rgb(47, 223, 189));
        layout_one.showContent();

    }

    @Override
    protected void showY() {
        Log.d("xiaowen","one"+"showY");
        if (banner!=null){
            banner.stopAutoPlay();
            mSwipeRefreshLayout.setRefreshing(false);
        }
    }

    @Override
    protected void showX() {
        Log.d("xiaowen","one"+"showX");
        if (banner!=null)
        banner.startAutoPlay();
    }


    @Override
    public void onRefresh() {

         new Handler().postDelayed(new Runnable() {

            public void run() {

                //execute the task
                mSwipeRefreshLayout.setRefreshing(false);
            }

        }, 5000);
    }

    @Override
    public void showData(List<meizitu.ResultsBean> list) {

    }

    @Override
    public void showLoad() {

    }

    @Override
    public void showErroy() {

    }

    @Override
    public void showNull() {

    }

    @Override
    public void dissMiss() {

    }

    @Override
    public void onError(Exception mssage) {

    }



    @Override
    public void ShowBanner(List<String> list) {
        //设置图片加载器
        banner.setImageLoader(new GlideImageLoaders());
        //设置图片集合
        banner.setImages(list);
        banner.start();
    }



}
