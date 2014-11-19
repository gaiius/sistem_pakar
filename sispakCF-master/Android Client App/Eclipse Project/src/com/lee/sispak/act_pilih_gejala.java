package com.lee.sispak;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.ListView;

public class act_pilih_gejala extends SlidingActivity {
	private String json_gejala = null;
	private String json_handphone = null;
	
	private ListView list;
	private Button btnAnalisa;
	
	protected void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		setContentView(R.layout.pilih_gejala);
		setTitle("Pilih Gejala Kerusakan");
		
		Bundle data = getIntent().getExtras();
		if(data != null){
			json_gejala = data.getString("DAFTAR_GEJALA");
			json_handphone = data.getString("HANDPHONE_PILIHAN");
			start();
		} else {
			Utils.showFatalErrorMessage(this, "Tidak ada data tersedia");
		}
	}
	
	private JSONArray dataGejala;
	//private JSONObject dataHandphone;
	private listCheckboxGejalaAdapter adapter;
	private void start(){
		list = (ListView) findViewById(R.id.list_gejala);
		btnAnalisa = (Button) findViewById(R.id.lakukan_analisa);
		
		if(list == null || btnAnalisa == null){
			Utils.showFatalErrorMessage(this, "Error: Komponen aplikasi tidak ditemukan.");
			return;
		}
		
		try{
			dataGejala = new JSONArray(json_gejala);
			//dataHandphone = new JSONObject(json_handphone);
		} catch(Exception e){
			Utils.showFatalErrorMessage(this, "Error membaca data: " + e.getMessage());
		}
		
		List<JSONObject> gejala = new ArrayList<JSONObject>();
		for(int i = 0; i < dataGejala.length(); i++){
			try {
				gejala.add(dataGejala.getJSONObject(i));
			} catch (JSONException e) {
				e.printStackTrace();
			}
		}
		
		adapter = new listCheckboxGejalaAdapter(this, 0, gejala);
		list.setAdapter(adapter);
		list.setOnItemClickListener(new OnItemClickListener(){
			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				adapter.toggleCheckbox(position);
				adapter.notifyDataSetChanged();
			}			
		});
		
		btnAnalisa.setOnClickListener(new OnClickListener(){
			@Override
			public void onClick(View arg0) {
				List<JSONObject> selected = adapter.getSelected();
				if(selected.size() < 1){
					Utils.showErrorMessage(act_pilih_gejala.this, "Harap Pilih " +
							"Gejala yang terjadi.");
					return;
				}
				String _post = "";
				try{
					_post = selected.get(0).getString("id_gejala");
					for(int i = 1; i < selected.size(); i++){
						_post += ";" + selected.get(i).getString("id_gejala");
					}
					List<NameValuePair> post = new ArrayList<NameValuePair>();
					post.add(new BasicNameValuePair("selected", _post));
					
					AsyncRequest req = new AsyncRequest(act_pilih_gejala.this, 
							MainActivity.SERVER_URL + "/index.php?q=sispak/cf",
							post){
								@Override
								protected void onPostExecute(Integer result) {
									Dialog.dismiss();
									if(result != 0){
										return;
									}
									Intent komponen = new Intent(getBaseContext(), act_kamuskomponen.class);
									komponen.putExtra("HASIL_IDENTIFIKASI", httpResult);
									startActivity(komponen);
								}						
					};
					req.setMessage("Analisa dilakukan diserver berdasarkan gejala yang dipilih.<br/>" +
							"Harap tunggu...");
					req.execute();
				} catch(Exception e){
					Utils.showErrorMessage(act_pilih_gejala.this, e.getMessage());
				}
			}			
		});
	}
}
