package com.sungard.energy.jarvis;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by cyberdanes on 13/05/15.
 */
public class MessageDataHelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "cyberdanes.db";
    public static final String MESSAGES_TABLE_NAME = "messages";
    public static final String MESSAGES_COLUMN_ID = "id";
    public static final String MESSAGES_COLUMN_HEADER = "header";
    public static final String MESSAGES_COLUMN_CONTENT = "content";

    private HashMap hp;

    public MessageDataHelper(Context context)
    {
        super(context, DATABASE_NAME , null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // TODO Auto-generated method stub
        db.execSQL(
                "create table messages " +
                        "(id integer primary key, header text,content text)"
        );
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // TODO Auto-generated method stub
        db.execSQL("DROP TABLE IF EXISTS messages");
        onCreate(db);
    }

    public boolean insertFeed  (String id, String header, String content)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put("id", id);
        contentValues.put("header", header);
        contentValues.put("content", content);

        db.insert("messages", null, contentValues);
        return true;
    }
    public Cursor getData(int id){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res =  db.rawQuery( "select * from messages where id="+id+"", null );
        return res;
    }
    public int numberOfRows(){
        SQLiteDatabase db = this.getReadableDatabase();
        int numRows = (int) DatabaseUtils.queryNumEntries(db, MESSAGES_TABLE_NAME);
        return numRows;
    }
//    public boolean updateContact (Integer id, String name, String phone, String email, String street,String place)
//    {
//        SQLiteDatabase db = this.getWritableDatabase();
//        ContentValues contentValues = new ContentValues();
//        contentValues.put("name", name);
//        contentValues.put("phone", phone);
//        contentValues.put("email", email);
//        contentValues.put("street", street);
//        contentValues.put("place", place);
//        db.update("contacts", contentValues, "id = ? ", new String[] { Integer.toString(id) } );
//        return true;
//    }

//    public Integer deleteContact (Integer id)
//    {
//        SQLiteDatabase db = this.getWritableDatabase();
//        return db.delete("contacts",
//                "id = ? ",
//                new String[] { Integer.toString(id) });
//    }
    public ArrayList getAllMessages()
    {
        ArrayList array_list = new ArrayList();
        //hp = new HashMap();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res =  db.rawQuery( "select * from messages", null );
        res.moveToFirst();
        while(res.isAfterLast() == false){
            array_list.add(res.getString(res.getColumnIndex(MESSAGES_COLUMN_CONTENT)));
            res.moveToNext();
        }
        return array_list;
    }
}
