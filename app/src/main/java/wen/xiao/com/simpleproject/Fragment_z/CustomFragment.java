package wen.xiao.com.simpleproject.Fragment_z;


import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.v7.widget.Toolbar;
import android.transition.ArcMotion;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.view.animation.Interpolator;
import android.widget.Button;
import android.widget.ImageView;

import com.awen.photo.Awen;
import com.awen.photo.photopick.controller.PhotoPagerConfig;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.drawable.GlideDrawable;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;


import java.util.ArrayList;

import jp.wasabeef.glide.transformations.BlurTransformation;
import wen.xiao.com.simpleproject.Base_z.BaseFragment;
import wen.xiao.com.simpleproject.Imag_kan.ImagePagerActivity;
import wen.xiao.com.simpleproject.R;
import wen.xiao.com.simpleproject.Utils_z.CommonUtils;
import wen.xiao.com.simpleproject.Utils_z.CustomChangeBounds;
import wen.xiao.com.simpleproject.Utils_z.StatusBarUtil;
import wen.xiao.com.simpleproject.View_z.simpleview.MyNestedScrollView;
import wen.xiao.com.simpleproject.preview.ImageDetail;

public class CustomFragment extends BaseFragment implements View.OnClickListener {
    String ima_url = "http://img1.imgtn.bdimg.com/it/u=3208352844,3803539103&fm=23&gp=0.jpg";
    /**
     * 传图片地址 逗号分隔
     */
    public static final String KEY_IMG_PATH = "infoList";
    /**
     * 当前显示item
     */
    public static final String KEY_ITEM_NUM = "itemNum";
    // 这个是高斯图背景的高度
    private int imageBgHeight;
    // 在多大范围内变色
    private int slidingDistance;
    private ImageView iv_one_photo,img_item_bg,iv_title_head_bg;
    //private TextView tv_txt;
    // private RecyclerView xrv_list;
    private Toolbar title_tool_bar;
    private MyNestedScrollView nsv_scrollview;
    private Button but_kan,but_kan2;
    @Override
    protected View initView() {
         
      View view= LayoutInflater.from(mContext).inflate(R.layout.custom, null);
        nsv_scrollview= (MyNestedScrollView) view.findViewById(R.id.nsv_scrollview);
        iv_one_photo= (ImageView)view. findViewById(R.id.iv_one_photo);  //图片
        iv_title_head_bg= (ImageView) view.findViewById(R.id.iv_title_head_bg);
        img_item_bg= (ImageView) view.findViewById(R.id.img_item_bg);     //背景图
        //tv_txt= (TextView) findViewById(R.id.tv_txt);
        //xrv_list= (RecyclerView) findViewById(R.id.xrv_list);
        title_tool_bar= (Toolbar) view.findViewById(R.id.title_tool_bar);
        but_kan= (Button) view.findViewById(R.id.but_kan);
        but_kan.setOnClickListener(this);
        but_kan2= (Button) view.findViewById(R.id.but_kan2);
        but_kan2.setOnClickListener(this);
        setMotion();
        setTitleBar();
        setPicture();
        initSlideShapeTheme();
        setText();

        return view;
    }

    /**
     * 设置自定义 Shared Element切换动画
     */
    private void setMotion() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            //定义ArcMotion
            ArcMotion arcMotion = new ArcMotion();
            arcMotion.setMinimumHorizontalAngle(50f);
            arcMotion.setMinimumVerticalAngle(50f);
            //插值器，控制速度
            Interpolator interpolator = AnimationUtils.loadInterpolator(getActivity(), android.R.interpolator.fast_out_slow_in);

