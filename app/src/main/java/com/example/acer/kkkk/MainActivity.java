package com.example.acer.kkkk;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.ToggleButton;

import com.bumptech.glide.Glide;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity implements CompoundButton.OnCheckedChangeListener {
    TextView text;
    StringBuffer buffer;
    ToggleButton button;
    ImageView mImg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    @Override
    public void onContentChanged() {
        super.onContentChanged();
//        count();

        String one = "{'message':'ok','statue':'0'}";
        String other = "{'message':'ok','data':[{'a':'1'},{'a':'c'}]}";
        String onther = "[{'a':'onther'},{'b':'er'}]";

        try {
            JSONArray array = new JSONArray(onther);
            JSONObject jsonObject = array.getJSONObject(0);
            String a = jsonObject.getString("a");
            JSONObject jsonObject1 = array.getJSONObject(1);
            String b = jsonObject1.getString("b");
            Log.e("打印数据", "第三种" + a + b);

        } catch (JSONException e) {
            e.printStackTrace();
        }


        try {
            JSONObject object1 = new JSONObject(one);
            Object message = object1.get("message");
            Object statue = object1.get("statue");
            Log.e("打印数据", "第一种" + message + statue);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        try {
            JSONObject object = new JSONObject(other);

            JSONArray data = object.getJSONArray("data");
            for (int i = 0; i < data.length(); i++) {
                JSONObject jsonObject = data.getJSONObject(i);
                String a1 = jsonObject.getString("a");
//                String b1 = jsonObject.getString("b");
                Log.e("打印数据", "第二种" + a1);

            }


        } catch (JSONException e) {
            e.printStackTrace();
        }


        text = (TextView) findViewById(R.id.tex_main);
        button = (ToggleButton) findViewById(R.id.toggleButton);
        mImg = (ImageView) findViewById(R.id.img_main);
        button.setOnCheckedChangeListener(this);


        Glide.with(this).load("http://att.bbs.duowan.com/forum/201602/06/222217k8iidlwrzpgsxcyh.jpg").into(mImg);
    }
//    public void count(){
//             //必须在子线程中做操作
//    new Thread(){
//        @Override
//        public void run() {
//            HttpURLConnection urlConnection=null;
//            InputStream inputStream=null;
//            //获取Url的对象
//            try {
//                //封装一个地址 8080代表端口
//                URL url=new URL("http://tupian.enterdesk.com/2015/gha/0700/54/04.jpg");
//                //打开链接，并且获取相应吗
//                 urlConnection = (HttpURLConnection) url.openConnection();
//                //设置信息
//                //设置可以输入输出数据
////                urlConnection.setDoOutput(true);
//                urlConnection.setDoInput(true);
//                //设置请求方法，默认为get
////                urlConnection.setRequestMethod("POST");
//                //设置读取超时时间
//                urlConnection.setReadTimeout(5000);
//
//                //设置使用缓存
//                urlConnection.setUseCaches(false);
//
//                    //获取输出流，输入数据
////                OutputStream outputStream = urlConnection.getOutputStream();
////                outputStream.write("name=yy&password=7777".getBytes());
////                outputStream.flush();
////                获取连接状态
//                //获取响应码  200代表链接正常
//                int responseCode = urlConnection.getResponseCode();
//                Log.e("---","responseCode"+responseCode);
//                if (responseCode==HttpURLConnection.HTTP_OK) {
//                    //获取一个输入流，读取数据
//                     inputStream = urlConnection.getInputStream();
//                    byte[]bytes=new byte[1024];
//                    int len=0;
//                    buffer=new StringBuffer();
//                    handler.sendEmptyMessage(1);
//                    //-1表示读取到文件的末尾
//                    while ((len=inputStream.read(bytes))!=-1){
//                        //用buffer读取数据，也可以用Builder读取
//                        buffer.append(new String(bytes,0,len));
//                    }
////                    Log.e("---","德玛西亚"+buffer);
//                }
//
//            } catch (MalformedURLException e) {
//                e.printStackTrace();
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//            //断开连接
//            finally {
//                if (urlConnection!=null) {
//                    urlConnection.disconnect();
//                }
//                if (inputStream!=null) {
//                    try {
//                        //关流
//                        inputStream.close();
//                    } catch (IOException e) {
//                        e.printStackTrace();
//                    }
//                }
//            }
//        }
//
//    }.start();

//};
//    Handler handler=new Handler(){
//        @Override
//        public void handleMessage(Message msg) {
//                super.handleMessage(msg);
//
//
//        }
//    };

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        if (isChecked) {
//            text.setText(buffer.toString());
            Glide.with(this).load("http://att.bbs.duowan.com/forum/201403/15/171613hlhux1mdhb1fdvml.jpg").into(mImg);

        } else {
            Picasso.with(this).load("http://img.sgamer.com/esports_sgamer_com/images/20121102/8d01021b55d3452752ac0314f5953873.jpg").into(mImg);
            text.setText("对不起：你访问的网址有毒");
        }
    }
}
