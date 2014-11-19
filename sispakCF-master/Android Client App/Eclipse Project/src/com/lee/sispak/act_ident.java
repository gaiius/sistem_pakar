package com.lee.sispak;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.json.JSONException;
import org.json.JSONObject;

import android.os.AsyncTask;
import android.os.Bundle;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class act_ident extends SlidingActivity {

	private LinearLayout yesNoBtn;
	private Button startBtn;
	
	// Questions
	private String questionID = "", 
			question = "", yesReff = "", noReff = "";
	
	@SuppressLint("NewApi")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		//this.overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_left);
		setContentView(R.layout.activity_question);
		
		yesNoBtn = (LinearLayout) findViewById(R.id.yesNoWrapper);
		yesNoBtn.setVisibility(View.GONE);
		
		startBtn = (Button) findViewById(R.id.startBtn);
		
		
		
		final Activity self = this;
		View.OnClickListener startQuestion = new View.OnClickListener() {
			@Override
			public void onClick(View v) {				
				List<NameValuePair> post = new ArrayList<NameValuePair>(1);
				post.add(new BasicNameValuePair("q", "ident/start"));
				new HttpPoster(self, post).execute();
			}
		};
		
		Button startBtn = (Button) findViewById(R.id.startBtn);
		startBtn.setOnClickListener(startQuestion);
		
		Button yesBtn = (Button) findViewById(R.id.yesBtn);
		yesBtn.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View arg0) {
				sendQuestionAnswer("yes");
			}
		});
		
		Button noBtn = (Button) findViewById(R.id.noBtn);
		noBtn.setOnClickListener(new View.OnClickListener() {			
			@Override
			public void onClick(View v) {
				sendQuestionAnswer("no");
			}
		});
	}
	
	private void setFailDisplay(String msg)
	{
		startBtn.setVisibility(View.GONE);
		yesNoBtn.setVisibility(View.GONE);
		
		TextView q = (TextView) findViewById(R.id.qText);
		q.setText(msg);
	}
	
	private void sendQuestionAnswer(String ans)
	{
		List<NameValuePair> post = new ArrayList<NameValuePair>(3);
		post.add(new BasicNameValuePair("q", "ident/answer"));
		post.add(new BasicNameValuePair("id", questionID));
		post.add(new BasicNameValuePair("ans", ans));
		new HttpPoster(this, post).execute();
	}
	
	private void setQuestionView()
	{		
		startBtn.setVisibility(View.GONE);
		yesNoBtn.setVisibility(View.VISIBLE);
		
		TextView q = (TextView) findViewById(R.id.qText);
		q.setText(question);
	}
	
	public boolean onOptionsItemSelected(MenuItem m)
	{		
		AlertDialog dlg;
		if(yesNoBtn.getVisibility() != View.GONE)
		{
			AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
					this);
			alertDialogBuilder.setTitle("Konfirmasi");
			alertDialogBuilder
				.setMessage("Proses identifikasi sedang berjalan. Batalkan ?")
				.setCancelable(false)
				.setPositiveButton("Ya",new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog,int id) {
						act_ident.this.finish();
					}
				  })
				.setNegativeButton("Tidak",new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog,int id) {
						dialog.dismiss();
					}
				});

			AlertDialog alertDialog = alertDialogBuilder.create();
			alertDialog.show();
			
			return false;
		}
		
		finish();
		return true;
	}
	
	@Override
	public void onBackPressed() {
		this.onOptionsItemSelected(null);
	    return;
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_question, menu);
		return true;
	}
	
	private void setQuestionProperties(JSONObject json) throws JSONException
	{
		questionID = json.getString("id_pertanyaan");
		question = json.getString("pertanyaan");
		yesReff = json.getString("kode_ya");
		noReff = json.getString("kode_tidak");
		setQuestionView();
	}
	
	private void showConclusion(JSONObject json)
	{
		setContentView(R.layout.butterconclusion);
		
		try {
			final String photoURL = MainActivity.SERVER_URL + "/photos.php?id=" 
					+ json.getString("id_kupu-kupu");
			final ImageView img = (ImageView) findViewById(R.id.butterflyImage);
			new Thread(){
				public void run(){
					//Bitmap photo = downloadImage(photoURL);
					//img.setImageBitmap(photo);
				}
			}.start();
			
			((TextView) findViewById(R.id.nmKupuKupu)).setText(json.getString("nama_kupu-kupu"));
			((TextView) findViewById(R.id.jenkel)).setText(json.getString("jenis_kelamin"));
			((TextView) findViewById(R.id.penemu)).setText("---");
			((TextView) findViewById(R.id.habitat)).setText("---");		
			
		} catch (JSONException e) {
			e.printStackTrace();
		}		
	}
	
	private InputStream OpenHttpConnection(String urlString) throws IOException {
        InputStream in = null;
        int response = -1;

        URL url = new URL(urlString);
        URLConnection conn = url.openConnection();

        if (!(conn instanceof HttpURLConnection))
            throw new IOException("Not an HTTP connection");

        try {
            HttpURLConnection httpConn = (HttpURLConnection) conn;
            httpConn.setAllowUserInteraction(false);
            httpConn.setInstanceFollowRedirects(true);
            httpConn.setRequestMethod("GET");
            httpConn.connect();
            response = httpConn.getResponseCode();
            if (response == HttpURLConnection.HTTP_OK) {
                in = httpConn.getInputStream();
            }
        } catch (Exception ex) {
        	ex.printStackTrace();
            throw new IOException("Error connecting");
        }
        return in;
    }

    private Bitmap downloadImage(String URL) {
        Bitmap bitmap = null;
        InputStream in = null;
        try {
            in = OpenHttpConnection(URL);
            bitmap = BitmapFactory.decodeStream(in);
            in.close();
        } catch (IOException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }
        return bitmap;
    }
	
	private class HttpPoster extends AsyncTask<Void, Void, Integer> {	
		private ProgressDialog Dialog;
		private Activity act;
		private List<NameValuePair> postData;
		private String httpResult = "";

		public HttpPoster(Activity act, List<NameValuePair> postData)
		{
			this.act = act;
			this.Dialog = new ProgressDialog(act);
			this.postData = postData;
		}
		
	    @Override
	    protected void onPreExecute()
	    {
	        Dialog.setMessage("mengirimkan permintaan ...");
	        Dialog.show();
	    }
	    
		protected Integer doInBackground(Void... arg0) {
			
			// Create a new HttpClient and Post Header
		    HttpClient httpclient = new DefaultHttpClient();
		    HttpPost httppost = new HttpPost(MainActivity.SERVER_URL + "/sajax.php");

		    try{
		    	httppost.setEntity(new UrlEncodedFormEntity(this.postData));
		    	HttpResponse resp = httpclient.execute(httppost);
		    	
		    	this.httpResult = EntityUtils.toString(resp.getEntity());
		    } catch(ClientProtocolException e)
		    {
		    	e.printStackTrace();
		    } catch(IOException e)
		    {
		    	e.printStackTrace();
		    }
		    
			return 0;
		}

		protected void onPostExecute(Integer result)
	    {
			Dialog.dismiss();
	        if(result==0)
	        {
	        	try {
					JSONObject json = new JSONObject(this.httpResult);
					
					if(json.has("id_pertanyaan"))
					{
						setQuestionProperties(json);
					}
					else
					{
						String res = json.getString("res").toLowerCase();
						if(res.equals("question"))
						{
							JSONObject qst = json.getJSONObject("data");
							setQuestionProperties(qst);
						}
						else if(res.equals("conclusion"))
							showConclusion(json.getJSONObject("data"));
						else if(res.equals("fail") || res.equals("error"))
							setFailDisplay(json.getString("message"));
					}
					
				} catch (JSONException e) {
					Toast.makeText(getApplicationContext(), 
							"Response tidak dimengerti: " + this.httpResult + " - "
							+ e.getMessage(), 
							Toast.LENGTH_LONG)
					.show();
					
					setFailDisplay("Communication Failed: " + e.getMessage());
					
					e.printStackTrace();
				}
	        }
	        
	        return;
	    }
	}

}
