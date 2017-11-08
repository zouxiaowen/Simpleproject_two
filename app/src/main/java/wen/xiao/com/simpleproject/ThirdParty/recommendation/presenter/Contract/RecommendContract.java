package wen.xiao.com.simpleproject.ThirdParty.recommendation.presenter.Contract;

import android.app.Activity;

import java.util.List;

import wen.xiao.com.simpleproject.Common.entity.meizitu;
import wen.xiao.com.simpleproject.Common.presenter.BasePresenter;
import wen.xiao.com.simpleproject.Common.ui.BaseView;

/**
 * Created by Administrator on 2017/6/5.
 */

public interface RecommendContract {
    interface View extends BaseView {
        void showData(List<meizitu.ResultsBean> list);
        void showLoad();
        void showErroy();
        void showNull();
        void dissMiss();
        void onError(Exception mssage);
        void ShowBanner(List<String> list);

    }
    interface Presenter extends BasePresenter {
       // public void requestData(String url, int page, Activity context );
        public  void Banner();



    }
}
