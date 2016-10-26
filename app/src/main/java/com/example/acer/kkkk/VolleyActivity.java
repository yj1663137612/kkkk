package com.example.acer.kkkk;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ImageView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by ACER on 2016/10/25.
 */

public class VolleyActivity extends AppCompatActivity {
    String PATH="http://www.iyi8.com/uploadfile/2016/0225/20160225124617786.jpg";
    ImageView mImg;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_volley);
    }

    @Override
    public void onContentChanged() {
        mImg= (ImageView) findViewById(R.id.img_volley);
        super.onContentChanged();
       //获取对列请求
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        //获取请求
        //ImageRequest  图片请求  默认为GET 请求
        ImageRequest imageRequest=new ImageRequest(PATH, new Response.Listener<Bitmap>() {

            //请求成功
            @Override
            public void onResponse(Bitmap response) {
                mImg.setImageBitmap(response);

            }
        }, 0, 0, Bitmap.Config.ARGB_8888, new Response.ErrorListener() {
            //请求失败
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        //添加到对列消息
        requestQueue.add(imageRequest);
    }

    private void JsonGet() {
        //获取请求对列
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        //获取请求
        //JsonObjectRequest类型
        JsonObjectRequest jsonObjectRequest=new JsonObjectRequest(Request.Method.GET, PATH, null, new Response.Listener<JSONObject>() {
          //请求成功
            @Override
            public void onResponse(JSONObject response) {
            Log.e("请求成功","----"+response.toString());
            }
        }, new Response.ErrorListener() {
            //请求失败
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        //添加到对列消息
        requestQueue.add(jsonObjectRequest);
    }

    private void GET() {
        //获取请求对列
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        //获取请求
        //字符串申请
        StringRequest stringRequest=new StringRequest(Request.Method.GET, PATH, new Response.Listener<String>() {
          //请求成功
            @Override
            public void onResponse(String response) {
             Log.e("成功","--"+response);
            }
        }, new Response.ErrorListener() {
            //请求失败
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        //添加到对列消息
        requestQueue.add(stringRequest);
    }

    private void POST() {
        //获取请求列队
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        //获取请求
        //字符串请求
        StringRequest stringRequest = new StringRequest(Request.Method.POST, PATH, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                //请求成功
                Log.e("第一种","---"+response);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                //请求失败
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> map = new HashMap<>();
                map.put("name","zs");
                map.put("password","123456");
                return super.getParams();


            }
        };
        //添加到消息对列
        requestQueue.add(stringRequest);
    }
}
