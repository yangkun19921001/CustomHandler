package com.example.android_architecture.core;

import android.os.Message;

public class CustomHandler {

    private CustomLooper mLooper;
    private CustomMessageQueue mMessageQueue;

    public CustomHandler() {
        mLooper = CustomLooper.myLooper();
        if (mLooper == null) {
            throw new RuntimeException(
                    "Can't create handler inside thread " + Thread.currentThread()
                            + " that has not called Looper.prepare()");
        }
        mMessageQueue = mLooper.mQueue;
    }

    public void handleMessage(CustomMessage msg) {
    }

    public void sendMessage(CustomMessage message) {
        //将消息放入消息队列
        enqueueMessage(message);
    }

    private void enqueueMessage(CustomMessage message) {
        //赋值当前消息
        message.target = this;

        //使用mMessageQueue，将消息传入
        mMessageQueue.enqueueMessage(message);
    }

    public void dispatchMessage(CustomMessage message) {
        handleMessage(message);
    }
}
