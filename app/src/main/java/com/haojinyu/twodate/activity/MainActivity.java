package com.haojinyu.twodate.activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.haojinyu.twodate.adapter.MonthAdapter;
import com.haojinyu.twodate.beans.DayPrice;
import com.haojinyu.twodate.beans.Month;
import com.haojinyu.twodate.R;
import com.haojinyu.twodate.popu.MonthWindow;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class MainActivity extends Activity {
	/** 当天日期 */
	int currDay;
	/** 可点击未来多少天的 */
	int count = 30;
	/** 显示多少个月 */
	int monthCount = 2;
	private ListView listView;
	private MonthWindow monthWindow;
	private List<Month> monthList = new ArrayList<Month>();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		monthList = getMonth();
		listView = (ListView) findViewById(R.id.listView);
		MonthAdapter adapter = new MonthAdapter(this, monthList);
		listView.setAdapter(adapter);
	}

	/**
	 * 获取月
	 * 
	 * @return
	 */
	private List<Month> getMonth() {
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.DAY_OF_MONTH, 1);
		currDay = cal.get(Calendar.DAY_OF_MONTH);
		cal.add(Calendar.DAY_OF_MONTH, -1);
		List<Month> listMonth = new ArrayList<Month>();
		listMonth.add(getDays(cal));
		for (int i = 1; i < monthCount; i++) {
			cal.add(Calendar.MONTH, 1);
			listMonth.add(getDays(cal));
		}
		return listMonth;
	}

	/**
	 * 获取
	 * 
	 * @return
	 */
	public Month getDays(Calendar cal) {
		int year = cal.get(Calendar.YEAR);
		int month = cal.get(Calendar.MONTH) + 1;
		int days = cal.getActualMaximum(Calendar.DATE);// 天数
		cal.set(Calendar.DAY_OF_MONTH, 1);
		int dayPosition = cal.get(Calendar.DAY_OF_WEEK);
		List<DayPrice> list = new ArrayList<DayPrice>();
		for (int i = 0; i < dayPosition - 1; i++) {
			list.add(new DayPrice("", "", false));
		}
		for (int i = 1; i <= days; i++) {
			if (count >= 0 && (i >= currDay || count < 30)) {
				list.add(new DayPrice(i + "", (i + 50) + "", true));
				count--;
			} else {
				list.add(new DayPrice(i + "", "", false));
			}
		}
		return new Month(year + "年" + month + "月", list);
	}

	/**
	 * 点击获取某天的信息
	 * 
	 * @param view
	 */
	public void onClick(View view) {
		switch (view.getId()) {
		case R.id.layout_day:
			View pView = (View) view.getParent().getParent();
			TextView tv_month_name = (TextView) pView.findViewById(R.id.tv_month_name);
			TextView tv_day = (TextView) view.findViewById(R.id.day);
			TextView tv_day_price = (TextView) view.findViewById(R.id.day_price);
			String info = tv_month_name.getText().toString() + tv_day.getText() + "," + tv_day_price.getText();
			Toast.makeText(this, info, Toast.LENGTH_SHORT).show();
			break;
		case R.id.showPopu:
			if (monthWindow == null) {
				monthWindow = new MonthWindow(this, monthList);
			}
			monthWindow.showAtLocation(view, Gravity.CENTER, 0, 0);
			monthWindow.backgroundAlpha(0.5f);
			break;
		default:
			break;
		}
	}
}
