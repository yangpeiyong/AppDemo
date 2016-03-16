package com.yangpeiyong.appdemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.yangpeiyong.appdemo.data.ArticleInfo;
import com.yangpeiyong.appdemo.network.ReceiveData;
import com.yangpeiyong.appdemo.network.RestClient;
import com.yangpeiyong.appdemo.utils.ViewHolder;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    @Bind(R.id.listView)
    ListView listView;

    List<ArticleInfo> articleInfoList;

    ArticleAdapter articleAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        articleAdapter = new ArticleAdapter();
        listView.setAdapter(articleAdapter);

        RestClient.api().articles(10,20).enqueue(new Callback<ReceiveData.ArticleListResponse>() {
            @Override
            public void onResponse(Call<ReceiveData.ArticleListResponse> call, Response<ReceiveData.ArticleListResponse> response) {
                articleInfoList = response.body().results;
                articleAdapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<ReceiveData.ArticleListResponse> call, Throwable t) {
                Toast.makeText(MainActivity.this,t.getMessage(),Toast.LENGTH_SHORT).show();
            }
        });

    }

    private class ArticleAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            return articleInfoList==null?0:articleInfoList.size();
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            if (convertView == null ){
                convertView = LayoutInflater.from(MainActivity.this).inflate(R.layout.item_article,null);
            }

            ImageView imageView = ViewHolder.get(convertView,R.id.img);
            TextView titleTxt = ViewHolder.get(convertView,R.id.title);

            ArticleInfo info = articleInfoList.get(position);

            titleTxt.setText(info.desc);

            Glide.with(MainActivity.this).load("http://ww4.sinaimg.cn/large/7a8aed7bjw1ezrtpmdv45j20u00spahy.jpg").into(imageView);

            return convertView;
        }
    }
}
