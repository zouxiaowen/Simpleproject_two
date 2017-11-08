package wen.xiao.com.simpleproject.ThirdParty;


import android.content.res.Resources;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;

import org.greenrobot.eventbus.EventBus;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import wen.xiao.com.simpleproject.Base_z.BaseFragment;
import wen.xiao.com.simpleproject.ThirdParty.rankin.tablayout_for;
import wen.xiao.com.simpleproject.ThirdParty.recommendation.ui.tablayout_one;
import wen.xiao.com.simpleproject.ThirdParty.hoststation.tablayout_three;
import wen.xiao.com.simpleproject.R;
import wen.xiao.com.simpleproject.ThirdParty.song.tablayout_two;
import wen.xiao.com.simpleproject.entity.MessageEvent;


public class two_fragment extends BaseFragment {
    private TabLayout tabLayout;
    private ViewPager viewPager;

    private List<Fragment> list;
    private MyAdapter adapter;
    private String[] titles = {"个性推荐", "歌单", "主播电台","排行榜"};
    private LinearLayout liner;


   
    @Override
    protected View initView() {
        View view=LayoutInflater.from(getActivity()).inflate(R.layout.two, null);
        viewPager = (ViewPager) view.findViewById(R.id.viewpager);
        tabLayout = (TabLayout) view.findViewById(R.id.tablayout);
        liner= (LinearLayout) view.findViewById(R.id.liner);
        initState(liner);

        return view;
    }



    @Override
    protected void initData() {
        
        //页面，数据源
        list = new ArrayList<>();
        list.add(new tablayout_one());
        list.add(new tablayout_two());
        list.add(new tablayout_three());
        list.add(new tablayout_for());
        //ViewPager的适配器
//        getActivity().getSupportFragmentManager()
        adapter = new MyAdapter(getChildFragmentManager());
        viewPager.setAdapter(adapter);
        //绑定
        tabLayout.setupWithViewPager(viewPager);

        viewPager.setOffscreenPageLimit(3);
       
    }
    MessageEvent me;
    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        if (me==null){
            me = new MessageEvent();
        }
        if (hidden){
            Log.d("===","onHiddenChanged"+":"+hidden);

            me.setIsok(true);
            EventBus.getDefault().post(me);
        }else {
            me.setIsok(false);
            EventBus.getDefault().post(me);
            Log.d("===","onHiddenChanged"+":"+hidden);
        }
    }
    class MyAdapter extends FragmentPagerAdapter {

        public MyAdapter(FragmentManager fm) {
            super(fm);
        }
        @Override
        public Fragment getItem(int position) {
            return list.get(position);
        }

        @Override
        public int getCount() {
            return list.size();
        }

        //重写这个方法，将设置每个Tab的标题
        @Override
        public CharSequence getPageTitle(int position) {
            
            return titles[position];
        }
    }
}
