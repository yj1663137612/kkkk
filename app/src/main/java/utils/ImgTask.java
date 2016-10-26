package utils;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.util.Log;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import inter.OnLoadBitmapLister;

/**
 * Created by ACER on 2016/10/24.
 */

public class ImgTask extends AsyncTask<String,Void,Bitmap>{

    Bitmap bitmap=null;
    HttpURLConnection urlConnection=null;
    InputStream inputStream=null;
    //第一个执行的方法，在做准备任务
    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }
//
    @Override
    protected Bitmap doInBackground(String... params) {
        try {
            //获取URL对象 接收参数
            URL url=new URL(params[0]);
            //打开链接
             urlConnection = (HttpURLConnection) url.openConnection();
            //获取响应码
            int responseCode = urlConnection.getResponseCode();
            Log.e("响应码","=="+responseCode);
            if (responseCode==200) {
             //获取到一个输入流，得到图片信息
                InputStream inputStream = urlConnection.getInputStream();
                Log.e("流","=="+inputStream);
                //将流转换为图片
                 bitmap = BitmapFactory.decodeStream(inputStream);
                Log.e("图片","=="+bitmap);
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        finally {
            //断开连接
            if (urlConnection != null) {
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


        return bitmap;
    }
    //第四个方法 在doInBackground处理以后得到结果的
    @Override
    protected void onPostExecute(Bitmap bitmap) {
        super.onPostExecute(bitmap);
        if (bitmap != null&&OnLoadBitmapLister!=null) {
            OnLoadBitmapLister.OnLoadBitmap(bitmap);

        }



    }
    //接口对象
    OnLoadBitmapLister OnLoadBitmapLister;

    public void setOnLoadBitmapLister(OnLoadBitmapLister onLoadBitmapLister) {
       this.OnLoadBitmapLister = onLoadBitmapLister;
    }
}
