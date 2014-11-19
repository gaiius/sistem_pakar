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
import android.widget.CheckBox;
import android.widget.TextView;

public class listCheckboxGejalaAdapter extends ArrayAdapter {
	private final Activity activity;
    private final List<JSONObject> list;
    private List<Integer> selected;
    
    @SuppressWarnings("unchecked")
	public listCheckboxGejalaAdapter(Activity activity, int view, List<JSONObject> list) {
    	super(activity, view, list);
        this.activity = activity;
        this.list = list;
        this.selected = new ArrayList<Integer>();
    }
    
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
    	View v = convertView;
    	if(v == null){
    		LayoutInflater vi = LayoutInflater.from(getContext());
    		v = vi.inflate(R.layout.list_gejala_checkbox, null);
    	}

    	JSONObject gjl = list.get(position);
    	if(gjl != null){
    		TextView mainText = (TextView) v.findViewById(R.id.checkbox_gejala_text);
    		CheckBox selector = (CheckBox) v.findViewById(R.id.checkbox_gejala_selector);
    		
    		if(mainText != null){
    			try {
					mainText.setText(gjl.getString("nm_gejala"));
				} catch (JSONException e) {
					mainText.setText("key nm_gejala NOT FOUND");
				}
    		}
    		
    		if(selector != null){
    			selector.setChecked(selected.contains(position));
    		}
    	}
    	
    	return v;
    }
    
    public void toggleCheckbox(int pos){
    	if(selected.contains(pos))
    		selected.remove((Object)pos);
    	else
    		selected.add(pos);
    }
    
    public List<JSONObject> getSelected(){
    	List<JSONObject> res = new ArrayList<JSONObject>();
    	for(int i = 0; i < selected.size(); i++){
    		res.add( list.get( selected.get(i) ) );
    	}
    	
    	return res;
    }
}
