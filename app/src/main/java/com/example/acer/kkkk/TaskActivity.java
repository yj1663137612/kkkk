package com.example.acer.kkkk;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import static android.provider.Contacts.SettingsColumns.KEY;
import static android.provider.Contacts.SettingsColumns.VALUE;

/**
 * Created by ACER on 2016/10/19.
 */

public class TaskActivity extends Activity {
    TextView mTxt;
    public static final String PATH="http://118.244.212.82:9092/newsClient/news_list?ver=1&subid=1&dir=1&nid=1&stamp=20140321&cnt=20";
    HttpURLConnection urlConnection;
    InputStream inputStream;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task);
        mTxt = (TextView) findViewById(R.id.txt_task);
        Task task = new Task();
        //启动异步任务  并将参数传给第二个方法
        task.execute(PATH);

    }

    class Task extends AsyncTask<String, String, String> {

        //第一个执行的，说白了就是做准备工作的
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        //第二个执行的方法 当准备工作就绪后 开启后台操作
        @Override
        protected String doInBackground(String... params) {
            try {
                //可变参数相当于数组，可以根据下标拿值
                URL url=new URL(params[0]);
                //打开链接
                 urlConnection = (HttpURLConnection) url.openConnection();
                //添加响应头，以键值对的形式出现
                urlConnection.setRequestProperty(KEY,VALUE);
                //获取响应码
                int responseCode = urlConnection.getResponseCode();
                Log.e("===","11"+responseCode);
                if (responseCode== HttpURLConnection.HTTP_OK) {
                    //获取一个输入流
                     inputStream = urlConnection.getInputStream();
                    //通过流拿图片  BitmapFactory.decodeStream(inputStream);
                    byte[]bytes=new byte[1024];
                    int length=0;
                    StringBuffer buffer=new StringBuffer();
                   while(((length=inputStream.read(bytes))!=-1)){
                             buffer.append(new String(bytes,0,length));
                       Log.e("---",buffer.toString());
                    }

                   return  buffer.toString();

                }
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            finally {
                if (urlConnection!=null) {
                    urlConnection.disconnect();
                }
                if (inputStream!=null) {
                    try {
                        inputStream.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }



//            while (true){
//                //获取系统时间
//                Date date=new Date(System.currentTimeMillis());
//                //j将获取到的时间转换格式
//                SimpleDateFormat format=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//                String format1 = format.format(date);
//                //调用此方法，开启第三个方法执行
//
//                try {
//                    Thread.sleep(1000);
            //通知主线程
//                    publishProgress(format1);
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
//
//            }
           return null;

        }

        //第三个方法 与doInBackground方法协调实现，实时刷新，进度更新
//        @Override
//        protected void onProgressUpdate(String... values) {
//            super.onProgressUpdate(values);
//            String value = values[0];
//            mTxt.setText(value);
//
//        }

        //第四个方法，在doInBackground处理以后得到结果的
        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);
            if (result!=null) {
              mTxt.setText(result);
            }else {
                Toast.makeText(TaskActivity.this,"你输入的网址有误",Toast.LENGTH_SHORT).show();

            }
        }

        //第五个方法，取消task执行的方法
        @Override
        protected void onCancelled(String aLong) {
            super.onCancelled();
        }
    }
}
