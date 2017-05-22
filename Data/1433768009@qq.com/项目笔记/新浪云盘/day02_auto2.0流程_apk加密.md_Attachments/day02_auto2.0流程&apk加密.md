#新浪微盘
    1. 网址:http://vdisk.weibo.com/developers/index.php?module=api&action=upgrade&sudaref=vdisk.me

    2. 如果需要访问正式的空间,需要申请basic权限



##auto2.0实现流程
![](imgs/oAuth2_01.gif)


        第一次点击授权
        onPageStarted URL: https://auth.sina.com.cn/oauth2/authorize?client_id=2330724462&redirect_uri=http%3A%2F%2Fvauth.appsina.com%2Fcallback1.php&display=mobile
        
        输入用户名密码后处理的网页
        onPageStarted URL: https://auth.sina.com.cn/oauth2/authorize
        回调的网页
        http://vauth.appsina.com/callback1.php?code=b1517b3ba07405ddc84a45456544603b&state=
        
        去申请我们的accessKey
        https://auth.sina.com.cn/oauth2/access_token
        内容
         client_id=2330724462&client_secret=04f81fc56cc936bfc8f0fa1cef285158&grant_type=authorization_code&code=6e993fb507ba4daba546f94aa1d421c4&redirect_uri=http%3A%2F%2Fvauth.appsina.com%2Fcallback1.php
        
        返回的accessToken信息
        
        {
          "access_token" : "8860766663s7I3j2xJtU22Z5rTV76c4f",
          "expires_in" : 1459302292,
          "time_left" : 82259,
          "uid" : "3163973541",
          "refresh_token" : "87c4f46663s7I3j2xJtU22Z5rTVf443a"
        }

![](imgs/auto2.0.png)



##我们的apk如何进行加密(补充)
    1. 爱加密
    2. 绑绑加固
    3. 这两个是比较早,百度,360都有


##对象之间比较
    1. equals:内存地址
    2. 实际需要应该比较路径

##android studio下的图片
    1. mipmap用来存放我们的图片文件
    2. 提高效率
    3. 9.path不能放入
    4. selector不能放入

##android studio快捷键
    1. F11:做标签
    2. shift+F11:跳入标签选择项
    3. alt+鼠标:进入列编辑
    4. ctrl+shift+U:大小写切换
    5. ctrl+alt+V:提取参数

##如何定义一个接口
    1. 定义篮子(接口)
    2. 数据拥有者放数据
    3. 想要数据的去初始化我们的接口