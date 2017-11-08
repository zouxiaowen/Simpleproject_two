package wen.xiao.com.simpleproject.ThirdParty.recommendation.presenter;

import android.app.Activity;

import java.util.List;

import wen.xiao.com.simpleproject.ThirdParty.recommendation.model.RecommendModel;
import wen.xiao.com.simpleproject.ThirdParty.recommendation.presenter.Contract.RecommendContract;

/**
 * Created by Administrator on 2017/6/5.
 */

public class RecommendPresenter implements RecommendContract.Presenter{
    public RecommendContract.View mview;
    public RecommendModel mModel;

    public RecommendPresenter(RecommendContract.View mview){
        this.mview=mview;
        mModel=new RecommendModel();
    }


    @Override
    public void Banner() {
        List<String> list = mModel.SetBanner();
        if (list!=null){
            mview.ShowBanner(list);
        }
    }
}
