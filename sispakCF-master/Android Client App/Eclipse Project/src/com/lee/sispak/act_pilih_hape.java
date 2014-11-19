package com.lee.sispak;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.ListView;

public class act_pilih_hape extends SlidingActivity {
	private ListView listHape = null;
	private Button startBtn = null;
	@Override
	protected void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		setContentView(R.layout.pilih_hape);
		setTitle("Pilih Handphone");
		
		listHape = (ListView) findViewById(R.id.ident_list_hape);
		startBtn = (Button) findViewById(R.id.ident_show_gejala);
		
		start();		
	}
	
	private ListHapeAdapter adapter;
	private void start(){
		if(listHape == null || startBtn == null) {
			Utils.showFatalErrorMessage(this, "Komponen aplikasi tidak ditemukan.");
			return;
		};
		
		listHape.setOnItemClickListener(new OnItemClickListener(){
			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				adapter.setSelectedIndex(position);
				adapter.notifyDataSetChanged();
			}
			
		});
		
		startBtn.setOnClickListener(new OnClickListener(){
			@Override
			public void onClick(View arg0) {
				final JSONObject selected = adapter.getSelectedItem();
				if(selected == null){
					Utils.showErrorMessage(act_pilih_hape.this, "Pilih dulu Model Handphone " +
							"yang ingin dianalisa.");
					return;
				}
				
				try{
					List<NameValuePair> post = new ArrayList<NameValuePair>();
					post.add(new BasicNameValuePair("id", "0"));
					post.add(new BasicNameValuePair("handphone", selected.getString("id_handphone")));
					AsyncRequest req = new AsyncRequest(act_pilih_hape.this, 
							MainActivity.SERVER_URL + "/index.php?q=pertanyaan/list",
							post){

								@Override
								protected void onPostExecute(Integer result) {
									Dialog.dismiss();
									if(result != 0) {
										return;
									}
									
									Intent pilihGejala = new Intent(act_pilih_hape.this, act_pilih_gejala.class);
									pilihGejala.putExtra("DAFTAR_GEJALA", this.httpResult);
									pilihGejala.putExtra("HANDPHONE_PILIHAN", selected.toString());
									act_pilih_hape.this.startActivity(pilihGejala);
								}
						
					};
					req.setMessage("Mengambil Daftar Gejala Kerusakan untuk handphone " +
							selected.getString("nm_handphone"));
					req.execute();
				} catch(Exception e){
					Utils.showFatalErrorMessage(act_pilih_hape.this, 
							"Error: " + e.getMessage());
				}
			}
			
		});

		AsyncRequest req = new AsyncRequest(this, 
				MainActivity.SERVER_URL + "/index.php?q=handphone/list", 
				new ArrayList<NameValuePair>()){
					@Override
					protected void onPostExecute(Integer result) {
						Dialog.dismiss();
						if(result != 0) {
							return;
						}
						
						JSONArray res = null;
						try {
							res = new JSONArray(httpResult);
						} catch (JSONException e) {
							Utils.showErrorMessage(act,
					    			"Error mengenali response Server:<br/> "
									+ httpResult + "<br/>"
					    			+ e.getMessage());
							return;
						}
						if(res == null) 
							return;
						
						List<JSONObject> data = new ArrayList<JSONObject>();
						for(int i = 0; i < res.length(); i++){
			    			try {
								data.add(res.getJSONObject(i));
							} catch (JSONException e) {
								Utils.log(e.getMessage());
							}
			    		}
			    		adapter = new ListHapeAdapter(act_pilih_hape.this, 0, 
			        			data);
			        	listHape.setAdapter(adapter);
					};
			
		};
		req.setMessage("Mengambil data Handphone dari server");
		req.execute();
	}
}
