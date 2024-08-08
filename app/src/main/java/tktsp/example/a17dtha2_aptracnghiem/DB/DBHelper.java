    package tktsp.example.a17dtha2_aptracnghiem.DB;


import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Build;

import androidx.annotation.Nullable;

import tktsp.example.a17dtha2_aptracnghiem.DAO.categoryDao;
import tktsp.example.a17dtha2_aptracnghiem.DAO.quizcontractDao;
import tktsp.example.a17dtha2_aptracnghiem.DAO.userDao;

public class DBHelper extends SQLiteOpenHelper {

public static final String DB_NAME="17dtha2_education.db";
public static final int VERSION=1;

//create DB
    public DBHelper( Context context) {
        super(context, DB_NAME, null, VERSION);
    }
//create Table
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

            sqLiteDatabase.execSQL(userDao.SQL_CREATE_USER);
            sqLiteDatabase.execSQL(quizcontractDao.SQL_CREATE_QUIZ);
            sqLiteDatabase.execSQL(categoryDao.SQL_CREATE_CAT);

    }
//khi nào cần update thì chạy hàm này, thay đổi version
    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("drop table if exists "+userDao.TBL_USER);
        sqLiteDatabase.execSQL("drop table if exists "+quizcontractDao.TBL_QUIZ);
        sqLiteDatabase.execSQL("drop table if exists "+categoryDao.TBL_CAT);
    }
}
