package com.lee.sispak;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class ListHasilKomponenAdapter extends ArrayAdapter {
	private final Activity activity;
    private final List<JSONObject> list;

    @SuppressWarnings("unchecked")
	public ListHasilKomponenAdapter(Activity activity, int view, List<JSONObject> list) {
    	super(activity, view, list);
        this.activity = activity;
        this.list = list;
    }
    
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
    	View v = convertView;
    	if(v == null){
    		LayoutInflater vi = LayoutInflater.from(getContext());
    		v = vi.inflate(R.layout.item_list, null);
    	}

    	JSONObject gjl = list.get(position);
    	if(gjl != null){
    		TextView mainText = (TextView) v.findViewById(R.id.mainItemText);
    		TextView subText = (TextView) v.findViewById(R.id.subItemText);
    		if(mainText != null){
    			try {
					mainText.setText(gjl.getString("nm_komponen"));
				} catch (JSONException e) {
					mainText.setText("key nm_komponen NOT FOUND");
				}
    		}
    		if(subText != null){
    			try {
					subText.setText(gjl.getString("_PERSENTASE_") + " %");
				} catch (JSONException e) {
					subText.setText("key _PERSENTASE_ NOT FOUND");
				}
    		}
    	}
    	
    	return v;
    }
}
