#Pull解析xml文件（掌握）
* 先自己写一个xml文件，存一些天气信息
###拿到xml文件
		
		InputStream is = getClassLoader().getResourceAsStream("weather.xml");
###拿到pull解析器

		XmlPullParser xp = Xml.newPullParser();

###开始解析
* 拿到指针所在当前节点的事件类型

		int type = xp.getEventType();
* 事件类型主要有五种
	* START_DOCUMENT：xml头的事件类型
	* END_DOCUMENT：xml尾的事件类型
	* START_TAG：开始节点的事件类型
	* END_TAG：结束节点的事件类型
	* TEXT：文本节点的事件类型

* 如果获取到的事件类型不是END_DOCUMENT，就说明解析还没有完成，如果是，解析完成，while循环结束

		while(type != XmlPullParser.END_DOCUMENT)
* 当我们解析到不同节点时，需要进行不同的操作，所以判断一下当前节点的name
	* 当解析到weather的开始节点时，new出list
	* 当解析到city的开始节点时，创建city对象，创建对象是为了更方便的保存即将解析到的文本
	* 当解析到name开始节点时，获取下一个节点的文本内容，temp、pm也是一样

			case XmlPullParser.START_TAG:
			//获取当前节点的名字
				if("weather".equals(xp.getName())){
					citys = new ArrayList<City>();
				}
				else if("city".equals(xp.getName())){
					city = new City();
				}
				else if("name".equals(xp.getName())){
					//获取当前节点的下一个节点的文本
					String name = xp.nextText();
					city.setName(name);
				}
				else if("temp".equals(xp.getName())){
					String temp = xp.nextText();
					city.setTemp(temp);
				}
				else if("pm".equals(xp.getName())){
					String pm = xp.nextText();
					city.setPm(pm);
				}
				break;

* 当解析到city的结束节点时，说明city的三个子节点已经全部解析完了，把city对象添加至list

		case XmlPullParser.END_TAG:
			if("city".equals(xp.getName())){
					citys.add(city);
			}


---
#测试（了解）
* 黑盒测试
	* 测试逻辑业务
* 白盒测试
	* 测试逻辑方法

* 根据测试粒度
	* 方法测试：function test
	* 单元测试：unit test
	* 集成测试：integration test
	* 系统测试：system test

* 根据测试暴力程度
	* 冒烟测试：smoke test
	* 压力测试：pressure test

------
#单元测试junit（熟悉）
* 定义一个类继承AndroidTestCase，在类中定义方法，即可测试该方法


* 在指定指令集时，targetPackage指定你要测试的应用的包名

		<instrumentation 
	    android:name="android.test.InstrumentationTestRunner"
	    android:targetPackage="com.itheima.junit"
	    ></instrumentation>

* 定义使用的类库

		<uses-library android:name="android.test.runner"></uses-library>

* 断言的作用，检测运行结果和预期是否一致
* 如果应用出现异常，会抛给测试框架

-----
#SQLite数据库（掌握）
* 轻量级关系型数据库
* 创建数据库需要使用的api：SQLiteOpenHelper
	* 必须定义一个构造方法：

			//arg1:数据库文件的名字
			//arg2:游标工厂
			//arg3:数据库版本
			public MyOpenHelper(Context context, String name, CursorFactory factory, int version){

			}
	* 数据库被创建时会调用：onCreate方法
	* 数据库升级时会调用：onUpgrade方法

###创建数据库

	//创建OpenHelper对象
	MyOpenHelper oh = new MyOpenHelper(getContext(), "person.db", null, 1);
	//获得数据库对象,如果数据库不存在，先创建数据库，后获得，如果存在，则直接获得
	SQLiteDatabase db = oh.getWritableDatabase();

* getWritableDatabase()：打开可读写的数据库
* getReadableDatabase()：在磁盘空间不足时打开只读数据库，否则打开可读写数据库
* 在创建数据库时创建表

		public void onCreate(SQLiteDatabase db) {
			// TODO Auto-generated method stub
			db.execSQL("create table person (_id integer primary key autoincrement, name char(10), phone char(20), money integer(20))");
		}

---
#数据库的增删改查（掌握）
###SQL语句
* insert into person (name, phone, money) values ('张三', '159874611', 2000);
* delete from person where name = '李四' and _id = 4;
* update person set money = 6000 where name = '李四';
* select name, phone from person where name = '张三';

###执行SQL语句实现增删改查

		//插入
		db.execSQL("insert into person (name, phone, money) values (?, ?, ?);", new Object[]{"张三", 15987461, 75000});
		//查找
		Cursor cs = db.rawQuery("select _id, name, money from person where name = ?;", new String[]{"张三"});
* 测试方法执行前会调用此方法

		protected void setUp() throws Exception {
			super.setUp();
			//					获取虚拟上下文对象
			oh = new MyOpenHelper(getContext(), "people.db", null, 1);
		}
###使用api实现增删改查
* 插入

		//以键值对的形式保存要存入数据库的数据
		ContentValues cv = new ContentValues();
		cv.put("name", "刘能");
		cv.put("phone", 1651646);
		cv.put("money", 3500);
		//返回值是改行的主键，如果出错返回-1
		long i = db.insert("person", null, cv);
