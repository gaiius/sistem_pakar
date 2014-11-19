package com.lee.sispak;

import android.os.Bundle;
import android.view.MenuItem;

public class act_profil extends SlidingActivity {
	@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.panduan);
        setTitle("Profil");
	}
	
	public boolean onOptionsItemSelected(MenuItem m)
	{
		finish();
		return true;
	}
}
