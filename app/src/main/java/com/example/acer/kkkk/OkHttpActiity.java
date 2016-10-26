package com.example.acer.kkkk;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.IOException;
import java.io.InputStream;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.ResponseBody;

/**
 * Created by ACER on 2016/10/25.
 */

public class OkHttpActiity extends AppCompatActivity {
    String PATH = "http://192.168.191.1:8080/haha/servlet/Login?";

    String IMG="http://www.iyi8.com/uploadfile/2016/0225/20160225124617786.jpg";
    TextView mTxt;
   ImageView mImg;
    Bitmap bitmap;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    public void onContentChanged() {
        super.onContentChanged();
        mTxt = (TextView) findViewById(R.id.tex_main);
        mImg = (ImageView) findViewById(R.id.img_main);

     /*如果说你要使用OKHTTP
    * 1.导入包
    *2.启动器
    *3.请求（请求体）
    *4.获取呼叫器
    * 5.开始呼叫
    * */
        //启动器
        OkHttpClient client=new OkHttpClient();
        //请求  构造器模式构造请求
        FormBody.Builder formBuilder = new FormBody.Builder();
        //构建请求体，以键值对的形式储存数据
        formBuilder.add("name","zs");
        formBuilder.add("password","123456");
        //获取请求体FormBody extends RequestBody 继承关系
        RequestBody requestBody = formBuilder.build();
        //获取请求  ，构造器模式
        Request.Builder builder = new Request.Builder();
        //通过构造器构造请求
        builder.url(PATH);
        //使用构造器，将请求体放入请求中
        builder.post(requestBody);
        //构建请求
        Request request = builder.build();
        //获取CALL模型
        Call call = client.newCall(request);
    }

    private void GET() {
    /*如果说你要使用OKHTTP
    * 1.导入包
    *2.启动器
    *3.请求（请求体）
    *4.获取呼叫器
    * 5.开始呼叫
    * */
        //启动器
        OkHttpClient client = new OkHttpClient();
        /*
        * 请求  考虑使用 GET  获取POST
        * 先使用 GET 不需要请求体
        *
        * */
        //使用构造器模式构造请求
        Request.Builder builder = new Request.Builder();
        //构建地址
        builder.url(IMG);
        //因为要使用GET方法，所以不需要别的东西
        //构建请求
        Request request = builder.build();
        //获取CALL呼叫器
        final Call call = client.newCall(request);
        //开始呼叫，考虑使用  同步还是异步操作
        /*1.注意此时，如果使用同步操作的时候，需要注意CALL模型在execute()的时候，必须在子线程中操作，否则报网络工作在主线程的异常
        * 2.如果使用异步操作的时候，我们要注意，enqueue(new CallBack)在callBack的时候，他是在后台线程（守护线程），也就是在子线程中回调的
        * 所以不能直接使用，必须发送至主线程中才能使用，那么这里可以考虑接口回调的方法，刷新主线程的两种方法runOnUiThread和handler
        * */
        //使用异步操作  注意这里的回调全部在后台线程执行
        call.enqueue(new Callback() {
            //链接失败
            @Override
            public void onFailure(Call call, IOException e) {
              //后台线程中
            }
            //链接失败
            @Override
            public void onResponse(Call call, Response response) throws IOException {
             //后台线程中
                //得到响应体
                ResponseBody body = response.body();
                //获取一个输入流
                InputStream inputStream = body.byteStream();
                 bitmap = BitmapFactory.decodeStream(inputStream);
                handler.sendEmptyMessage(1);
//                 runOnUiThread(new Runnable() {
//                     @Override
//                     public void run() {
//                         mImg.setImageBitmap(bitmap);
//                     }
//                 });

            }
            Handler handler=new Handler(){
                @Override
                public void handleMessage(Message msg) {
                    super.handleMessage(msg);

                }
            };
        });

//        //使用同步操作  定义一个子线程
//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//                //异步操作
//                try {
//                    //得到响应
//                    Response execute = call.execute();
//                    /*1.响应中包含了响应的详细信息，所以要分部获取
//                    * 2.在获取到响应体之后，看你需要什么类型的数据，就使用什么样的方法*/
//
//                    boolean successful = execute.isSuccessful();
//                    Log.e("----","---"+successful);
//                    if (successful) {
//                        //得到响应体
//                        ResponseBody body = execute.body();
//                        //响应体中有各种数据类型
//                        //获取字符串
////                        String string = body.string();
//                        //获取输入流 如果此时你的URL 指向的是一张图片
//                        // 那么你可以使用图片工厂模式 将一个流转换为Bitmap
//                        InputStream inputStream = body.byteStream();
//                        bitmap = BitmapFactory.decodeStream(inputStream);
//                        handler.sendEmptyMessage(1);
//                        Log.e("Bitmap", "__" + bitmap);
////                        runOnUiThread(new Runnable() {
////                            @Override
////                            public void run() {
////                                mImg.setImageBitmap(bitmap);
////                            }
////                        });
//                    }
//
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//
//
//            }
//        }).start();


//    Handler handler=new Handler(){
//        @Override
//        public void handleMessage(Message msg) {
//            super.handleMessage(msg);
//            mImg.setImageBitmap(bitmap);
//        }
//    };
    }
}
