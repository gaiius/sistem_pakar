package com.lee.sispak;

import android.os.Bundle;
import android.view.MenuItem;

public class act_panduan extends SlidingActivity {
	@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.panduan);
        setTitle("Panduan");
	}
	
	public boolean onOptionsItemSelected(MenuItem m)
	{
		finish();
		return true;
	}
}
