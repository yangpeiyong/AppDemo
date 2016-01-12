package com.yangpeiyong.appdemo.network;

import com.yangpeiyong.appdemo.data.ArticleInfo;

import java.util.List;

/**
 * Created by yangpeiyong on 16/1/12.
 */
public class ReceiveData {

    public static class ArticleListResponse {
        public boolean error;
        public List<ArticleInfo> results;
    }
}
