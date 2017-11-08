package wen.xiao.com.simpleproject;

import android.content.Intent;
import android.os.Build;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.WindowManager;
import android.widget.RadioGroup;
import android.widget.Toast;


import java.util.ArrayList;
import java.util.List;

import wen.xiao.com.simpleproject.Base_z.BaseFragment;
import wen.xiao.com.simpleproject.Common.ui.CommonFrameFragment;
import wen.xiao.com.simpleproject.Fragment_z.CustomFragment;
import wen.xiao.com.simpleproject.Fragment_z.OtherFragment;
import wen.xiao.com.simpleproject.ThirdParty.two_fragment;

public class MainActivity extends FragmentActivity {
    private RadioGroup mRg_main;

    private List<BaseFragment> mBaseFragment;

    /**
     * 选中的Fragment的对应的位置
     */
    private int position;

    /**
     * 上次切换的Fragment
     */
    private Fragment mContent;
   // private TextView text;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

         //immersion();
       // text= (TextView) findViewById(R.id.text);
       // initState();
        // 初始化View
        initView();
        // 初始化Fragment
        initFragment();
        // 设置RadioGroup的监听
        setListener();

    }

    /**
     * 当用户手机大于API19以及以上版本中才能够做到。
     */
    private void immersion() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            //透明状态栏
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            //透明导航栏
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
        }
    }

    private void setListener() {
        mRg_main.setOnCheckedChangeListener(new MyOnCheckedChangeListener());
        // 设置默认选中常用框架
        mRg_main.check(R.id.rb_common_frame);
    }

    class MyOnCheckedChangeListener implements
            RadioGroup.OnCheckedChangeListener {

        @Override
        public void onCheckedChanged(RadioGroup group, int checkedId) {
            switch (checkedId) {
                case R.id.rb_common_frame:// 常用框架
                    position = 0;
                    //text.setText("常用框架");
                    break;
                case R.id.rb_thirdparty:// 第三方
                    position = 1;
                   // text.setText("第三方");
                    break;
                case R.id.rb_custom:// 自定义
                    position = 2;
                    //text.setText("自定义");
                    break;
                case R.id.rb_other:// 其他
                    //text.setText("其他");
                    position = 3;
                    break;
                default:
                    position = 0;
                    break;
            }

            // 根据位置得到对应的Fragment
            BaseFragment to = getFragment();
            // 替换
            switchFrament(mContent, to);

        }
    }

    /**
     * @param from 刚显示的Fragment,马上就要被隐藏了
     * @param to   马上要切换到的Fragment，一会要显示
     */
    private void switchFrament(Fragment from, Fragment to) {
        if (from != to) {
            mContent = to;
            FragmentTransaction ft = getSupportFragmentManager()
                    .beginTransaction();
            // 才切换
            // 判断有没有被添加
            if (!to.isAdded()) {
                // to没有被添加
                // from隐藏
                if (from != null) {
                    ft.hide(from);
                }
                // 添加to
                if (to != null) {
                    ft.add(R.id.fl_content, to).commit();
                }
            } else {
                // to已经被添加
                // from隐藏
                if (from != null) {
                    ft.hide(from);
                }
                // 显示to
                if (to != null) {
                    ft.show(to).commit();
                }
            }
        }

    }

    /**
     * 根据位置得到对应的Fragment
     *
     * @return
     */
    private BaseFragment getFragment() {
        BaseFragment fragment = mBaseFragment.get(position);
        return fragment;
    }

    private void initFragment() {
        mBaseFragment = new ArrayList< >();
        mBaseFragment.add(new CommonFrameFragment());// 常用框架Fragment
        //mBaseFragment.add(new ThirdPartyFragment());// 第三方Fragment
        mBaseFragment.add(new two_fragment());
        mBaseFragment.add(new CustomFragment());// 自定义控件Fragment
        mBaseFragment.add(new OtherFragment());// 其他Fragment
    }

    private void initView() {

        mRg_main = (RadioGroup) findViewById(R.id.rg_bottom_tag);

    }


    private long mExitTime = 0;

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && event.getRepeatCount() == 0) {

            exit();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    public void exit() {
        if ((System.currentTimeMillis() - mExitTime) > 2000) {
            Toast.makeText(MainActivity.this, "再按一次退出App", Toast.LENGTH_SHORT).show();
            mExitTime = System.currentTimeMillis();
            System.gc();
        } else {

            finish();
            System.exit(0);
        }
    }

//    @Override
//    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
////        BaseFragment fragment = mBaseFragment.get(position);
//        Fragment fragment=mBaseFragment.get(3);
//        if (fragment!=null){
//            fragment.onActivityResult(requestCode,resultCode,data);
//        }
//    }
}
