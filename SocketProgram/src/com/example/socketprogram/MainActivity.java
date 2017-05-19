package com.example.socketprogram;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import android.os.Bundle;
import android.os.StrictMode;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends Activity implements OnClickListener {
	
	EditText txtUser,txtPass;
	Button btnL,btnC;
	
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        this.btnC = (Button)this.findViewById(R.id.button2);
        this.btnL = (Button)this.findViewById(R.id.button1);
        this.txtUser = (EditText)this.findViewById(R.id.editText1);
        this.txtPass = (EditText)this.findViewById(R.id.editText2);
        
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        
        this.btnL.setOnClickListener(this);
        this.btnC.setOnClickListener(this);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }


	@Override
	public void onClick(View arg0) {
		// TODO Auto-generated method stub
		
		int id = arg0.getId();
		switch(id){
		
		case R.id.button1:
			

			String username = this.txtUser.getText().toString();
			String password = this.txtPass.getText().toString();
			
			if(!username.equals("") && !password.equals("")){
				try {
					URL url = new URL("http://172.19.131.78/summer/loginvalidation.php?username="+username+"&password="+password);
					HttpURLConnection conn = (HttpURLConnection) url.openConnection();
					
					InputStream is = conn.getInputStream();
					StringBuffer sb = new StringBuffer();
					
					char ch = '\u0000';
					
					int n = 0;
					
					while(n!=-1){
						
						n=is.read();
						ch = (char)n;
						sb.append(ch);
					}
					
					is.close();
					conn.disconnect();
					
					AlertDialog.Builder builder = new AlertDialog.Builder(this);
						builder.setMessage(sb.toString());
						builder.setNeutralButton("Okey", null);
						
					AlertDialog dialog = builder.create();
						dialog.show();
					
				} catch (MalformedURLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			else{
				
				Toast.makeText(this, "Fill all fields", Toast.LENGTH_SHORT).show();
			}
			
			break;
			
		case R.id.button2:
				
			this.txtUser.setText("");
			this.txtPass.setText("");
			this.txtUser.requestFocus();
		}
		
	}
    
}
