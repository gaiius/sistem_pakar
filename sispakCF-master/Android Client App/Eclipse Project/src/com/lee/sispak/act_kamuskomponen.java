package com.lee.sispak;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.EditText;
import android.widget.ListView;

public class act_kamuskomponen extends SlidingActivity {
	private EditText searchfield;
	private ListView list;
	
	private ListKomponenAdapter adapter;
	private ListHasilKomponenAdapter resultAdapter;
	@Override
    protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
        setContentView(R.layout.view_kamusgejala);
        
        searchfield = (EditText) findViewById(R.id.searchField);
        searchfield.setHint("Cari Komponen");
        list = (ListView) findViewById(R.id.listItem);
        
        JSONArray hasilIdentifikasi = null;
        Bundle data = getIntent().getExtras();
        if(data != null){
        	try {
				hasilIdentifikasi = new JSONArray(data.getString("HASIL_IDENTIFIKASI"));
			} catch (JSONException e) {
				Utils.showFatalErrorMessage(this, "d: " + e.getMessage());
				return;
			}
        }
        
        if(hasilIdentifikasi != null){
        	showResult(hasilIdentifikasi);
        } else {
        	showKamus();
        }
	}
	
	private void showResult(final JSONArray _res){
		setTitle("Kemungkinan Komponen yang rusak");
		searchfield.setVisibility(View.GONE);
		List<JSONObject> res = new ArrayList<JSONObject>();
		for(int i = 0; i < _res.length(); i++){
			try {
				res.add(_res.getJSONObject(i));
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		if(res.size() < 1){
			Utils.showMessage(this, "Tidak ada Data", 
					"Maaf, tidak dapat menemukan kemungkinan komponen yang rusak dari gejala yang " +
					"ada. Silakan dicoba lagi.", 
					android.R.drawable.ic_dialog_info, 
					new android.content.DialogInterface.OnClickListener() {						
						@Override
						public void onClick(DialogInterface dialog, int which) {
							finish();							
						}
					});
			return;
		}
		
		list.setOnItemClickListener(new OnItemClickListener(){

			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				try {
					JSONObject komponen = _res.getJSONObject(position);
					
					Intent intent = new Intent(getBaseContext(), act_datakomponen.class);
					intent.putExtra("DATA_KOMPONEN", position);
					intent.putExtra("DATA_KOMPONEN_JSON", komponen.toString());
					startActivity(intent);
				} catch (JSONException e) {
					Utils.showErrorMessage(act_kamuskomponen.this, "Error: " + e.getMessage());
				}
			}
        	
        });
		
		resultAdapter = new ListHasilKomponenAdapter(this, 0, 
    			res);
    	list.setAdapter(resultAdapter);
	}
	
	private void showKamus(){
        setTitle("Kamus Komponen");
		if(MainActivity.DAFTAR_KOMPONEN != null){
        	adapter = new ListKomponenAdapter(this, R.id.mainItemText, 
        			MainActivity.DAFTAR_KOMPONEN);
        	list.setAdapter(adapter);
        } else  {
        	List<NameValuePair> post = new ArrayList<NameValuePair>();
        	post.add(new BasicNameValuePair("groups", "1"));
        	final AsyncRequest req = new AsyncRequest(this, MainActivity.SERVER_URL + "/index.php?q=komponen/list", post){
				@Override
				protected void onPostExecute(Integer result) {
					Dialog.dismiss();
					if(result != 0) {
						//Utils.showErrorMessage(act, "Error Http Code: " + result);
						//finish();
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
			    	if(res != null) {
			    		MainActivity.DAFTAR_KOMPONEN = new ArrayList<JSONObject>();
			    		for(int i = 0; i < res.length(); i++){
			    			try {
								MainActivity.DAFTAR_KOMPONEN.add(res.getJSONObject(i));
							} catch (JSONException e) {
								Utils.log(e.getMessage());
							}
			    		}
			    		adapter = new ListKomponenAdapter(act_kamuskomponen.this, R.id.mainItemText, 
			        			MainActivity.DAFTAR_KOMPONEN);
			        	list.setAdapter(adapter);
			    	}
					
				}
        		
        	};
        	req.setMessage("Harap Tunggu,<br/>Mengambil data Komponen dari Server");
        	req.execute();
        }
        
        list.setOnItemClickListener(new OnItemClickListener(){

			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				if(MainActivity.DAFTAR_KOMPONEN == null || position >= MainActivity.DAFTAR_KOMPONEN.size()){
					Utils.showErrorMessage(act_kamuskomponen.this, "Item tidak ditemukan");
					return;
				}
				
				Intent intent = new Intent(getBaseContext(), act_datakomponen.class);
				intent.putExtra("DATA_KOMPONEN", position);
				startActivity(intent);
			}
        	
        });
	}
	
	public boolean onOptionsItemSelected(MenuItem m)
	{
		finish();
		return true;
	}
}
