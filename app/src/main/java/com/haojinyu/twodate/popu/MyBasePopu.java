package com.haojinyu.twodate.popu;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

public class MyBasePopu extends android.widget.PopupWindow {
	public Activity activity;

	public MyBasePopu(Activity activity) {
		this.activity = activity;
	}

	@Override
	public void showAsDropDown(View anchor) {
		super.showAsDropDown(anchor);
		hideSoftInput();
	}

	private void hideSoftInput() {
		InputMethodManager inputMethodManager = (InputMethodManager) activity
				.getSystemService(Context.INPUT_METHOD_SERVICE);
		if (activity.getCurrentFocus() != null) {
			inputMethodManager.hideSoftInputFromWindow(activity
					.getCurrentFocus().getWindowToken(), 0);
		}
	}
}
