package com.lee.sispak;

import org.json.JSONObject;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.TextView;

public class act_datakomponen extends SlidingActivity {
	@Override
	protected void onCreate(Bundle saved){
		super.onCreate(saved);
		setContentView(R.layout.data_komponen);
		TextView nama = (TextView)findViewById(R.id.txt_nama_komponen);
		TextView desc = (TextView)findViewById(R.id.txt_desk_komponen);
		TextView penc = (TextView)findViewById(R.id.txt_pencegahan_komponen);
		setTitle("Data Komponen");
		
		JSONObject komponen = null;
		Bundle data = getIntent().getExtras();
		if(data != null){
			int pos = data.getInt("DATA_KOMPONEN");
			String dataJson = data.getString("DATA_KOMPONEN_JSON");
			if(dataJson != null){
				try{
					komponen = new JSONObject(dataJson);
				} catch(Exception e){
					Utils.showFatalErrorMessage(this, 
							"Tidak dapat membaca data hasil identifikasi kerusakan" +
							" Komponen untuk ditampilkan.");
					return;
				}
			}
			else if(MainActivity.DAFTAR_KOMPONEN == null || pos >= MainActivity.DAFTAR_KOMPONEN.size()){
				Utils.showErrorMessage(this, "Item tidak ditemukan");
				return;
			} else {				
				komponen = MainActivity.DAFTAR_KOMPONEN.get(pos);
			}
			
			try{
				nama.setText(komponen.getString("nm_komponen"));
				desc.setText(komponen.getString("deskripsi"));
				penc.setText(komponen.getString("pencegahan"));
				
				setTitle("Komponen " + komponen.getString("nm_komponen"));
			} catch(Exception e){
				Utils.showErrorMessage(this, "Data Error: " + e.getMessage());
			}
		} else {
			Utils.showErrorMessage(this, "Tidak ada data yang dapat ditampilkan");
			finish();
		}
	}
}
