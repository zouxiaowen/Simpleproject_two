package wen.xiao.com.simpleproject.Adapter_z;

import android.content.Context;
import android.util.TypedValue;
import android.widget.ImageView;


import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
 
import java.util.List;

import wen.xiao.com.simpleproject.Common.entity.meizitu;
import wen.xiao.com.simpleproject.R;
 
import wen.xiao.com.simpleproject.entity.meinv;

/**
 * Created by Administrator on 2017/5/26.
 */

public class MyAdpter extends BaseQuickAdapter<meizitu.ResultsBean,BaseViewHolder> {
    Context context;
    public MyAdpter(Context context, List<meizitu.ResultsBean> data) {
        super(R.layout.layout_item, data);
        this.context=context;
    }

//    @Override
//    protected void convert(final BaseViewHolder helper, final meinv.TngouBean item) {
//        helper.setText(R.id.tv_ce,item.getTitle());
//    //    SimpleDraweeView draweeView = (SimpleDraweeView) helper.getView(R.id.my_image_view);
//
//
////        int width = 50, height = 50;
////        Uri uri;
////        uri = Uri.parse( "http://tnfs.tngou.net/image"+item.getImg());
////        ImageRequest request = ImageRequestBuilder.newBuilderWithSource(uri)
////                .setProgressiveRenderingEnabled(true)
////
////                .build();
////        DraweeController controller = Fresco.newDraweeControllerBuilder()
////                .setImageRequest(request)
////                .setOldController(draweeView.getController())
////                .setTapToRetryEnabled(true)
////
////                .build();
////        draweeView.setController(controller) ;
////        draweeView.setAspectRatio(1.33f);
//
//        int width = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 200, mContext.getResources().getDisplayMetrics());
//        int height = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 200f, mContext.getResources().getDisplayMetrics());
//
//        Glide.with(mContext)
//                .load("http://tnfs.tngou.net/image"+item.getImg())
//                .placeholder(R.mipmap.ic_launcher) //占位图
//                .error(R.mipmap.ic_launcher)  //出错的占位图
//                .override(width, height) //图片显示的分辨率 ，像素值 可以转化为DP再设置
//               // .animate(R.anim.glide_anim)
//                .centerCrop()
//                .fitCenter()
//                .into((ImageView)helper.getView(R.id.image_glide));
//
//    }


    @Override
    protected void convert(BaseViewHolder helper, meizitu.ResultsBean item) {
        int width = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 200, mContext.getResources().getDisplayMetrics());
        int height = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 200f, mContext.getResources().getDisplayMetrics());

        Glide.with(mContext)
                .load(item.getUrl())
                .placeholder(R.mipmap.ic_launcher) //占位图
                .error(R.mipmap.ic_launcher)  //出错的占位图
                .override(width, height) //图片显示的分辨率 ，像素值 可以转化为DP再设置
               // .animate(R.anim.glide_anim)
                .centerCrop()
                .fitCenter()
                .into((ImageView)helper.getView(R.id.image_glide));
    }
}
