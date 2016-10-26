package utils;

import android.os.AsyncTask;
import android.util.Log;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import inter.OnLoadTestListener;

/**
 * Created by ACER on 2016/10/24.
 */

public class TestTask extends AsyncTask<String, Void, String> {
    HttpURLConnection urlConnection;
    InputStream inputStream;
    //第一个执行的方法，在做准备工作
    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    //第二个执行的方法，当准备工作执行后，开启后台操作
    @Override
    protected String doInBackground(String... params) {
        try {
            //获取URL对象
            URL url = new URL(params[0]);
            //打开一个链接
        urlConnection = (HttpURLConnection) url.openConnection();
            //获取响应码
            int responseCode = urlConnection.getResponseCode();
            if (responseCode == 200) {
                //获取一个输入流，读出数据
                inputStream = urlConnection.getInputStream();
                byte[] bytes = new byte[1024];
                int length;
                StringBuffer buffer = new StringBuffer();
                while (((length = inputStream.read(bytes)) != -1)) {
                    buffer.append(new String(bytes, 0, length));
                    String s = buffer.toString();
                    Log.e("网址", "---" + s);
                }
                return buffer.toString();
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (urlConnection != null) {
                //断开连接
                urlConnection.disconnect();
            }
            if (inputStream != null) {
                try {
                    //关流
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return null;
    }

    //第四个方法，在第二个方法处理完以后，得到结果
    @Override
    protected void onPostExecute(String test) {
        if (test != null&&OnLoadTestListener!=null) {
            OnLoadTestListener.OnLoadTest(test);

        }

        super.onPostExecute(test);
    }

    //接口对象
    OnLoadTestListener OnLoadTestListener;
    public void setOnLoadTestListener(  OnLoadTestListener onLoadTestListener){
        this.OnLoadTestListener=onLoadTestListener;
    };

}

