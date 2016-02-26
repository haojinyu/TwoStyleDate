package com.haojinyu.twodate.adapter;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.TextView;

import com.haojinyu.twodate.beans.Month;
import com.haojinyu.twodate.R;

import java.util.List;

public class MonthAdapter extends BaseAdapter<Month> {

	public MonthAdapter(Activity activity, List<Month> list) {
		super(activity, list);
	}

	public View getView(int position, View convertView, ViewGroup parent) {
		convertView = getItemViewNoCase(convertView, R.layout.item_month);
		TextView tv_month_name = getView(convertView, R.id.tv_month_name);
		GridView gridview = getView(convertView, R.id.gridview);
		Month month =getItem(position);
		tv_month_name.setText(month.getName());
		DayAdapter dayAdapter =new DayAdapter(activity,month.getDays());
		gridview.setAdapter(dayAdapter);
		return convertView;
	}
}
