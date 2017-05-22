package com.itheima.news06.act;

/**
 * ============================================================
 * 
 * 版 权 ： 黑马程序员教育集团 版权所有 (c) 2016
 * 
 * 作 者 :徐鑫
 * 
 * 版 本 ： 1.0
 * 
 * 创建日期 ： 2016-3-18 上午10:23:24
 * 
 * 描 述 ：引导界面
 * 
 * 
 * 修订历史 ：xxx
 * 
 * ============================================================
 **/

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;

import com.itheima.news06.R;
import com.itheima.news06.act.view.RotatePageTransformer;
import com.itheima.news06.utils.DensityUtil;

public class GuideActivity extends Activity {
	private ViewPager viewpager;
	private int[] imageResIds = { R.drawable.guide_1, R.drawable.guide_2,
			R.drawable.guide_3 };// 引导界面数据
	private List<ImageView> images;// 数据集合
	private Button button;
	private LinearLayout ll_point_group;// 普通点的容器
	private View dot_focus;// 选中点
	private int dot_width;//点之间的间距

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		initView();
	}

	// 界面初始化方法
	private void initView() {
		setContentView(R.layout.activity_guide);
		ll_point_group = (LinearLayout) findViewById(R.id.ll_point_group);
		dot_focus = findViewById(R.id.dot_focus);
		viewpager = (ViewPager) findViewById(R.id.viewpager);
		button = (Button) findViewById(R.id.button);
		button.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Intent intent = new Intent(GuideActivity.this,
						MainActivity.class);
				startActivity(intent);
				GuideActivity.this.finish();
			}
		});
		initData();
		viewpager.setAdapter(new GuideAdapter());
		viewpager.setPageTransformer(true, new RotatePageTransformer());
		// viewpager.setPageTransformer(true, new DepthPageTransformer ());
		// viewpager.setPageTransformer(true, new ZoomOutPageTransformer());
		viewpager.setOnPageChangeListener(new OnPageChangeListener() {

			// 页面选中方法
			@Override
			public void onPageSelected(int position) {
				if (position == images.size() - 1) {
					button.setVisibility(View.VISIBLE);
				} else {
					button.setVisibility(View.GONE);
				}
			}
			
			//position 被选中界面的索引位置 positionOffset 界面滑动的比例值 positionOffsetPixels 界面滑动过的像素值
			@Override
			public void onPageScrolled(int position, float positionOffset,
					int positionOffsetPixels) {
				//根据计算，实时设置选中点的平移，让他跟随我们手指进行滑动
				int translationX = (int) (dot_width*(position + positionOffset));
				dot_focus.setTranslationX(translationX);
			}

			@Override
			public void onPageScrollStateChanged(int state) {
				// TODO Auto-generated method stub

			}
		});
		
		dot_focus.postDelayed(new Runnable() {
			

			@Override
			public void run() {
				dot_width = ll_point_group.getChildAt(1).getLeft() - ll_point_group.getChildAt(0).getLeft();
				System.out.println("dot_width="+dot_width);
			}
		}, 20);
		
	}

	// 数据初始化方法
	private void initData() {
		images = new ArrayList<ImageView>();
		for (int i = 0; i < imageResIds.length; i++) {
			ImageView iv = new ImageView(getApplicationContext());
			// iv.setImageResource(imageResIds[i]);
			iv.setBackgroundResource(imageResIds[i]);
			images.add(iv);

			View view = new View(getApplicationContext());
			// 代码中直接写数字实际上像素
			LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
					DensityUtil.dip2px(getApplicationContext(), 10),
					DensityUtil.dip2px(getApplicationContext(), 10));
			if (i != 0) {
				params.leftMargin = DensityUtil.dip2px(getApplicationContext(), 10);
			}
			view.setBackgroundResource(R.drawable.dot_normal);
			view.setLayoutParams(params);
			
			ll_point_group.addView(view);
		}
	}

	private class GuideAdapter extends PagerAdapter {

		@Override
		public int getCount() {
			// TODO Auto-generated method stub
			return images.size();
		}

		@Override
		public boolean isViewFromObject(View view, Object object) {
			// TODO Auto-generated method stub
			return view == object;
		}

		@Override
		public Object instantiateItem(ViewGroup container, int position) {
			container.addView(images.get(position));
			// viewpager.addChildView(images.get(position),position);
			return images.get(position);
		}

		@Override
		public void destroyItem(ViewGroup container, int position, Object object) {
			container.removeView((View) object);
			// viewpager.removeChildView(position);
		}

	}

}
