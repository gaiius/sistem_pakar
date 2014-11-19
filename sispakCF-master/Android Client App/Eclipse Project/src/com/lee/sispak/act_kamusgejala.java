package com.lee.sispak;

import java.util.ArrayList;

import org.apache.http.NameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.AdapterView.OnItemClickListener;

public class act_kamusgejala extends SlidingActivity {
	private EditText searchfield;
	private ListView list;
	
	private ListGejalaAdapter adapter;
	
	@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.view_kamusgejala);
        
        searchfield = (EditText) findViewById(R.id.searchField);
        list = (ListView) findViewById(R.id.listItem);
        
        showKamus();
	}
	
	private void showKamus(){
        setTitle("Kamus Gejala");
		if(MainActivity.DAFTAR_GEJALA != null){
        	adapter = new ListGejalaAdapter(this, R.id.mainItemText, 
        			MainActivity.DAFTAR_GEJALA);
        	list.setAdapter(adapter);
        } else  {
        	AsyncRequest req = new AsyncRequest(this, MainActivity.SERVER_URL + "/index.php?q=pertanyaan/list", new ArrayList<NameValuePair>()){
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
			    		MainActivity.DAFTAR_GEJALA = new ArrayList<JSONObject>();
			    		for(int i = 0; i < res.length(); i++){
			    			try {
								MainActivity.DAFTAR_GEJALA.add(res.getJSONObject(i));
							} catch (JSONException e) {
								Utils.log(e.getMessage());
							}
			    		}
			    		adapter = new ListGejalaAdapter(act_kamusgejala.this, R.id.mainItemText, 
			        			MainActivity.DAFTAR_GEJALA);
			        	list.setAdapter(adapter);
			    	}
					
				}
        		
        	};
        	req.setMessage("Harap Tunggu,<br/>Mengambil data Gejala dari Server");
        	req.execute();
        }

        searchfield.addTextChangedListener(new TextWatcher(){

			@Override
			public void afterTextChanged(Editable arg0) {				
			}

			@Override
			public void beforeTextChanged(CharSequence arg0, int arg1,
					int arg2, int arg3) {
				
			}

			@Override
			public void onTextChanged(CharSequence arg0, int arg1, int arg2,
					int arg3) {
				act_kamusgejala.this.adapter.getFilter().filter(arg0);				
			}
        	
        });
        list.setOnItemClickListener(new OnItemClickListener(){

			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				if(MainActivity.DAFTAR_GEJALA == null || position >= MainActivity.DAFTAR_GEJALA.size()){
					Utils.showErrorMessage(act_kamusgejala.this, "Item tidak ditemukan");
					return;
				}
				
				Intent intent = new Intent(getBaseContext(), act_datagejala.class);
				intent.putExtra("DATA_GEJALA", position);
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
