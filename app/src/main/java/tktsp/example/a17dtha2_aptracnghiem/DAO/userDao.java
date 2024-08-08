package tktsp.example.a17dtha2_aptracnghiem.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.lang.reflect.Array;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;

import tktsp.example.a17dtha2_aptracnghiem.DB.DBHelper;
import tktsp.example.a17dtha2_aptracnghiem.endcode.enCode;
import tktsp.example.a17dtha2_aptracnghiem.model.User;
import tktsp.example.a17dtha2_aptracnghiem.endcode.enCode;

public class userDao {
    private SQLiteDatabase db;
    private DBHelper dbHelper;
    public static final String SQL_CREATE_USER = "CREATE TABLE tbl_user(\n" +
            "\t username text PRIMARY KEY,\n" +
            "\t password text NOT NULL,\n" +
            "\t name_user text NOT NULL,\n" +
            "\t email text NOT NULL UNIQUE,\n" +
            "\t permission INTEGER NOT NULL);";
    public static final String TBL_USER = "tbl_user";
    public static final String TAG = "UserDAO";

    //khởi tạo DB, cho phép ghi sql
    public userDao(Context context) {
        dbHelper = new DBHelper(context);
        db = dbHelper.getWritableDatabase();
    }

    public int insertUser(User user) {
        enCode enCode = new enCode();
        String pass = enCode.md5(user.getPassword().toString());
        ContentValues values = new ContentValues();
        values.put("username", user.getUsername());
        values.put("password", pass);
        values.put("name_user", user.getName_user());
        values.put("email", user.getEmail());
        values.put("permission", 1);
        try {
            if (db.insert(TBL_USER, null, values) == -1) {
                return -1;
            }
        } catch (Exception e) {
            Log.e(TAG, e.toString());
        }
        return 1;

    }

    public List<User> getallUser() {
        List<User> dsuser = new ArrayList<>();
        Cursor cursor = db.query(TBL_USER, null, null, null, null, null, null);
        cursor.moveToFirst();//bắt đầu từ hàng đầu tiên
        while (cursor.isAfterLast() == false) { //nếu qua khỏi phần từ cuối cùng
            User u = new User(
                    cursor.getString(0),
                    cursor.getString(1),
                    cursor.getString(2),
                    cursor.getString(3),
                    Integer.parseInt(cursor.getString(4))

            );
            dsuser.add(u);
            cursor.moveToNext();



        }
        cursor.close();
        Log.d("email", dsuser.get(0).getEmail());
        return dsuser;
    }

    public boolean checkUsername(String username) {
        String sql = "SELECT * FROM " + TBL_USER + " WHERE username='" + username + "'";
        Cursor c = db.rawQuery(sql, null);
        Log.d("giatri", String.valueOf(c.getCount()));
        if (c.getCount() > 0) {
//            String pass=c.getString(1);
//            Log.d("giatri",pass);
            c.close();
            return true;
        } else {
            c.close();
            return false;
        }

    }

    public Boolean checkLogin(String username, String pass) {
        List<User> listuser = new ArrayList<>();
        enCode edCode = new enCode();
        String password = edCode.md5(pass);
        String sql = "SELECT * FROM " + TBL_USER + " WHERE username='" + username + "' and password='" + password + "'";
        Cursor c = db.rawQuery(sql, null);
        if (c.getCount() > 0) {
            c.close();
            return true;
        } else {
            c.close();
            return false;
        }

    }

    public User getProfile(String username) {
      //  String sql = "SELECT * FROM " + TBL_USER + " WHERE username='" + username + "'"; // WHERE username='" + username + "'
        String where="username = ?";
        String[] whereArgs={username};
       // Cursor c = db.rawQuery(sql, null);
        Cursor c=db.query(TBL_USER,null,where,whereArgs,null,null,null);
        c.moveToFirst();
            User user = new User(
                    c.getString(0),
                    c.getString(1),
                    c.getString(2),
                    c.getString(3),
                    Integer.parseInt(c.getString(4))
                    );
        c.close();
        return user;

    }

}