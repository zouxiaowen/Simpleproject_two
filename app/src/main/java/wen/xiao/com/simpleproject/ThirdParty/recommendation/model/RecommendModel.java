package wen.xiao.com.simpleproject.ThirdParty.recommendation.model;

import android.app.Activity;

import com.lzy.okgo.OkGo;

import java.util.ArrayList;
import java.util.List;

import wen.xiao.com.simpleproject.Common.entity.meizitu;
import wen.xiao.com.simpleproject.callback.A_DialogCallback;

/**
 * Created by Administrator on 2017/6/5.
 * 写方法
 */

public class RecommendModel {
//    public void GetApp(String url, int page, Activity context, A_DialogCallback<meizitu> callback){
//        OkGo.get(url+page)
//                .tag(this)
//                .execute(callback);
//    }
    public List<String> SetBanner( ){
        final List<String> urlss = new ArrayList<>();
        urlss.add("http://i0.hdslb.com/bfs/archive/9d121568f2a43e4bcb453dc29cd89cb97d6e7c88.jpg");
        urlss.add("http://i0.hdslb.com/bfs/archive/d9b75cafba4df27ca6f5ea5212b7697774406c14.jpg");
        urlss.add("http://i0.hdslb.com/bfs/archive/2c1d067142c0508efb83af5c70b8b7c3edb18763.jpg");
        urlss.add("http://i0.hdslb.com/bfs/archive/e65052a577be3f649d401a41127c10499dd85fc5.jpg");
        return urlss;
    }
}
