package com.example.tabs;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class AddNameActivity extends Activity {
	Button btnSAVE,btnCANCEL;
	NameDatabase db;
	EditText txtName;
	TextView tv1;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		this.setContentView(R.layout.addname);
		
		db = new NameDatabase(this);
		this.txtName = (EditText) this.findViewById(R.id.editText1);
		this.btnSAVE = (Button) this.findViewById(R.id.button1);
		this.btnCANCEL = (Button) this.findViewById(R.id.button2);
		
		this.tv1 = (TextView) this.findViewById(R.id.textView1);
	
		StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
	    StrictMode.setThreadPolicy(policy);
	
		btnSAVE.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
			String name = txtName.getText().toString();
			if(!name.equals("")){
				db.AddName(name);
				Toast.makeText(AddNameActivity.this,"Added Successfuly!", Toast.LENGTH_LONG).show();
				//overridePendingTransition(0,0);
				Intent intent = new Intent(AddNameActivity.this,MainActivity.class);
				intent.setFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
				//overridePendingTransition(0,0);
				startActivity(intent);
			}
	}});
		
		btnCANCEL.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				txtName.setText("");
				txtName.requestFocus();
			}
		});
		
	}

	
	
}
