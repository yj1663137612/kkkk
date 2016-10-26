package com.example.acer.kkkk;

import android.app.Activity;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;

import enter.Person;
import enter.Result;
import inter.OnLoadBitmapLister;
import utils.ImgTask;

/**
 * Created by ACER on 2016/10/24.
 */

public class TwoActivity extends Activity implements OnLoadBitmapLister {
    public static final String PATH = "http://img04.sogoucdn.com/app/a/100520024/6ce6d8d40dc3f0377f38901cefcce423";
    ImageView mImg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_two);

        Gson gson = new Gson();
        //对象数组嵌套型
        String array="{'message':'ok','data':[{'name':'zs','age':23},{'name':'ls','age':29},{'name':'wu','age':09}],'statue':'1'}";
        Type type= new TypeToken<Result>() {
        }.getType();
        Result result = gson.fromJson(array,Result.class );
        Log.e("1","---"+result.getMessage());
        Log.e("1","---"+result.getStatue());
        ArrayList<Person> data = result.getData();
        for (Person p1 : data) {
            Log.e("对象数组型","---"+p1);
        }
        ArrayList<Person> p0=new ArrayList<>();
        p0.add(new Person("zs","23"));
        p0.add(new Person("li","13"));
        p0.add(new Person("zs","09"));
        Result result1=new Result();
        result1.setMessage("ko");
        result1.setStatue("2");
        result1.setData(p0);
        String s = gson.toJson(result1);

        Log.e("对象数组型1","===="+s);


    }

    private void aaa( Gson gson) {

        //通过匿名内部类获取类型
//        Type type=new TypeToken<Person>(){}.getType();
        String data1 = "{'name':'zs','age':1}";
        Person person = gson.fromJson(data1, new TypeToken<Person>() {
        }.getType());

//        Person person = gson.fromJson(data, Person.class);
//        Log.e("name", "---" + person.getName());
//        Log.e("age", "---" + person.getAge());
//      将一个实体类对象转换为JSON字符型
        String s = gson.toJson(person);
        Log.e("字符型", "---" + s);
        ArrayList<Person> list = new ArrayList<>();
        list.add(new Person("ZS", "23"));
        list.add(new Person("LI", "25"));
        list.add(new Person("WU", "29"));
        list.add(new Person("ML", "20"));
        String s1 = gson.toJson(list);
        Log.e("数组型", "===" + s1);
//       数组型
        String arr = "[{'name':'zs','age':23},{'name':'ls','age':29},{'name':'wu','age':09}]";
        ArrayList<Person> p = gson.fromJson(arr, new TypeToken<ArrayList<Person>>() {
        }.getType());
        for (Person person1 : p) {
            Log.e("数组型11", "" + person1.toString());
        }
        Person []p3=new Person[3];
        Person[] p4 = gson.fromJson(arr, p3.getClass());
        for (Person p5 : p4) {
            Log.e("数组型22","===="+p5.toString());
        }





        mImg = (ImageView) findViewById(R.id.img_two);
        ImgTask task = new ImgTask();
        //实现监听事件  子类对象指向父类，    @Override
//        调的是父类的方法，其实是子类自己在调
        task.setOnLoadBitmapLister(this);
        //启动异步任务 并传递参数，参数传递到ImgTask中doInBackground的方法里面
        task.execute(PATH);
    }

    public void OnLoadBitmap(Bitmap bitmap) {
        mImg.setImageBitmap(bitmap);

    }
}
