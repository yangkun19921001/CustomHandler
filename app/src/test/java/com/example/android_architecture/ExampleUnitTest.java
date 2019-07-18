package com.example.android_architecture;

import com.example.android_architecture.core.CustomHandler;
import com.example.android_architecture.core.CustomLooper;
import com.example.android_architecture.core.CustomMessage;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {


    @Test
    public void addition_isCorrect() {
        assertEquals(4, 2 + 2);
    }


    @Test
    public void testHandler(){
//        - 子线程中存，子线程中取
        new Thread("thread-1"){
            @Override
            public void run() {
                ThreadLocal<String> mThread_A = new ThreadLocal();
                mThread_A.set("thread-1");
                System.out.println("mThread_A :"+mThread_A.get());

            }
        }.start();


//        - 主线程中存，子线程取
        final ThreadLocal<String> mThread_B = new ThreadLocal();
        mThread_B.set("thread_B");
        new Thread(){
            @Override
            public void run() {
                System.out.println("mThread_B :"+mThread_B.get());
            }
        }.start();


//        - 主线程存，主线程取
        ThreadLocal<String> mThread_C = new ThreadLocal();
        mThread_C.set("thread_C");
        System.out.println("mThread_C :"+mThread_C.get());


        CustomLooper.prepare();

        CustomHandler customHandler = new CustomHandler(){
            @Override
            public void handleMessage(CustomMessage msg) {
                super.handleMessage(msg);

                String mes = (String) msg.obj;
                System.out.println("收到消息---》"+ mes);
            }
        };

        CustomMessage customMessage = new CustomMessage();
        customMessage.obj = "哈哈，简易 Handler 架构实现";
        customHandler.sendMessage(customMessage);
        //开始循环获取消息 - 》 分发消息
        CustomLooper.loop();

    }


}