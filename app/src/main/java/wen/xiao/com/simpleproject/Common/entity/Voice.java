package wen.xiao.com.simpleproject.Common.entity;

import java.util.ArrayList;

/**
 * Created by Administrator on 2017/6/5.
 */

public class Voice {

    public ArrayList<WSBean> ws;

    public class WSBean {
        public ArrayList<CWBean> cw;
    }

    public class CWBean {
        public String w;
    }
}
