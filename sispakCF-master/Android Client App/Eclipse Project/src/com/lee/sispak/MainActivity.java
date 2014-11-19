package com.lee.sispak;

import java.util.List;

import org.json.JSONObject;

import android.app.ListActivity;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.ListView;
import android.widget.Toast;

public class MainActivity extends ListActivity {
	
	// ip localhost komputer, jika diakses dari emulator adalah 10.0.2.2
	//public static final String SERVER_URL = "http://10.0.2.2/sispakCF";
	
	// testing dari web online
	public static final String SERVER_URL = "http://cf.konglie.web.id";
	
	public static List<JSONObject> DAFTAR_GEJALA = null;
	public static List<JSONObject> DAFTAR_KOMPONEN = null;
	
	public static final int MENU_PANDUAN 		= 0;
	public static final int MENU_IDENTIFIKASI 	= 1;
	public static final int MENU_KAMUS_GEJALA	= 2;
	public static final int MENU_KAMUS_KOMPONEN = 3;
	public static final int MENU_PROFIL			= 4;
	
	static final String[] menus = new String[]{
			"Panduan", 					//	0
			"Identifikasi Kerusakan", 	//	1
			"Kamus Istilah Gejala", 	// 	2
			"Kamus Komponen",			// 	3 
			"Profil"					// 	4
		};
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);	
		setListAdapter( new HomeMenuAdapter(this, R.layout.activity_home, menus) );
		Utils.showInfoMessage(this, "test"); 
	}
	
	@Override
	protected void onStart(){
		super.onStart();
		this.overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_right);
	}
	
	@Override
	protected void onListItemClick(ListView l, View v, int position, long id) {
		
		switch(position){
			case MENU_PANDUAN 			: launchAct(act_panduan.class); break;
			case MENU_IDENTIFIKASI 		:
				Utils.showMessage(this, "Informasi", "Aplikasi ini hanya bertujuan mendemonstrasikan " +
						"Sistem Pakar dengan metode Perhitungan CF.<br/>" +
						"Keakuratan data yang disajikan <strong>tidak dapat diandalkan</strong>.<br/>" +
						"&nbsp;<br/>" +
						"<em>Source Code</em> aplikasi dapat didownload di<br/>" +
						"<a href='http://www.konglie.web.id'>http://www.konglie.web.id</a>",
						android.R.drawable.ic_dialog_alert, 
						new android.content.DialogInterface.OnClickListener(){
							@Override
							public void onClick(DialogInterface arg0, int arg1) {
								MainActivity.this.runOnUiThread(new Runnable(){
									public void run(){
										launchAct(act_pilih_hape.class);
									}
								});
							}
						}			
				);  
			break;
			case MENU_KAMUS_GEJALA 		: launchAct(act_kamusgejala.class); break;
			case MENU_KAMUS_KOMPONEN 	: launchAct(act_kamuskomponen.class); break;
			case MENU_PROFIL 			: launchAct(act_profil.class); break;
		}
	}
	
	private void launchAct(Class act){
		Intent actv = new Intent(this, act);
		this.startActivity(actv);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the acti	on bar if it is present.
		getMenuInflater().inflate(R.menu.activity_main, menu);
		return true;
	}
}
