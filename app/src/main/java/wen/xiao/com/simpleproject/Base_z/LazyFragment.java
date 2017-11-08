package wen.xiao.com.simpleproject.Base_z;

/**
 * Created by Administrator on 2017/6/30.
 */

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * created by blink
 */
public abstract class LazyFragment extends Fragment {

    private boolean isVisible;
    private boolean isViewCreated;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = view= initViews(inflater, container, savedInstanceState);
        Log.e("blink","onCreateView");
        isViewCreated = true;
        return view;
    }
    protected abstract View initViews(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState);

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if(getUserVisibleHint()) {
            isVisible = true;
            loadForData();
        } else {
            isVisible = false;
            onInvisible();
        }
        if (isViewCreated && isVisible)
            loadForUI();
    }

    protected abstract void onInvisible();

    protected abstract void loadForData();

    protected abstract void loadForUI();

    // provide layout id
   // public abstract int getLayoutID();

}
