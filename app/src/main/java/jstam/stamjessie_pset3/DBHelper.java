package jstam.stamjessie_pset3;

/**
 * Created by Jessie on 08/05/2016.
 */

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class DBHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "firstdb.db";
    private static final int DATABASE_VERSION = 1;
    private static final String TABLE = "todo_table";
    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION); }

    @Override
    public void onCreate(SQLiteDatabase db) {

        String query = "CREATE TABLE"+ TABLE+ " (_id"+" INTEGER PRIMARY KEY AUTOINCREMENT" + "todo TEXT)";
        db.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("DROP TABLE IF EXISTS " + TABLE);
        onCreate(db);
    }

    public void create(MainActivity addToList) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("todo", addToList.todo_item);

        db.insert(TABLE, null, values);
        db.close();
    }

    public ArrayList<HashMap<String, String>> read() {
        SQLiteDatabase db = getReadableDatabase();
        String query = "SELECT _id "+"todo "+ " FROM"+ TABLE;
        ArrayList<HashMap<String, String>> todo = new ArrayList<>();
        Cursor cursor = db.rawQuery(query, null);
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

    public void update(MainActivity addToList) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("todo", addToList.todo_item);
        db.update(TABLE, values, " id = ? ", new String[] {String.valueOf(addToList.id)});
        db.close();
    }

    public void delete(int id) {
        SQLiteDatabase db = getWritableDatabase();
        db.delete(TABLE, " _id = ? ", new String[] {String.valueOf(id)});
        db.close();
    }
}