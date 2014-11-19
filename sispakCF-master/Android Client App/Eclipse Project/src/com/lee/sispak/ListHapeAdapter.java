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
import android.widget.RadioButton;
import android.widget.TextView;

public class ListHapeAdapter extends ArrayAdapter {
	private final Activity activity;
    private final List<JSONObject> list;

    @SuppressWarnings("unchecked")
	public ListHapeAdapter(Activity activity, int view, List<JSONObject> list) {
    	super(activity, view, list);
        this.activity = activity;
        this.list = list;
    }
    
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
    	View v = convertView;
    	if(v == null){
    		LayoutInflater vi = LayoutInflater.from(getContext());
    		v = vi.inflate(R.layout.list_hape_item, null);
    	}

    	JSONObject gjl = list.get(position);
    	if(gjl != null){
    		TextView mainText = (TextView) v.findViewById(R.id.hape_item_text);
    		RadioButton radio = (RadioButton) v.findViewById(R.id.hape_item_radio);
    		if(mainText != null){
    			try {
					mainText.setText(gjl.getString("nm_handphone"));
				} catch (JSONException e) {
					mainText.setText("key nm_handphone NOT FOUND");
				}
    		}
    		
    		if(radio != null){
    			radio.setChecked(selectedIndex == position);
    		}
    	}
    	
    	return v;
    }
    
    private int selectedIndex = -1;
    public void setSelectedIndex(int index){
        selectedIndex = index;
    }
    
    public JSONObject getSelectedItem(){
    	if(selectedIndex >= 0 && selectedIndex < list.size()){
    		return list.get(selectedIndex);
    	}
    	
    	return null;
    }
}
