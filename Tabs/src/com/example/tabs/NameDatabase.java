package com.example.tabs;

import java.util.ArrayList;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class NameDatabase extends SQLiteOpenHelper {

	static String DB = "db_location";
	static String NAME = "tbl_location";

	public NameDatabase(Context context) {
		super(context, DB, null, 1);
		//
	}
	
	@Override
	public void onCreate(SQLiteDatabase db) {
		// TODO Auto-generated method stub
		String sql = "CREATE TABLE "+NAME+"(id integer primary key autoincrement,name varchar(50))";
		db.execSQL(sql);
	}
	
	
	public long AddName(String name){
		long result = 0;
		ContentValues cv = new ContentValues();
		cv.put("name", name);
		
		SQLiteDatabase db = this.getWritableDatabase();
		result = db.insert(NAME, null, cv);
		db.close();
		return result;
		
	}
	public ArrayList<PersonName> getAllPerson(){
		ArrayList<PersonName> list = new ArrayList<PersonName>();
		SQLiteDatabase db = this.getReadableDatabase();
		Cursor c = db.query(NAME, null, null, null, null, null,"name");
		c.moveToFirst();
		while(!c.isAfterLast()){
			int id = c.getInt(c.getColumnIndex("id"));
			String name = c.getString(c.getColumnIndex("name"));
			PersonName p = new PersonName(name);
			list.add(p);
			c.moveToNext();
			
		}
		db.close();
		return list;
	}
	
	public int deleteName(String name){
		SQLiteDatabase db=this.getWritableDatabase();
		return db.delete(NAME, "name=?", new String[]{name});
	}
	
	@Override
	public void onUpgrade(SQLiteDatabase arg0, int arg1, int arg2) {
		// TODO Auto-generated method stub

	}
	public int getCount(){
		return getAllPerson().size();
		
	}
	
}
