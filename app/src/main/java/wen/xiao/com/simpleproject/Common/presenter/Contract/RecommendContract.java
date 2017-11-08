package wen.xiao.com.simpleproject.Common.presenter.Contract;

import android.app.Activity;
import android.content.Context;
import android.os.Message;

import java.util.ArrayList;
import java.util.List;

import wen.xiao.com.simpleproject.Common.entity.meizitu;
import wen.xiao.com.simpleproject.Common.presenter.BasePresenter;
import wen.xiao.com.simpleproject.Common.ui.BaseView;


public interface RecommendContract {
	interface View extends BaseView {
		void showData(List<meizitu.ResultsBean> list);
		void showLoad();
		void showErroy();
		void showNull();
		void dissMiss();
		void onError(Exception mssage);
		void showSpeechRecognition(String sp);
	}
	interface Presenter extends BasePresenter {
		public void requestData(String url, int page, Activity context );

        void SpeechRecognition(Context context);
    }

}
