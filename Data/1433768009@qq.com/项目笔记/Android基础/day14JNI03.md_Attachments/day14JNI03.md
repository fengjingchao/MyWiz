#锅炉检测
* C代码是执行在主线程的，会导致阻塞

#自定义竖型进度条
* 自定义控件，继承View
* 三个构造方法
* 布局文件中使用自定义控件要写包名
* onDraw方法用于在组件内绘制内容，图形或者文字都是通过这个方法绘制到界面上的

#C++实现JNI
* C++中的JNIEnv和C的JNIEnv不是同一个结构体
* C++的 JNIEnv 是jni.h中定义的 _JNIEnv
* C的 JNIEnv 是jni.h中定义的 JNINativeInterface*
* _JNIEnv结构体中的函数其实就是调用了JNINativeInterface中的同名函数指针
* C++中函数要先声明才能使用

#分支C进程
* fork函数分支一个C进程，返回子进程的pid
* 子进程执行fork函数时不会再分支进程了，返回0