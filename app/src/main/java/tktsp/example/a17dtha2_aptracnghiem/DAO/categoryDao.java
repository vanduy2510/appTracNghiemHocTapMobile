package tktsp.example.a17dtha2_aptracnghiem.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import tktsp.example.a17dtha2_aptracnghiem.DB.DBHelper;
import tktsp.example.a17dtha2_aptracnghiem.model.Category;
import tktsp.example.a17dtha2_aptracnghiem.model.User;

public class categoryDao {
    private SQLiteDatabase db;
    private DBHelper dbHelper;
    public static final String SQL_CREATE_CAT = "CREATE TABLE tbl_category(\n" +
            "\t id INTEGER PRIMARY KEY AUTOINCREMENT,\n" +
            "\t name TEXT NOT NULL);";
    public static final String TBL_CAT = "tbl_category";
    public static final String TAG = "CategotyDAO";
    public categoryDao(Context context){
        dbHelper=new DBHelper(context);
        db=dbHelper.getWritableDatabase();
    }
    public int insertCat(Category category){
        ContentValues values = new ContentValues();
        values.put("id", category.getId());
        values.put("name", category.getName());
        try {
            if (db.insert(TBL_CAT, null, values) == -1) {
                return -1;
            }
        } catch (Exception e) {
            Log.e(TAG, e.toString());
        }

        return 1;
    }
    public List<Category>  allCat(){
        List<Category> categories=new ArrayList<>();
        Cursor c=db.query(TBL_CAT,null,null,null,null,null,null);
        c.moveToFirst();
        while (c.isAfterLast() == false) {
            Category category = new Category(
                    Integer.parseInt(c.getString(0)),
                    c.getString(1)
            );
            c.moveToNext();
            categories.add(category);

        }

        c.close();
        Log.d("checkname", categories.get(1).getName());
        Log.d("checkid", String.valueOf(categories.get(1).getId()) );
        return categories;
    }
}
