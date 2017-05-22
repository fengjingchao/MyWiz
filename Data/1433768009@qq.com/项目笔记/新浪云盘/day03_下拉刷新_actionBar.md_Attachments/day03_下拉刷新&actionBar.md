##新浪微盘第三天

##下拉刷新
    1. 导入库
    2. pulltorefresh_Lib
    3. 初始化我们的下拉刷新控件
        1. <com.handmark.pulltorefresh.library.PullToRefreshListView
        android:layout_width="match_parent"
        android:id="@+id/ptrlv_main_show"
        android:layout_height="match_parent"
        ></com.handmark.pulltorefresh.library.PullToRefreshListView>

    4. 初始化我们的listView
        1.  //获取我们的listview
        mLv_main_show = mPtrlv_main_show.getRefreshableView();
    5. 正常的listView初始化
    6. 设置下拉刷新监听 
        1.  //设置下拉刷新的监听
        mPtrlv_main_show.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener() {
            @Override
            public void onRefresh() {

    7. 注意这个耗时要开线程
    8. 停止我们的下拉刷新状态
        1.  //下拉刷新完成
                        mPtrlv_main_show.onRefreshComplete();
    9. 下拉刷新注意当前postion
        1.  position = position - mLv_main_show.getHeaderViewsCount();//减去我们的头部


##actionbar
    1. actionbar 3.0 
    2. toolbar 5.0兼容包兼容2.2
    3. sherlockaction:兼容2.1

##集成方式actionbar
    1. 导入actionbarsherlock
    2. 样式   
        3. <!-- Sherlock样式修改 -->
        <style name="Theme.HM" parent="@style/Theme.Sherlock.Light">
            这里我们actionbar样式
            <item name="android:actionBarStyle">@style/Widget.HM.ActionBar</item>
                // 设置箭头样式
            <item name="android:homeAsUpIndicator">@mipmap/home_up_icon</item>
        </style>
            //指定背景
        <style name="Widget.HM.ActionBar" parent="Widget.Sherlock.Light.ActionBar.Solid.Inverse">
            <item name="android:background">@drawable/bg_actionbar</item>
        </style>
    3. 初始化actionbar 
        1.    //我们设置icon隐藏
        ActionBar actionbar = getSupportActionBar();//得到支持的actionbar
        actionbar.setDisplayShowHomeEnabled(false);
        //把我们title设置上海黑马6期网盘
        actionbar.setTitle(Contains.ActionBar.title);
    4. 初始化界面
        1.  @Override
            public boolean onCreateOptionsMenu(Menu menu) {
                getSupportMenuInflater().inflate(R.menu.actionbar,menu);
                return super.onCreateOptionsMenu(menu);
            }
    5. 
       <menu xmlns:android="http://schemas.android.com/apk/res/android">
            //子的菜单条目
        <item
            android:title="上传"
            android:icon="@mipmap/icon_upload"
            android:showAsAction="always">
            //always一直显示
        
        </item><item
            android:title="下载"
            android:icon="@mipmap/icon_download"
            android:showAsAction="always">
        
        </item><item
            android:title="更多操作"
            android:icon="@mipmap/icon_action_home_category"
            android:showAsAction="always">
        //  子条目点击后弹出来的菜单
        <menu
            >
            <item
                android:title="新建文件夹"
                android:showAsAction="never"></item>
           //never隐藏
            <item
                android:title="上传"
                android:showAsAction="never"></item>
            <item
                android:title="注销"
                android:showAsAction="never"></item>
        </menu>
        </item>



##当前actionbar 标题点击事件
  @Override
    public boolean onMenuItemSelected(int featureId, MenuItem item) {
        //拿到我们的item的id
        switch (item.getItemId()) {

            case   android.R.id.home:

新浪微盘三个小知识点,面试时可说
     *          1. 统一网络处理
     *          2. 统一错误处理
     *          3. 发送请求菜单了,一行代码


##as快捷键 
    1. ctrl+alt+H:查看方法被调用
    2. ctrl+H:查看类继承
    3. ctrl+alt+B:查看方法实现
    4. ctrl+alt+P:参数提取到方法体内