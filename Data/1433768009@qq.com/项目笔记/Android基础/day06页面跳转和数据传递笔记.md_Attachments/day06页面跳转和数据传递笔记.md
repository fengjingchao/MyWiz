#创建第二个Activity（掌握）
* 需要在清单文件中为其配置一个activity标签
* 标签中如果带有这个子节点，则会在系统中多创建一个快捷图标

		 <intent-filter>
             <action android:name="android.intent.action.MAIN" />
             <category android:name="android.intent.category.LAUNCHER" />
         </intent-filter>
* 一个应用程序可以在桌面创建多个快捷图标。
* activity的名称、图标可以和应用程序的名称、图标不相同

		android:icon="@drawable/ic_launcher"
        android:label="@string/app_name"

---
#Activity的跳转（掌握）
>Activity的跳转需要创建Intent对象，通过设置intent对象的参数指定要跳转Activity
>
>通过设置Activity的包名和类名实现跳转，称为显式意图
>
>通过指定动作实现跳转，称为隐式意图
###显式意图（掌握）
* 跳转至同一项目下的另一个Activity，直接指定该Activity的字节码即可

		Intent intent = new Intent();
		intent.setClass(this, SecondActivity.class);
    	startActivity(intent);
* 跳转至其他应用中的Activity，需要指定该应用的包名和该Activity的类名

		Intent intent = new Intent();
		//启动系统自带的拨号器应用
		intent.setClassName("com.android.dialer", "com.android.dialer.DialtactsActivity");
		startActivity(intent);

###隐式意图（掌握）
* 隐式意图跳转至指定Activity

		Intent intent = new Intent();
		//启动系统自带的拨号器应用
    	intent.setAction(Intent.ACTION_DIAL);
    	startActivity(intent);
* 要让一个Activity可以被隐式启动，需要在清单文件的activity节点中设置intent-filter子节点

		<intent-filter >
            <action android:name="com.itheima.second"/>
            <data android:scheme="asd" android:mimeType="aa/bb"/>
            <category android:name="android.intent.category.DEFAULT"/>
        </intent-filter>
	* action 指定动作（可以自定义，可以使用系统自带的）
	* data   指定数据（操作什么内容）
	* category 类别 （默认类别，机顶盒，车载电脑）
* 隐式意图启动Activity，需要为intent设置以上三个属性，且值必须与该Activity在清单文件中对三个属性的定义匹配
* intent-filter节点及其子节点都可以同时定义多个，隐式启动时只需与任意一个匹配即可
#####获取通过setData传递的数据（掌握）

		//获取启动此Activity的intent对象
		Intent intent = getIntent();
		Uri uri = intent.getData();
###显式意图和隐式意图的应用场景（掌握）
* 显式意图用于启动同一应用中的Activity
* 隐式意图用于启动不同应用中的Activity
	* 如果系统中存在多个Activity的intent-filter同时与你的intent匹配，那么系统会显示一个对话框，列出所有匹配的Activity，由用户选择启动哪一个
	
---
#Activity跳转时的数据传递（掌握）
* Activity通过Intent启动时，可以通过Intent对象携带数据到目标Activity

		Intent intent = new Intent(this, SecondActivity.class);
    	intent.putExtra("maleName", maleName);
    	intent.putExtra("femaleName", femaleName);
    	startActivity(intent);
* 在目标Activity中取出数据

		Intent intent = getIntent();
		String maleName = intent.getStringExtra("maleName");
		String femaleName = intent.getStringExtra("femaleName");

---
#Activity生命周期（掌握）
###void onCreate()
* Activity已经被创建完毕
###void onStart()
* Activity已经显示在屏幕，但没有得到焦点
###void onResume()
* Activity得到焦点，可以与用户交互
###void onPause()
* Activity失去焦点，无法再与用户交互，但依然可见
###void onStop()
* Activity不可见，进入后台
###void onDestroy()
* Activity被销毁
###void onRestart()
* Activity从不可见变成可见时会执行此方法
###使用场景
* Activity创建时需要初始化资源，销毁时需要释放资源；或者播放器应用，在界面进入后台时需要自动暂停

###完整生命周期（entire lifetime）
onCreate-->onStart-->onResume-->onPause-->onStop-->onDestory

###可视生命周期（visible lifetime）
onStart-->onResume-->onPause-->onStop

###前台生命周期（foreground lifetime）
onResume-->onPause

---
#Activity的四种启动模式（掌握）
>每个应用会有一个Activity任务栈，存放已启动的Activity
>
>Activity的启动模式，修改任务栈的排列情况

* standard 标准启动模式
* singleTop 单一顶部模式 
	* 如果任务栈的栈顶存在这个要开启的activity，不会重新的创建activity，而是复用已经存在的activity。保证栈顶如果存在，不会重复创建。
	* 应用场景：浏览器的书签
* singeTask 单一任务栈，在当前任务栈里面只能有一个实例存在
	* 当开启activity的时候，就去检查在任务栈里面是否有实例已经存在，如果有实例存在就复用这个已经存在的activity，并且把这个activity上面的所有的别的activity都清空，复用这个已经存在的activity。保证整个任务栈里面只有一个实例存在
	* 应用场景：浏览器的activity
	* 如果一个activity的创建需要占用大量的系统资源（cpu，内存）一般配置这个activity为singletask的启动模式。webkit内核 c代码

* singleInstance启动模式非常特殊， activity会运行在自己的任务栈里面，并且这个任务栈里面只有一个实例存在
	* 如果你要保证一个activity在整个手机操作系统里面只有一个实例存在，使用singleInstance
	* 应用场景： 电话拨打界面

---
##横竖屏切换的生命周期（熟悉）
>默认情况下 ，横竖屏切换， 销毁当前的activity，重新创建一个新的activity
>
> 快捷键ctrl+F11

在一些特殊的应用程序常见下，比如游戏，不希望横竖屏切换activity被销毁重新创建
需求：禁用掉横竖屏切换的生命周期

1. 横竖屏写死 
		android:screenOrientation="landscape"
		android:screenOrientation="portrait"

2. 让系统的环境 不再去敏感横竖屏的切换。
	
		 android:configChanges="orientation|screenSize|keyboardHidden"

---
#掌握开启activity获取返回值（掌握）
###从A界面打开B界面， B界面关闭的时候，返回一个数据给A界面
#####步骤：

1. 开启activity并且获取返回值

		startActivityForResult(intent, 0);
2. 在新开启的界面里面实现设置数据的逻辑
	
		Intent data = new Intent();
		data.putExtra("phone", phone);
		//设置一个结果数据，数据会返回给调用者
		setResult(0, data);
		finish();//关闭掉当前的activity，才会返回数据

3. 在开启者activity里面实现方法

		//通过data获取返回的数据
		onActivityResult(int requestCode, int resultCode, Intent data) {
		
		}
4. 通过判断请求码和结果码确定返回值的作用
