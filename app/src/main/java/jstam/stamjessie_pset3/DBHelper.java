package jstam.stamjessie_pset3;

/**
 * DBHelper.java
 * Jessie Stam
 * 10560599
 *
 * The database helper for the To-Do application.
 *
 * Although the two files are not actually linked, I hope you will take the time to check this file.
 * I would like to get some feedback, since I still find it really hard to understand how to files
 * interact with one another. Thanks in advance.
 */

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.HashMap;

public class DBHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "firstdb.db";
    private static final int DATABASE_VERSION = 1;
    private static final String TABLE = "todo_table";
    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION); }

    @Override
    public void onCreate(SQLiteDatabase db) {

        // create the table, add id and to-do items
        String query = "CREATE TABLE"+ TABLE+ " (_id"+" INTEGER PRIMARY KEY AUTOINCREMENT" + "todo TEXT)";
        db.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        // drop table and create it again when application is updated
        db.execSQL("DROP TABLE IF EXISTS " + TABLE);
        onCreate(db);
    }

    /*
     * Add to-do items to the list
     */
    public void create(MainActivity addToList) {

        // initialize database for writing
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();

        // add to-do item to the list and insert into table
        values.put("todo", addToList.todo_item);
        db.insert(TABLE, null, values);
        db.close();
    }

    /*
     * Read through the database
     */
    public ArrayList<HashMap<String, String>> read() {

        // initialize database for reading
        SQLiteDatabase db = getReadableDatabase();

        // select id and item from the table
        String query = "SELECT _id "+"todo "+ " FROM"+ TABLE;
        ArrayList<HashMap<String, String>> todo = new ArrayList<>();
        Cursor cursor = db.rawQuery(query, null);

        // for every item in the list, read id and to-do
        if (cursor.moveToFirst()) {
            do {
                HashMap<String, String> todo_list = new HashMap<>();
                todo_list.put("id", cursor.getString(cursor.getColumnIndex(" id")));
                todo_list.put("todo", cursor.getString
                        (cursor.getColumnIndex("todo")));
                todo_list.put("todo", cursor.getString
                        (cursor.getColumnIndex("todo")));
                todo.add(todo_list);
            }
            while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return todo;
    }

    /*
     * Update database
     */
    public void update(MainActivity addToList) {

        // initialize database for writing
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();

        // add to-do item to list and add to the table
        values.put("todo", addToList.todo_item);
        db.update(TABLE, values, " id = ? ", new String[] {String.valueOf(addToList.id)});
        db.close();
    }

    /*
     * Delete item from the table
     */
    public void delete(int id) {

        // initialize database for writing
        SQLiteDatabase db = getWritableDatabase();

        // delete to-do item from the table
        db.delete(TABLE, " _id = ? ", new String[] {String.valueOf(id)});
        db.close();
    }
}