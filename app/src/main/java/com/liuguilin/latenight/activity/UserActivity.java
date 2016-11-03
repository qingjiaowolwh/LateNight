package com.liuguilin.latenight.activity;

/*
 *  项目名：  LateNight 
 *  包名：    com.liuguilin.latenight.activity
 *  文件名:   UserActivity
 *  创建者:   LGL
 *  创建时间:  2016/10/24 21:09
 *  描述：    个人中心
 */

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.ScrollView;

import com.liuguilin.gankclient.R;
import com.liuguilin.latenight.entity.GankUser;
import com.liuguilin.latenight.util.ListViewUtils;

import java.util.ArrayList;
import java.util.List;

import cn.bmob.v3.BmobUser;

public class UserActivity extends BaseActivity implements View.OnClickListener {

    private ListView mListView;
    private List<String> mList = new ArrayList<>();
    private ArrayAdapter<String> mAdapter;
    private ScrollView mZoomScrollView;
    private Button btn_exit_user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_user);

        initView();
    }

    //初始化View
    private void initView() {
        btn_exit_user = (Button) findViewById(R.id.btn_exit_user);
        btn_exit_user.setOnClickListener(this);
        mZoomScrollView = (ScrollView) findViewById(R.id.mZoomScrollView);
        mZoomScrollView.smoothScrollTo(0, 0);
        mListView = (ListView) findViewById(R.id.mListView);
        getUser();
    }

    //获取到User属性
    private void getUser() {
        //获取到本地属性，前提是已经登录
        GankUser user = BmobUser.getCurrentUser(GankUser.class);
        mList.add("姓名：" + user.getUsername());
        mList.add("年龄：" + user.getAge());
        mList.add("性别：" + (user.isSex() ? "男" : "女"));
        mList.add("身高：" + user.getHeight());
        mList.add("体重：" + user.getWeight());
        mList.add("生日：" + user.getBirthday());
        mList.add("星座：" + user.getConstellation());
        mList.add("学校：" + user.getSchool());
        mList.add("职业：" + user.getOccupation());
        mList.add("简介：" + user.getDesc());
        //这里可以尝试着重写layout
        mAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, mList);
        mListView.setAdapter(mAdapter);
        ListViewUtils.setListViewHeightBasedOnChildren(mListView);
    }



    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_exit_user:
                GankUser.logOut();
                BmobUser currentUser = GankUser.getCurrentUser();
                startActivity(new Intent(this, LoginActivity.class));
                finish();
                break;
        }
    }
}
