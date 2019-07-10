package com.bawei.day0_4;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.bawei.day0_4.adapter.MyAdapter;
import com.bawei.day0_4.bean.UserBean;
import com.bawei.day0_4.util.MyOkHttp;
import com.google.gson.Gson;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.recycle)
    RecyclerView recycle;
    private String path="http://172.17.8.100/small/commodity/v1/commodityList";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);


        MyOkHttp.getInstance().LastRequest(path, new MyOkHttp.MyCallback() {
            @Override
            public void success(String json) {

                Gson gson=new Gson();
                UserBean userBean = gson.fromJson(json, UserBean.class);
                List<UserBean.ResultBean.MlssBean.CommodityListBeanXX> commodityList = userBean.getResult().getMlss().getCommodityList();
                GridLayoutManager gridLayoutManager=new GridLayoutManager(MainActivity.this,2);
                recycle.setLayoutManager(gridLayoutManager);
                MyAdapter adapter=new MyAdapter(commodityList,MainActivity.this);
                recycle.setAdapter(adapter);

            }

            @Override
            public void error(String error) {

            }
        });



    }
}
