package com.del.app.jsoup.activity;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

public abstract class BaseActivity extends AppCompatActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	}
	
	public abstract void setupView();
	public abstract void initLogic();
	public abstract void initListener();
}