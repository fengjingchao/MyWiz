package com.itheima.news06.act.view;

import java.util.HashMap;
import java.util.Map;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;

public class CustomViewPager extends ViewPager {

	public CustomViewPager(Context context, AttributeSet attrs) {
		super(context, attrs);
		// TODO Auto-generated constructor stub
	}

	public CustomViewPager(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
	}

	// 左边的界面
	private View left;
	// 右边的界面
	private View right;
	// 用来保存对应位置的view对象
	private Map<Integer, ImageView> mImages = new HashMap<Integer, ImageView>();
	
	//最小缩放值
	private static float MIN_SCALE = 0.75f;

	// 保存对应位置的view对象
	public void addChildView(ImageView imageView, int position) {
		mImages.put(position, imageView);
	}

	// 当viewpager做销毁操作时，同时删除mImages中对应的view对象
	public void removeChildView(int position) {
		mImages.remove(position);
	}

	// 当viewpager滚动时，实时调用

	/**
	 * position 被选中界面的索引位置 offset 界面滑动的比例值 offsetPixels 界面滑动过的像素值
	 */
	@Override
	protected void onPageScrolled(int position, float offset, int offsetPixels) {
		// System.out.println("position ="+position);
		// System.out.println("offset ="+offset);
		// System.out.println("offsetPixels ="+offsetPixels);
		super.onPageScrolled(position, offset, offsetPixels);
		left = mImages.get(position);
		right = mImages.get(position + 1);
		startAnimation(left, right, position, offset, offsetPixels);

	}

	//开始动画
	private void startAnimation(View left, View right, int position,
			float offset, int offsetPixels) {
//		 // (0,1]
////    	view 第二个view（下一个页面的view）  B页面
////    	position:[1,0]
//        // Fade the page out.
//    	//根据positon的值，动态设置B页面的透明度
//        view.setAlpha(1 - position);
//
//        // Counteract the default slide transition
//        //320*(-position)根据position给B页面设置X轴滑动，保证它永远显示在A页面的下方
//        view.setTranslationX(pageWidth * -position);
//
//        // Scale the page down (between MIN_SCALE and 1)
//        //0.75+0.25*1   [0.75,1]
//        //根据position实时设置B页面的缩放值取值范围：[0.75,1]
//        float scaleFactor = MIN_SCALE
//                + (1 - MIN_SCALE) * (1 - Math.abs(position));
//        view.setScaleX(scaleFactor);
//        view.setScaleY(scaleFactor);
		//右边的界面，X轴的平移，让右边界面在左边界面的下方；缩放操作
		if (right != null) {
			//实时根据像素值来计算B页面要移动的间距，为了保证在A页面的下方进行显示
			int translationX = -(getWidth() - offsetPixels);
			right.setTranslationX(translationX);
			//offset [0,1]
			//0.75 +0.25    [0.75,1]
			
			//根据offset百分比实时计算B页面的缩放值
			float scaleFactor = MIN_SCALE
	                + (1 - MIN_SCALE) * (Math.abs(offset));
	        right.setScaleX(scaleFactor);
	        right.setScaleY(scaleFactor);
			
		}
		
		if (left != null) {
			//让A页面显示到B页面的上方
			left.bringToFront();
		}
	}
}
