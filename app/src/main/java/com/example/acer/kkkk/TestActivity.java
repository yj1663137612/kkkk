package com.example.acer.kkkk;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import enter.TestTltle;
import inter.OnLoadTestListener;
import utils.TestTask;

/**
 * Created by ACER on 2016/10/24.
 */

public class TestActivity extends Activity implements OnLoadTestListener {
    public static final String PATH = "http://118.244.212.82:9092/newsClient/news_list?ver=1&subid=1&dir=1&nid=1&stamp=20140321&cnt=20";
    TextView mTxt_message;
    TextView mTxt_statue;
    TextView mTxt_data;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
        mTxt_message = (TextView) findViewById(R.id.txt_message);
        mTxt_statue = (TextView) findViewById(R.id.txt_status);
        mTxt_data = (TextView) findViewById(R.id.txt_data);
        TestTask task = new TestTask();
        task.setOnLoadTestListener(this);
        task.execute(PATH);

    }

    @Override
    public void OnLoadTest(String test) {

        Gson gson = new Gson();


        TestTltle tltle = gson.fromJson(test, new TypeToken<TestTltle>() {
        }.getType());
        String s = gson.toJson(tltle);
        mTxt_data.setText(s);
//        String message = tltle.getMessage();
//        TestTltle tltleMessage = gson.fromJson(message, new TypeToken<TestTltle>() {
//        }.getType());
//        mTxt_message.setText(gson.toJson(tltleMessage));
//        Log.e("message", "--" + gson.toJson(tltleMessage));
//        String status = tltle.getStatus();
//        mTxt_statue.setText(gson.toJson(status));
//        Log.e("status", "--" + status);
//        ArrayList<Content> data = tltle.getData();
//        TestTltle testTltle=new TestTltle();
//        testTltle.setMessage(message);
//        testTltle.setStatus(status);
//        testTltle.setData(data);
//        TestTltle tltle1 = gson.fromJson(content, new TypeToken<TestTltle>() {
//        }.getType());
//        tltle1.getMessage();
//        Log.e("mmmmmmMMMM", "--" + tltle1.getMessage());

    }


}
