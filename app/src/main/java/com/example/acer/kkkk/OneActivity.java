package com.example.acer.kkkk;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by ACER on 2016/10/18.
 */

public class OneActivity extends AppCompatActivity{
    HttpURLConnection urlConnection;
    InputStream inputStream;
    @Override
    protected void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_one);
    }

    @Override
    public void onContentChanged() {
        super.onContentChanged();

        count();
        new Thread(){
            @Override
            public void run() {
              //获取URL对象
                try {
                    URL url=new URL("http://192.168.3.249:8080/haha/servlet/Login?");
                    //打开链接
                     urlConnection = (HttpURLConnection) url.openConnection();
                    //设置可以输出数据
                    urlConnection.setDoOutput(true);
                    //设置请求方式为POST
                    urlConnection.setRequestMethod("POST");
                    //获取输出流，写入数据
                    OutputStream outputStream = urlConnection.getOutputStream();
                    outputStream.write("name=zs&password=123456".getBytes());
                    //刷新
                    outputStream.flush();
                    //获取响应码
                    int responseCode = urlConnection.getResponseCode();
                    //判断网络是否链接
                    if (responseCode==200) {
                        //获取输入流，读取数据
                         inputStream = urlConnection.getInputStream();

                        byte []bytes=new byte[1024];
                        int length;
                        StringBuffer buffer=new StringBuffer();
                        while ((length=inputStream.read(bytes))!=-1){
                            //用buffer流读取数据
                            buffer.append(new String(bytes,0,length));

                        }
                        String s = buffer.toString();
                        Log.e("这是GET",s);

                    }
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
      finally {
                    if (urlConnection!=null) {
                        //断开链接
                        urlConnection.disconnect();
                    }
                    if (inputStream!=null) {
                        try {
                            //关流
                            inputStream.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }

            }
        }.start();




    }

    private void count() {
//        String one="{'a':'a','b':[ {'c':[{'e':'e'},{'f':'f'}]},{'d':'d'}}";
//        try {
//            //获取最外层的JSON对象
//            JSONObject object2=new JSONObject(one);
//            //获取内层的JSON数组
//            JSONArray jsonArray = object2.getJSONArray("b");
//        } catch (JSONException e) {
//            e.printStackTrace();
//        }
        //生成对象型
        JSONObject object=new JSONObject();
        try {
            object.put("message","ok");
            object.put("statue",1);

            String s = object.toString();
            Log.e("这是对象型的","s=="+s);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        //这是数组型
        JSONArray array=new JSONArray();

JSONObject jsonObject=new JSONObject();
        try {
            jsonObject.put("三 娃",array);
            JSONObject object1=new JSONObject();
            object1.put("大娃","一号");
            object1.put("二娃","二号");

            array.put(1,object);
            array.put(0,object1);

            String string = jsonObject.toString();
            Log.e("这是数组型","ss==="+string);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
