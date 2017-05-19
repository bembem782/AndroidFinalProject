package com.example.networkchat;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import android.os.Bundle;
import android.os.StrictMode;
import android.app.Activity;
import android.view.View;
import android.view.View.OnClickListener;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.EditText;


import android.widget.Toast;

public class MainActivity extends Activity implements OnClickListener {
	
	WebView wv;

	Button btnSend;
	EditText txtM, txtN;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        
        this.wv = (WebView) this.findViewById(R.id.webView1);
        this.btnSend = (Button) this.findViewById(R.id.button1);
        this.txtM = (EditText) this.findViewById(R.id.editText2);
        this.txtN = (EditText) this.findViewById(R.id.editText1);
      
        this.btnSend.setOnClickListener(this);
        
        this.wv.loadUrl("http://172.19.131.99/chatroom/posting.php");
        wv.setScrollY(wv.getBottom());
        
    }
	@Override
	public void onClick(View arg0) {
	
		// TODO Auto-generated method stub
		String message = this.txtM.getText().toString();
		String name = this.txtN.getText().toString();
		if(!message.equals("") && !name.equals(""))
		{
			
		try {
			URL url = null;			
			String link = "http://172.19.131.99/chatroom/sendmessage.php?name="+name+"&message="+message;
			url = new URL(link.replaceAll(" ","%20")); 
			
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		
			InputStream is = conn.getInputStream();
			StringBuffer sb = new StringBuffer();
			
			char ch ='\u0000';
			int n=0;
			while(n!=-1)
			{
				n=is.read();
				sb.append((char)n);
			}
			is.close();
			conn.disconnect();
			
			this.txtM.setText("");
			this.txtN.requestFocus();
			
			Toast.makeText(this, sb.toString(), Toast.LENGTH_SHORT).show();
			wv.scrollTo(0, wv.getBottom());
			
			wv.reload();
			
			
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
			
		}
		
	}

    
    
}
