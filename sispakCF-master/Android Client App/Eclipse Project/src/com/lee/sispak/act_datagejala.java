package com.lee.sispak;

import org.json.JSONObject;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.TextView;

public class act_datagejala extends SlidingActivity {
	@Override
	protected void onCreate(Bundle saved){
		super.onCreate(saved);
		setContentView(R.layout.data_gejala);
		TextView nama = (TextView)findViewById(R.id.txt_pertanyaan);
		TextView desc = (TextView)findViewById(R.id.txt_desk_gejala);
		TextView penc = (TextView)findViewById(R.id.txt_gejala_handphone);
		setTitle("Data GEJALA");
		
		Bundle data = getIntent().getExtras();
		if(data != null){
			int pos = data.getInt("DATA_GEJALA");
			if(MainActivity.DAFTAR_GEJALA == null || pos >= MainActivity.DAFTAR_GEJALA.size()){
				Utils.showErrorMessage(this, "Item tidak ditemukan");
				return;
			}
			JSONObject komponen = MainActivity.DAFTAR_GEJALA.get(pos);
			try{
				nama.setText(komponen.getString("nm_gejala"));
				desc.setText(komponen.getString("deskripsi"));
				penc.setText(komponen.getString("nm_handphone"));
				
				setTitle("Gejala " + komponen.getString("nm_gejala"));
			} catch(Exception e){
				Utils.showErrorMessage(this, "Data Error: " + e.getMessage());
			}
		} else {
			Utils.showErrorMessage(this, "Tidak ada data yang dapat ditampilkan");
			finish();
		}
	}
}
