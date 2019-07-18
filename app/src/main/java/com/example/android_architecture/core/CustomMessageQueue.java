package com.example.android_architecture.core;

import com.example.android_architecture.core.CustomMessage;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class CustomMessageQueue {

    //阻塞队列
    BlockingQueue<CustomMessage> blockingQueue = new ArrayBlockingQueue<>(50);

    public void enqueueMessage(CustomMessage message) {
        try {
            blockingQueue.put(message);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    //从消息队列中取出消息
    public CustomMessage next() {
        try {
            return blockingQueue.take();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return  null;
    }
}
