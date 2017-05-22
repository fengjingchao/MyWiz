#### Handler的原理

>####创建Message

	Message msg = new Message();
			msg = Message.obtain();

>####创建Handler

	Handler mHandler = new Handler(){
		handleMessage(){
		
		}
	}
>####Handler用到的Looper何时创建的，是在应用启动时，ActivityThread.java的main()方法中
	
	1. public static void main(String[] args) {
        ...

        Looper.prepareMainLooper();//创建Looper,MessageQueue

        ...

        Looper.loop();//取出消息并处理
    }
	
	2. prepareMainLooper()的实现如下：
	
    3. public static void prepareMainLooper() {
        prepare(false);
        ...
    	}
	
	4. 转调prepare()方法
	 private static void prepare(boolean quitAllowed) {
        ...
        sThreadLocal.set(new Looper(quitAllowed));//创建Looper通过ThreadLocal绑定到主线程中
    }

	5. new Looper(quitAllowed)实现如下：
		
		private Looper(boolean quitAllowed) {

        mQueue = new MessageQueue(quitAllowed);//创建MessageQueue

    }

>####创建Handler时会用到主线程为我们创建的Looper和MessageQueue
	
	    public Handler(Callback callback, boolean async) {
   		...
        mLooper = Looper.myLooper();
       
        mQueue = mLooper.mQueue;
 		...
    }
	
>#### Looper.loop()方法的实现：

	  public static void loop() {
        final Looper me = myLooper();
       
        final MessageQueue queue = me.mQueue;


        for (;;) {//死循环，会不会把主线程卡死？？？
            Message msg = queue.next(); 
            
            msg.target.dispatchMessage(msg);//处理消息

            msg.recycle();
        }
    }

>####for (;;) {//死循环，会不会把主线程卡死？？？
		不会被卡死。
		涉及到Linux下的通讯机制，管道通讯的概念，管道就是内存中的一个特殊的一个文件，这个文件有两个句柄（其实就是java中的引用），一个是读的引用，一个是写的引用，当写的一端写入内容时，读的一端就会被唤醒，读的一端在没有读到消息时会休眠

>####Handler发送消息
	handler.sendMessage(msg);

	具体代码如下：
	public final boolean sendMessage(Message msg)
    {
        return sendMessageDelayed(msg, 0);
    }
    转调如下方法：
	 public boolean sendMessageAtTime(Message msg, long uptimeMillis) {
        MessageQueue queue = mQueue;
        
        return enqueueMessage(queue, msg, uptimeMillis);
    }
	转调如下方法：
 	private boolean enqueueMessage(MessageQueue queue, Message msg, long uptimeMillis) {

        msg.target = this;//将发送消息的handler绑定到消息上
        
        return queue.enqueueMessage(msg, uptimeMillis);//消息直接放入消息队列中
    }

>#### Looper.loop()方法不断的取消息，处理消息

	  public static void loop() {
        final Looper me = myLooper();//取出对应的Looper对象
       
        final MessageQueue queue = me.mQueue;


        for (;;) {
            Message msg = queue.next(); //取出刚刚发出去的消息
            //msg.target就是发这个消息的handler
            msg.target.dispatchMessage(msg);

            msg.recycle();
        }
    }
	转调如下方法：
  	public void dispatchMessage(Message msg) {
        if (msg.callback != null) {
            handleCallback(msg);
        } else {
            if (mCallback != null) {
                if (mCallback.handleMessage(msg)) {
                    return;
                }
            }
            handleMessage(msg);//调到子类复写的handleMessage()方法
        }
    }
	