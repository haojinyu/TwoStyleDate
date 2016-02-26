package com.haojinyu.twodate.adapter;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.text.Html;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.haojinyu.twodate.beans.DayPrice;
import com.haojinyu.twodate.R;

import java.util.List;

public class DayAdapter extends BaseAdapter<DayPrice> {

	public DayAdapter(Activity activity, List<DayPrice> list) {
		super(activity, list);
	}

	@SuppressLint("NewApi")
	public View getView(int position, View convertView, ViewGroup parent) {
		convertView = getItemViewNoCase(convertView, R.layout.item_day);
		TextView day_price = getView(convertView, R.id.day_price);
		TextView day = getView(convertView, R.id.day);
		DayPrice dayPrice = getItem(position);
		day.setText(dayPrice.getDay());
		if (!dayPrice.getPrice().equals("")) {
			String htmlPrice = "<small>￥</small><big>" + dayPrice.getPrice() + "<big>";
			day_price.setText(Html.fromHtml(htmlPrice));
		}
		convertView.setActivated(dayPrice.isCanClick());
		convertView.setClickable(dayPrice.isCanClick());
		return convertView;
	}
}
