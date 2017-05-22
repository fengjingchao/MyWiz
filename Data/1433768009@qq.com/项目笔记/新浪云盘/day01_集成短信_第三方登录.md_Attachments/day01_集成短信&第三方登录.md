#新浪微盘
    1. 主要讲什么
        1. 登录
        2. auth2.0
        3. 第三方的在as中的集中
        4. 新浪微盘
    2. 理解auto2.0的理念
    3. 总结的出来的第三方集中的步骤
##今天的内容
    1. 发送短信,接收验证码进行登录
    2. 第三方登录(单点登录)
    3. 传统的登录方法

##会话维持
    1. session了解
        1. 浏览器单独
        2. 手动退出
        3. 超时
        4. cookie,一个文本存了一些信息

    2. 我们进行模仿,把我们登录的信息放到一个sp,db,file


##传统的登录方式
![](imgs/login.png)

##sessionKey跟我们tonkenKey
    1. 暂时把这个放一下,第三方的QQ,微博...
##发送短信
    1. 美团的验证码是怎么做

![](imgs/sms_login.png)

##短信集成的步骤
    http://www.mob.com/#/官网
    1. 注册帐号
    2. 新建我们应用
    3. App Key,App Secret
    4. 下载sdk,
    5. 查看一下sdk文档
    6. DEMO运行起来


##短信集成DEMO步骤
    1. 是否有依赖的库
    2. 是否有jar包
    3. 是否有jni
    4. 点击事件
    5. 查看activity的oncrate方法
    6. 解决赋值的错误
    7. 权限
    8. 如果出错不明真理的错误,查看文档
    9. 把我们的App Key,App Secret替换掉
    10. 注意:后台的智能验证需要关闭

##什么是平台
    1. 提供资源
    2. 提供服务

##QQ集成
    1. 平台
    2. http://open.qq.com/
    3. 参考上面

##QQ集成引出来auto2.0
    1. 申请授权
    2. 拿到授权结果-->accessToken
    3. 第三步拿到用户信息去注册
        1. app端拿数据
        2. 服务器端:通常

##QQ登录的概念
    1. sso,app上如果有QQ,直接登陆,如果没有使用webView控件进行登陆


##新浪微博
    1. 注册新浪微博这个需要创建
        1. Android包名
        2. Android签名
        3. 回调页:必须是一个不能访问的页面


##android studio快捷键
    ctrl+alt+F:全局查找
    ctrl+alt+home:对应的布局界面跟我们的activity切换 
    ctrl+W:选中单词
    ctrl_shift+enter:向上一行插入
    F2:定位到错误
    alt+enter:修正 
    ctrl+B:进入代码
    ctrl+shift+a:万能命令行输入split分隔
    ctrl+F12:查看方法
    ctrl+d:复制粘贴到下一行
    ctrl+p:查看参数 
    ctrl+shift+a:万能命令行输入 data to 


##科学上网google
   网址: http://world.22ba.com/article/google-hosts.html
   路径: C:\Windows\System32\drivers\etc

##项目集
    http://www.trinea.cn/android/android-open-project-summary/


##数字公式
在资料里面

##这里chrome
    1. Adblock Plus:这个清屏
    2. Octotree:github上树型查看工程
    