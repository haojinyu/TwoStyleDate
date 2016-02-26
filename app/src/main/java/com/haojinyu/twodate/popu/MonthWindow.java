package com.haojinyu.twodate.popu;

import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.BitmapDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.LinearLayout.LayoutParams;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.haojinyu.twodate.adapter.DayAdapter;
import com.haojinyu.twodate.beans.DayPrice;
import com.haojinyu.twodate.beans.Month;
import com.haojinyu.twodate.R;

import java.util.ArrayList;
import java.util.List;

/**
 * 弹出日历
 */
public class MonthWindow extends MyBasePopu {
	private Activity activity;
	private DayAdapter adapter;
	private TextView tv_month_name;
	int  currMonth = 0,monthCount;
	private List<Month> all_list = new ArrayList<Month>();
	private List<DayPrice> day_list = new ArrayList<DayPrice>();
	private ImageButton ibtn_pre, ibtn_next;

	public MonthWindow(Activity activity, List<Month> all_list) {
		super(activity);
		this.activity = activity;
		this.all_list = all_list;
		initView();
		setPopupWindowAttribute();
	}

	private void initView() {
		LayoutInflater inflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		View rootView = inflater.inflate(R.layout.item_month, null);
		 ibtn_pre = (ImageButton) rootView.findViewById(R.id.ibtn_pre);
		 ibtn_next = (ImageButton) rootView.findViewById(R.id.ibtn_next);
		tv_month_name = (TextView) rootView.findViewById(R.id.tv_month_name);
		ibtn_pre.setVisibility(View.INVISIBLE);
		ibtn_next.setVisibility(View.VISIBLE);
		ibtn_pre.setOnClickListener(onClickListenter);
		ibtn_next.setOnClickListener(onClickListenter);
		GridView gridView = (GridView) rootView.findViewById(R.id.gridview);
		day_list .addAll(all_list.get(0).getDays());
		tv_month_name.setText(all_list.get(0).getName());
		adapter = new DayAdapter(activity, day_list);
		gridView.setAdapter(adapter);
		setContentView(rootView);
	}

	private void setPopupWindowAttribute() {
		setWidth(LayoutParams.WRAP_CONTENT);
		setHeight(LayoutParams.WRAP_CONTENT);
		setFocusable(true);
		setBackgroundDrawable(new BitmapDrawable());
	}

	/**
	 * 设置屏幕的背景透明度
	 * 
	 * @param bgAlpha
	 */
	public void backgroundAlpha(float bgAlpha) {
		Window window = activity.getWindow();
		WindowManager.LayoutParams lp = window.getAttributes();
		lp.alpha = bgAlpha;
		window.setAttributes(lp);
	}

	public void dismiss() {
		backgroundAlpha(1f);
		super.dismiss();

	}

	@Override
	public void showAsDropDown(View anchor) {
		super.showAsDropDown(anchor);
		hideSoftInput();
	}

	@Override
	public void showAtLocation(View parent, int gravity, int x, int y) {
		super.showAtLocation(parent, gravity, x, y);
		hideSoftInput();
	}

	/**
	 * 隐藏输入法
	 */
	private void hideSoftInput() {
		InputMethodManager inputMethodManager = (InputMethodManager) activity.getSystemService(Context.INPUT_METHOD_SERVICE);
		if (activity.getCurrentFocus() != null) {
			inputMethodManager.hideSoftInputFromWindow(activity.getCurrentFocus().getWindowToken(), 0);
		}
	}
	
	private OnClickListener onClickListenter = new OnClickListener() {

		@Override
		public void onClick(View view) {
			if (view.getId() == R.id.ibtn_pre) {
				currMonth = currMonth - 1;
				ibtn_next.setVisibility(View.VISIBLE);
			} else {
				currMonth = currMonth + 1;
				ibtn_pre.setVisibility(View.VISIBLE);
			}
			if (currMonth <= 0 || (currMonth+1) >= monthCount) {
				view.setVisibility(View.INVISIBLE);
			}
			day_list.clear();
			day_list.addAll(all_list.get(currMonth).getDays());
			adapter.notifyDataSetChanged();
			tv_month_name.setText(all_list.get(currMonth).getName());
		}
	};
}
