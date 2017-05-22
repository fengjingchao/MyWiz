#查询联系人信息
* 使用Phone.CONTENT_URI可以查到联系人详细信息
* 包含
	* contact_id：联系人id
	* data1:号码
	* display_name：联系人姓名
#群组数据库
* groups表：保存群组信息
	* _id
	* name
	* create_date
	* thread_count
* thread_group表：保存群组和会话的映射关系
	* _id
	* group_id
	* thread_id