            //实例化自定义的ChangeBounds
            CustomChangeBounds changeBounds = new CustomChangeBounds();
            changeBounds.setPathMotion(arcMotion);
            changeBounds.setInterpolator(interpolator);
            changeBounds.addTarget(iv_one_photo);
            //将切换动画应用到当前的Activity的进入和返回
            getActivity().getWindow().setSharedElementEnterTransition(changeBounds);
            getActivity().getWindow().setSharedElementReturnTransition(changeBounds);
        }
    }


    /**
     * 高斯背景图和一般图片
     */
    private void setPicture() {
        Glide.with(this)
                .load(ima_url)
                .override((int) CommonUtils.getDimens(R.dimen.movie_detail_width), (int) CommonUtils.getDimens(R.dimen.movie_detail_height))
                .into(iv_one_photo);

        // "14":模糊度；"3":图片缩放3倍后再进行模糊
        Glide.with(this)
                .load(ima_url)
                .error(R.drawable.stackblur_default)
                .placeholder(R.drawable.stackblur_default)
                .crossFade(500)
                .bitmapTransform(new BlurTransformation(getActivity(), 14, 3))
                .into(img_item_bg);
    }
    /**
     * 显示文本
     */
    private void setText() {
        //tv_txt.setVisibility(View.VISIBLE);
        //xrv_list.setVisibility(View.GONE);
    }
    /**
     * toolbar设置
     */
    private void setTitleBar() {
//        getActivity().setSupportActionBar(title_tool_bar);
//        ActionBar actionBar = getSupportActionBar();
//        if (actionBar != null) {
//            //去除默认Title显示
//            actionBar.setDisplayShowTitleEnabled(false);
//            actionBar.setDisplayHomeAsUpEnabled(true);
//            actionBar.setHomeAsUpIndicator(R.drawable.back);
//
//        }
//        title_tool_bar.setTitle("爱情如彩虹一般绚丽");
//        title_tool_bar.setNavigationOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                onBackPressed();
//            }
//        });
    }


    /**
     * 初始化滑动渐变
     */
    private void initSlideShapeTheme() {
        setImgHeaderBg();

        // toolbar的高度
        int toolbarHeight = title_tool_bar.getLayoutParams().height;
        // toolbar+状态栏的高度
        final int headerBgHeight = toolbarHeight + StatusBarUtil.getStatusBarHeight(getActivity());

        // 使背景图向上移动到图片的最底端，保留toolbar+状态栏的高度
        iv_title_head_bg.setVisibility(View.VISIBLE);
        ViewGroup.LayoutParams params = iv_title_head_bg.getLayoutParams();
        ViewGroup.MarginLayoutParams ivTitleHeadBgParams = (ViewGroup.MarginLayoutParams) iv_title_head_bg.getLayoutParams();
        int marginTop = params.height - headerBgHeight;
        ivTitleHeadBgParams.setMargins(0, -marginTop, 0, 0);
        iv_title_head_bg.setImageAlpha(0);

        // 为头部是View的界面设置状态栏透明
        StatusBarUtil.setTranslucentImageHeader(getActivity(), 0, title_tool_bar);

        ViewGroup.LayoutParams imgItemBgparams = img_item_bg.getLayoutParams();
        // 获得高斯图背景的高度
        imageBgHeight = imgItemBgparams.height;
        // 监听改变透明度
        initScrollViewListener();
    }
    public final static String IMAGE_URL_LARGE = "http://img1.imgtn.bdimg.com/it/u=3208352844,3803539103&fm=23&gp=0.jpg";
    /**
     * 加载titlebar背景,加载后将背景设为透明
     */
    private void setImgHeaderBg() {
        Glide.with(this).load( IMAGE_URL_LARGE)
//                .placeholder(R.drawable.stackblur_default)
                .error(R.drawable.stackblur_default)
                .bitmapTransform(new BlurTransformation(getActivity(), 14, 3))// 设置高斯模糊
                .listener(new RequestListener<String, GlideDrawable>() {//监听加载状态
                    @Override
                    public boolean onException(Exception e, String model, Target<GlideDrawable> target, boolean isFirstResource) {
                        return false;
                    }

                    @Override
                    public boolean onResourceReady(GlideDrawable resource, String model, Target<GlideDrawable> target, boolean isFromMemoryCache, boolean isFirstResource) {
                        title_tool_bar.setBackgroundColor(Color.TRANSPARENT);
                        iv_title_head_bg.setImageAlpha(0);
                        iv_title_head_bg.setVisibility(View.VISIBLE);
                        return false;
                    }
                }).into(iv_title_head_bg);
    }

    private void initScrollViewListener() {
        // 为了兼容api23以下
        nsv_scrollview.setOnMyScrollChangeListener(new MyNestedScrollView.ScrollInterface() {
            @Override
            public void onScrollChange(int scrollX, int scrollY, int oldScrollX, int oldScrollY) {
                scrollChangeHeader(scrollY);
            }
        });

        int titleBarAndStatusHeight = (int) (CommonUtils.getDimens(R.dimen.nav_bar_height) + StatusBarUtil.getStatusBarHeight(getActivity()));
        slidingDistance = imageBgHeight - titleBarAndStatusHeight - (int) (CommonUtils.getDimens(R.dimen.nav_bar_height_more));
    }

    /**
     * 根据页面滑动距离改变Header透明度方法
     */
    private void scrollChangeHeader(int scrolledY) {

//        DebugUtil.error("---scrolledY:  " + scrolledY);
//        DebugUtil.error("-----slidingDistance: " + slidingDistance);

        if (scrolledY < 0) {
            scrolledY = 0;
        }
        float alpha = Math.abs(scrolledY) * 1.0f / (slidingDistance);
        Drawable drawable = iv_title_head_bg.getDrawable();
//        DebugUtil.error("----alpha:  " + alpha);

        if (drawable != null) {
            if (scrolledY <= slidingDistance) {
                // title部分的渐变
                drawable.mutate().setAlpha((int) (alpha * 255));
                iv_title_head_bg.setImageDrawable(drawable);
            } else {
                drawable.mutate().setAlpha(255);
                iv_title_head_bg.setImageDrawable(drawable);
            }
        }
    }

    @Override
    public void onClick(View v) {
       
        switch (v.getId()){
            case R.id.but_kan:
//                new PhotoPagerConfig.Builder(getActivity())
//                        //.setLowImageUrls(Util.getURL())
//                        .setBigImageUrls(Util.getURL())
//                       // .setImageUrls(Util.getURL()) //图片url,可以是sd卡res，asset，网络图片.
//                        .setSavaImage(true)                         //开启保存图片，默认false
//                        .setPosition(2)                             //默认展示第2张图片
//                        .setSaveImageLocalPath("Android/SD/image/app")//这里是你想保存大图片到手机的地址，不传会有默认地址
//                        .build();

                int index = 2;
                ArrayList<String> info = new ArrayList<String>();
                info.add("http://pic.huakr.com/post/20170509121154_740570.jpg?640*851");
                info.add("http://pic.huakr.com/post/20170509121248_682390.jpg?481*640");
                info.add("http://pic.huakr.com/post/20170509121211_588572.jpg?960*1278");
                info.add("http://img0.imgtn.bdimg.com/it/u=1802555014,204234422&fm=23&gp=0.jpg");
                info.add("http://img3.imgtn.bdimg.com/it/u=3348138270,554106099&fm=23&gp=0.jpg");
                info.add("https://ss3.bdstatic.com/70cFv8Sh_Q1YnxGkpoWK1HF6hhy/it/u=2261844264,1398222573&fm=23&gp=0.jpg");
                Intent intent=new Intent();
                intent.putExtra(KEY_ITEM_NUM,index);
                intent.putStringArrayListExtra(KEY_IMG_PATH, info);
                intent.setClass(getActivity(),ImageDetail.class);
                startActivity(intent);
                break;
            case R.id.but_kan2:




                break;
        }
    }

    /**
     * 打开图片查看器
     *
     * @param position
     * @param urls2
     */
    protected void imageBrower(int position, ArrayList<String> urls2) {
        Intent intent = new Intent(mContext, ImagePagerActivity.class);
        // 图片url,为了演示这里使用常量，一般从数据库中或网络中获取
        intent.putExtra(ImagePagerActivity.EXTRA_IMAGE_URLS, urls2);
        intent.putExtra(ImagePagerActivity.EXTRA_IMAGE_INDEX, position);
        mContext.startActivity(intent);
    }
}