* 删除

		//返回值是删除的行数
		int i = db.delete("person", "_id = ? and name = ?", new String[]{"1", "张三"});
* 修改
	
		ContentValues cv = new ContentValues();
		cv.put("money", 25000);
		int i = db.update("person", cv, "name = ?", new String[]{"赵四"});
* 查询

		//arg1:要查询的字段
		//arg2：查询条件
		//arg3:填充查询条件的占位符
		Cursor cs = db.query("person", new String[]{"name", "money"}, "name = ?", new String[]{"张三"}, null, null, null);
		while(cs.moveToNext()){
			//							获取指定列的索引值
			String name = cs.getString(cs.getColumnIndex("name"));
			String money = cs.getString(cs.getColumnIndex("money"));
			System.out.println(name + ";" + money);
		}

###事务
* 保证多条SQL语句要么同时成功，要么同时失败
* 最常见案例：银行转账
* 事务api

		try {
			//开启事务
			db.beginTransaction();
			...........
			//设置事务执行成功
			db.setTransactionSuccessful();
		} finally{
			//关闭事务
			//如果此时已经设置事务执行成功，则sql语句生效，否则不生效
			db.endTransaction();
		}

---
#把数据库的数据显示至屏幕（了解）
1. 任意插入一些数据
*  定义业务bean：Person.java
*  读取数据库的所有数据

		Cursor cs = db.query("person", null, null, null, null, null, null);
        while(cs.moveToNext()){
        	String name = cs.getString(cs.getColumnIndex("name"));
        	String phone = cs.getString(cs.getColumnIndex("phone"));
        	String money = cs.getString(cs.getColumnIndex("money"));
        	//把读到的数据封装至Person对象
        	Person p = new Person(name, phone, money);
        	//把person对象保存至集合中
        	people.add(p);
        }

* 把集合中的数据显示至屏幕

         LinearLayout ll = (LinearLayout) findViewById(R.id.ll);
         for(Person p : people){
			 //创建TextView，每条数据用一个文本框显示
        	 TextView tv = new TextView(this);
        	 tv.setText(p.toString());
			 //把文本框设置为ll的子节点
        	 ll.addView(tv);
         }
* 分页查询

		Cursor cs = db.query("person", null, null, null, null, null, null, "0, 10");

---
#ListView（掌握）
* 用于显示列表
* MVC结构
	* M：model模型层，要显示的数据           ————people集合
	* V：view视图层，用户看到的界面          ————ListView
	* c：control控制层，操作数据如何显示     ————adapter对象
* 每一个条目都是一个View对象
###BaseAdapter
* 必须实现的两个方法

	* 第一个

			//系统调用此方法，用来获知模型层有多少条数据
			@Override
			public int getCount() {
				return people.size();
			}

	* 第二个

			//系统调用此方法，获取要显示至ListView的View对象
			//position:是return的View对象所对应的数据在集合中的位置
			@Override
			public View getView(int position, View convertView, ViewGroup parent) {
				System.out.println("getView方法调用" + position);
				TextView tv = new TextView(MainActivity.this);
				//拿到集合中的元素
				Person p = people.get(position);
				tv.setText(p.toString());
				
				//把TextView的对象返回出去，它会变成ListView的条目
				return tv;
			}
* 屏幕上能显示多少个条目，getView方法就会被调用多少次，屏幕向下滑动时，getView会继续被调用，创建更多的View对象显示至屏幕
###条目的缓存
* 当条目划出屏幕时，系统会把该条目缓存至内存，当该条目再次进入屏幕，系统在重新调用getView时会把缓存的条目作为convertView参数传入，但是传入的条目不一定是之前被缓存的该条目，即系统有可能在调用getView方法获取第一个条目时，传入任意一个条目的缓存
###ArrayAdapter（熟悉）
* 在条目中显示一个字符串

		String[] objects = new String[]{
				"张三",
				"李四",
				"王五"
		};
		
		ListView lv = (ListView) findViewById(R.id.lv);
		//arg1：指定要填充的布局文件
		//arg2：指定文本显示至哪一个文本框内
		lv.setAdapter(new ArrayAdapter<String>(this, R.layout.item_array, R.id.tv_name, objects));
###SimpleAdapter（熟悉）
* 可在条目中显示多种数据
* 要显示的数据封装在List中，集合的每一个元素存放的是一个条目会显示的数据，因为可能会有多种数据，而集合的泛型只能指定一种数据，所以把数据先存放在Map中，在把Map放入List中

		 List<Map<String, Object>> data = new ArrayList<Map<String,Object>>();
		 
		 //张三的头像和名字是两种类型的数据，先封装至Map
		 Map<String, Object> map1 = new HashMap<String, Object>();
		 map1.put("name", "张三");
		 map1.put("image", R.drawable.photo1);
		 //把Map封装至List
		 data.add(map1);
		 ...

* 通过两个数组的下标对应指定数据存放入对应的控件中
		lv.setAdapter(new SimpleAdapter(this, data, R.layout.item_array, 
				new String[]{"name", "image"}, new int[]{R.id.tv_name, R.id.iv_photo}));

