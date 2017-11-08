package wen.xiao.com.simpleproject.Common.presenter;

import android.app.Activity;
import android.content.Context;

import com.google.gson.Gson;
import com.iflytek.cloud.RecognizerResult;
import com.iflytek.cloud.SpeechError;
import com.iflytek.cloud.ui.RecognizerDialogListener;
import com.lzy.okgo.OkGo;

import java.util.ArrayList;
import java.util.List;


import okhttp3.Call;
import okhttp3.Response;
import wen.xiao.com.simpleproject.Common.entity.Voice;
import wen.xiao.com.simpleproject.Common.entity.meizitu;
import wen.xiao.com.simpleproject.Common.model.RecommenModel;
import wen.xiao.com.simpleproject.Common.presenter.Contract.RecommendContract;
import wen.xiao.com.simpleproject.callback.A_DialogCallback;

public class RecommendPresenter implements RecommendContract.Presenter {
	
	public RecommendContract.View mview;
	public RecommenModel mModel;
	public RecommendPresenter(RecommendContract.View mview) {
		super();
		this.mview = mview;
		mModel=new RecommenModel();
	}
	@Override
	public void requestData(String url, int page, Activity context) {
        mModel.GetApp(url, page, context, new A_DialogCallback<meizitu>(context) {
            @Override
            public void onSuccess(meizitu meizitu, Call call, Response response) {
                    if (meizitu.getResults()!=null){
                        mview.showData(meizitu.getResults());
                    }else{
                        mview.showNull();
                    }
            }
            @Override
            public void onError(Call call, Response response, Exception e) {
                super.onError(call, response, e);
                mview.onError(e);
            }
        });
	}

    @Override
    public void SpeechRecognition(Context context) {
            mModel.GetSpeechRecognition(context, new RecognizerDialogListener() {
                @Override
                public void onResult(RecognizerResult recognizerResult, boolean b) {
                    if (!b) {
                        //解析语音
                        String result = parseVoice(recognizerResult.getResultString());
                        //tv.setText(result);
                        if (result!=null){
                            mview.showSpeechRecognition(result);
                        }
                    }
                }

                @Override
                public void onError(SpeechError speechError) {
                    mview.onError(speechError);
                }
            });
    }

    /**
     * 解析语音json
     */
    public String parseVoice(String resultString) {
        Gson gson = new Gson();
        Voice voiceBean = gson.fromJson(resultString, Voice.class);

        StringBuffer sb = new StringBuffer();
        ArrayList<Voice.WSBean> ws = voiceBean.ws;
        for (Voice.WSBean wsBean : ws) {
            String word = wsBean.cw.get(0).w;
            sb.append(word);
        }
        return sb.toString();
    }
}
