package com.example.android_architecture.core;

public class CustomLooper {
    static final ThreadLocal<CustomLooper> sThreadLocal = new ThreadLocal<CustomLooper>();
    final CustomMessageQueue mQueue;

    private CustomLooper(){
        mQueue = new CustomMessageQueue();
    }

    public static void prepare() {
        if (sThreadLocal.get() != null) {
            throw new RuntimeException("Only one CustomLooper may be created per thread");
        }
        sThreadLocal.set(new CustomLooper());
    }

    public static CustomLooper myLooper() {
        return sThreadLocal.get();
    }

    public static void loop() {
        //从全局ThreadLocalMap中获取唯一， looper对象
      CustomLooper customLooper = myLooper();
        CustomMessageQueue mQueue = customLooper.mQueue;

        while (true){
            CustomMessage message = mQueue.next();
            if(message != null ){
                message.target.dispatchMessage(message);
            }
        }
    }
}
