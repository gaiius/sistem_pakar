package com.lee.sispak;

import java.io.IOException;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.text.Html;
import android.widget.Toast;

abstract class AsyncRequest extends AsyncTask<Void, Void, Integer> {	
	protected ProgressDialog Dialog;
	protected Activity act;
	protected List<NameValuePair> postData;
	protected String httpResult = "";
	protected String serverUrl;
	private String msg = "Harap Tunggu...";

	public AsyncRequest(Activity act, String url, List<NameValuePair> postData)
	{	
		this.act = act;
		this.serverUrl = url;
		this.Dialog = new ProgressDialog(act);
		this.postData = postData;
	}
	
	public AsyncRequest setMessage(String m){
		this.msg = m;
		return this;
	}
	
    @Override
    protected void onPreExecute()
    {
        Dialog.setMessage(Html.fromHtml(msg));
        Dialog.show();
    }
    
	protected Integer doInBackground(Void... arg0) {
		
		if(!Utils.isConnected(act)){
			Dialog.dismiss();
			Utils.showFatalErrorMessage(act, "Konektivitas tidak terdeteksi.<br/>" +
					"Periksa Jaringan Perangkat Anda.");
			Utils.log("NO NETWORK");
			return 1;
		}
		
		HttpClient httpclient = new DefaultHttpClient();
	    HttpPost httppost = new HttpPost(this.serverUrl);
	    httppost.addHeader("X-Requested-With", "XMLHttpRequest");

	    try{
	    	httppost.setEntity(new UrlEncodedFormEntity(this.postData));
	    	HttpResponse resp = httpclient.execute(httppost);
	    	
	    	this.httpResult = EntityUtils.toString(resp.getEntity());				    	
	    } catch(Exception e)
	    {
	    	Utils.showErrorMessage(act, 
	    			"Error:<br/>" + e.getMessage());
	    }
	    
		return 0;
	}

	protected abstract void onPostExecute(Integer result);
}
