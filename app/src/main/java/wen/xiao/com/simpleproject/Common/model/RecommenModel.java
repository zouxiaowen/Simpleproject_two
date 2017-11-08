package wen.xiao.com.simpleproject.Common.model;

import android.app.Activity;
import android.content.Context;

import com.iflytek.cloud.RecognizerResult;
import com.iflytek.cloud.SpeechConstant;
import com.iflytek.cloud.SpeechError;
import com.iflytek.cloud.ui.RecognizerDialog;
import com.iflytek.cloud.ui.RecognizerDialogListener;
import com.lzy.okgo.OkGo;
import wen.xiao.com.simpleproject.Common.entity.meizitu;
import wen.xiao.com.simpleproject.callback.A_DialogCallback;
public class RecommenModel {
	public void GetApp(String url, int page, Activity context,A_DialogCallback<meizitu> callback){
        OkGo.get(url+page)
                .tag(this)
                .execute(callback);
	}
	public void GetSpeechRecognition(Context context,RecognizerDialogListener recognizerDialogListener){
//		//1.创建RecognizerDialog对象
//		RecognizerDialog mDialog = new RecognizerDialog(context, null);
//		//2.设置accent、language等参数
//		mDialog.setParameter(SpeechConstant.LANGUAGE, "zh_cn");
//		mDialog.setParameter(SpeechConstant.ACCENT, "mandarin");
//		//3.设置回调接口
//		mDialog.setListener(new RecognizerDialogListener() {
//			@Override
//			public void onResult(RecognizerResult recognizerResult, boolean isLast) {
//				if (!isLast) {
//					//解析语音
//					String result = parseVoice(recognizerResult.getResultString());
//					tv.setText(result);
//				}
//			}
//
//			@Override
//			public void onError(SpeechError speechError) {
//
//			}
//		});
//		//4.显示dialog，接收语音输入
//		mDialog.show();
		//1.创建RecognizerDialog对象
		RecognizerDialog mDialog = new RecognizerDialog(context, null);
		//2.设置accent、language等参数
		mDialog.setParameter(SpeechConstant.LANGUAGE, "zh_cn");
		mDialog.setParameter(SpeechConstant.ACCENT, "mandarin");
		//3.设置回调接口
		mDialog.setListener(recognizerDialogListener);
		//4.显示dialog，接收语音输入
		mDialog.show();
	}
}
