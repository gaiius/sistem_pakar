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
import android.widget.Filter;
import android.widget.TextView;

public class ListKomponenAdapter extends ArrayAdapter {
	private final Activity activity;
    private final List<JSONObject> list;

    @SuppressWarnings("unchecked")
	public ListKomponenAdapter(Activity activity, int view, List<JSONObject> list) {
    	super(activity, view, list);
        this.activity = activity;
        this.list = list;
        
        this.allModelItemsArray = new ArrayList<JSONObject>();
        allModelItemsArray.addAll(list);
        this.filteredModelItemsArray = new ArrayList<JSONObject>();
        filteredModelItemsArray.addAll(allModelItemsArray);
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
					mainText.setText("key 'nm_komponen' NOT FOUND");
				}
    		}
    		if(subText != null){
    			try {
					subText.setText(gjl.getString("deskripsi"));
				} catch (JSONException e) {
					subText.setText("key 'deskripsi' NOT FOUND");
				}
    		}
    	}
    	
    	return v;
    }
    
    private itemFilter filter;
    @Override
    public Filter getFilter() {
        if (filter == null){
          filter  = new itemFilter();
        }
        return filter;
    }
    
    private List<JSONObject> allModelItemsArray;
    private List<JSONObject> filteredModelItemsArray;
    private class itemFilter extends Filter
    {

        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            
            constraint = constraint.toString().toLowerCase();
            FilterResults result = new FilterResults();
            if(constraint != null && constraint.toString().length() > 0)
            {
                ArrayList<JSONObject> filteredItems = new ArrayList<JSONObject>();
               
                for(int i = 0, l = allModelItemsArray.size(); i < l; i++)
                {
                	JSONObject m = allModelItemsArray.get(i);
                	try{
	                    if(m.getString("nm_komponen").toLowerCase().contains(constraint))
	                        filteredItems.add(m);
                	} catch(Exception e){
                		
                	}
                }
                result.count = filteredItems.size();
                result.values = filteredItems;
            }
            else
            {
                synchronized(this)
                {
                    result.values = allModelItemsArray;
                    result.count = allModelItemsArray.size();
                }
            }
            return result;
        }

        @SuppressWarnings("unchecked")
        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
           
            filteredModelItemsArray = (ArrayList<JSONObject>)results.values;
            notifyDataSetChanged();
            clear();
            for(int i = 0, l = filteredModelItemsArray.size(); i < l; i++)
                add(filteredModelItemsArray.get(i));
            notifyDataSetInvalidated();
        }
   }
}
