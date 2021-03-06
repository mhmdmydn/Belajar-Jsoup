package com.del.app.jsoup.fragment;

import androidx.fragment.app.Fragment;
import android.os.Bundle;
import android.view.View;

public abstract class BaseFragment extends Fragment {

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	}
	
	public abstract void setupView(View v);
	public abstract void initLogic(View v);
	public abstract void initListener(View v);
}