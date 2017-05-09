package com.example.tabs;
import java.util.ArrayList;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.AdapterContextMenuInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class NameListActivity extends Activity implements OnClickListener{

	NameDatabase db;
	ArrayList<PersonName> list = new ArrayList<PersonName>();
	ListView lv;
	TextView tv;
	MyAdapter adapter;
	AdapterView.AdapterContextMenuInfo info;
	AlertDialog g;
	
	
	//Edit
	Dialog d5;
	Button save,cancel;
	EditText editName;
	String delname;
	
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		this.setContentView(R.layout.namelist);
		
		db = new NameDatabase(this);
		if(db.getCount()>0){
			list = db.getAllPerson();
		}
		
		this.lv = (ListView) this.findViewById(R.id.listView1);
		this.adapter = new MyAdapter(this,list);
		this.lv.setAdapter(adapter);
		adapter.notifyDataSetChanged();
		this.registerForContextMenu(lv);
		
	}
	@Override
	public boolean onContextItemSelected(MenuItem item) {
		// TODO Auto-generated method stub
		
		int i = item.getItemId();
		switch(i){
		/*case R.id.edit:
			d5 = new Dialog(this);
			d5.setContentView(R.layout.addname);
			
			d5.setTitle(list.get(info.position).getName());
	        editName = (EditText) d5.findViewById(R.id.editText1);
	        Button save = (Button) d5.findViewById(R.id.button1);
	        Button cancel = (Button) d5.findViewById(R.id.button2);
			
	        editName.setText(list.get(info.position).getName());
	        save.setText("Update");
	       
	        
	        save.setOnClickListener(this);
	        cancel.setOnClickListener(this);

	        d5.show();
			break;*/
		case R.id.delete:
			AlertDialog.Builder ad = new AlertDialog.Builder(this);
			ad.setTitle("Delete Tab");
			ad.setMessage(String.format("Are you sure you want to delete %s ? ",list.get(info.position).getName()));
			ad.setPositiveButton("Yes",this);
			ad.setNegativeButton("No", this);
			
			g = ad.create();
			g.show();
		}
		
		
		
		return super.onContextItemSelected(item);
	}
	@Override
	public void onCreateContextMenu(ContextMenu menu, View v,
			ContextMenuInfo menuInfo) {
		// TODO Auto-generated method stub
		super.onCreateContextMenu(menu, v, menuInfo);
		getMenuInflater().inflate(R.menu.contextmenu, menu);
		info=(AdapterContextMenuInfo) menuInfo;
		menu.setHeaderTitle(list.get(info.position).getName());
	}
	@Override
	public void onClick(DialogInterface arg0, int arg1) {
		// TODO Auto-generated method stub
		
		switch(arg1){
		case DialogInterface.BUTTON_POSITIVE:
			String delname = list.get(info.position).getName();
			list.remove(info.position);
			db.deleteName(delname);
			Toast.makeText(this,String.format("%s has been deleted",delname),Toast.LENGTH_SHORT).show();
			adapter.notifyDataSetChanged();
		
			break;
		case DialogInterface.BUTTON_NEGATIVE:
			g.dismiss();
		}
	}
}
