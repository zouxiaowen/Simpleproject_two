package wen.xiao.com.simpleproject.preview;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.util.DisplayMetrics;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

import wen.xiao.com.simpleproject.R;


public class ImageDetailType extends Activity {

    PhotoView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_image_detail_type);
        initValues();
    }

    protected void initValues() {
        imageView= (PhotoView) findViewById(R.id.image);
        /*获取Bundle中的数据，注意类型和key*/
        String url = getIntent().getExtras().getString("infoList");
        Glide.with(this)
                .load(url)
                .placeholder(R.mipmap.placeholder) //占位图
                .error(R.mipmap.placeholder)  //出错的占位图
                // .animate(R.anim.glide_anim)
                .centerCrop()
                .fitCenter()
                .into(imageView);

        imageView.enable();
        imageView.setScaleType(ImageView.ScaleType.FIT_CENTER);
//        imageView.setOnLongClickListener(new View.OnLongClickListener() {
//            @Override
//            public boolean onLongClick(View v) {
//                if (longPressListener != null) longPressListener.longPress(position);
//                return true;
//            }
//        });

        imageView.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                 finish();
            }
        });

    }

}
