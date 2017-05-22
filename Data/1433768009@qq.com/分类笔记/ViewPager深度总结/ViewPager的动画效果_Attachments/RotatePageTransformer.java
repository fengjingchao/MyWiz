package com.itheima.news06.act.view;

import android.support.v4.view.ViewPager;
import android.view.View;

public class RotatePageTransformer implements ViewPager.PageTransformer{
	 private static float MAX_ROTATE = 25f;//最大旋转角度

	@Override
	public void transformPage(View view, float position) {

    	
    	System.out.println("view="+view);
    	System.out.println("position="+position);
    	
//    	view 第一个view（当前页面的view）
//    	position；[-0,-1] = [0,-1]
//
//    	view 第二个view（下一个页面的view）
//    	position:[1,0]
    	
    	
    	//320 屏幕的宽度
        int pageWidth = view.getWidth();
        System.out.println("pageWidth="+pageWidth);
        //把最左边的界面设置为不旋转
        if (position < -1) { // [-Infinity,-1)
            // This page is way off-screen to the left.
            view.setRotation(0);

        } else if (position <= 0) { // [-1,0]
        	
//        	view 第一个view（当前页面的view） A页面
//        	position；[-0,-1] = [0,-1]
        	//让A页面从0度旋转到-25 [0,-25]
        	//25*-1
        	float rotate =  (MAX_ROTATE * position);
        	view.setRotation(rotate);
        	//设置中心点，X轴的一半，y轴的整个高度
        	view.setPivotX(pageWidth/2);
        	view.setPivotY(view.getMeasuredHeight());

        } else if (position <= 1) { // (0,1]
//        	view 第二个view（下一个页面的view）  B页面
//        	position:[1,0]
        	//取值范围是[25，0]
        	//25*0
        	float rotate = MAX_ROTATE*position;
        	view.setRotation(rotate);
        	//设置中心点，X轴的一半，y轴的整个高度
        	view.setPivotX(pageWidth/2);
        	view.setPivotY(view.getMeasuredHeight());

        } else { // (1,+Infinity]//把最右边的界面设置为不旋转
            // This page is way off-screen to the right.
            view.setRotation(0);
        }
    
	}

}
