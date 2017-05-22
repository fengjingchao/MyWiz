package com.itheima.news06.act.view;

import android.support.v4.view.ViewPager;
import android.view.View;

public class DepthPageTransformer implements ViewPager.PageTransformer {
    private static float MIN_SCALE = 0.75f;

    //当界面滑动时，实时调用
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
        //把最左边的界面设置为不可见
        if (position < -1) { // [-Infinity,-1)
            // This page is way off-screen to the left.
            view.setAlpha(0);

        } else if (position <= 0) { // [-1,0]
            // Use the default slide transition when moving to the left page
        	
//        	view 第一个view（当前页面的view） A页面
//        	position；[-0,-1] = [0,-1]
            view.setAlpha(1);
            view.setTranslationX(0);
            view.setScaleX(1);
            view.setScaleY(1);

        } else if (position <= 1) { // (0,1]
//        	view 第二个view（下一个页面的view）  B页面
//        	position:[1,0]
            // Fade the page out.
        	//根据positon的值，动态设置B页面的透明度
            view.setAlpha(1 - position);

            // Counteract the default slide transition
            //320*(-position)根据position给B页面设置X轴滑动，保证它永远显示在A页面的下方
            view.setTranslationX(pageWidth * -position);

            // Scale the page down (between MIN_SCALE and 1)
            //0.75+0.25*1   [0.75,1]
            //根据position实时设置B页面的缩放值取值范围：[0.75,1]
            float scaleFactor = MIN_SCALE
                    + (1 - MIN_SCALE) * (1 - Math.abs(position));
            view.setScaleX(scaleFactor);
            view.setScaleY(scaleFactor);

        } else { // (1,+Infinity]//把最右边的界面设置为不可以见
            // This page is way off-screen to the right.
            view.setAlpha(0);
        }
    }
}