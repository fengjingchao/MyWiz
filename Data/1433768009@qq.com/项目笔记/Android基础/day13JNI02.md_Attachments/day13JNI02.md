# 交叉编译
* 在一个平台下编译出另一个平台可以执行的二进制程序
* CPU平台：arm，x86，mips
* 系统平台：Windows、Linux、Mac OS
* 原理：模拟另一个平台的特性去编译代码
	* 源代码->预编译->编译->链接->可执行程序
* 工具链：一个工具使用完毕自动使用下一个
# 常见工具
* NDK：native development kits
* CDT：C/C++ development tools
	* eclipse插件
	* 高亮显示C关键字
* cygwin：Windows平台下的Linux命令行模拟器
#NDK目录结构
* docs：帮助文档
* build/tools:Linux批处理文件
* platforms:存放开发jni用到的h头文件和so动态链接库
* prebuilt：预编译使用的工具
* sample：使用jni的案例
* source：NDK的部分源码
* toolchains：工具链
* ndk-build.cmd：编译打包C代码的指令

#JNI
###步骤
1. 定义并调用本地方法
2. 创建jni文件夹
3. jni文件夹里创建c文件
4. c文件中实现本地方法，格式如下

		//返回值与本地方法一致
		//函数名：Java_包名_类名_本地方法名
		//env:结构体二级指针，该结构体中封装了大量的函数指针，可以帮助程序员实现某些常用功能
		//thiz:本地方法调用者的对象(MainActivity的对象)
		jstring Java_com_itheima_helloworld1_MainActivity_helloFromC(JNIEnv* env, jobject thiz)
5. 创建Android.mk文件，指定要编译的c文件
6. 在jni目录下，执行ndk-build.cmd，编译打包出so动态链接库
7. 在java代码中加载动态链接库
8. 部署，运行
###常见错误
* 找不到类库
	* 没有添加对应平台的支持
	* 类库名写错了
* 找不到本地方法
	* 忘记加载类库
	* c函数名写错了
###javah指令
* 自动生成jni样式的头文件，头文件中就包含了我们需要的函数名
* 1.7：在src目录下使用：javah com.itheima.helloworld2.MainActivity
* 1.6：在bin/classes目录下使用：
###添加本地支持
* 自动生成jni文件夹
* 自动生成c文件和Android.mk文件
* 指定jni.h头文件的路径，相当于关联源码
* 不需要再去jni目录下使用ndk-build.cmd指令，项目部署时，会先打包编译so类库再去部署到手机上
###数组传递
* java的数组是对象，传递对象是传递对象的地址，c函数中修改了地址上的值，所以数组的值就改变了
###javap
* 打印指定类中所有方法的签名
* 在bin/classes目录下使用：javap -s com.itheima.helloworld2.MainActivity