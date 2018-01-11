package wen.xiao.com.simpleproject.Common.ui;


import android.content.Intent;
import android.graphics.Color;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.iflytek.cloud.SpeechConstant;
import com.iflytek.cloud.SpeechUtility;
import com.lzy.okgo.OkGo;
import java.util.ArrayList;
import java.util.List;
import wen.xiao.com.simpleproject.Adapter_z.MyAdpter;
import wen.xiao.com.simpleproject.Base_z.BaseFragment;
import wen.xiao.com.simpleproject.Common.entity.meizitu;
import wen.xiao.com.simpleproject.Common.presenter.Contract.RecommendContract;
import wen.xiao.com.simpleproject.Common.presenter.RecommendPresenter;
import wen.xiao.com.simpleproject.R;
import wen.xiao.com.simpleproject.View_z.SearviewActivity;
import wen.xiao.com.simpleproject.preview.ImageDetailType;


public class CommonFrameFragment extends BaseFragment implements SwipeRefreshLayout.OnRefreshListener, BaseQuickAdapter.RequestLoadMoreListener, View.OnClickListener, RecommendContract.View {
    private RecyclerView recycler;
    private SwipeRefreshLayout mSwipeRefreshLayout;
    private int mCurrentCounter = 1;
    private MyAdpter myAdpter;
    RecommendContract.Presenter presenter;
    private ImageView search;
    private TextView text;

    /**
     * 传图片地址
     */
    public static final String KEY_IMG_PATH = "infoList";

    @Override
    protected View initView() {
        View view = LayoutInflater.from(mContext).inflate(R.layout.common, null);
        recycler = (RecyclerView) view.findViewById(R.id.rv_list);
        text = (TextView) view.findViewById(R.id.text);
        search = (ImageView) view.findViewById(R.id.search);
        search.setOnClickListener(this);
        text.setOnClickListener(this);
        LinearLayout ll_bar = (LinearLayout) view.findViewById(R.id.ll_bar);
        initState(ll_bar);
        mSwipeRefreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.swipeLayout);
        return view;
    }

    @Override
    protected void initData() {
        super.initData();
        SpeechUtility.createUtility(mContext, SpeechConstant.APPID + "=581405a0");
        mSwipeRefreshLayout.setOnRefreshListener(this);
        mSwipeRefreshLayout.setColorSchemeColors(Color.rgb(47, 223, 189));
        recycler.setLayoutManager(new LinearLayoutManager(getActivity()));//设置布局管理器
        presenter = new RecommendPresenter(this);
        presenter.requestData("http://gank.io/api/data/%E7%A6%8F%E5%88%A9/10/", mCurrentCounter, getActivity());
        myAdpter = new MyAdpter(getActivity(), new ArrayList<meizitu.ResultsBean>());
        myAdpter.openLoadAnimation(BaseQuickAdapter.SLIDEIN_LEFT);//开启动画
        myAdpter.setOnLoadMoreListener(CommonFrameFragment.this, recycler);
        recycler.setAdapter(myAdpter);
        //设置item之间的间隔
        AdpterOnclick();
    }
    /*item 点击事件*/
    private void AdpterOnclick() {
        myAdpter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                meizitu.ResultsBean data= (meizitu.ResultsBean) adapter.getItem(position);
                Intent intent = new Intent();
                intent.setClass(getActivity(), ImageDetailType.class);
                intent.putExtra(KEY_IMG_PATH, data.getUrl());
                startActivity(intent);
            }
        });


    }

    /*下拉刷新*/
    @Override
    public void onRefresh() {
        mSwipeRefreshLayout.setRefreshing(false);
        presenter.requestData("http://gank.io/api/data/%E7%A6%8F%E5%88%A9/10/", mCurrentCounter, getActivity());
        myAdpter.getData().clear();
        myAdpter.notifyDataSetChanged();
    }

    /**
     * 上拉加载
     */
    @Override
    public void onLoadMoreRequested() {
        if (mCurrentCounter <= 51) {
            presenter.requestData("http://gank.io/api/data/%E7%A6%8F%E5%88%A9/10/", mCurrentCounter++, getActivity());
        }
    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        if (!hidden) {
            mSwipeRefreshLayout.setRefreshing(false);
            OkGo.getInstance().cancelTag(this);
        } else {

        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        OkGo.getInstance().cancelTag(this);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.sousuo:
                break;
            case R.id.search:
                presenter.SpeechRecognition(mContext);
                break;
            case R.id.text:
                Intent intent = new Intent();
                intent.setClass(getActivity(), SearviewActivity.class);
                startActivity(intent);
                break;
        }
    }


    @Override
    public void showData(List<meizitu.ResultsBean> list) {
        myAdpter.addData(list);
        myAdpter.loadMoreComplete();
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
        Log.d("===", mssage.toString());
        if (myAdpter!=null){
            myAdpter.loadMoreFail();//加载失败
        }
    }

    @Override
    public void showSpeechRecognition(String sp) {
        text.setText(sp);
    }


}



	 

    

   

    


